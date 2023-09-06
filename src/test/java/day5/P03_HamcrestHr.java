package day5;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.HrTestBase;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
public class P03_HamcrestHr extends HrTestBase {

   /*
    Given accept type is Json
    And parameters : q = { " job_id " : " IT_PROG " }
    When users sends a GET request to " / employees "
    Then status code is 200
    And Content type is application / json
    Verify
        - each employees has manager
        - each employees working as IT PROG
        - each of them getting salary greater than 3000
        - first names are ( find proper method to check list against list )
         emails without checking order ( provide emails in different order , just make sure it has same emails )
         List < String > names mes = Arrays.asList ( " Alexander " , " Bņuce " , " David " , " Valli " , " Diana " ) ;
         " DAUSTIN " , " AHUNOLD " , " BERNST " , " VPATARAL " , " DLORENTZ "


   */
    @DisplayName("Get employees IT_PROG with hamcrest")
    @Test
    public void test1(){
        //expected firstnames
        List< String > expectedFirstNames = Arrays.asList ( " Alexander " , " Bņuce " , " David " , " Valli " , " Diana " ) ;
        given()
                .accept(ContentType.JSON)
                .queryParam("q","{ \" job_id \" : \" IT_PROG \" }")
                .when()
                .get("/employees")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body("items.manager_id",everyItem(notNullValue()
                        ), "items.job_id", everyItem(equalTo("IT_PROG")),
                        "items.salary", everyItem(greaterThan(3000)),
                        "items.first_name", equalTo(expectedFirstNames),
                         "items.email",containsInAnyOrder("DAUSTIN","AHUNOLD","BERNST","VPATABAL"));


    }
    /*
    Given that accept type is application/json
    When user sends get request to /regions
    Then
                response status code must be 200
                verify Date has values
                first region name is Europe
                first region id is 1
                four regions we have
                region names are not null
                Regions name should be same order as " Europe " , " Americas " , " Asia " , " Middle East and Africa "
                region ids needs to be 1,2,3,4
                print all the regions names

     */


}
