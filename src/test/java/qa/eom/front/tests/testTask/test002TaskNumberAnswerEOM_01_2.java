package qa.eom.front.tests.testTask;

import org.testng.annotations.*;
import qa.eom.front.logic.ApiActionsForTests;
import qa.eom.front.logic.GenerateText;
import qa.eom.front.logic.UserCredentials;
import qa.eom.front.logic.api.services.Authorization;
import qa.eom.front.logic.driver.CookiesHandler;
import qa.eom.front.logic.driver.DesktopDriver;
import qa.eom.front.logic.dto.TaskFillData;
import qa.eom.front.logic.pages.TaskPreviewPage;
import qa.eom.front.logic.pages.tasksAnswerTypes.NumberAnswer;
import qa.eom.front.logic.pojo.authresponse.ResponseAuth;


public class test002TaskNumberAnswerEOM_01_2 implements DesktopDriver, Authorization, CookiesHandler, GenerateText, ApiActionsForTests {

    private ResponseAuth responseAuth;
    private NumberAnswer numberAnswer;
    private TaskPreviewPage taskPreviewPage;
    private TaskFillData taskFillData;
    private String idOfCreatedTask;


    @BeforeClass
    void authorizationApi() {

        responseAuth = validAuth(UserCredentials.TEACHER_ASTAHOVA.getLogin(),
                UserCredentials.TEACHER_ASTAHOVA.getHashPasswordOne(),
                UserCredentials.TEACHER_ASTAHOVA.getHashPasswordTwo());

    }

    @BeforeMethod
    void setUp() {

        setAuthorizationCookies(responseAuth);
        numberAnswer = new NumberAnswer();
        taskPreviewPage = new TaskPreviewPage();
        taskFillData = new TaskFillData("MegabobaTask_" + generateRandomNumber(5),
                4, "Биология", "Бактерии. Грибы и лишайники", "Лишайники");

    }

    @Test
    void test() {

        numberAnswer
                .openTaskConstructorPage()
                .clickAnswerFormSelector()
                .clickAnswerFormBtnInSelector("Ввод числа")
                .enterSymbolsToQuestionField("a question...");
        numberAnswer
                .enterSymbolsToAnswerFieldInput("123.1")
                .clickPreviewTaskBtn()
                ;

        taskPreviewPage
                .enterSymbolsToAnswerFieldInput("123")
                .clickAnswerBtn()
                .checkAnswerIsWrongMsgIsVisible()
                .enterSymbolsToAnswerFieldInput("123.1")
                .clickAnswerBtn()
                .checkAnswerIsCorrectMsgIsVisible()
                .clickGoToEditBtn()
                ;

        numberAnswer
                .clickGoToSettingsBtn()
                .fillAllSettingsFiedsAndSaveTask(taskFillData)
                ;

        idOfCreatedTask = numberAnswer.getIdOfCreatedTask();

    }

    @AfterClass
    void afterClass() {
        deleteTaskApi(responseAuth.getAuthenticationToken(),
                responseAuth.getProfiles().stream().findFirst().get().getId(),
                idOfCreatedTask);
    }

}
