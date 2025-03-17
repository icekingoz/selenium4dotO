package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class GetBookingIdsTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI ="https://restful-booker.herokuapp.com";
        System.out.println("Setting up baseURI:" + RestAssured.baseURI);
    }

    @Test
    public void getBookingIds() {
        Response response = RestAssured.get("/booking");

        Assert.assertEquals(response.getStatusCode(), 200, "Status is NOT 200");
        System.out.println("Status code is 200!");

        List<Integer> bookingIds = response.jsonPath().getList("bookingid");
        Assert.assertFalse(bookingIds.isEmpty(), "List of booking Ids is EMPTY");
        System.out.println("List of Booking Ids contains at least one ID");

    }

}
