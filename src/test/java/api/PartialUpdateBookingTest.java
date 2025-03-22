package api;

import base.BaseAPI;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;


public class PartialUpdateBookingTest extends BaseAPI {

    @Test
    public void partialUpdateBookingTest() {
        String newName = "William";
        String newCheckin = "2020-03-02";
        String newCheckout = "2020-03-03";

//        Create a new response to use
        Response responseCreate = createBooking();
        responseCreate.print();
        int id = responseCreate.jsonPath().getInt("bookingid");

//        New Body
        JSONObject body = new JSONObject();
        body.put("firstname", newName);
        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", newCheckin);
        bookingDates.put("checkout", newCheckout);
        body.put("bookingdates",bookingDates);

//        New Response
        Response responsePartialUpdate = RestAssured.given(spec)
                .auth()
                .preemptive()
                .basic("admin","password123")
                .contentType(ContentType.JSON)
                .body(body.toString())
                .patch("/booking/" + id);
        responsePartialUpdate.print();

        // Verify response 200
        Assert.assertEquals(responsePartialUpdate.getStatusCode(), 200, "Status code should be 200, but it's not");

        Assert.assertEquals(responsePartialUpdate.jsonPath().getString("firstname"),newName);
        Assert.assertEquals(responsePartialUpdate.jsonPath().getString("bookingdates.checkin"),newCheckin);

    }

}
