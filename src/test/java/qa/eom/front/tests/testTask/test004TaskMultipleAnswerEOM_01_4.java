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
import qa.eom.front.logic.pages.tasksAnswerTypes.MultipleAnswer;
import qa.eom.front.logic.pojo.authresponse.ResponseAuth;

public class test004TaskMultipleAnswerEOM_01_4 implements DesktopDriver, Authorization, CookiesHandler, GenerateText, ApiActionsForTests {

    private ResponseAuth responseAuth;
    private MultipleAnswer multipleAnswer;
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
        multipleAnswer = new MultipleAnswer();
        taskPreviewPage = new TaskPreviewPage();
        taskFillData = new TaskFillData("AutoMegabobaTask_" + generateRandomNumber(5),
                4, "Биология", "Бактерии. Грибы и лишайники", "Лишайники");

    }

    @Test
    void test() {

        multipleAnswer
                .openTaskConstructorPage()
                .clickAnswerFormSelector()
                .clickAnswerFormBtnInSelector("Выбор нескольких вариантов ответа")
                .enterSymbolsToQuestionField("a question...")
                ;
        multipleAnswer
                .clickAddAnswerBtn()
                .enterSymbolsToAnswerFieldInput(0, "Правильный ответ1")
                .enterSymbolsToAnswerFieldInput(1, "Правильный ответ2")
                .clickAddDistractorBtn()
                .clickAddDistractorBtn()
                .enterSymbolsToDistractorFieldInput(0, "Дистрактор1")
                .enterSymbolsToDistractorFieldInput(1, "Дистрактор2")
                .clickPreviewTaskBtn()
                ;

        taskPreviewPage
                .checkMultipleAnswerOptionsRandom()
                .clickMultipleAnswerOptionBtn("Правильный ответ1")
                .clickMultipleAnswerOptionBtn("Дистрактор1")
                .clickAnswerBtn()
                .checkAnswerIsWrongMsgIsVisible()
                .clickMultipleAnswerOptionBtn("Дистрактор1")
                .clickMultipleAnswerOptionBtn("Правильный ответ2")
                .checkAnswerIsCorrectMsgIsVisible()
                .clickGoToEditBtn()
                ;

        multipleAnswer
                .clickGoToSettingsBtn()
                .fillAllSettingsFiedsAndSaveTask(taskFillData)
                ;

        idOfCreatedTask = multipleAnswer.getIdOfCreatedTask();

    }

    @AfterMethod
    void afterMethod() {
        deleteTaskApi(responseAuth.getAuthenticationToken(),
                responseAuth.getProfiles().stream().findFirst().get().getId(),
                idOfCreatedTask);
    }
}
