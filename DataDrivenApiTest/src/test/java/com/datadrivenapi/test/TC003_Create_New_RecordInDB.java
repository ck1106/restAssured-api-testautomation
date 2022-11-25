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
public class TC003_Create_New_RecordInDB extends baseTest {
    //auto generated string params from (utility class)
    String empName = RestUtility.empName();
    String empSal = RestUtility.empSal();
    String empAge = RestUtility.empAge();

    //super constructor to call superclass (to inherit from the superclass properties)
    public TC003_Create_New_RecordInDB(){
        super();
    }


    @BeforeMethod // will be run before each test method
    public void setup(){
        callBaseUri_PostPut(empName,empAge,empSal);// a call base uri and request object
        response = httpRequest.request(Method.POST, prop.getProperty("create"));// response object to hold request response
    }

    @Test //Get response Body as a string
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

}
