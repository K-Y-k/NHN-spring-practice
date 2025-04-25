/*
 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 * + Copyright 2024. NHN Academy Corp. All rights reserved.
 * + * While every precaution has been taken in the preparation of this resource,  assumes no
 * + responsibility for errors or omissions, or for damages resulting from the use of the information
 * + contained herein
 * + No part of this resource may be reproduced, stored in a retrieval system, or transmitted, in any
 * + form or by any means, electronic, mechanical, photocopying, recording, or otherwise, without the
 * + prior written permission.
 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 */

package com.nhnacademy.exam.parser.impl;

import com.nhnacademy.exam.parser.DepartmentParser;
import com.nhnacademy.exam.parser.ParsingObject;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class TextDepartmentParser implements DepartmentParser {

    @Override
    public String getFileType() {
        return "txt";
    }

    @Override
    public List<ParsingObject> parsing(File file) throws IOException {
        List<ParsingObject> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            br.readLine();
            br.readLine();
            br.readLine();

            String line;
            while((line=br.readLine()) != null) {
                line = line.trim();
                if (line.isBlank() || line.equals("|----------------|----------------|----------------|----------------|")) {
                    continue;
                }

                String[] splitLine = line.split("\\|");
                String employeeId = splitLine[1].trim();
                String employeeName = splitLine[2].trim();
                String departmentName = splitLine[3].trim();
                String departmentId = splitLine[4].trim();

                list.add(new ParsingObject(employeeId, employeeName, departmentName, departmentId));
            }

            return list;
        }
    }
}
