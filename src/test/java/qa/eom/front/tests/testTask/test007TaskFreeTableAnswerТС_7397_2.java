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
import qa.eom.front.logic.pages.tasksAnswerTypes.TableAnswer;
import qa.eom.front.logic.pojo.authresponse.ResponseAuth;

public class test007TaskFreeTableAnswerТС_7397_2 implements DesktopDriver, Authorization, CookiesHandler, GenerateText, ApiActionsForTests {

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
                .enterSymbolsToTableCellInput(0, "Столбик")
                .enterSymbolsToTableCellInput(1, "Строка")
                .clickPreviewTaskBtn()
                ;

        taskPreviewPage
                .enterTableAnswerSymbolsToCellInput(0, "Ответ")
                .clickAnswerBtn()
                .checkTeacherVerificationMsgIsVisible()
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
