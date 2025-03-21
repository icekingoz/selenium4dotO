package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class GetBookingTest {

    @Test
    public void getBooking() {
        Response response = RestAssured.get("/booking/4");
        response.print();
        String firstName = "Sally";
        String lastName = "Jackson";
        int totalPrice = 484;
        boolean depositPaid = false;
        String checkin = "2023-12-14";
        String checkout = "2024-01-10";

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
        softAssert.assertEquals(actualFirstName, "Sally", "firstname in response is not expected");

        String actualLastName = response.jsonPath().getString("lastname");
        softAssert.assertEquals(actualLastName, "Ericsson", "lastname in response is not expected");

        int price = response.jsonPath().getInt("totalprice");
        softAssert.assertEquals(price, 753, "totalprice in response is not expected");

        boolean depositpaid = response.jsonPath().getBoolean("depositpaid");
        softAssert.assertTrue(depositpaid, "depositpaid should be true, but it's not");

        String actualCheckin = response.jsonPath().getString("bookingdates.checkin");
        softAssert.assertEquals(actualCheckin, "2016-02-06", "checkin in response is not expected");

        String actualCheckout = response.jsonPath().getString("bookingdates.checkout");
        softAssert.assertEquals(actualCheckout, "2016-09-27", "checkout in response is not expected");

        String actualAdditionalneeds = response.jsonPath().getString("additionalneeds");
        softAssert.assertEquals(actualAdditionalneeds, "Breakfast", "additionalneeds in response is not expected");

        softAssert.assertAll();

    }
}
