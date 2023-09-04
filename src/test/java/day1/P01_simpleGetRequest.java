package day1;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class P01_simpleGetRequest {
    String url = "http://44.201.221.73:8000/api/spartans";
    /*
    When user sends request to api/spartans endpoint
    Then user should be able to see status code is 200
    and print out response body into screen
     */
    @Test
    public void simpleGetRequest(){
        //send request to url and get response as response interface
        Response response = RestAssured.get(url);
        //get the response status code
        System.out.println("response status code is : " + response.statusCode());
        //verify that status code is 200
        int statusCode = response.statusCode();
        Assertions.assertEquals(200,statusCode);
        //how to print json response body
        response.prettyPrint();
    }
}
