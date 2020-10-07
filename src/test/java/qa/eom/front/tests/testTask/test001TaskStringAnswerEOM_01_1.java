package qa.eom.front.tests.testTask;


import org.testng.annotations.*;
import qa.eom.front.logic.ApiActionsForTests;
import qa.eom.front.logic.GenerateText;
import qa.eom.front.logic.api.services.Authorization;
import qa.eom.front.logic.driver.CookiesHandler;
import qa.eom.front.logic.driver.DesktopDriver;
import qa.eom.front.logic.UserCredentials;
import qa.eom.front.logic.dto.TaskFillData;
import qa.eom.front.logic.pages.TaskPreviewPage;
import qa.eom.front.logic.pages.tasksAnswerTypes.StringAnswer;
import qa.eom.front.logic.pojo.authresponse.ResponseAuth;


public class test001TaskStringAnswerEOM_01_1 implements DesktopDriver, Authorization, CookiesHandler, GenerateText, ApiActionsForTests {


    private ResponseAuth responseAuth;
    private StringAnswer stringAnswer;
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
        stringAnswer = new StringAnswer();
        taskPreviewPage = new TaskPreviewPage();
        taskFillData = new TaskFillData("AutoMegabobaTask_" + generateRandomNumber(5),
                4, "Биология", "Бактерии. Грибы и лишайники", "Лишайники");

    }

    @Test
    void test() {

        stringAnswer
                .openTaskConstructorPage()
                .clickAnswerFormSelector()
                .clickAnswerFormBtnInSelector("Ввод строки")
                .enterSymbolsToQuestionField("a question...");
        stringAnswer
                .enterSymbolsToTaskFieldInput("An answer...")
                .clickPreviewTaskBtn()
                ;

        taskPreviewPage
                .enterSymbolsToAnswerFieldInput("a false answer...")
                .clickAnswerBtn()
                .checkAnswerIsWrongMsgIsVisible()
                .enterSymbolsToAnswerFieldInput("an answer...")
                .clickAnswerBtn()
                .checkAnswerIsCorrectMsgIsVisible()
                .clickGoToEditBtn()
                ;

        stringAnswer
                .clickAnswerIsCaseSensitiveCheckbox()
                .clickPreviewTaskBtn()
                ;

        taskPreviewPage
                .enterSymbolsToAnswerFieldInput("an answer...")
                .clickAnswerBtn()
                .checkAnswerIsWrongMsgIsVisible()
                .enterSymbolsToAnswerFieldInput("An answer...")
                .clickAnswerBtn()
                .checkAnswerIsCorrectMsgIsVisible()
                .clickGoToEditBtn()
                ;

        stringAnswer
                .clickGoToSettingsBtn()
                .fillAllSettingsFiedsAndSaveTask(taskFillData)
                ;

        idOfCreatedTask = stringAnswer.getIdOfCreatedTask();

    }

    @AfterMethod
    void afterMethod() {
        deleteTaskApi(responseAuth.getAuthenticationToken(),
                responseAuth.getProfiles().stream().findFirst().get().getId(),
                idOfCreatedTask);
    }

}
