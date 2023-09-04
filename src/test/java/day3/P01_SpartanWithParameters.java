package day3;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.SpartanTestBase;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class P01_SpartanWithParameters extends SpartanTestBase {
    /*
    * given that accept type is json
    * and id parameter value is 24
    * when user sends Get request to api/spartans/{id}
    * then response status code should be 200
    * and response content-type : application/json
    * and "Julio" should be in response payload(body)
     */
    @DisplayName("Get Spartan api/spartans/{id} path param with 24")
    @Test
    public void test1(){
        Response response = given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id",24)
                .when()
                .get("api/spartans/{id}");
        response.prettyPrint();
        //then response status code should be 200
        assertEquals(200,response.getStatusCode());
        //and response content-type : application/json
        assertEquals("application/json",response.getContentType());
        //and "Julio" should be in response payload(body)
        assertTrue(response.body().asString().contains("Julio"));
    }
    //task1
    /*
     * given that accept type is json
     * and id parameter value is 500
     * when user sends Get request to api/spartans/{id}
     * then response status code should be 404
     * and response content-type : application/json
     * and "Not Found" should be in response payload(body)
     */
    @DisplayName("Get Spartan api/spartans/{id} path param with invalid id")
    @Test
    public void test2(){
        Response response = given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id",554)
                .when()
                .get("api/spartans/{id}");
        response.prettyPrint();
        //then response status code should be 200
        assertEquals(404,response.getStatusCode());
        assertEquals(HttpStatus.SC_NOT_FOUND,response.statusCode());//another way of validating status code
        //and response content-type : application/json
        assertEquals("application/json",response.getContentType());
        //and "Julio" should be in response payload(body)
        assertTrue(response.body().asString().contains("Not Found"));
    }
    //task2
    /*
     * given that accept type is json
     * and query parameter values are:
             gender | Female
             nameContains | e
     * when user sends Get request to api/spartans/search
     * then response status code should be 200
     * and response content-type : application/json
     * and "Female" should be in response payload(body)
     * and "Janette" should be in response payload
     */
    @DisplayName("Get request to api/spartans/search with query parameters")
    @Test
    public void test3(){
        Response response = given()
                .accept(ContentType.JSON)
                .and()
                .queryParams("gender","Female")
                .and()
                .queryParams("nameContains","e")
                .when()
                .get("api/spartans/search");
        //then response status code should be 200
        assertEquals(200,response.getStatusCode());
        //and response content-type : application/json
        assertEquals("application/json",response.getContentType());
        //and "Female" should be in response payload(body)
        assertTrue(response.body().asString().contains("Female"));
        //and "Janette" should be in response payload
        assertTrue(response.body().asString().contains("Janette"));
    }
    @DisplayName("Get request to api/spartans/search with query parameters using map")
    @Test
    public void test4(){
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("gender","Female");
        queryMap.put("nameContains","e");

        Response response = given()
                .accept(ContentType.JSON)
                .and()
                .queryParams(queryMap)
                .when()
                .get("api/spartans/search");
        //then response status code should be 200
        assertEquals(200,response.getStatusCode());
        //and response content-type : application/json
        assertEquals("application/json",response.getContentType());
        //and "Female" should be in response payload(body)
        assertTrue(response.body().asString().contains("Female"));
        //and "Janette" should be in response payload
        assertTrue(response.body().asString().contains("Janette"));
    }


}
