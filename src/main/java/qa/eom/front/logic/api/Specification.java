package qa.eom.front.logic.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import qa.eom.front.logic.GetEnv;

public interface Specification extends GetEnv {



    default RequestSpecification getRequestSpecification(String path) {

        return new RequestSpecBuilder()
                .addFilter(new AllureRestAssured())
                .setContentType(ContentType.JSON)
                .setBaseUri("https://uchebnik-stable.opk.su/")
                .setBasePath(path)
                .build()
                ;

    }

    default ResponseSpecification getResponseSpecification() {

        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build()
                ;

    }

    default RestAssuredConfig requestConfig() {

        return RestAssuredConfig.config().httpClient(HttpClientConfig.httpClientConfig()
        .setParam("http.connection.timeout", 3000)
        .setParam("http.socket.timeout", 3000)
        .setParam("http.connection-manager.timeout", 3000));

    }

}
