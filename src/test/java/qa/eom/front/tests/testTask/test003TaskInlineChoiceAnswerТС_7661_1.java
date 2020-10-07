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
import qa.eom.front.logic.pages.tasksAnswerTypes.InlineSingleChoiceAnswer;
import qa.eom.front.logic.pojo.authresponse.ResponseAuth;


public class test003TaskInlineChoiceAnswerТС_7661_1 implements DesktopDriver, Authorization, CookiesHandler, GenerateText, ApiActionsForTests {

    private ResponseAuth responseAuth;
    private InlineSingleChoiceAnswer inlineSingleChoiceAnswer;
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
        inlineSingleChoiceAnswer = new InlineSingleChoiceAnswer();
        taskPreviewPage = new TaskPreviewPage();
        taskFillData = new TaskFillData("AutoMegabobaTask_" + generateRandomNumber(5),
                4, "Биология", "Бактерии. Грибы и лишайники", "Лишайники");

    }

    @Test
    void test() {

        inlineSingleChoiceAnswer
                .openTaskConstructorPage()
                .clickAnswerFormSelector()
                .clickAnswerFormBtnInSelector("Выпадающий список в тексте")
                .enterSymbolsToQuestionField("a question...")
                ;
        inlineSingleChoiceAnswer
                .clickAsnwerOptionsBtn()
                .clickAddAnswerOptionBtn()
                .enterSymbolsToAnswerOptionsFieldInputs(0,"Правильный ответ1")
                .enterSymbolsToAnswerOptionsFieldInputs(1,"Неправильный ответ1")
                .clickAnswerOptionsRadio(0)
                .clickSaveAnswerOptionBtn()
                .enterSymbolsToTaskFieldInput("задание")
                .clickAsnwerOptionsBtn()
                .clickAddAnswerOptionBtn()
                .enterSymbolsToAnswerOptionsFieldInputs(0,"Правильный ответ2")
                .enterSymbolsToAnswerOptionsFieldInputs(1,"Неправильный ответ2")
                .clickAnswerOptionsRadio(0)
                .clickSaveAnswerOptionBtn()
                .enterSymbolsToTaskFieldInput("задание")
                .clickAsnwerOptionsBtn()
                .clickAddAnswerOptionBtn()
                .enterSymbolsToAnswerOptionsFieldInputs(0,"Правильный ответ3")
                .enterSymbolsToAnswerOptionsFieldInputs(1,"Неправильный ответ3")
                .clickAnswerOptionsRadio(0)
                .clickSaveAnswerOptionBtn()
                .checkAnswerOptionsInTaskFieldIsVisible("Правильный ответ1")
                .checkAnswerOptionsInTaskFieldIsVisible("Правильный ответ2")
                .checkAnswerOptionsInTaskFieldIsVisible("Правильный ответ3")
                .clickPreviewTaskBtn()
                ;

        taskPreviewPage
                .clickInlineChoiceAnswerSelector(0)
                .clickInlineChoiceAnswerOptionInSelectorBtn("Неправильный ответ1")
                .clickInlineChoiceAnswerSelector(1)
                .clickInlineChoiceAnswerOptionInSelectorBtn("Правильный ответ2")
                .clickInlineChoiceAnswerSelector(2)
                .clickInlineChoiceAnswerOptionInSelectorBtn("Правильный ответ3")
                .clickAnswerBtn()
                .checkAnswerIsWrongMsgIsVisible()
                .clickInlineChoiceAnswerSelector(0)
                .clickInlineChoiceAnswerOptionInSelectorBtn("Правильный ответ1")
                .clickAnswerBtn()
                .checkAnswerIsCorrectMsgIsVisible()
                .clickGoToEditBtn()
                ;

        inlineSingleChoiceAnswer
                .clickGoToSettingsBtn()
                .fillAllSettingsFiedsAndSaveTask(taskFillData)
                ;

        idOfCreatedTask = inlineSingleChoiceAnswer.getIdOfCreatedTask();

    }

    @AfterMethod
    void afterMethod() {
        deleteTaskApi(responseAuth.getAuthenticationToken(),
                responseAuth.getProfiles().stream().findFirst().get().getId(),
                idOfCreatedTask);
    }
}
