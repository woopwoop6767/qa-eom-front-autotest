package qa.eom.front.logic;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import qa.eom.front.logic.api.Specification;

public interface ApiActionsForTests extends Specification {


    @Step("Удаление ТЗ учителем")
    default void deleteTaskApi(String authToken, String profileId, String taskId) {
        RestAssured
                .given()
                .header("auth-token", authToken)
                .header("profile-id", profileId)
                .spec(getRequestSpecification("exam/rest/secure/task/".concat(taskId)))
                .delete()
                .then()
                .spec(getResponseSpecification())
                ;
    }

    @Step("Удаление ТС учителем")
    default void deleteSpecApi(String authToken, String profileId, String specId) {
        RestAssured
                .given()
                .header("auth-token", authToken)
                .header("profile-id", profileId)
                .spec(getRequestSpecification("exam/rest/secure/spec/".concat(specId)))
                .delete()
                .then()
                .spec(getResponseSpecification())
                ;
    }
}
