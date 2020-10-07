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
        taskFillData = new TaskFillData("AutoMegabobaTask_" + generateRandomNumber(5),
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
                .clickInsertTableBtn()
                .checkTableCellsNumberEquals(3)
                .clickCellMenuBtn(0)
                .clickColumnMenuOptionBtn("справа")
                .clickCellMenuBtn(2)
                .clickRowMenuOptionBtn("ниже")
                .checkTableCellsNumberEquals(8)
                .clickCellMenuBtn(1)
                .clickColumnMenuOptionBtn("Удалить")
                .clickCellMenuBtn(1)
                .clickRowMenuOptionBtn("Удалить")
                .checkTableCellsNumberEquals(3)
                .enterSymbolsToTableCellInput(0, "Неправильный ответ1")
                .enterSymbolsToTableCellInput(1, "Неправильный ответ2")
                .enterSymbolsToTableCellInput(2, "Правильный ответ1")
                .clickCorrectAnswerTableModeBtn()
                .clickCellInCorrectAnswerMode("Правильный ответ1")
                .clickPreviewTaskBtn()
                ;

        taskPreviewPage
                .enterTableAnswerSymbolsToCellInput(0, "Неправильный ответ1")
                .clickAnswerBtn()
                .checkAnswerIsWrongMsgIsVisible()
                .enterTableAnswerSymbolsToCellInput(0, "Правильный ответ1")
                .clickAnswerBtn()
                .checkAnswerIsCorrectMsgIsVisible()
                .clickGoToEditBtn()
                ;

        tableAnswer
                .clickGoToSettingsBtn()
                .fillAllSettingsFiedsAndSaveTask(taskFillData)
                ;

        idOfCreatedTask = tableAnswer.getIdOfCreatedTask();

    }

    @AfterMethod
    void afterMethod() {
        deleteTaskApi(responseAuth.getAuthenticationToken(),
                responseAuth.getProfiles().stream().findFirst().get().getId(),
                idOfCreatedTask);
    }
}
