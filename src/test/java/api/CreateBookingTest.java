package api;

import base.BaseAPI;
import base.Booking;
import base.BookingDates;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreateBookingTest extends BaseAPI {
//    curl -X POST \
//    https://restful-booker.herokuapp.com/booking \
//            -H 'Content-Type: application/json' \
//            -d '{
//            "firstname" : "Jim",
//            "lastname" : "Brown",
//            "totalprice" : 111,
//            "depositpaid" : true,
//            "bookingdates" : {
//        "checkin" : "2018-01-01",
//                "checkout" : "2019-01-01"
//    },
//            "additionalneeds" : "Breakfast"
//}'

    @Test
    public void createBookingTest() {
        Response response = createBooking();
        response.print();

        // Verifications
        // Verify response 200
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200, but it's not");

        // Verify All fields
        SoftAssert softAssert = new SoftAssert();
        String actualFirstName = response.jsonPath().getString("booking.firstname");
        softAssert.assertEquals(actualFirstName, "Dmitry", "firstname in response is not expected");

        String actualLastName = response.jsonPath().getString("booking.lastname");
        softAssert.assertEquals(actualLastName, "Shyshkin", "lastname in response is not expected");

        int price = response.jsonPath().getInt("booking.totalprice");
        softAssert.assertEquals(price, 150, "totalprice in response is not expected");

        boolean depositpaid = response.jsonPath().getBoolean("booking.depositpaid");
        softAssert.assertFalse(depositpaid, "depositpaid should be false, but it's not");

        String actualCheckin = response.jsonPath().getString("booking.bookingdates.checkin");
        softAssert.assertEquals(actualCheckin, "2020-03-25", "checkin in response is not expected");

        String actualCheckout = response.jsonPath().getString("booking.bookingdates.checkout");
        softAssert.assertEquals(actualCheckout, "2020-03-27", "checkout in response is not expected");

        String actualAdditionalneeds = response.jsonPath().getString("booking.additionalneeds");
        softAssert.assertEquals(actualAdditionalneeds, "Baby crib", "additionalneeds in response is not expected");

        softAssert.assertAll();

    }

    @Test
    public void createBookingWithPOJOTest() {
        //        Variables to use
        String firstName = "Sally";
        String lastName = "Jackson";
        int totalPrice = 484;
        boolean depositPaid = false;
        String checkin = "2023-12-14";
        String checkout = "2024-01-10";
        String additionalNeeds = "Breakfast";

//        Create new Body with POJOs
        BookingDates bookingdates = new BookingDates(checkin, checkout);
        Booking booking = new Booking(firstName, lastName, totalPrice, depositPaid,bookingdates, additionalNeeds );

        Response response = RestAssured.given(spec).contentType(ContentType.JSON).body(booking)
                        .post("/booking");

        response.print();

        // Verifications
        // Verify response 200
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200, but it's not");

        // Verify All fields
        SoftAssert softAssert = new SoftAssert();
        String actualFirstName = response.jsonPath().getString("booking.firstname");
        softAssert.assertEquals(actualFirstName, "Dmitry", "firstname in response is not expected");

        String actualLastName = response.jsonPath().getString("booking.lastname");
        softAssert.assertEquals(actualLastName, "Shyshkin", "lastname in response is not expected");

        int price = response.jsonPath().getInt("booking.totalprice");
        softAssert.assertEquals(price, 150, "totalprice in response is not expected");

        boolean depositpaid = response.jsonPath().getBoolean("booking.depositpaid");
        softAssert.assertFalse(depositpaid, "depositpaid should be false, but it's not");

        String actualCheckin = response.jsonPath().getString("booking.bookingdates.checkin");
        softAssert.assertEquals(actualCheckin, "2020-03-25", "checkin in response is not expected");

        String actualCheckout = response.jsonPath().getString("booking.bookingdates.checkout");
        softAssert.assertEquals(actualCheckout, "2020-03-27", "checkout in response is not expected");

        String actualAdditionalneeds = response.jsonPath().getString("booking.additionalneeds");
        softAssert.assertEquals(actualAdditionalneeds, "Baby crib", "additionalneeds in response is not expected");

        softAssert.assertAll();

    }

}
