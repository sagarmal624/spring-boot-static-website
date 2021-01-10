package com.pe.builddemo;

import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

public class Demo {

    public static void main(String[] args) {
        String fileName = "C:\\Users\\sagshank\\Projects data\\DPMRAS\\appdemo\\src\\main\\java\\com\\pe\\builddemo\\demo.txt";

        Set<SSN> ssnFromDatabase = new HashSet<>();
        SSN record1 = new SSN();
        record1.setReferenceNum("999673565836");
        ssnFromDatabase.add(record1);


        SSN record2 = new SSN();
        record2.setEmployeeSsn("923-45-7404");
        record2.setEmployeeSsn_Hash("asdakdadnaidsbaisdbaidb");

        record2.setEmployeeSsn_First_Five("92345");
        record2.setEmployeeSsn_First_Five_Hash("asdadada");

        record2.setEmployeeSsn_Last_Four("7404");
        record2.setEmployeeSsn_Last_Four_Hash("sasasa");

        record2.setEmployeeSsn_formated("923457404");
        record2.setEmployeeSsn_formated_Hash("sasasa");
        record2.setReferenceNum("999673564159");

        record2.setReferenceNum("44555");
        ssnFromDatabase.add(record2);
        SSN record3 = new SSN();
        record3.setReferenceNum("999673564159");
        ssnFromDatabase.add(record3);

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.map(it -> it.split(",")).map(it -> {
                it[1] = it[1].replaceAll("\"", "");
                return it;
            }).forEach(it -> {
                SSN ssn = compare(it[2], it[1], ssnFromDatabase);
                if (Objects.nonNull(ssn) && StringUtils.isEmpty(it[1]) && StringUtils.isEmpty(ssn.getEmployeeSsn())) {
                    System.out.println("SSN number is empty for this reference number="+ssn.getReferenceNum());
                } else if (Objects.nonNull(ssn)) {
                    System.out.println("Record is valid for =" + ssn.getEmployeeSsn());
                } else {
                    System.out.println("Record is invalid or not found in database=" + it);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isEmpty(String str) {
        return StringUtils.isEmpty(str);
    }

    private static SSN compare(String refnum, String lookupssn, Set<SSN> ssnFromDatabase) {
        return ssnFromDatabase.stream().filter(ssn ->
        {
            String formated = lookupssn.replaceAll("-", "");

            if (StringUtils.isEmpty(lookupssn) && StringUtils.isEmpty(ssn.getEmployeeSsn()) && ssn.getReferenceNum().equals(refnum)) {
                return true;
            } else {
                return (!isEmpty(ssn.getEmployeeSsn()) && ssn.getEmployeeSsn().equals(lookupssn) && !isEmpty(ssn.getEmployeeSsn_Hash()))
                        &&
                        ((ssn.getEmployeeSsn_First_Five().equals(formated.substring(0, 5))) && !isEmpty(ssn.getEmployeeSsn_First_Five_Hash()))
                        &&
                        (ssn.getEmployeeSsn_Last_Four().equals(formated.substring(formated.length() - 4)) && !isEmpty(ssn.getEmployeeSsn_Last_Four_Hash()))
                        &&
                        (ssn.getEmployeeSsn_formated().equals(formated) && !isEmpty(ssn.getEmployeeSsn_formated_Hash()));
            }
        }).findFirst().orElse(null);

    }
//    private static SSN compare(String lookupssn, Set<SSN> ssnFromDatabase) {
//        return ssnFromDatabase.stream().filter(ssn ->
//        {
//            String formated = lookupssn.replaceAll("-", "");
//            return (ssn.getEmployeeSsn().equals(lookupssn) && !isEmpty(ssn.getEmployeeSsn_Hash()))
//                    &&
//                    ((ssn.getEmployeeSsn_First_Five().equals(formated.substring(0, 5))) && !isEmpty(ssn.getEmployeeSsn_First_Five_Hash()))
//                    &&
//                    (ssn.getEmployeeSsn_Last_Four().equals(formated.substring(formated.length() - 4)) && !isEmpty(ssn.getEmployeeSsn_Last_Four_Hash()))
//                    &&
//                    (ssn.getEmployeeSsn_formated().equals(formated) && !isEmpty(ssn.getEmployeeSsn_formated_Hash()));
//        }).findFirst().orElse(null);
//
//    }
}
