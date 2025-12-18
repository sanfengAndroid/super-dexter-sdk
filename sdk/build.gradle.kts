/*
 * Copyright (C) 2025 sanfengAndroid
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

plugins {
    id("java-library")
    id("maven-publish")
    id("org.jreleaser")
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

fun normalizeVersion(raw: String?): String? =
    raw
        ?.trim()
        ?.takeIf { it.isNotEmpty() }
        ?.let { if (it.startsWith("v") && it.length > 1) it.removePrefix("v") else it }

val versionFromTag: String? = normalizeVersion(System.getenv("GITHUB_REF_NAME"))
val versionFromProp: String? =
    normalizeVersion(project.findProperty("sdexterSdkVersion") as? String)
val sdkVersion: String = versionFromTag
    ?: versionFromProp
    ?: "1.0.0"

version = sdkVersion

val generatedSrcDir = layout.buildDirectory.dir("generated/sources/buildConfig/java")
tasks.register("generateBuildConfig") {
    val packageName = "io.github.sanfengandroid.sdexter.sdk"
    val className = "BuildConfig"

    val outputDir = generatedSrcDir.map { it.dir(packageName.replace('.', '/')) }
    outputs.dir(outputDir)

    doLast {
        val outDir = outputDir.get().asFile
        outDir.mkdirs()

        val versionParts = sdkVersion.split('.')
        val major = versionParts.getOrElse(0) { "0" }.toIntOrNull() ?: 0
        val minor = versionParts.getOrElse(1) { "0" }.toIntOrNull() ?: 0
        val patch = versionParts.getOrElse(2) { "0" }.split('-').first().toIntOrNull() ?: 0

        outDir.resolve("$className.java").writeText(
            """
            package $packageName;

            /**
             * Build configuration class containing version information for the sdexter-sdk.
             * <p>
             * This class is automatically generated during the build process and should not be modified manually.
             * </p>
             */
            public final class $className {
                /**
                 * The full version name string.
                 * Format: MAJOR.MINOR.PATCH[-SUFFIX]
                 */
                public static final String VERSION_NAME = "$sdkVersion";
                
                /**
                 * The major version number.
                 */
                public static final int VERSION_MAJOR = $major;
                
                /**
                 * The minor version number.
                 */
                public static final int VERSION_MINOR = $minor;
                
                /**
                 * The patch version number.
                 */
                public static final int VERSION_PATCH = $patch;
            }
        """.trimIndent()
        )
    }
}

sourceSets {
    main {
        java {
            srcDir(generatedSrcDir)
        }
    }
}

tasks.named<JavaCompile>("compileJava") {
    dependsOn("generateBuildConfig")
    options.encoding = "UTF-8"
}

tasks.withType<Javadoc> {
    val opts = options as StandardJavadocDocletOptions
    opts.encoding = "UTF-8"
    opts.charSet = "UTF-8"
    opts.docEncoding = "UTF-8"
    opts.locale = "en_US"
}

// Generate BuildConfig.java file
val sourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets["main"].allSource)
    dependsOn("generateBuildConfig")
}

val javadocJar by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")
    from(tasks.named("javadoc"))
}

// ============ Maven publish configuration ============
publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            groupId = "io.github.sanfengandroid"
            artifactId = "sdexter-sdk"
            version = sdkVersion

            artifact(sourcesJar)
            artifact(javadocJar)

            pom {
                name.set("sdexter-sdk")
                description.set("Android SDK for the super-dexter project")
                url.set("https://github.com/sanfengandroid/super-dexter-sdk")

                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }

                developers {
                    developer {
                        id.set("sanfengandroid")
                        name.set("sanfengAndroid")
                        email.set("")
                    }
                }

                scm {
                    connection.set("scm:git:git://github.com/sanfengandroid/super-dexter-sdk.git")
                    developerConnection.set("scm:git:ssh://github.com:sanfengandroid/super-dexter-sdk.git")
                    url.set("https://github.com/sanfengandroid/super-dexter-sdk")
                }
            }
        }
    }

    repositories {
        maven {
            name = "staging"
            url = uri(layout.buildDirectory.dir("staging-deploy"))
        }
    }
}

// ============ JReleaser configuration ============
jreleaser {
    signing {
        active.set(org.jreleaser.model.Active.ALWAYS)
        armored.set(true)
    }
    deploy {
        maven {
            mavenCentral {
                create("sonatype") {
                    active.set(org.jreleaser.model.Active.ALWAYS)
                    url.set("https://central.sonatype.com/api/v1/publisher")
                    stagingRepository(layout.buildDirectory.dir("staging-deploy").get().asFile.path)
                }
            }
        }
    }
}
