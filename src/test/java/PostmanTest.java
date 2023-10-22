import Specifications.Spec;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;

public class PostmanTest {
    @BeforeEach
    public void setUp() {
        RestAssured.requestSpecification = Spec.request();
        RestAssured.responseSpecification = Spec.response();
    }

    @Test
    @DisplayName("Postman Echo Method GET")
    public void getTest() {
        given()
                .when().get("/get?Language=en")
                .then()
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.content-type", equalTo("application/json"))
                .body("headers.accept", equalTo("application/json, " +
                        "application/javascript, text/javascript, text/json"))
                .body("headers.accept-encoding", equalTo("gzip,deflate"))
                .body("url", equalTo("https://postman-echo.com/get?Language=en"));
    }

    @Test
    @DisplayName("Postman Echo Method POST")
    public void postTest() {
        given()
                .when().post("/post")
                .then()
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.content-type", equalTo("application/json"))
                .body("headers.accept", equalTo("application/json, " +
                        "application/javascript, text/javascript, text/json"))
                .body("headers.accept-encoding", equalTo("gzip,deflate"))
                .body("url", equalTo("https://postman-echo.com/post"));
    }

    @Test
    @DisplayName("Postman Echo Method PUT")
    public void putTest() {
        given()
                .when().put("/put")
                .then()
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.content-type", equalTo("application/json"))
                .body("headers.accept", equalTo("application/json, " +
                        "application/javascript, text/javascript, text/json"))
                .body("headers.accept-encoding", equalTo("gzip,deflate"))
                .body("url", equalTo("https://postman-echo.com/put"));
    }

    @Test
    @DisplayName("Postman Echo Method DELETE")
    public void deleteTest() {
        given()
                .when().delete("/delete")
                .then()
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.content-type", equalTo("application/json"))
                .body("headers.accept", equalTo("application/json, " +
                        "application/javascript, text/javascript, text/json"))
                .body("headers.accept-encoding", equalTo("gzip,deflate"))
                .body("url", equalTo("https://postman-echo.com/delete"));
    }

    @Test
    @DisplayName("Postman Echo Method PATCH")
    public void patchTest() {
        given()
                .when().patch("/patch")
                .then()
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.content-type", equalTo("application/json"))
                .body("headers.accept", equalTo("application/json, " +
                        "application/javascript, text/javascript, text/json"))
                .body("headers.accept-encoding", equalTo("gzip,deflate"))
                .body("url", equalTo("https://postman-echo.com/patch"));
    }
}
