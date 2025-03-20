package base;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;

public class BaseAPI {




    protected Response createBooking() {
        JSONObject body = new JSONObject();
        body.put("firstname", "James");
        body.put("lastname", "Bond");
        body.put("totalprice", 007);
        body.put("depositpaid", false);

        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", "2018-01-01");
        bookingDates.put("checkout", "2019-01-01");
        //add bookingdates into the body
        body.put("bookingdates", bookingDates);
        //Additional Needs
        body.put("additionalneeds", "Breakfast");

        Response response = RestAssured.given().contentType(ContentType.JSON)
                .body(body.toString())
                .post("https://restful-booker.herokuapp.com/booking");
        return response;
    }

}
