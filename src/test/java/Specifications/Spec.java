/*
 * В данном файле определены команды, возвращающие необходимые для теста
 * RequestSpecification и ResponseSpecification
 */
package Specifications;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;

public class Spec {
    private static final String URL = "https://postman-echo.com";

    public static RequestSpecification request() {
        return new RequestSpecBuilder()
                .setBaseUri(URL)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification response() {
        return new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .expectStatusCode(HttpStatus.SC_OK)
                .build();
    }
}
