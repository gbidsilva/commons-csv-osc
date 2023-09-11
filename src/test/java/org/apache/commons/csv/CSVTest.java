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

import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;

public class CSVTest {

    //    @Test
    public void readCSVFileSimpleOne() {
        // happy path one
        try {
            // change your csv file path properly
            Reader in = new FileReader("D:\\code\\apache\\csv\\samples\\basicCsvSample-1\\src\\main\\resources\\longCsvFile.csv");

            CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                    .setHeader()
                    .setSkipHeaderRecord(true)
                    .build();

            Iterable<CSVRecord> records = csvFormat.parse(in); // return a CSVParser - which is an Iterable

            for (CSVRecord record : records) {
                String firstName = record.get("firstname");
                String lastName = record.get("lastname");
                String age = record.get("age");
                String email = record.get("email");
                System.out.println("FirstName: " + firstName + ", LastName: "+ lastName +", Age: " + age + ", Email: " + email);
            }
        } catch (Exception e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }

    @Test
    public void testFaultyCSVshouldThrowErrorWithDetailedMessage(){

//        String csvContent = "col1,col2\n" +
//                "aaa;bbb";
        String csvContent = "col1,col2\n" +
                "\"aaa\";\"bbb\"";

        try {
            StringReader stringReader = new StringReader(csvContent);
            CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                    .setHeader()
                    .setSkipHeaderRecord(true)
                    .setQuote('\"')
                    .setQuoteMode(QuoteMode.ALL)
                    .build();

            Iterable<CSVRecord> records = csvFormat.parse(stringReader);

            for (CSVRecord record : records) {
//                System.out.println(record.get(0) + " " + record.get(1));
//                System.out.println(record.get(0));
            }
        } catch (Exception e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }
}
