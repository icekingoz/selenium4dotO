package api;

import base.BaseAPI;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GetBookingIdsTest extends BaseAPI {

    @Test
    public void getBookingIds() {
        Response response = RestAssured.given(spec).get("/booking");

        Assert.assertEquals(response.getStatusCode(), 200, "Status is NOT 200");
        System.out.println("Status code is 200!");

        List<Integer> bookingIds = response.jsonPath().getList("bookingid");
        Assert.assertFalse(bookingIds.isEmpty(), "List of booking Ids is EMPTY");
        System.out.println("List of Booking Ids contains at least one ID");

    }

    @Test
    public void getBookingIdsWithFilters() {
//        add Query Param
        spec.queryParam("firstname", "Susan");

        Response response = RestAssured.given(spec).get("/booking?firstname=Susan");
        response.print();

        Assert.assertEquals(response.getStatusCode(), 200, "Status is NOT 200");
        System.out.println("Status code is 200!");

        List<Integer> bookingIds = response.jsonPath().getList("bookingid");
        Assert.assertFalse(bookingIds.isEmpty(), "List of booking Ids is EMPTY");
        System.out.println("List of Booking Ids contains at least one ID");

    }

}
