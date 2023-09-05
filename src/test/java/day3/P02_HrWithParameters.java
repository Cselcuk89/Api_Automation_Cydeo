package day3;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.HrTestBase;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P02_HrWithParameters extends HrTestBase {
    /*
    * given that accept type is json
    * and parameters : q = "{"region_id":2}"
    * when users sends Get request to "/countries"
    * then status code is 200
    * and content type is application/json
    * and payload should contain "United States of America"
     */
    @DisplayName("Get request to /countries with Region_id")
    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .queryParam("q","{\"region_id\":2}")
                .when()
                .get("/countries");
        //then status code is 200
        assertEquals(HttpStatus.SC_OK,response.getStatusCode());
        //and content type is application/json
        assertEquals("application/json",response.getContentType());
        //and payload should contain "United States of America"
        assertTrue(response.body().asString().contains("United States of America"));


    }
}
