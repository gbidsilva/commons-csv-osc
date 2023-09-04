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

package org.apache.commons.csv;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TmpTest {
    @Test
    public void test_1() throws IOException {
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader()
                .setQuoteMode(QuoteMode.NONE)
                .build();

        CSVPrinter printer = new CSVPrinter(System.out, csvFormat);

        List<String[]> temp = new ArrayList<>();

        temp.add(new String[] { "ㅁㅎㄷㄹ", "ㅁㅎㄷㄹ", "", "test2" });
        temp.add(new String[] { "한글3", "hello3", "3한글3", "test3" });
        temp.add(new String[] { "", "\"hello4", "", "test4" });

        for (String[] temp1 : temp) {
            printer.printRecord(temp1);
        }
        printer.close();

        /*
        This method gives following error...

        java.lang.IllegalArgumentException: No quotes mode set but no escape character is set
        at org.apache.commons.csv.CSVFormat.validate(CSVFormat.java:2452)
        at org.apache.commons.csv.CSVFormat.<init>(CSVFormat.java:1465)
        at org.apache.commons.csv.CSVFormat.<init>(CSVFormat.java:181)
        at org.apache.commons.csv.CSVFormat$Builder.build(CSVFormat.java:275)
        at org.apache.commons.csv.TmpTest.test_1(TmpTest.java:32)
        at java.base/java.lang.reflect.Method.invoke(Method.java:568)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)

	    Above should not happen when setQuoteMode(QuoteMode.NONE)
         */
    }

    @Test
    public void test_2() throws IOException {
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader()
                .setQuoteMode(QuoteMode.MINIMAL)
                .build();

        CSVPrinter printer = new CSVPrinter(System.out, csvFormat);

        List<String[]> temp = new ArrayList<>();

        temp.add(new String[] { "ㅁㅎㄷㄹ", "ㅁㅎㄷㄹ", "", "test2" });
        temp.add(new String[] { "한글3", "hello3", "3한글3", "test3" });
        temp.add(new String[] { "", "hello4", "", "test4" });
        temp.add(new String[] { "", "", "", "" });
        temp.add(new String[] { null, null, null, null });



        for (String[] temp1 : temp) {
            printer.printRecord(temp1);
        }
        printer.close();
        // todo: why in line 3, col1 only getting the quotes when printing ? - seems a bug - need to check the internal
    }


}
