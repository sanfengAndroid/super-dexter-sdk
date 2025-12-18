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
 * Configuration for removing fields from classes.
 * When applied to a class, removes all matching fields in that class.
 * When applied to a field, removes that specific field.
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RemoveFields {

    /**
     * Exact field names to remove, regardless of field type (instance or static).
     * When unique matching is not possible, use this annotation on mirror fields for identification.
     *
     * @return array of field names to remove
     */
    String[] value() default {};

    /**
     * Regular expression patterns matching field names to remove, regardless of field type (instance or static).
     * When unique matching is not possible, use this annotation on mirror fields for identification.
     *
     * @return array of regex patterns for field name matching
     */
    String[] namePatterns() default {};
}
