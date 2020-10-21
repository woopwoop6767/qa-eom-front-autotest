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
import qa.eom.front.logic.pages.tasksAnswerTypes.MatchAnswer;
import qa.eom.front.logic.pojo.authresponse.ResponseAuth;

public class test013TaskMatchAnswerEOM_01_12 implements DesktopDriver, Authorization, CookiesHandler, GenerateText, ApiActionsForTests {

    private ResponseAuth responseAuth;
    private MatchAnswer matchAnswer;
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
        matchAnswer = new MatchAnswer();
        taskPreviewPage = new TaskPreviewPage();
        taskFillData = new TaskFillData("AutoMegabobaTask_" + generateRandomNumber(5),
                4, "Биология", "Бактерии. Грибы и лишайники", "Лишайники");

    }

    @Test
    void test() {

        matchAnswer
                .openTaskConstructorPage()
                .clickAnswerFormSelector()
                .clickAnswerFormBtnInSelector("Установление соответствия")
                .enterSymbolsToQuestionField("a question...")
                ;

        matchAnswer
                .clickAddMatchingBtn()
                .clickAddMatchingBtn()
                .clickAddMatchingBtn()
                .checkNumberOfMatchingEquals(4)
                .clickDeleteMatchingBtn(2)
                .checkNumberOfMatchingEquals(3)
                .enterSymbolsToTaskFieldInput("Соответствие1", 0)
                .enterSymbolsToTaskFieldInput("Соответствие2", 1)
                .enterSymbolsToTaskFieldInput("Соответствие3", 2)
                .enterSymbolsToAnswerFieldInput("Ответ1", 0)
                .enterSymbolsToAnswerFieldInput("Ответ2", 1)
                .enterSymbolsToAnswerFieldInput("Ответ3", 2)
                .clickPreviewTaskBtn()
                ;

        taskPreviewPage
//                .checkGlobalAnswerOptionsRandom()   // Проверка отключена из-за подозрения нецелесообразности проверять рандом в предпросмотре
                .checkMatchAnswerMatchingsRandom()
                .clickGlobalAnswerOptionInBlock("Ответ1")
                .clickMatchAnswerMatchingBlock("Соответствие2")
                .clickGlobalAnswerOptionInBlock("Ответ2")
                .clickMatchAnswerMatchingBlock("Соответствие1")
                .clickGlobalAnswerOptionInBlock("Ответ3")
                .clickMatchAnswerMatchingBlock("Соответствие3")
                .clickAnswerBtn()
                .checkAnswerIsWrongMsgIsVisible()
                .clickMatchAnswerOptionInMatching("Ответ1")
                .clickMatchAnswerMatchingBlock("Соответствие1")
                .clickGlobalAnswerOptionInBlock("Ответ2")
                .clickMatchAnswerMatchingBlock("Соответствие2")
                .clickAnswerBtn()
                .checkAnswerIsCorrectMsgIsVisible()
                .clickGoToEditBtn()
                ;

        matchAnswer
                .clickGoToSettingsBtn()
                .fillAllSettingsFiedsAndSaveTask(taskFillData)
                ;

        idOfCreatedTask = matchAnswer.getIdOfCreatedTask();

    }

    @AfterMethod
    void afterMethod() {
        deleteTaskApi(responseAuth.getAuthenticationToken(),
                responseAuth.getProfiles().stream().findFirst().get().getId(),
                idOfCreatedTask);
    }

}
