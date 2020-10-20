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
import qa.eom.front.logic.pages.tasksAnswerTypes.GroupAnswer;
import qa.eom.front.logic.pojo.authresponse.ResponseAuth;

public class test011TaskGroupAnswerEOM_01_8 implements DesktopDriver, Authorization, CookiesHandler, GenerateText, ApiActionsForTests {

    private ResponseAuth responseAuth;
    private GroupAnswer groupAnswer;
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
        groupAnswer = new GroupAnswer();
        taskPreviewPage = new TaskPreviewPage();
        taskFillData = new TaskFillData("AutoMegabobaTask_" + generateRandomNumber(5),
                4, "Биология", "Бактерии. Грибы и лишайники", "Лишайники");

    }

    @Test
    void test() {

        groupAnswer
                .openTaskConstructorPage()
                .clickAnswerFormSelector()
                .clickAnswerFormBtnInSelector("Распределение элементов по группам")
                .enterSymbolsToQuestionField("a question...")
                ;

        groupAnswer
                .clickAddGroupBtn()
                .clickAddGroupBtn()
                .clickAddGroupBtn()
                .checkNumberOfGroupsEquals(4)
                .clickAddAnswerBtn(0)
                .clickAddAnswerBtn(1)
                .checkNumberOfAnswersEquals(6)
                .clickDeleteAnswerBtn(2)
                .checkNumberOfAnswersEquals(5)
                .clickDeleteGroupBtn(3)
                .checkNumberOfGroupsEquals(3)
                .enterSymbolsToGroupNameFieldInput("Первая группа", 0)
                .enterSymbolsToGroupNameFieldInput("Вторая группа", 1)
                .enterSymbolsToGroupNameFieldInput("Третья группа", 2)
                .enterSymbolsToAnswerFieldInput("Первый ответ", 0)
                .enterSymbolsToAnswerFieldInput("Второй ответ", 1)
                .enterSymbolsToAnswerFieldInput("Третий ответ", 2)
                .enterSymbolsToAnswerFieldInput("Четвёртый ответ", 3)
                .clickPreviewTaskBtn()
                ;

        taskPreviewPage
                .checkGroupAnswerAnswerOptionsRandom()
                .clickGroupAnswerOptionInBlock("Первый ответ")
                .clickGroupAnswerGroupBlock("Вторая группа")
                .clickGroupAnswerOptionInBlock("Второй ответ")
                .clickGroupAnswerGroupBlock("Первая группа")
                .clickGroupAnswerOptionInBlock("Третий ответ")
                .clickGroupAnswerGroupBlock("Вторая группа")
                .clickGroupAnswerOptionInBlock("Четвёртый ответ")
                .clickGroupAnswerGroupBlock("Третья группа")
                .clickAnswerBtn()
                .checkAnswerIsWrongMsgIsVisible()
                .clickGroupAnswerListBtn("Первая группа")
                .clickGroupAnswerListBtn("Вторая группа")
                .clickGroupAnswerListBtn("Третья группа")
                .clickGroupAnswerOptionInGroup("Первый ответ")
                .clickGroupAnswerGroupBlock("Первая группа")
                .clickAnswerBtn()
                .checkAnswerIsCorrectMsgIsVisible()
                .clickGoToEditBtn()
                ;


        groupAnswer
                .clickGoToSettingsBtn()
                .fillAllSettingsFiedsAndSaveTask(taskFillData)
                ;

        idOfCreatedTask = groupAnswer.getIdOfCreatedTask();

    }

    @AfterMethod
    void afterMethod() {
        deleteTaskApi(responseAuth.getAuthenticationToken(),
                responseAuth.getProfiles().stream().findFirst().get().getId(),
                idOfCreatedTask);
    }
}
