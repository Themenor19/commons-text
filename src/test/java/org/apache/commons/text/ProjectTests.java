/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.text;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ProjectTests {
    //Boundary value testing for CharMatcher
    @Test
    public void testMatchAtStartOfBuffer() {
        char[] buffer = {'x', 'b', 'c'};
        StrMatcher matcher = StrMatcher.charMatcher('x');

        int result = matcher.isMatch(buffer, 0, 0, buffer.length);
        assertEquals(1, result, "Should match at start of buffer");
    }

    @Test
    public void testMatchAtEndOfBuffer() {
        char[] buffer = {'a', 'b', 'z'};
        StrMatcher matcher = StrMatcher.charMatcher('z');

        int result = matcher.isMatch(buffer, 2, 0, buffer.length);
        assertEquals(1, result, "Should match at end of buffer");
    }

    @Test
    public void testNoMatchAtStart() {
        char[] buffer = {'a', 'b', 'c'};
        StrMatcher matcher = StrMatcher.charMatcher('z');

        int result = matcher.isMatch(buffer, 0, 0, buffer.length);
        assertEquals(0, result, "Should not match at start of buffer");
    }

    @Test
    public void testMatchInMiddleOfBuffer() {
        char[] buffer = {'a', 'x', 'c'};
        StrMatcher matcher = StrMatcher.charMatcher('x');

        int result = matcher.isMatch(buffer, 1, 0, buffer.length);
        assertEquals(1, result, "Should match in middle of buffer");
    }

    @Test
    public void testMatchOutsideActiveRange() {
        char[] buffer = {'x', 'y', 'z'};
        StrMatcher matcher = StrMatcher.charMatcher('x');

        // Limit active buffer to [1, 3)
        int result = matcher.isMatch(buffer, 0, 1, 3);
        assertEquals(0, result, "Should not match outside active range");
    }

    @Test
    public void testMatchWithSingleCharBuffer() {
        char[] buffer = {'x'};
        StrMatcher matcher = StrMatcher.charMatcher('x');

        int result = matcher.isMatch(buffer, 0, 0, 1);
        assertEquals(1, result, "Should match with single character buffer");
    }
    //end of char matcher tests
}
