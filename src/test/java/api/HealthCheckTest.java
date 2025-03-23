package api;

import base.BaseAPI;
import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HealthCheckTest extends BaseAPI {

    @Test
    public void healthCheck() {
        Response response = RestAssured.given(spec).get("/ping");
//        response.print();
        Assert.assertEquals(response.getStatusCode(), 201, "Status code is NOT 201 like expected");
        System.out.println("Health check passed");
    }

    @Test
    public void headersCookies() {
//        Added headers before the response
        Header someHeader = new Header("some_value", "Some_value");
        spec.header(someHeader);

//       Added cookies before the response
        Cookie someCookie = new Cookie.Builder("some_cookie", "some_cookie_value").build();
        spec.cookie(someCookie);

//        Added headers and cookies with the response and logged it all
        Response response = RestAssured.given(spec)
                .cookies("TestCookie", "TestValue")
                .header("TestHeader", "headerValue")
                .log().all()
                .get("/ping");
//       Get Headers
        Headers headers = response.getHeaders();
        System.out.println("Headers: " + headers);

//        Store as header
        Header serverHeader1 = headers.get("Server");
        System.out.println(serverHeader1.getName() + ": " + serverHeader1.getValue());

//       Store as String
        String serverHeader2 = response.getHeader("Server");
        System.out.println("Server: " + serverHeader2);

//       Get Cookies
        Cookies cookies = response.getDetailedCookies();
        System.out.println("Cookies: " + cookies);

    }

}
