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
 * Configuration for modifying method parameters.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface ParamConfig {
    /**
     * New name for the parameter.
     * Empty string means no renaming will be performed.
     *
     * @return the new parameter name, or empty string for no renaming
     */
    String rename() default "";

    /**
     * Whether to remove all annotations on this parameter.
     * Annotations from the mirror parameter will be applied instead.
     *
     * @return {@code true} to remove annotations, {@code false} otherwise
     */
    boolean removeAnnotations() default false;
}
