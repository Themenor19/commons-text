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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class ProjectFourthTests {

    private AlphabetConverter converter;

    @BeforeEach
    public void setUp() {
        Map<Integer, String> map = new HashMap<>();
        map.put((int) 'a', "1");
        map.put((int) 'b', "2");
        map.put((int) 'c', "3");
        converter = AlphabetConverter.createConverterFromMap(map);
    }

    @Test
    public void testDecode_NullInput() throws UnsupportedEncodingException {
        assertNull(converter.decode(null));
    }

    @Test
    public void testDecode_EmptyString() throws UnsupportedEncodingException {
        assertEquals("", converter.decode(""));
    }

    @Test
    public void testDecode_SingleCharacterBoundary() throws UnsupportedEncodingException {
        assertEquals("a", converter.decode("1"));
    }

    @Test
    public void testDecode_TwoCharacterBoundary() throws UnsupportedEncodingException {
        assertEquals("ab", converter.decode("12"));
    }

    @Test
    public void testDecode_InvalidGroupThrowsException() {
        Map<Integer, String> faultyMap = new HashMap<>();
        faultyMap.put((int) 'x', "xx");
        AlphabetConverter faultyConverter = AlphabetConverter.createConverterFromMap(faultyMap);

        assertThrows(UnsupportedEncodingException.class, () -> {
            faultyConverter.decode("x");
        });
    }

    @Test
    public void testDecode_UnexpectedEndThrowsException() {
        Map<Integer, String> shortMap = new HashMap<>();
        shortMap.put((int) 'y', "yy");
        AlphabetConverter shortConverter = AlphabetConverter.createConverterFromMap(shortMap);

        assertThrows(UnsupportedEncodingException.class, () -> {
            shortConverter.decode("y");
        });
    }
}
