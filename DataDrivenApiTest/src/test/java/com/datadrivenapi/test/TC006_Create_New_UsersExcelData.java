package com.datadrivenapi.test;

import com.datadrivenapi.base.baseTest;
import com.datadrivenapi.utilities.ExcelLab;
import io.restassured.http.Method;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.IDataProviderAnnotation;
import org.testng.annotations.Test;

import java.io.IOException;


/**
 * Bernard Akondoh
 * 11/21/2022
 */

public class TC006_Create_New_UsersExcelData extends baseTest {



    public  TC006_Create_New_UsersExcelData(){
        super();
    }

    @BeforeMethod
    public void setup(){

    }

    @Test(dataProvider = "empdataprovider")
    void createEmployees(String empName, String empSal, String empAge){
        callBaseUri_PostPut(empName,empSal, empAge);
        response = httpRequest.request(Method.POST, prop.getProperty("create"));// response object to hold request response

        String body =response.getBody().asString();
        System.out.println("response body is: "+ body);

        int statusCode =response.getStatusCode();
        int exStatus = 200;
        Assert.assertEquals(statusCode, exStatus);

        String statusLine = response.getStatusLine();
        String expStatusLine = "HTTP/1.1 200 OK";
        Assert.assertEquals(statusLine, expStatusLine);

    }

    //retrieving data from excel
    @DataProvider(name= "empdataprovider")
    Object[][] getEmpData() throws IOException {
        String path = "C:/Users/time/Downloads/Compressed/DataDrivenApiTest/src/test/TestData/empdata.xlsx";
        int rowNum = ExcelLab.getRowCount(path, "Sheet1");
        int colCount = ExcelLab.getCellCount(path,"Sheet1", 1);

        String empdata[][] = new String[rowNum][colCount];
        for(int i=1; i<=rowNum; i++){
            for (int j=0; j<colCount; j++){
                empdata[i-1][j] = ExcelLab.getCellData(path, "Sheet1", i,j);
            }
        }
        return (empdata);
    }

}
