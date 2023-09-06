package day4;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.CydeoTestBase;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class P03_CydeoTrainingApiTests extends CydeoTestBase {
    /*
    * given that accept type is application/json
    * and path param is 2
    * when user sends request to /student/{id}
    * then status code is 200
    * and content type is application/json;charset=UTF-8
    * and date header exists
    * and server header is envoy
    * and verify the following :
              * firstName is ertret
              * batch is 1
              * major is test
              * emailAddress is 324234@email.com
              * companyName is ewttwre
              * street name is 234 ee
              * zipcode is 3333
     */
    @DisplayName("Get request to /student/4")
    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .and()
                .pathParam("id",4)
                .when()
                .get("/student/{id}");
        //verify status code
        assertEquals(200,response.getStatusCode());
        //verify content type
        assertEquals("application/json;charset=UTF-8",response.getContentType());
        //verify that date header exists
        assertTrue(response.headers().hasHeaderWithName("Date"));
        //verify that server header is envoy
        assertEquals("envoy",response.header("server"));
        JsonPath jsonPath = response.jsonPath();
//        firstName is Mark               ===> students[0].firstName
        assertEquals("ertret",jsonPath.getString("students[0].firstName"));
//        batch is 13                     ===> students[0].batch
        assertEquals(1,jsonPath.getInt("students[0].batch"));
//        major is math                   ===> students[0].major
        assertEquals("test",jsonPath.getString("students[0].major"));
//        emailAddress is mark@email.com  ===> students[0].contact.emailAddress
        assertEquals("324234@email.com",jsonPath.getString("students[0].contact.emailAddress"));
//        companyName is Cydeo            ===> students[0].company.companyName
        assertEquals("ewttwre",jsonPath.getString("students[0].company.companyName"));
//        street name is 777 5th Ave      ===> students[0].company.address.street
        assertEquals("234 ee",jsonPath.getString("students[0].company.address.street"));
//        zipcode is 33222                ===> students[0].company.address.zipCode
        assertEquals(3333,jsonPath.getInt("students[0].company.address.zipCode"));
    }
}
