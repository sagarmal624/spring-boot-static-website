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
        record1.setEmployeeSsn("823456992");
        record1.setEmployeeSsn_Hash("asdakdadnaidsbaisdbaidb");

        record1.setEmployeeSsn_First_Five("82345");
        record1.setEmployeeSsn_First_Five_Hash("asdadada");

        record1.setEmployeeSsn_Last_Four("6992");
        record1.setEmployeeSsn_Last_Four_Hash("sasasa");

        record1.setEmployeeSsn_formated("823456992");
        record1.setEmployeeSsn_formated_Hash("sasasa");
        ssnFromDatabase.add(record1);


        SSN record2 = new SSN();
        record2.setEmployeeSsn("923457404");
        record2.setEmployeeSsn_Hash("asdakdadnaidsbaisdbaidb");

        record2.setEmployeeSsn_First_Five("92345");
        record2.setEmployeeSsn_First_Five_Hash("asdadada");

        record2.setEmployeeSsn_Last_Four("7404");
        record2.setEmployeeSsn_Last_Four_Hash("sasasa");

        record2.setEmployeeSsn_formated("923457404");
        record2.setEmployeeSsn_formated_Hash("sasasa");
        ssnFromDatabase.add(record2);


        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.map(it -> it.split(",")[1]).map(it -> it.replaceAll("-", "").replaceAll("\"", "")).forEach(it -> {
                SSN ssn = compare(it, ssnFromDatabase);
                if (Objects.nonNull(ssn)) {
                    System.out.println("Record is valid for ="+ssn.getEmployeeSsn());
                } else {
                    System.out.println("Record is invalid or not found in database="+ssn.getEmployeeSsn());
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isEmpty(String str) {
        return StringUtils.isEmpty(str);
    }

    private static SSN compare(String lookupssn, Set<SSN> ssnFromDatabase) {
        return ssnFromDatabase.stream().filter(ssn ->
                (ssn.getEmployeeSsn().equals(lookupssn) && !isEmpty(ssn.getEmployeeSsn_Hash()))
                        &&
                        ((ssn.getEmployeeSsn_First_Five().equals(lookupssn.substring(0, 5))) && !isEmpty(ssn.getEmployeeSsn_First_Five_Hash()))
                        &&
                        (ssn.getEmployeeSsn_Last_Four().equals(lookupssn.substring(lookupssn.length() - 4)) && !isEmpty(ssn.getEmployeeSsn_Last_Four_Hash()))
                        &&
                        (ssn.getEmployeeSsn_formated().equals(lookupssn) && !isEmpty(ssn.getEmployeeSsn_formated_Hash()))
        ).findFirst().orElse(null);

    }
}
