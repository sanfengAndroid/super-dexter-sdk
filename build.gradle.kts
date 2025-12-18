// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jreleaser) apply false
}

tasks.withType<Javadoc>().configureEach {
    options.encoding = "UTF-8"
    (options as StandardJavadocDocletOptions).locale = "en_US"
//    (options as StandardJavadocDocletOptions).addStringOption("Xdoclint:none", "-quiet")
}