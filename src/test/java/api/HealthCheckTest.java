package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class HealthCheckTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI ="https://restful-booker.herokuapp.com";
        System.out.println("Setting up baseURI:" + RestAssured.baseURI);
    }

    @Test
    public void healthCheck() {
        Response response = RestAssured.get("/ping");
//        response.print();
        Assert.assertEquals(response.getStatusCode(), 201, "Status code is NOT 201 like expected");
        System.out.println("Health check passed");
    }

}
