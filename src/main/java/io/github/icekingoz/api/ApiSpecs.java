package io.github.icekingoz.api;

import io.github.icekingoz.config.ConfigReader;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

/**
 * Reusable REST Assured specs. Every API test starts from the same base
 * request (base URI, JSON, Allure logging) and asserts the same base response,
 * so individual tests stay tiny and consistent.
 */
public final class ApiSpecs {

    private ApiSpecs() { }

    public static RequestSpecification request() {
        return new RequestSpecBuilder()
                .setBaseUri(ConfigReader.get("api.base.url"))
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .addFilter(new AllureRestAssured())   // request/response captured in Allure
                .build();
    }

    public static ResponseSpecification okJson() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
    }
}
