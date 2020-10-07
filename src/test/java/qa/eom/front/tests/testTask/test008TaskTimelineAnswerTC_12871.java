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
import qa.eom.front.logic.pages.tasksAnswerTypes.TimelineMatchAnswer;
import qa.eom.front.logic.pojo.authresponse.ResponseAuth;

public class test008TaskTimelineAnswerTC_12871 implements DesktopDriver, Authorization, CookiesHandler, GenerateText, ApiActionsForTests {

    private ResponseAuth responseAuth;
    private TimelineMatchAnswer timelineMatchAnswer;
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
        timelineMatchAnswer = new TimelineMatchAnswer();
        taskPreviewPage = new TaskPreviewPage();
        taskFillData = new TaskFillData("AutoMegabobaTask_" + generateRandomNumber(5),
                4, "Биология", "Бактерии. Грибы и лишайники", "Лишайники");

    }

    @Test
    void test() {

        timelineMatchAnswer
                .openTaskConstructorPage()
                .clickAnswerFormSelector()
                .clickAnswerFormBtnInSelector("Лента времени")
                .enterSymbolsToQuestionField("a question...")
                ;
        timelineMatchAnswer
                .clickAddMarkBtn()
                .clickAddAnswerBtn(1)
                .clickGoToSettingsBtn()
                .clickSaveTaskBtn();
        timelineMatchAnswer
                .checkEmptyFieldsMarksAndAnswersValidMsgs(5)
                .checkNumberOfMarksEquals(2)
                .checkNumberOfAnswersEquals(3)
                .clickDeleteAnswerInMarkBtn(2)
                .checkNumberOfAnswersEquals(2)
                .clickDeleteMarkBtn(1)
                .checkNumberOfMarksEquals(1)
                .clickAddMarkBtn()
                .clickAddMarkBtn()
                .clickAddDistractorBtn()
                .enterSymbolsToDistractorField(0, "Дистрактор1")
                .enterSymbolsToMarkDescriptionField(0, "Отметка1")
                .clickSetDateOfMarkBtn(0)
                .enterNumberToDayOfMarkField("1")
                .enterNumberToMonthOfMarkField("1")
                .enterNumberToYearOfMarkField("1")
                .clickSaveDateOfMarkBtn()
                .enterSymbolsToMarkAnswerField(0, "Ответ1")
                .enterSymbolsToMarkDescriptionField(1, "Отметка2")
                .clickSetDateOfMarkBtn(0)
                .enterNumberToDayOfMarkField("1")
                .enterNumberToMonthOfMarkField("2")
                .enterNumberToYearOfMarkField("2")
                .clickSaveDateOfMarkBtn()
                .enterSymbolsToMarkAnswerField(1, "Ответ2")
                .enterSymbolsToMarkDescriptionField(2, "Отметка3")
                .clickSetDateOfMarkBtn(0)
                .enterNumberToDayOfMarkField("2")
                .enterNumberToMonthOfMarkField("2")
                .enterNumberToYearOfMarkField("2")
                .clickSaveDateOfMarkBtn()
                .enterSymbolsToMarkAnswerField(2, "Ответ3")
                .clickPositionTypeSelector()
                .clickOptionInPositionTypeSelector("Сглаженный")
                .clickPreviewTaskBtn()
                ;

        taskPreviewPage

                .checkTimelineAnswerMarkPosition(2, "35.9542")
                .clickGoToEditBtn()
                ;
        timelineMatchAnswer
                .clickPositionTypeSelector()
                .clickOptionInPositionTypeSelector("Равномерный")
                .clickPreviewTaskBtn()
                ;
        taskPreviewPage
                .checkTimelineAnswerMarkPosition(2, "100")
                .clickGoToEditBtn()
                ;
        timelineMatchAnswer
                .clickPositionTypeSelector()
                .clickOptionInPositionTypeSelector("Пропорциональный")
                .clickPreviewTaskBtn()
                ;
        taskPreviewPage
                .checkTimelineAnswerMarkPosition(2, "33.6692")
                .clickTimelineAnswerOptionInBlock("Ответ1")
                .clickTimelineAnswerMark("Отметка1")
                .clickTimelineAnswerOptionInBlock("Ответ2")
                .clickTimelineAnswerMark("Отметка2")
                .clickTimelineAnswerOptionInBlock("Дистрактор1")
                .clickTimelineAnswerMark("Отметка3")
                .clickAnswerBtn()
                .checkAnswerIsWrongMsgIsVisible()
                .clickTimelineAnswerMark("Отметка3")
                .clickTimelineAnswerOptionInMark("Дистрактор1")
                .clickTimelineAnswerOptionsBlock()
                .clickTimelineAnswerOptionInBlock("Ответ3")
                .clickTimelineAnswerMark("Отметка3")
                .clickAnswerBtn()
                .checkAnswerIsCorrectMsgIsVisible()
                .clickGoToEditBtn()
                ;

        timelineMatchAnswer
                .clickGoToSettingsBtn()
                .fillAllSettingsFiedsAndSaveTask(taskFillData)
                ;

        idOfCreatedTask = timelineMatchAnswer.getIdOfCreatedTask();

    }

    @AfterMethod
    void afterMethod() {
        deleteTaskApi(responseAuth.getAuthenticationToken(),
                responseAuth.getProfiles().stream().findFirst().get().getId(),
                idOfCreatedTask);
    }
}
