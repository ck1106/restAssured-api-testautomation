package com.datadrivenapi.base;


import com.datadrivenapi.utilities.ExtentManager;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.json.simple.JSONObject;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


/**
 * Bernard Akondoh
 * 11/21/2022
 */

public class baseTest {
    public static RequestSpecification httpRequest;
    public static Response response;
    public static Properties prop;



    //load configuration file :: reads configuration file (loading configuration)
    public baseTest(){
        try {
            prop = new Properties();
            String path = "C:/Users/time/Downloads/Compressed/DataDrivenApiTest/src/test/java/com/datadrivenapi/configuration/config.properties";
            FileInputStream ip = new FileInputStream(path);
            prop.load(ip);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //call to base URI
    public static void callBaseUri()  {
        RestAssured.baseURI = prop.getProperty("base_uri");
        httpRequest= RestAssured.given();

    }


    //call to base URI
    public static void callBaseUri_PostPut(String empName, String empSal, String empAge ){
        RestAssured.baseURI = prop.getProperty("base_uri");
        httpRequest= RestAssured.given();
        JSONObject requestParams = new JSONObject(); //specify parameters
        requestParams.put("name",empName);
        requestParams.put("salary", empSal);
        requestParams.put("age", empAge );

        httpRequest.header("Content-Type", prop.getProperty("contentTyp"));// specify content as json
        httpRequest.body(requestParams.toJSONString()); //convert body to json

    }


    //Call to response body as string
    public static String Get_ResponseBody(){
        String body = response.getBody().asString();
        return body;
    }

    //Call to status code
    public static int Get_StatusCode(){
        int statusCode= response.getStatusCode();
        return statusCode;
    }

    //call to status line
    public static String Get_StatusLine(){
        String statusLine = response.getStatusLine();
        return statusLine;
    }

    //call to single header by specifying header name
    public static String Get_Header(String header_name){
        String header = response.header(header_name);
        return  header;
    }

    //call to response time by specifying header name
    public static long Get_Response_Time(){
        long respTime = response.getTime();
        return  respTime;
    }


    // call all headers with their names and values
    public static void Get_All_Headers(){
        Headers allHeaders = response.headers();
        for (Header header : allHeaders) {
            System.out.println(header.getName() + " ====== " + header.getValue());
        }
    }

    @BeforeSuite
    public void setUp(){
        DOMConfigurator.configure("log4j.xml");
        ExtentManager.setExtent();
    }

    @AfterSuite
    public void afterSuite(){
        ExtentManager.endReport();
    }


}
