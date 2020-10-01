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
import qa.eom.front.logic.pages.tasksAnswerTypes.SingleAnswer;
import qa.eom.front.logic.pojo.authresponse.ResponseAuth;

public class test005TaskSingleAnswerEOM_01_5 implements DesktopDriver, Authorization, CookiesHandler, GenerateText, ApiActionsForTests {


    private ResponseAuth responseAuth;
    private SingleAnswer singleAnswer;
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
        singleAnswer = new SingleAnswer();
        taskPreviewPage = new TaskPreviewPage();
        taskFillData = new TaskFillData("AutoMegabobaTask_" + generateRandomNumber(5),
                4, "Биология", "Бактерии. Грибы и лишайники", "Лишайники");

    }

    @Test
    void test() {

        singleAnswer
                .openTaskConstructorPage()
                .clickAnswerFormSelector()
                .clickAnswerFormBtnInSelector("Выбор одного ответа")
                .enterSymbolsToQuestionField("a question...")
                ;
        singleAnswer
                .enterSymbolsToTaskFieldInput("Правильный ответ")
                .clickAddDistractorBtn()
                .enterSymbolsToDistractorFieldInput(0, "Дистрактор1")
                .clickPreviewTaskBtn()
                ;

        taskPreviewPage
                .checkSingleAnswerOptionsRandom()
                .clickSingleAnswerOptionBtn("Дистрактор1")
                .clickAnswerBtn()
                .checkAnswerIsWrongMsgIsVisible()
                .clickSingleAnswerOptionBtn("Правильный ответ")
                .clickAnswerBtn()
                .checkAnswerIsCorrectMsgIsVisible()
                .clickGoToEditBtn()
                ;

        singleAnswer
                .clickGoToSettingsBtn()
                .fillAllSettingsFiedsAndSaveTask(taskFillData)
                ;

        idOfCreatedTask = singleAnswer.getIdOfCreatedTask();

    }

    @AfterMethod
    void afterMethod() {
        deleteTaskApi(responseAuth.getAuthenticationToken(),
                responseAuth.getProfiles().stream().findFirst().get().getId(),
                idOfCreatedTask);
    }


}
