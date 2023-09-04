package day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P02_NegativeSpartanTests {
    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://44.201.221.73:8000/";
    }
    /*
    *given accept content type is application/json
    * when user sends Get request to api/spartans endpoint
    * then status code is 200
     */
    @Test
    public void getAllSpartans(){
        Response response = given()
                .accept(ContentType.JSON)
                .when()
                .get("api/spartans");
        assertEquals(200,response.getStatusCode());
    }
    //task
    /*
    * given accept type is application/xml
    * when user sends Get request to api/spartans/10 end point
    * then status code must be 406
    * and response content type must be application/xml;charset=UTF-8;
     */
    @DisplayName("validation of content type of application/xml")
    @Test
    public void xmlTest(){
        Response response = given()
                .accept(ContentType.XML)
                .when()
                .get("api/spartans/10");
        //verify status code is 406
        assertEquals(406,response.statusCode());
        //response content type must be application/xml;charset=UTF-8;
        assertEquals("application/xml;charset=UTF-8",response.contentType());
    }
}
