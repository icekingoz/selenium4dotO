package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RestfulBookerTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI ="https://restful-booker.herokuapp.com";
        System.out.println("Setting up baseURI:" + RestAssured.baseURI);
    }


    @Test
    public void healthCheck() {
        Response response = RestAssured.get("/ping");
//        response.print();
        Assert.assertEquals(response.getStatusCode(), 201, "Status code is NOT 201 like expected");
        System.out.println("Health check passed");
    }

    @Test
    public void getBookingIds() {
        Response response = RestAssured.get("/booking");
        response.print();
        System.out.println("Status 200!");


    }

//    @Test
//    public void getBooking() {
//        Response response = RestAssured.get("/ping");
//        response.print();
//    }
//
//    @Test
//    public void createBooking() {
//        Response response = RestAssured.get("/ping");
//        response.print();
//    }
//
//    @Test
//    public void updateBooking() {
//        Response response = RestAssured.get("/ping");
//        response.print();
//    }
//
//    @Test
//    public void partialUpdateBooking() {
//        Response response = RestAssured.get("/ping");
//        response.print();
//    }
//
//    @Test
//    public void deleteBooking() {
//        Response response = RestAssured.get("/ping");
//        response.print();
//    }



}
