package qa.eom.front.tests.testTask;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.eom.front.logic.ApiActionsForTests;
import qa.eom.front.logic.GenerateText;
import qa.eom.front.logic.UserCredentials;
import qa.eom.front.logic.api.services.Authorization;
import qa.eom.front.logic.driver.CookiesHandler;
import qa.eom.front.logic.driver.DesktopDriver;
import qa.eom.front.logic.dto.TaskFillData;
import qa.eom.front.logic.pages.TaskPreviewPage;
import qa.eom.front.logic.pages.tasksAnswerTypes.OrderAnswer;
import qa.eom.front.logic.pojo.authresponse.ResponseAuth;

public class test012TaskOrderAnswerEOM_01_11 implements DesktopDriver, Authorization, CookiesHandler, GenerateText, ApiActionsForTests {

    private ResponseAuth responseAuth;
    private OrderAnswer orderAnswer;
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
        orderAnswer = new OrderAnswer();
        taskPreviewPage = new TaskPreviewPage();
        taskFillData = new TaskFillData("AutoMegabobaTask_" + generateRandomNumber(5),
                4, "Биология", "Бактерии. Грибы и лишайники", "Лишайники");

    }

    @Test
    void test() {

        orderAnswer
                .openTaskConstructorPage()
                .clickAnswerFormSelector()
                .clickAnswerFormBtnInSelector("Упорядочивание элементов")
                .enterSymbolsToQuestionField("a question...")
                ;

        orderAnswer
                .clickAddAnswerBtn()
                .clickAddAnswerBtn()
                .clickAddAnswerBtn()
                .checkNumberOfAnswersEquals(4)
                .clickDeleteAnswerBtn(3)
                .checkNumberOfAnswersEquals(3)
                .enterSymbolsToAnswerFieldInput("Ответ1", 0)
                .enterSymbolsToAnswerFieldInput("Ответ2", 1)
                .enterSymbolsToAnswerFieldInput("Ответ3", 2)
                .clickPreviewTaskBtn()
                ;

        taskPreviewPage
                .checkOrderAnswerAnswerOptionsRandom()
                .moveOrderAnswerOptionToPosition("Ответ1", 1)
                .clickAnswerBtn()
                .checkAnswerIsWrongMsgIsVisible()
                .moveOrderAnswerOptionToPosition("Ответ1", 0)
                .moveOrderAnswerOptionToPosition("Ответ2", 1)
                .moveOrderAnswerOptionToPosition("Ответ3", 2)
                .clickAnswerBtn()
                .checkAnswerIsCorrectMsgIsVisible()
                .clickGoToEditBtn()
                ;


        orderAnswer
                .clickGoToSettingsBtn()
                .fillAllSettingsFiedsAndSaveTask(taskFillData)
                ;

        idOfCreatedTask = orderAnswer.getIdOfCreatedTask();

    }

    @AfterMethod
    void afterMethod() {
        deleteTaskApi(responseAuth.getAuthenticationToken(),
                responseAuth.getProfiles().stream().findFirst().get().getId(),
                idOfCreatedTask);
    }
}
