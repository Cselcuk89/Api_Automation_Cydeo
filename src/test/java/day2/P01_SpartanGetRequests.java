package day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class P01_SpartanGetRequests {
    String url = "http://44.201.221.73:8000/";
    /*
    * Given content type is application/json
    * When user sends Get request to api/spartans endpoint
    * then status code is 200
    * and content type is application/json
     */
    @Test
    public void getAllSpartans(){
        Response response = RestAssured.given()
                                       .accept(ContentType.JSON)//hey api please send me json response
                .when()
                      .get(url + "api/spartans");
        //print the response body
        response.prettyPrint();

        //validation of status code
        int actualStatusCode = response.statusCode();
        Assertions.assertEquals(200,actualStatusCode);
        //how to get response content type header?
        String actualContentType = response.contentType();
        System.out.println(actualContentType);
        //assert the content type
        Assertions.assertEquals("application/json",actualContentType);
        //how to get other headers values?
        System.out.println(response.header("Connection"));
        System.out.println(response.header("Date"));
        //how to verify that header exists?
        boolean dateExists = response.headers().hasHeaderWithName("Date");
        Assertions.assertTrue(dateExists);


    }
    //task1
    /*
     * Given content type is application/json
     * When user sends Get request to api/spartans/3 endpoint
     * then status code is 200
     * and content type is application/json
     * and response body needs to contain Fidole
     */
    @Test
    public void spartansGetTask(){
        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .get(url + "api/spartans/4");
        //validation of status code
        int statusCode = response.statusCode();
        Assertions.assertEquals(200,statusCode);
        //validation of content type
        String contentType = response.contentType();
        Assertions.assertEquals("application/json",contentType);
        Assertions.assertEquals("application/json",response.getContentType());
        Assertions.assertEquals(ContentType.JSON.toString(),response.header("Content-Type"));
        response.prettyPrint();
        //validation of response body whether containing "Paige" or not
        Assertions.assertTrue(response.body().asString().contains("Paige"));


    }
    //task 2
    /*
     * Given no headers provided
     * when user sends Get request to api/hello
     * then response status code is 200
     * and content type header is "text/plain;charset=UTF-8"
     * and headers should contain "Date" header
     * and content-length should be 17
     * body should be "Hello from Sparta"
     *

     */
    @Test
    public void spartanGetTaskWithNoHeader(){
        Response response = RestAssured.when()
                .get(url + "api/hello");
        response.prettyPrint();
        //verify status code
        Assertions.assertEquals(200,response.getStatusCode());
        //verify content type header is "text/plain;charset=UTF-8"
        Assertions.assertEquals("text/plain;charset=UTF-8",response.getContentType());
        //verify that there is "Date" header
        Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));
        //verify that content length header is 17
        Assertions.assertEquals("17",response.header("Content-Length"));
        //verify that body is "Hello from Sparta"
        Assertions.assertTrue(response.body().asString().equals("Hello from Sparta"));


    }

}
