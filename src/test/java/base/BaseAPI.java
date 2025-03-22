package base;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;

public class BaseAPI {
    protected RequestSpecification spec;

    @BeforeMethod
    public void setup() {
//        RestAssured.baseURI ="https://restful-booker.herokuapp.com";
//        System.out.println("Setting up baseURI:" + RestAssured.baseURI);
         spec = new RequestSpecBuilder()
                .setBaseUri("https://restful-booker.herokuapp.com")
                .build();
    }

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

        Response response = RestAssured.given(spec).contentType(ContentType.JSON)
                .body(body.toString())
                .post("https://restful-booker.herokuapp.com/booking");
        return response;
    }

}
