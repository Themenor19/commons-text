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
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ProjectSecondTests {
// Tests for string matcher using boundary value testing
    @Test
    public void testExactMatchAtStart() {
        char[] buffer = "hello world".toCharArray();
        StrMatcher matcher = StrMatcher.stringMatcher("hello");

        int result = matcher.isMatch(buffer, 0, 0, buffer.length);
        assertEquals(5, result, "Should match 'hello' at position 0");
    }

    @Test
    public void testPartialMatchAtEndShouldFail() {
        char[] buffer = "hell".toCharArray(); // shorter than "hello"
        StrMatcher matcher = StrMatcher.stringMatcher("hello");

        int result = matcher.isMatch(buffer, 0, 0, buffer.length);
        assertEquals(0, result, "Should not match if buffer is too short for string");
    }

    @Test
    public void testMatchAtLastValidPosition() {
        char[] buffer = "ahello".toCharArray(); // "hello" starts at index 1
        StrMatcher matcher = StrMatcher.stringMatcher("hello");

        int result = matcher.isMatch(buffer, 1, 0, buffer.length);
        assertEquals(5, result, "Should match 'hello' at position 1");
    }

    @Test
    public void testMatchFailsPastBufferEnd() {
        char[] buffer = "helloX".toCharArray();
        StrMatcher matcher = StrMatcher.stringMatcher("hello");

        int result = matcher.isMatch(buffer, 2, 0, 4); // only valid range is [0,4)
        assertEquals(0, result, "Should not match if string extends past bufferEnd");
    }

    @Test
    public void testEmptyStringMatch() {
        char[] buffer = "abc".toCharArray();
        StrMatcher matcher = StrMatcher.stringMatcher("");

        int result = matcher.isMatch(buffer, 1, 0, buffer.length);
        assertEquals(0, result, "Empty string should not match anything (returns 0)");
    }

    @Test
    public void testSingleCharMatch() {
        char[] buffer = "abc".toCharArray();
        StrMatcher matcher = StrMatcher.stringMatcher("b");

        int result = matcher.isMatch(buffer, 1, 0, buffer.length);
        assertEquals(1, result, "Should match single character at correct position");
    }

    @Test
    public void testMismatchAtStart() {
        char[] buffer = "hello".toCharArray();
        StrMatcher matcher = StrMatcher.stringMatcher("world");

        int result = matcher.isMatch(buffer, 0, 0, buffer.length);
        assertEquals(0, result, "Should not match different string");
    }
}

