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

/**
 * Marker exception indicating that a method implementation should not replace the original code.
 * <p>
 * This is a pseudo-exception used during SDK configuration to signal that a mirror method's
 * body should be ignored, preserving the original method implementation instead.
 * It is never actually thrown at runtime.
 * </p>
 */
public class MethodNotReplacedException extends RuntimeException {
    /**
     * Constructs a new {@code MethodNotReplacedException} with a descriptive message.
     */
    public MethodNotReplacedException() {
        super("This is a pseudo-exception that does not replace the original method code.");
    }
}
