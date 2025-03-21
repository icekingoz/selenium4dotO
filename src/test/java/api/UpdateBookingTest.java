package api;

import base.BaseAPI;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class UpdateBookingTest extends BaseAPI {

    @Test
    public  void updateBookingTest() {
//        Values:
        String firstName ="Johny";
        String lastName ="Silverhand";
        int totalPrice =150;
        boolean depositPaid =true;
        String checkInDate = "2018-01-01";
        String checkOutDate = "2019-01-01";


//      Create a new response to use
        Response responseCreate = createBooking();
        responseCreate.print();
        int id = responseCreate.jsonPath().getInt("bookingid");
//      Create new JSON Object to update with
        JSONObject body = new JSONObject();
        body.put("firstname", firstName);
        body.put("lastname", lastName);
        body.put("totalprice", totalPrice);
        body.put("depositpaid", depositPaid);

        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", checkInDate);
        bookingDates.put("checkout", checkOutDate);
        //add bookingdates into the body
        body.put("bookingdates", bookingDates);
        //Additional Needs
        body.put("additionalneeds", "Breakfast");

//        curl -X POST \
//        https://restful-booker.herokuapp.com/auth \
//        -H 'Content-Type: application/json' \
//        -d '{
//        "username" : "admin",
//                "password" : "password123"
//    }'
//
        Response responseUpdate = RestAssured.given()
                .auth()
                .preemptive()
                .basic("admin","password123")
                .contentType(ContentType.JSON)
                .body(body.toString())
                .put("/booking/" + id);
        responseUpdate.print();

        // Verify response 200
        Assert.assertEquals(responseUpdate.getStatusCode(), 200, "Status code should be 200, but it's not");

        // Verify All fields
        SoftAssert softAssert = new SoftAssert();
        String actualFirstName = responseUpdate.jsonPath().getString("firstname");
        softAssert.assertEquals(actualFirstName, firstName, "firstname in response is not expected");

        String actualLastName = responseUpdate.jsonPath().getString("lastname");
        softAssert.assertEquals(actualLastName, lastName, "lastname in response is not expected");

        int price = responseUpdate.jsonPath().getInt("totalprice");
        softAssert.assertEquals(price, totalPrice, "totalprice in response is not expected");

        boolean depositpaid = responseUpdate.jsonPath().getBoolean("depositpaid");
        softAssert.assertTrue(depositpaid, "depositpaid should be true, but it's not");

        String actualCheckin = responseUpdate.jsonPath().getString("bookingdates.checkin");
        softAssert.assertEquals(actualCheckin, checkInDate, "checkin in response is not expected");

        String actualCheckout = responseUpdate.jsonPath().getString("bookingdates.checkout");
        softAssert.assertEquals(actualCheckout, checkOutDate, "checkout in response is not expected");

        String actualAdditionalneeds = responseUpdate.jsonPath().getString("additionalneeds");
        softAssert.assertEquals(actualAdditionalneeds, "Breakfast", "additionalneeds in response is not expected");

        softAssert.assertAll();


    }





}
