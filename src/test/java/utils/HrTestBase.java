package utils;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class HrTestBase {
    @BeforeAll
    //MyIpAddress:1000/ords/hr
    public static void init(){
        RestAssured.baseURI = "http://44.201.221.73:1000/ords/hr";
    }

}
