package day4;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.SpartanTestBase;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P01_SpartanWithJsonPath extends SpartanTestBase {
    /*
     * given that accept type is json
     * and path param id is 10
     * when users sends Get request to "api/spartans/{id}"
     * then status code is 200
     * and content type is application/json
     * and payload should contain the following:
     * id is 9
     * name is "Florrie"
     * gender is "Female"
     * phone is 1702025787
     */
    @DisplayName("Get request with spartan with jsonpath")
    @Test
    public void test1(){
        Response response = given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id",9)
                .when()
                .get("api/spartans/{id}");
        response.prettyPrint();
//         * then status code is 200
        assertEquals(200,response.getStatusCode());
//         * and content type is application/json
        assertEquals("application/json",response.getContentType());
//         * and payload should contain the following:
//               * id is 9
//               * name is "Florrie"
//               * gender is "Female"
//               * phone is 1702025787
        //save the response as jsonpath object
        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.getInt("id");
        String name = jsonPath.getString("name");
        String gender = jsonPath.getString("gender");
        int phone = jsonPath.getInt("phone");
        //assertions
        assertEquals(9,id);
        assertEquals("Florrie",name);


    }

}
