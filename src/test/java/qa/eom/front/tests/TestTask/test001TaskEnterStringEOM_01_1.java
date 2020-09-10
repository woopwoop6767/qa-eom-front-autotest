package qa.eom.front.tests.TestTask;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import qa.eom.front.logic.api.services.Authorization;
import qa.eom.front.logic.driver.CookiesHandler;
import qa.eom.front.logic.driver.DesktopDriver;
import qa.eom.front.logic.UserCredentials;
import qa.eom.front.logic.pages.TaskConstructorPage;
import qa.eom.front.logic.pages.tasksAnswerTypes.StringAnswer;
import qa.eom.front.logic.pojo.authresponse.ResponseAuth;

import static com.codeborne.selenide.Selenide.*;


public class test001TaskEnterStringEOM_01_1 implements DesktopDriver, Authorization, CookiesHandler {


    private ResponseAuth responseAuth;
    private StringAnswer stringAnswer;


//    @DataProvider()

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

    }

   // @Test(dataProvider = "")
    @Test
    void test() {

        open("https://uchebnik-stable.opk.su/exam/task/new/");

//        stringAnswer.clickGoToSettingsBtn();
//        stringAnswer.enterSymbolsToAnswerFieldInput("");
        stringAnswer





    }

}
