package qa.eom.front.logic.driver;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Cookie;
import qa.eom.front.logic.pojo.authresponse.ResponseAuth;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.refresh;

public interface CookiesHandler {


    default void setCookie (String cookieItem, String cookieValue) {

        WebDriverRunner.getWebDriver().manage().addCookie(new Cookie(cookieItem, cookieValue));

    }

    default void setAuthorizationCookies(ResponseAuth responseAuth) {

        setCookie("auth_token", responseAuth.getAuthenticationToken());
        setCookie("profile_id", responseAuth.getProfiles().get(1).getId());
        setCookie("profile_type", responseAuth.getProfiles().get(1).getType());
        refresh();
        SelenideElement userMenuButton = $x("//button[contains(@class,'userBtn')]");
        userMenuButton.shouldBe(Condition.visible);

    }
}
