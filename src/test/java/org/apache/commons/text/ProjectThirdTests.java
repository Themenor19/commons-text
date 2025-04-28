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

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

public class ProjectThirdTests {

    // Assume convertCharsToIntegers is in the same class for now
    private static Integer[] convertCharsToIntegers(final Character[] chars) {
        if (ArrayUtils.isEmpty(chars)) {
            return ArrayUtils.EMPTY_INTEGER_OBJECT_ARRAY;
        }
        final Integer[] integers = new Integer[chars.length];
        Arrays.setAll(integers, i -> (int) chars[i]);
        return integers;
    }

    @Test
    public void testNullInput() {
        Character[] input = null;
        Integer[] result = convertCharsToIntegers(input);
        assertArrayEquals(ArrayUtils.EMPTY_INTEGER_OBJECT_ARRAY, result);
    }

    @Test
    public void testEmptyInput() {
        Character[] input = new Character[0];
        Integer[] result = convertCharsToIntegers(input);
        assertArrayEquals(ArrayUtils.EMPTY_INTEGER_OBJECT_ARRAY, result);
    }

    @Test
    public void testSingleElementInput() {
        Character[] input = new Character[]{ 'A' };
        Integer[] result = convertCharsToIntegers(input);
        assertArrayEquals(new Integer[]{ (int) 'A' }, result);
    }

    @Test
    public void testTwoElementsInput() {
        Character[] input = new Character[]{ 'A', 'B' };
        Integer[] result = convertCharsToIntegers(input);
        assertArrayEquals(new Integer[]{ (int) 'A', (int) 'B' }, result);
    }

    @Test
    public void testMinMaxCharacterValues() {
        Character[] input = new Character[]{ Character.MIN_VALUE, Character.MAX_VALUE };
        Integer[] result = convertCharsToIntegers(input);
        assertArrayEquals(new Integer[]{ (int) Character.MIN_VALUE, (int) Character.MAX_VALUE }, result);
    }

    @Test
    public void testInputWithNullElement() {
        Character[] input = new Character[]{ null };
        assertThrows(NullPointerException.class, () -> convertCharsToIntegers(input));
    }
}
