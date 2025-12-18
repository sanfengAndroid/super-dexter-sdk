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
 * Configuration annotation for class-level transformations in the super-dexter framework.
 * <p>
 * This annotation is used to define class modification rules, such as removing superclasses,
 * interfaces, source file information, and annotations during DEX processing.
 * </p>
 *
 * @see FieldConfig
 * @see MethodConfig
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ClassConfig {
    /**
     * Whether to remove the superclass and replace it with {@link Object}.
     * Alternatively, the mirror class can directly extend a different superclass, which takes precedence.
     *
     * @return {@code true} to remove the superclass, {@code false} otherwise
     */
    boolean removeSuperClass() default false;

    /**
     * Whether to remove all implemented interfaces.
     * Interfaces from the mirror class will be applied instead.
     *
     * @return {@code true} to remove interfaces, {@code false} otherwise
     */
    boolean removeInterfaces() default false;

    /**
     * Whether to remove source file debug information.
     * This applies even if the mirror class contains source file information.
     *
     * @return {@code true} to remove source file info, {@code false} otherwise
     */
    boolean removeSourceFile() default false;

    /**
     * Whether to remove class-level annotations.
     * Annotations from the mirror class will be applied instead.
     *
     * @return {@code true} to remove class annotations, {@code false} otherwise
     */
    boolean removeClassAnnotation() default false;

    /**
     * Whether to remove all field annotations.
     * Annotations from mirror fields will be applied instead.
     *
     * @return {@code true} to remove field annotations, {@code false} otherwise
     */
    boolean removeFieldAnnotation() default false;

    /**
     * Whether to remove all method annotations.
     * Annotations from mirror methods will be applied instead.
     *
     * @return {@code true} to remove method annotations, {@code false} otherwise
     */
    boolean removeMethodAnnotation() default false;

    /**
     * Whether to remove all parameter annotations.
     * Annotations from mirror method parameters will be applied instead.
     *
     * @return {@code true} to remove parameter annotations, {@code false} otherwise
     */
    boolean removeParamAnnotation() default false;

    /**
     * Whether to remove unused fields based on optimization analysis.
     * Fields are removed if they are unused or produce no side effects.
     *
     * @return {@code true} to remove unused fields, {@code false} otherwise
     */
    boolean removeUnusedFields() default false;

    /**
     * Whether to remove unused methods based on optimization analysis.
     *
     * @return {@code true} to remove unused methods, {@code false} otherwise
     */
    boolean removeUnusedMethods() default false;

    /**
     * Whether to remove all static fields.
     * Combine with {@link #removeInstanceFields()} to remove all fields.
     * <b>Note: Static fields from the mirror class will be re-added.</b>
     *
     * @return {@code true} to remove static fields, {@code false} otherwise
     */
    boolean removeStaticFields() default false;

    /**
     * Whether to remove all instance fields.
     * Combine with {@link #removeStaticFields()} to remove all fields.
     * <b>Note: Instance fields from the mirror class will be re-added.</b>
     *
     * @return {@code true} to remove instance fields, {@code false} otherwise
     */
    boolean removeInstanceFields() default false;

    /**
     * Field removal configuration for matching specific fields.
     *
     * @return the {@link RemoveFields} configuration
     */
    RemoveFields removeFields() default @RemoveFields;

    /**
     * Whether to remove all direct methods (constructors, static methods, private methods).
     * Combine with {@link #removeVirtualMethods()} to remove all methods.
     * <b>Note: Constructors are included. Provide replacements in the mirror class.</b>
     *
     * @return {@code true} to remove direct methods, {@code false} otherwise
     */
    boolean removeDirectMethods() default false;

    /**
     * Whether to remove all virtual methods (public, protected, package-private instance methods).
     * Combine with {@link #removeDirectMethods()} to remove all methods.
     *
     * @return {@code true} to remove virtual methods, {@code false} otherwise
     */
    boolean removeVirtualMethods() default false;

    /**
     * Method removal configuration for matching specific methods.
     *
     * @return the {@link RemoveMethods} configuration
     */
    RemoveMethods removeMethods() default @RemoveMethods;

    /**
     * Whether to ignore the default no-arg constructor from the mirror class.
     * Mirror classes typically include a default constructor that should not be added or replaced.
     *
     * @return {@code true} to ignore mirror's default constructor, {@code false} otherwise
     */
    boolean ignoreMirrorDefaultConstructor() default true;

    /**
     * Whether to remove static field initialization values.
     * New initialization values can be specified in the mirror class.
     *
     * @return {@code true} to remove static initializers, {@code false} otherwise
     */
    boolean removeStaticInit() default false;

    /**
     * Custom source file name to set for this class.
     * Empty string uses the source file from the mirror class.
     *
     * @return the custom source file name, or empty string for default
     */
    String sourceFile() default "";

    /**
     * Whether to use the mirror class's source file name.
     * Not recommended since mirror classes typically have a "Mirror" suffix.
     *
     * @return {@code true} to use mirror's source file, {@code false} otherwise
     */
    boolean useMirrorSourceFile() default false;

    /**
     * New name for the class.
     * If it contains '.', performs absolute renaming with full package path.
     * Use `.TopClass` for top-level package placement.
     * Otherwise, renames only the class name portion (still affected by package renaming).
     * For inner classes, use '$' separator (e.g., {@code NewOuter$Inner}).
     *
     * @return the new class name, or empty string for no renaming
     */
    String rename() default "";

    /**
     * Whether to remove this class entirely.
     * <b>Note: Also remove all references to this class.</b>
     *
     * @return {@code true} to remove the class, {@code false} otherwise
     */
    boolean remove() default false;

    /**
     * Whether to completely replace this class with the mirror class.
     * All original class information is discarded.
     *
     * @return {@code true} to replace entirely, {@code false} otherwise
     */
    boolean replace() default false;

}
