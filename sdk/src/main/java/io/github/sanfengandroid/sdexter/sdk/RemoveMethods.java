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
 * Configuration for removing methods from classes.
 * When applied to a class, removes all matching methods in that class.
 * When applied to a method, removes that specific method.
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RemoveMethods {

    /**
     * Exact method prototypes to remove, regardless of method type (direct or virtual).
     * Can be specified in readable form or dex form.
     *
     * @return array of method prototypes to remove
     */
    String[] value() default {};

    /**
     * Regular expression patterns matching method prototypes to remove, regardless of method type (direct or virtual).
     * When unique matching is not possible, use this annotation on mirror methods for identification.
     * <b>Note: Patterns match dex internal format (e.g., {@code fun(ILcom/param/one)V}), not readable format.</b>
     *
     * @return array of regex patterns for method prototype matching
     */
    String[] protoPatterns() default {};
}
