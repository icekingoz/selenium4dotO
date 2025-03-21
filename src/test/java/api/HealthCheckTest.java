package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HealthCheckTest {

    @Test
    public void healthCheck() {
        Response response = RestAssured.get("/ping");
//        response.print();
        Assert.assertEquals(response.getStatusCode(), 201, "Status code is NOT 201 like expected");
        System.out.println("Health check passed");
    }

}
