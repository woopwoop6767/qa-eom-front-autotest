package qa.eom.front.tests.testTask;


import com.codeborne.selenide.SelenideElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.eom.front.logic.GenerateText;
import qa.eom.front.logic.api.services.Authorization;
import qa.eom.front.logic.driver.CookiesHandler;
import qa.eom.front.logic.driver.DesktopDriver;
import qa.eom.front.logic.UserCredentials;
import qa.eom.front.logic.dto.TaskFillData;
import qa.eom.front.logic.pages.TaskPreviewPage;
import qa.eom.front.logic.pages.tasksAnswerTypes.StringAnswer;
import qa.eom.front.logic.pojo.authresponse.ResponseAuth;

import static com.codeborne.selenide.Selenide.*;


public class test001TaskStringAnswerEOM_01_1 implements DesktopDriver, Authorization, CookiesHandler, GenerateText {


    private ResponseAuth responseAuth;
    private StringAnswer stringAnswer;
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
        stringAnswer = new StringAnswer();
        taskPreviewPage = new TaskPreviewPage();
        taskFillData = new TaskFillData("MegabobaTask_" + generateRandomNumber(5),
                4, "Биология", "Бактерии. Грибы и лишайники", "Лишайники");

    }

    @Test
    void test() {

        open("https://uchebnik-stable.opk.su/exam/task/new/");


        stringAnswer
                .clickAnswerFormSelector()
                .clickAnswerFormBtnInSelector("Ввод строки")
                .enterSymbolsToQuestionField("a question...");
        stringAnswer
                .enterSymbolsToAnswerFieldInput("An answer...")
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

    }

}
