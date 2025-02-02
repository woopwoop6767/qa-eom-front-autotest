package qa.eom.front.logic.api.services;

import qa.eom.front.logic.api.Specification;
import qa.eom.front.logic.pojo.authresponse.ResponseAuth;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public interface Authorization extends Specification {


    default ResponseAuth validAuth(String login, String hashPasswordFirst, String hashPasswordSecond) {

        return given()
                .spec(getRequestSpecification("api/sessions"))
                .header("Accept", "application/json; charset=UTF-8")
                .body(new HashMap() {{
                    put("login", login);
                    put("password_hash", hashPasswordFirst);
                    put("password_hash2", hashPasswordSecond);
                }})
                .post()
                .then()
                .spec(getResponseSpecification())
                .extract().body().as(ResponseAuth.class)
                ;
    }

}
