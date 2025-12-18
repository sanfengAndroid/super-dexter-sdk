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
 * Configuration for modifying method behavior and metadata.
 */
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodConfig {
    /**
     * New name for the method.
     * Empty string or constructors will not be renamed.
     *
     * @return the new method name, or empty string for no renaming
     */
    String rename() default "";

    /**
     * Whether to remove all annotations on this method.
     * Annotations from the mirror method will be applied instead.
     *
     * @return {@code true} to remove method annotations, {@code false} otherwise
     */
    boolean removeAnnotations() default false;

    /**
     * Whether to remove all parameter annotations from this method.
     * Parameter annotations from the mirror method will be applied instead.
     *
     * @return {@code true} to remove parameter annotations, {@code false} otherwise
     */
    boolean removeParamAnnotations() default false;

    /**
     * Whether to remove this method.
     * Alternatively, use {@link RemoveMethods} annotation for batch removal.
     *
     * @return {@code true} to remove the method, {@code false} otherwise
     */
    boolean remove() default false;

    /**
     * Whether to remove debug information from this method.
     *
     * @return {@code true} to remove debug info, {@code false} otherwise
     */
    boolean removeDbgInfo() default false;

    /**
     * Whether to remove the method body code.
     * A default return value will be generated based on the return type.
     * <b>Note: Does not apply to constructors, as they typically need to call super constructor.
     * To modify constructor code, implement it directly in the mirror method.</b>
     *
     * @return {@code true} to remove method body, {@code false} otherwise
     */
    boolean removeCode() default false;

}
