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
    /**
     * Set the prefix for all API calls. It's a private property in the APITest class.
     * It is not accessible in other classes, and can not be configured at run time for different environments.
     * It would be best if it was moved to an external property file, and accessed through a shared configuration object.
     */
    private final String baseURI = "https://reqres.in/api";

    /** Setup RestAssured, just the baseURI for now */
    @BeforeClass
    public void setup() {
        RestAssured.baseURI = baseURI;
    }

    /**
     * A testng scenario annotated with a description
     */
    @Test(description = "Fetch first page of list of users, and it contains a user George")
    public void testGetUsers() {
        Response response =
                given()                       // a place to setup initial conditions for the REST call
                .when().get("/users")         // the action to perform, eg. GET the /users endpoint
                .then().extract().response(); // fetches the response object

        // Verify the response status code
        assertThat(response.getStatusCode())
                .as("Response Status Code")
                .isEqualTo(SC_OK);

        // Verify the response content type
        assertThat(response.getContentType())
                .as("Response Content Type")
                .contains(ContentType.JSON.toString());

        // Verify the response schema using the UserListPage pojo
        UserListPage userListPage = response.as(UserListPage.class);
        assertThat(userListPage)
                .as("Response contains a valid userListPage response object")
                .isInstanceOf(UserListPage.class);

        // build a User object for the expected George user
        UserListPage.User user = UserListPage.User.builder()
                .id(1)
                .first_name("George")
                .last_name("Bluth")
                .email("george.bluth@reqres.in")
                .avatar("https://reqres.in/img/faces/1-image.jpg")
                .build();

        // Verify the "George" record is returned, and has the expected values
        assertThat(userListPage.data.stream()
                .filter( u -> u.first_name.equals("George"))
                .findAny()
                .orElseThrow()
        )
                .as("Response contains George user")
                .isEqualTo(user);
    }

    @Test(description = "Fetch second page of list of users, and it contains another user George")
    public void testGetUsersPage2() {
        Response response =
                given().param("page",2)
                .when().get("/users")
                .then().extract().response();

        assertThat(response.getStatusCode())
                .as("Response Status Code")
                .isEqualTo(SC_OK);

        assertThat(response.getContentType())
                .as("Response Content Type")
                .contains(ContentType.JSON.toString());

        UserListPage userListPage = response.as(UserListPage.class);
        assertThat(userListPage)
                .as("Response contains a valid userListPage response object")
                .isInstanceOf(UserListPage.class);

        UserListPage.User user = UserListPage.User.builder()
                .id(11)
                .first_name("George")
                .last_name("Edwards")
                .email("george.edwards@reqres.in")
                .avatar("https://reqres.in/img/faces/11-image.jpg")
                .build();

        assertThat(userListPage.data.stream()
                .filter( u -> u.first_name.equals("George"))
                .findAny()
                .orElseThrow()
        )
                .as("Second page response contains another George user")
                .isEqualTo(user);
    }
}