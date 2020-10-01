package qa.eom.front.tests.testTask;

import org.testng.annotations.AfterClass;
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
import qa.eom.front.logic.pages.tasksAnswerTypes.TableAnswer;
import qa.eom.front.logic.pojo.authresponse.ResponseAuth;

public class test006TaskTableAnswerТС_7397_1 implements DesktopDriver, Authorization, CookiesHandler, GenerateText, ApiActionsForTests {

    private ResponseAuth responseAuth;
    private TableAnswer tableAnswer;
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
        tableAnswer = new TableAnswer();
        taskPreviewPage = new TaskPreviewPage();
        taskFillData = new TaskFillData("MegabobaTask_" + generateRandomNumber(5),
                4, "Биология", "Бактерии. Грибы и лишайники", "Лишайники");

    }

    @Test
    void test() {

        tableAnswer
                .openTaskConstructorPage()
                .clickAnswerFormSelector()
                .clickAnswerFormBtnInSelector("Заполнение таблицы")
                .enterSymbolsToQuestionField("a question...")
                ;
        tableAnswer
                .enterNumberOfTableColumnsInput("2")
                .enterNumberOfTableRowsInput("2")
                ;
//                .enterSymbolsToAnswerFieldInput(1, "Правильный ответ2")
//                .clickAddDistractorBtn()
//                .clickAddDistractorBtn()
//                .enterSymbolsToDistractorFieldInput(0, "Дистрактор1")
//                .enterSymbolsToDistractorFieldInput(1, "Дистрактор2")
//                .clickPreviewTaskBtn()
//        ;

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

        tableAnswer
                .clickGoToSettingsBtn()
                .fillAllSettingsFiedsAndSaveTask(taskFillData)
        ;

        idOfCreatedTask = tableAnswer.getIdOfCreatedTask();

    }

    @AfterClass
    void afterClass() {
        deleteTaskApi(responseAuth.getAuthenticationToken(),
                responseAuth.getProfiles().stream().findFirst().get().getId(),
                idOfCreatedTask);
    }
}
