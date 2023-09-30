package net.dirksonline.securiteasetechassessment;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;
import static org.assertj.core.api.Assertions.assertThat;

public class APITest {
    private final String baseURI = "https://reqres.in/api";

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
        assertThat(response.getStatusCode())
                .as("Response Status Code")
                .isEqualTo(SC_OK);

        // Validate the response content type
        assertThat(response.getContentType())
                .as("Response Content Type")
                .contains(ContentType.JSON.toString());

        UserListPage userListPage = response.as(UserListPage.class);

        assertThat(userListPage)
                .as("Response contains a valid userListPage response object")
                .isInstanceOf(UserListPage.class);

        // Validate the response schema (example: user_id, username, email)
        // Example assertion:
        // assertNotNull(response.jsonPath().getString("user_id"), "user_id not found in response");
    }
}