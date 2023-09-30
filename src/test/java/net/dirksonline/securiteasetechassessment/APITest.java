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

    @Test(description = "Fetch first page of list of users, and it contains a user George")
    public void testGetUsers() {
        Response response = given()
                .when()
                .get("/users")
                .then()
                .extract().response();

        // Verify the response status code
        assertThat(response.getStatusCode())
                .as("Response Status Code")
                .isEqualTo(SC_OK);

        // Verify the response content type
        assertThat(response.getContentType())
                .as("Response Content Type")
                .contains(ContentType.JSON.toString());

        // Verify the response schema
        UserListPage userListPage = response.as(UserListPage.class);
        assertThat(userListPage)
                .as("Response contains a valid userListPage response object")
                .isInstanceOf(UserListPage.class);

        // Verify the "George" record is returned
        UserListPage.User user = UserListPage.User.builder()
                .id(1)
                .first_name("George")
                .last_name("Bluth")
                .email("george.bluth@reqres.in")
                .avatar("https://reqres.in/img/faces/1-image.jpg")
                .build();

        assertThat(userListPage.data.stream()
                .filter( u -> u.first_name.equals("George"))
                .findAny()
                .orElseThrow()
        )
                .as("Response contains George user")
                .isEqualTo(user);

        // Example assertion:
        // assertNotNull(response.jsonPath().getString("user_id"), "user_id not found in response");
    }
}