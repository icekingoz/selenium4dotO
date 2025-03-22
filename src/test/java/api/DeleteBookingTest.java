package api;

import base.BaseAPI;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteBookingTest extends BaseAPI {

    @Test
    public void deleteBookingTest() {
//        Create a new response to use
        Response responseCreate = createBooking();
        responseCreate.print();
        int id = responseCreate.jsonPath().getInt("bookingid");

        Response responseDelete = RestAssured.given(spec)
                .auth()
                .preemptive()
                .basic("admin","password123")
                .delete("/booking/" + id);
        responseDelete.print();
        // Verify response 200
        Assert.assertEquals(responseDelete.getStatusCode(), 201, "Status code should be 201, but it's not");

//        Verify booking is not found
        Response responseGet = RestAssured.given(spec).get("/booking/" + id);
        responseGet.print();
        Assert.assertEquals(responseGet.getBody().asString(), "Not Found", "Body should be 'Not Found' but its not");
    }
}
