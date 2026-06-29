package io.github.icekingoz.tests.api;

import io.github.icekingoz.api.ApiSpecs;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsonschema.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

@Epic("API")
@Feature("HTTP Methods (httpbin.org)")
public class HttpBinTest {

    @Test(groups = "smoke")
    @Severity(SeverityLevel.BLOCKER)
    @Description("GET /get returns 200 and a body that matches the published JSON schema")
    public void getReturnsValidSchema() {
        given().spec(ApiSpecs.request()).queryParam("team", "qa")
        .when().get("/get")
        .then().spec(ApiSpecs.okJson())
               .body("args.team", equalTo("qa"))
               .body(matchesJsonSchemaInClasspath("schemas/httpbin-get-schema.json"));
    }

    @Test(groups = "regression")
    @Severity(SeverityLevel.CRITICAL)
    @Description("POST /post echoes the JSON body back under the 'json' field")
    public void postEchoesJsonBody() {
        String payload = "{\"name\":\"Ozzy\",\"role\":\"SDET\"}";
        given().spec(ApiSpecs.request()).body(payload)
        .when().post("/post")
        .then().spec(ApiSpecs.okJson())
               .body("json.name", equalTo("Ozzy"))
               .body("json.role", equalTo("SDET"));
    }

    @Test(groups = "regression")
    @Severity(SeverityLevel.NORMAL)
    @Description("Custom request headers are reflected by /headers")
    public void customHeaderIsReflected() {
        given().spec(ApiSpecs.request()).header("X-Run-Id", "build-42")
        .when().get("/headers")
        .then().spec(ApiSpecs.okJson())
               .body("headers.X-Run-Id", equalTo("build-42"));
    }

    @Test(groups = "regression")
    @Severity(SeverityLevel.MINOR)
    @Description("DELETE /delete returns 200")
    public void deleteReturnsOk() {
        given().spec(ApiSpecs.request())
        .when().delete("/delete")
        .then().statusCode(200);
    }
}
