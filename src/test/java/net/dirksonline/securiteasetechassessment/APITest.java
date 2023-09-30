package net.dirksonline.securiteasetechassessment;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass; import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
public class APITest {
    private String baseURI = "<http://api.example.com";
    @BeforeClass
    public void setup() {
        RestAssured.baseURI = baseURI;
    }
    @Test
    public void testGetUsers() {
        Response response = given()
                .when()
                .get("/users")
                .then()
                .extract().response();
        // Validate the response status code
        assertEquals("Unexpected status code", 200, response.getStatusCode());

        // Validate the response content type
        assertEquals("Unexpected content type", ContentType.JSON.toString(), response.getContentType());
        // Validate the response schema (example: user_id, username, email)
        // Example assertion:
        // assertNotNull(response.jsonPath().getString("user_id"), "user_id not found in response");
    }
}