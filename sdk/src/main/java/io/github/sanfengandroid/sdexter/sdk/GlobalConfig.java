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
package io.github.sanfengandroid.sdexter.sdk;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Global configuration for class and package deletion and renaming.
 * Must be applied to a subclass of {@link BaseConfig}.
 * Currently, only one implementation class is supported.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface GlobalConfig {
    /**
     * Whether to remove source file information from all classes.
     *
     * @return {@code true} to remove source file info globally, {@code false} otherwise
     */
    boolean removeSourceFile() default false;

    /**
     * Global source file name to apply to all classes.
     * Non-empty values will override individual class source file information.
     *
     * @return the global source file name, or empty string for no override
     */
    String sourceFile() default "";

    /**
     * Fully qualified class names to remove from the output.
     * Example: {@code "com.example.MyClass"}
     *
     * @return array of class names to remove
     */
    String[] removeClasses() default {};

    /**
     * Regular expression patterns matching fully qualified class names to remove.
     * Example: {@code "com/example/MyClass"}
     * <b>Note: Patterns use dex internal '/' separator, not '.'</b>
     *
     * @return array of regex patterns for class removal
     */
    String[] removeClassPatterns() default {};

    /**
     * Package names to remove from the output.
     * Example: {@code "com.oldpackage"}
     *
     * @return array of package names to remove
     */
    String[] removePackages() default {};

    /**
     * Regular expression patterns matching package names to remove.
     * Example: {@code "com/oldpackage(/.*)?"}
     * <b>Note: Patterns use dex internal '/' separator, not '.'</b>
     *
     * @return array of regex patterns for package removal
     */
    String[] removePackagePatterns() default {};

    /**
     * Package name pairs for renaming.
     * Array should contain pairs: original name followed by new name.
     * Example: {@code {"com.oldpackage", "com.newpackage"}}
     *
     * @return array of package rename pairs
     */
    String[] renamePackages() default {};

    /**
     * Regular expression patterns for package renaming with group replacement.
     * Array should contain pairs: regex pattern followed by replacement pattern.
     * Example: {@code {"com/oldpackage/.(.*)", "com/newpackage/$1"}}
     * <b>Note: Patterns use dex internal '/' separator, not '.'</b>
     *
     * @return array of regex pattern and replacement pairs
     */
    String[] renamePackagePatterns() default {};

    /**
     * Whether to skip merging R.java resource classes.
     * These are typically unnecessary in the final output.
     *
     * @return {@code true} to skip R classes, {@code false} otherwise
     */
    boolean skipRClass() default true;

    /**
     * Fully qualified class names to skip during merging.
     * Use this when classes like {@code BuildConfig.java} cannot be directly referenced.
     *
     * @return array of class names to skip
     */
    String[] skipClasses() default {};

    /**
     * Class types to skip during merging.
     * Recommended over {@link #skipClasses()} for accessible classes.
     *
     * @return array of Class objects to skip
     */
    Class[] skipTypes() default {};

    /**
     * SDK version identifier.
     * Should be manually set to {@link BuildConfig#VERSION_NAME}.
     *
     * @return the SDK version string
     */
    String version() default "";

}
