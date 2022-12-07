/*
 * (c) Copyright 2022 Palantir Technologies Inc. All rights reserved.
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

package com.palantir.conjure.java.api.errors;

import com.google.errorprone.annotations.CompileTimeConstant;

public final class QosReason {
    public static final String DEFAULT_THROTTLE_REASON = "qos-throttle";
    public static final String DEFAULT_RETRY_OTHER_REASON = "qos-retry-other";
    public static final String DEFAULT_UNAVAILABLE_REASON = "qos-unavailable";

    private @CompileTimeConstant final String name;

    QosReason(@CompileTimeConstant final String name) {
        this.name = name;
    }

    String getName() {
        return this.name;
    }
}
