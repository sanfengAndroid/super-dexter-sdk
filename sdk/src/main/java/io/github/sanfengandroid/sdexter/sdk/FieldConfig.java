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
 * Configuration annotation for field-level transformations in the super-dexter framework.
 * <p>
 * This annotation is used to define field modification rules, such as renaming, removing,
 * and modifying field annotations during DEX processing.
 * </p>
 *
 * @see ClassConfig
 * @see MethodConfig
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldConfig {
    /**
     * New name for the field.
     * Empty string means no renaming will be performed.
     *
     * @return the new field name, or empty string for no renaming
     */
    String rename() default "";

    /**
     * Whether to remove this field.
     * Alternatively, use {@link RemoveFields} annotation for batch removal.
     * <b>Note: Also remove all references to this field.</b>
     *
     * @return {@code true} to remove the field, {@code false} otherwise
     */
    boolean remove() default false;

    /**
     * Whether to remove all annotations on this field.
     * Annotations from the mirror field will be applied instead.
     *
     * @return {@code true} to remove annotations, {@code false} otherwise
     */
    boolean removeAnnotations() default false;

    /**
     * Whether to remove the initial value of static fields.
     * Does not apply to instance fields.
     * To modify initial values, update them directly in the mirror field.
     *
     * @return {@code true} to remove static field initial values, {@code false} otherwise
     */
    boolean removeInitialValue() default false;

}
