package day3;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.HrTestBase;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P04_HrWithResponsePath extends HrTestBase {
    @DisplayName("Get request to countries with using Response path")
    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .queryParam("q","{\"region_id\":2}")
                .when().get("/countries");
        //response.prettyPrint();
        //print limit
        System.out.println("response.path(\"limit\") = " + response.path("limit"));
        //print hasMore
        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));
        //print second country name
        System.out.println("response.path(\"items[1].country_name\") = " + response.path("items[1].country_name"));
        //print 4th element country name
        System.out.println("response.path(\"items[3].country_name\") = " + response.path("items[3].country_name"));
        //print 3th element href
        System.out.println("response.path(\"items[2].links[0].href\") = " + response.path("items[2].links[0].href"));
        //get all countries name
        List<String> allCountryNames = response.path("items.country_name");
        System.out.println("allCountryNames = " + allCountryNames);
        for (String eachCountryName : allCountryNames){
            System.out.println("eachCountryName = " + eachCountryName);
        }
        //verify all region_ids are equal to 2
         List<Integer> allRegionIDs = response.path("items.region_id");
        for (Integer id : allRegionIDs){
            assertEquals(2,id);
            System.out.println("id = " + id);
        }

    }
}
