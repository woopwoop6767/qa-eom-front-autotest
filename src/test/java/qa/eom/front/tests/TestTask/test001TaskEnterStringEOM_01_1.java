package qa.eom.front.tests.TestTask;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.eom.front.logic.api.services.Authorization;
import qa.eom.front.logic.driver.CookiesHandler;
import qa.eom.front.logic.driver.DesktopDriver;
import qa.eom.front.logic.pages.TaskConstructorPage;
import qa.eom.front.logic.UserCredentials;
import qa.eom.front.logic.pojo.ResponseAuth;

import static com.codeborne.selenide.Selenide.*;


public class test001TaskEnterStringEOM_01_1 implements DesktopDriver, Authorization, CookiesHandler {


    private TaskConstructorPage taskConstructorPage;
    private ResponseAuth responseAuth;


    @BeforeMethod
    void setUp() {

        setAuthorizationCookies(responseAuth);
        taskConstructorPage = new TaskConstructorPage();

    }

    @BeforeClass
    void authorizationApi() {

        responseAuth = validAuth(UserCredentials.TEACHER_ASTAHOVA.getLogin(),
                UserCredentials.TEACHER_ASTAHOVA.getHashPasswordOne(),
                UserCredentials.TEACHER_ASTAHOVA.getHashPasswordTwo());

    }

    @Test
    void test() {

        open("https://uchebnik-stable.opk.su/exam/task/new/");

    }

}
