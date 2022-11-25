package com.datadrivenapi.utilities;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * Bernard Akondoh
 * 11/21/2022
 */

public class RestUtility {

     public static String empName(){
         String generatedString = RandomStringUtils.randomAlphabetic(1);
         return ("Enoch"+generatedString);
     }

    public static String empSal(){
        String generatedString = RandomStringUtils.randomNumeric(5);
        return( generatedString);
    }

    public static String empAge(){
        String generatedString = RandomStringUtils.randomNumeric(2);
        return (generatedString);
    }



}
