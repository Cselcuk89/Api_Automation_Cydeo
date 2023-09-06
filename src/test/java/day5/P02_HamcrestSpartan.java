package day5;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.SpartanTestBase;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
public class P02_HamcrestSpartan extends SpartanTestBase {
    /*
    * given that accept type is json
    * and path param id is 15
    * when user sends a get request to api/spartans/{id}
    * then status code is 200
    * and content type is json
    * and json data hs the followings:
            * "id" : 12,
            * "name" : "Sol",
            * "gender" : "Male"
            * "phone" :  7006438852
     */
    @DisplayName("Get single spartan with hamcrest")
    @Test
    public void test1(){
        given().accept(ContentType.JSON)
                .pathParam("id",12)
                .when()
                .get("api/spartans/{id}")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body("id",is(equalTo(12)),
                        "name",is("Sol"),
                        "gender",is("Male"),
                        "phone",is(equalTo(7006438852L)));

    }
    @DisplayName("Get single spartan with hamcrest using logs")
    @Test
    public void test2(){
        given().accept(ContentType.JSON)
                .pathParam("id",12)
                .log().all()
                .when()
                .get("api/spartans/{id}")
                .then()
                .log().ifValidationFails(LogDetail.STATUS)
                .statusCode(200)
                .contentType("application/json")
                .body("id",is(equalTo(13)),
                        "name",is("Sol"),
                        "gender",is("Male"),
                        "phone",is(equalTo(7006438852L)));

    }
    @DisplayName("Get single spartan with hamcrest using extract() method")
    @Test
    public void test3(){
       JsonPath jsonPath = given().accept(ContentType.JSON)
                .pathParam("id",12)
                .when()
                .get("api/spartans/{id}")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body("id",is(equalTo(12)),
                        "name",is("Sol"),
                        "gender",is("Male"),
                        "phone",is(equalTo(7006438852L)))
                .extract().jsonPath();


        // below lines are our assumption
        //api actual data(id,name)
        int id = jsonPath.getInt("id");
        System.out.println("id = " + id);
        String name = jsonPath.getString("name");
        //expected data from database
        //using db utils and saving variables
        int expectedIdDb = 12;
        String expectedNameDb = "Sol";
        //comparison between api and database
        //we can use either junit5 or hamcrest class
        assertThat(id,is(expectedIdDb));
        assertThat(name,is(equalTo(expectedNameDb)));


    }

}
