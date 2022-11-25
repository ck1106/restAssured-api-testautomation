package com.datadrivenapi.test;

import com.datadrivenapi.base.baseTest;
import com.datadrivenapi.utilities.RestUtility;
import io.restassured.http.Method;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Bernard Akondoh
 * 11/21/2022
 * extends BaseTest class
 */
public class TC004_Update_Employee_Record extends baseTest {
    String empName = RestUtility.empName();
    String empSal = RestUtility.empSal();
    String empAge = RestUtility.empAge();


    //super constructor to call superclass (to inherit from the superclass properties)
    public TC004_Update_Employee_Record(){
        super();
    }

    @BeforeMethod // will be run before each test method
    public void setup() throws InterruptedException {
        callBaseUri_PostPut(empName,empAge,empSal);// a call base uri and request object
        response = httpRequest.request(Method.PUT, prop.getProperty("update"));// response object to hold request response
        Thread.sleep(3000);
    }

    @Test
        //Get response Body as a string
    void GetResponseBody(){
        Get_ResponseBody();
        System.out.println("body is :"+Get_ResponseBody());
        Assert.assertTrue(Get_ResponseBody().contains(empName));
        Assert.assertTrue(Get_ResponseBody().contains(empAge));
        Assert.assertTrue(Get_ResponseBody().contains(empSal));

    }
    @Test //Get statusCode and validate
    void statusCode() {
        Get_StatusCode();
        int sCode = 200;
        Assert.assertEquals(Get_StatusCode(), sCode);
        System.out.println("StatusCode is :" + Get_StatusCode());
    }

    @Test //Get statusLine and validate
    void statusLine()  {
        Get_StatusLine();
        String sLine = "HTTP/1.1 200 OK";
        Assert.assertEquals(Get_StatusLine(),sLine);
        System.out.println(Get_StatusLine()+ " "+ sLine);
    }

    @Test //Get header content_Type and validate
    void content_Type(){
        String header =Get_Header("Content-Type");
        String exHeader = prop.getProperty("contentTyp");
        Assert.assertEquals(header, exHeader);
        System.out.println("content-type is: "+ header);
    }

    @Test //Get header server_Type and validate
    void server_Type(){
        String serverT =Get_Header("Server");
        String exServer  = prop.getProperty("serverput");
        Assert.assertEquals(serverT, exServer);
        System.out.println("server-type is: "+ serverT);
    }

    @Test //Get header Content_Encoding and validate
    void Content_Encoding(){
        String conEncoding =Get_Header("Content-Encoding");
        String exConEnco  = prop.getProperty("contentEnc");
        Assert.assertEquals(conEncoding, exConEnco);
        System.out.println("server-type is: "+ conEncoding);
    }

    @Test // Get response Time from response
    void Get_ResponseTime(){
        long response_time = Get_Response_Time();
        if (response_time< 2000){
            System.out.println("response time is :" + response_time);
        }else{
            System.out.println("response time is greater than 2000 :" + response_time);
        }
    }



}
