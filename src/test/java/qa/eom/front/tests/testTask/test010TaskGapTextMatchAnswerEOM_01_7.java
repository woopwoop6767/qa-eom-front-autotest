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
import qa.eom.front.logic.pages.tasksAnswerTypes.GapTextMatchAnswer;
import qa.eom.front.logic.pojo.authresponse.ResponseAuth;

public class test010TaskGapTextMatchAnswerEOM_01_7 implements DesktopDriver, Authorization, CookiesHandler, GenerateText, ApiActionsForTests {

    private ResponseAuth responseAuth;
    private GapTextMatchAnswer gapTextMatchAnswer;
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
        gapTextMatchAnswer = new GapTextMatchAnswer();
        taskPreviewPage = new TaskPreviewPage();
        taskFillData = new TaskFillData("AutoMegabobaTask_" + generateRandomNumber(5),
                4, "Биология", "Бактерии. Грибы и лишайники", "Лишайники");

    }

    @Test
    void test() {

        gapTextMatchAnswer
                .openTaskConstructorPage()
                .clickAnswerFormSelector()
                .clickAnswerFormBtnInSelector("Подстановка слов в пропуски в тексте")
                .enterSymbolsToQuestionField("a question...")
                ;
        gapTextMatchAnswer
                .clickAddOptionFieldInTaskFieldBtn()
                .checkNumberOfLocationsForAnswerOptionsInTaskFieldEquals(1)
                .deleteLocationForAnswerOptions(0)
                .checkNumberOfLocationsForAnswerOptionsInTaskFieldEquals(0)
                .clickAddOptionFieldInTaskFieldBtn()
                .enterSymboslToTaskField("Первая часть")
                .clickAddOptionFieldInTaskFieldBtn()
                .enterSymboslToTaskField("вторая часть")
                .clickAddOptionFieldInTaskFieldBtn()
                .clickEditAnswerOptionsBtn()
                .clickAddOptioBtn()
                .clickAddOptioBtn()
                .enterSymbolsToOptionField(0, "Ответ2")
                .enterSymbolsToOptionField(1, "Ответ1")
                .enterSymbolsToOptionField(2, "Ответ3")
                .clickCloseModalEditOptionsBtn()
                .moveAnswerOptionFromOptionsBlock("Ответ1", 0)
                .moveAnswerOptionFromOptionsBlock("Ответ2", 1)
                .moveAnswerOptionFromOptionsBlock("Ответ3", 2)
                .clickAnswerModeRadioBtn("Перетаскивание")
                .clickPreviewTaskBtn()
                ;

        taskPreviewPage
                .clickGapTextMatchAnswerOptionInBlock("Ответ1")
                .clickGapTextMatchAnswerLocation(0)
                .checkGapTextMatchAnswerOptionVisibilityInBlock("Ответ1", false)
                .clickGoToEditBtn()
                ;
        gapTextMatchAnswer
                .clickAnswerModeRadioBtn("Копирование")
                .clickPreviewTaskBtn()
                ;
        taskPreviewPage
                .clickGapTextMatchAnswerOptionInBlock("Ответ1")
                .clickGapTextMatchAnswerLocation(0)
                .checkGapTextMatchAnswerOptionVisibilityInBlock("Ответ1", true)
                .clickGoToEditBtn()
                ;
        gapTextMatchAnswer
                .clickSortSelectorBtn()
                .clickOptionInSortSelectorBtn("В алфавитном порядке")
                .clickPreviewTaskBtn()
                ;
        taskPreviewPage
                .checkGapTextMatchAnswerOptionsAlphabetOrder()
                .clickGoToEditBtn()
                ;
        gapTextMatchAnswer
                .clickSortSelectorBtn()
                .clickOptionInSortSelectorBtn("Не перемешивать")
                .clickPreviewTaskBtn()
                ;
        taskPreviewPage
                .checkGapTextMatchAnswerOptionsOrder("Ответ2", 0)
                .checkGapTextMatchAnswerOptionsOrder("Ответ1", 1)
                .checkGapTextMatchAnswerOptionsOrder("Ответ3", 2)
                .clickGoToEditBtn()
                ;
        gapTextMatchAnswer
                .clickSortSelectorBtn()
                .clickOptionInSortSelectorBtn("В случайном порядке")
                .clickPreviewTaskBtn()
                ;
        taskPreviewPage
                .checkGapTextMatchAnswerOptionsRandom()
                .clickGapTextMatchAnswerOptionInBlock("Ответ2")
                .clickGapTextMatchAnswerLocation(0)
                .clickGapTextMatchAnswerOptionInBlock("Ответ1")
                .clickGapTextMatchAnswerLocation(1)
                .clickGapTextMatchAnswerOptionInBlock("Ответ3")
                .clickGapTextMatchAnswerLocation(2)
                .clickAnswerBtn()
                .checkAnswerIsWrongMsgIsVisible()
                .clickGapTextMatchAnswerLocation(0)
                .clickGapTextMatchAnswerLocation(1)
                .clickGapTextMatchAnswerOptionInBlock("Ответ1")
                .clickGapTextMatchAnswerLocation(0)
                .clickGapTextMatchAnswerOptionInBlock("Ответ2")
                .clickGapTextMatchAnswerLocation(1)
                .clickAnswerBtn()
                .checkAnswerIsCorrectMsgIsVisible()
                .clickGoToEditBtn()
                ;

        gapTextMatchAnswer
                .clickGoToSettingsBtn()
                .fillAllSettingsFiedsAndSaveTask(taskFillData)
                ;

        idOfCreatedTask = gapTextMatchAnswer.getIdOfCreatedTask();

    }

    @AfterMethod
    void afterMethod() {
        deleteTaskApi(responseAuth.getAuthenticationToken(),
                responseAuth.getProfiles().stream().findFirst().get().getId(),
                idOfCreatedTask);
    }
}
