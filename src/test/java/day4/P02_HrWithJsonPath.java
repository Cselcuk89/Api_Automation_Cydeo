package day4;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.HrTestBase;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P02_HrWithJsonPath extends HrTestBase {
    @DisplayName("Get request to all /countries")
    @Test
    public void test1(){
        Response response = get("/countries");//to get all countries as a response
        //response.prettyPrint();
        //verify status code
        assertEquals(200,response.getStatusCode());
        //create jsonpath object
        JsonPath jsonPath = response.jsonPath();
        //get me the 3rd country name
        System.out.println("jsonPath.getString(\"items[2].country_name\") = "
                              + jsonPath.getString("items[2].country_name"));
        //get me 3rd and 4th country names together
        System.out.println("jsonPath.getString(\"items[2,3].country_name\") = "
                              + jsonPath.getString("items[2,3].country_name"));
        //get me all country names where region_id is 2
        List<String> list = jsonPath.getList("items.findAll {it.region_id==2}.country_name");
        System.out.println("list = " + list);


    }
    /*
    * given that accept type is application/json
    * and query param limit is 200
    * when user sends request to /employees
    * then user should see.......
     */
    @DisplayName("Get all /employees?limit=200 with jsonpath")
    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON)
                .and()
                .queryParam("Limit",200)
                .when()
                .get("/employees");
        //assert status code
        assertEquals(200,response.getStatusCode());
        // create jsonpath object
        JsonPath jsonPath = response.jsonPath();
        //get all emails from response
        List<String> allEmails = jsonPath.getList("items.email");
        System.out.println("allEmails = " + allEmails);
        System.out.println("allEmails.size() = " + allEmails.size());
        //get all emails of people who are working as IT-PROG
        List<String> emaislIt = jsonPath.getList("items.findAll {it.job_id=='IT_PROG'}.email");
        System.out.println("emaislIt = " + emaislIt);

        //get me all employees first names whose salary is more than 10000
        List<String> allEmpSalaryMoreThan10k = jsonPath.getList("items.findAll {it.salary > 10000}.first_name");
        System.out.println("allEmpSalaryMoreThan10k = " + allEmpSalaryMoreThan10k);
        //get me all info from response who has max salary
        System.out.println("jsonPath.getString(\"items.max {it.salary}\") = "
                     + jsonPath.getString("items.max {it.salary}"));

        //get me first name from response who has max salary
        System.out.println("jsonPath.getString(\"items.max {it.salary}.first_name\") = "
                     + jsonPath.getString("items.max {it.salary}.first_name"));
        //get me first name from response who has min salary
        System.out.println("jsonPath.getString(\"items.min {it.salary}.first_name\") = "
                + jsonPath.getString("items.min {it.salary}.first_name"));


    }
}
