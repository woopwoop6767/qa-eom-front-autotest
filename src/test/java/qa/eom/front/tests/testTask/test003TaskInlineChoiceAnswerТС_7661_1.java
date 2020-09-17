package qa.eom.front.tests.testTask;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.eom.front.logic.GenerateText;
import qa.eom.front.logic.UserCredentials;
import qa.eom.front.logic.api.services.Authorization;
import qa.eom.front.logic.driver.CookiesHandler;
import qa.eom.front.logic.driver.DesktopDriver;
import qa.eom.front.logic.dto.TaskFillData;
import qa.eom.front.logic.pages.TaskPreviewPage;
import qa.eom.front.logic.pages.tasksAnswerTypes.InlineSingleChoiceAnswer;
import qa.eom.front.logic.pojo.authresponse.ResponseAuth;

import static com.codeborne.selenide.Selenide.open;

public class test003TaskInlineChoiceAnswerТС_7661_1 implements DesktopDriver, Authorization, CookiesHandler, GenerateText {

    private ResponseAuth responseAuth;
    private InlineSingleChoiceAnswer inlineSingleChoiceAnswer;
    private TaskPreviewPage taskPreviewPage;
    private TaskFillData taskFillData;


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
        taskFillData = new TaskFillData("MegabobaTask_" + generateRandomNumber(5),
                4, "Биология", "Бактерии. Грибы и лишайники", "Лишайники");

    }

    @Test
    void test() {

        open("https://uchebnik-stable.opk.su/exam/task/new/");


        inlineSingleChoiceAnswer
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

    }
}
