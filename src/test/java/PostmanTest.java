import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class PostmanTest {
    RequestSpecification requestSpec;
    @BeforeEach
    public void setUp() {
        final String URL = "https://postman-echo.com";
        RequestSpecification requestSpec = given()
                .baseUri(URL).header("Language", "en");

    }

    @Test
    public void getTest() {
        given()
                .when().get("https://postman-echo.com/get?Language=en")
                .then().log().body()
                .contentType("application/json")
                .statusCode(HttpStatus.SC_OK).and()
                .body("args.Language", equalTo("en"))
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.content-length", notNullValue())
                .body("url", equalTo("https://postman-echo.com/get?Language=en"));
    }
}
