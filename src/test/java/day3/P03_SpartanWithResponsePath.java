package day3;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.HrTestBase;
import utils.SpartanTestBase;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class P03_SpartanWithResponsePath extends SpartanTestBase {
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
    @DisplayName("Get request with spartan with response path")
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
        int id = response.path("id");
        String name = response.path("name");
        String gender = response.path("gender");
        int phone = response.path("phone");

        System.out.println("phone = " + phone);
        System.out.println("id = " + id);
        System.out.println("gender = " + gender);
        System.out.println("name = " + name);
        //assertions
        assertEquals(9,id);
        assertEquals("Florrie",name);
        assertEquals("Female",gender);
        assertEquals(1702025787,phone);

    }
    @DisplayName("Get all spartans")
    @Test
    public void test2(){
        Response response = given()
                .accept(ContentType.JSON)
                .when()
                .get("api/spartans");
        //get me first spartan id
        int firstId = response.path("id[0]");
        System.out.println(firstId);
        //get first spartan name
        System.out.println("first name is : " + response.path("name[0]"));
        //get the last spartan name
        System.out.println("response.path(\"name[-1]\") = " + response.path("name[-1]"));
        //get the name before last spartan name
        System.out.println("response.path(\"name[-2]\") = " + response.path("name[-2]"));
        //get me all spartan names
        List<String> allNames = response.path("name");
        //how to print all names
        for (String eachName : allNames){
            System.out.println("eachName = " + eachName);
        }
    }
}
