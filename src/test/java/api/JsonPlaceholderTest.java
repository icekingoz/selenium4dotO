package api;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class JsonPlaceholderTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test  //WITH BDD Keywords
    public void testGetPosts() {
        given()
//                .log().all()
                .when()
                .get("/posts")
                .then()
                .log().all()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    // Test to verify that GET /posts/1 returns the expected post data
    @Test
    public void testGetSinglePost() {
        given()
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("userId", notNullValue())
                .body("title", notNullValue());
    }

    // Test to create a new post using POST /posts
    @Test
    public void testCreatePost() {
        String requestBody = "{ \"title\": \"foo\", \"body\": \"bar\", \"userId\": 1 }";

        given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .body("title", equalTo("foo"))
                .body("body", equalTo("bar"))
                .body("userId", equalTo(1));
    }

}
