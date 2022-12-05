/*
 * (c) Copyright 2017 Palantir Technologies Inc. All rights reserved.
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

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import org.junit.jupiter.api.Test;

public final class QosExceptionTest {

    @Test
    public void testVisitorSanity() throws Exception {
        QosException.Visitor<Class> visitor = new QosException.Visitor<Class>() {
            @Override
            public Class visit(QosException.Throttle exception) {
                return exception.getClass();
            }

            @Override
            public Class visit(QosException.RetryOther exception) {
                return exception.getClass();
            }

            @Override
            public Class visit(QosException.Unavailable exception) {
                return exception.getClass();
            }
        };

        assertThat(QosException.throttle().accept(visitor)).isEqualTo(QosException.Throttle.class);
        assertThat(QosException.retryOther(new URL("http://foo")).accept(visitor))
                .isEqualTo(QosException.RetryOther.class);
        assertThat(QosException.unavailable().accept(visitor)).isEqualTo(QosException.Unavailable.class);
    }

    enum MyReason implements Reason {
        REASON1 {
            @Override
            public String toString() {
                return "my reason 1";
            }
        },
        REASON2 {
            @Override
            public String toString() {
                return "my reason 2";
            }
        };
    }

    @Test
    public void testPritham() {

        QosException.Throttle.Factory throttleFactory = QosException.Throttle.reason("reason");
        QosException.Throttle.Factory throttleFactory2 = QosException.Throttle.reason(MyReason.REASON1);
        throw throttleFactory.throttle();
    }
}
