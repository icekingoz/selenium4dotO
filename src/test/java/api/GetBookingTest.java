package api;

import base.BaseAPI;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class GetBookingTest extends BaseAPI {

    @Test
    public void getBooking() {
//        Variables to use
        String firstName = "Sally";
        String lastName = "Jackson";
        int totalPrice = 484;
        boolean depositPaid = false;
        String checkin = "2023-12-14";
        String checkout = "2024-01-10";
        String additionalNeeds = "Breakfast";

//        Create a new response to use
        Response responseCreate = createBooking(firstName,lastName, totalPrice, depositPaid, checkin, checkout, additionalNeeds);
        responseCreate.print();
        int id = responseCreate.jsonPath().getInt("bookingid");
//        Set the ID as a pathParam
        spec.pathParam("bookingID", id);

        Response response = RestAssured.given(spec).get("/booking/{bookingID}");
        response.print();

//        Assert.assertEquals(response.jsonPath().getString("firstname"), firstName);
//        System.out.println("firstName is "+ firstName);
//        Assert.assertEquals(response.jsonPath().getString("lastname"), lastName);
//        System.out.println("lastname is "+ lastName);
//        Assert.assertEquals(response.jsonPath().getInt("totalprice"), totalPrice);
//        System.out.println("totalPrice is "+ totalPrice);
//        Assert.assertEquals(response.jsonPath().getBoolean("depositpaid"), depositPaid);
//        System.out.println("depositpaid is "+ depositPaid);
//
//        Assert.assertEquals(response.jsonPath().getString("bookingdates.checkin"), checkin);
//        System.out.println("checkin is "+ checkin);
//        Assert.assertEquals(response.jsonPath().getBoolean("bookingdates.checkout"), checkout);
//        System.out.println("checkout is "+ checkout);

        // Verify response 200
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200, but it's not");

        // Verify All fields
        SoftAssert softAssert = new SoftAssert();
        String actualFirstName = response.jsonPath().getString("firstname");
        softAssert.assertEquals(actualFirstName, firstName, "firstname in response is not expected");

        String actualLastName = response.jsonPath().getString("lastname");
        softAssert.assertEquals(actualLastName, lastName, "lastname in response is not expected");

        int price = response.jsonPath().getInt("totalprice");
        softAssert.assertEquals(price, totalPrice, "totalprice in response is not expected");

        boolean actualDepositpaid = response.jsonPath().getBoolean("depositpaid");
        if (depositPaid) {
            softAssert.assertTrue(actualDepositpaid, "depositpaid should be true, but it's not");
        } else {
            softAssert.assertFalse(actualDepositpaid, "depositpaid should be false, but it's not");
        }

        String actualCheckin = response.jsonPath().getString("bookingdates.checkin");
        softAssert.assertEquals(actualCheckin, checkin, "checkin in response is not expected");

        String actualCheckout = response.jsonPath().getString("bookingdates.checkout");
        softAssert.assertEquals(actualCheckout, checkout, "checkout in response is not expected");

        String actualAdditionalneeds = response.jsonPath().getString("additionalneeds");
        softAssert.assertEquals(actualAdditionalneeds, additionalNeeds, "additionalneeds in response is not expected");

        softAssert.assertAll();

    }
}
