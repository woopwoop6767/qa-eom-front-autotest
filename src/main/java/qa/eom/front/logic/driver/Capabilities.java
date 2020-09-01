package qa.eom.front.logic.driver;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.qameta.allure.selenide.LogType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.logging.Level;

public interface Capabilities {


    default void initLocalCapabiities() {

        Configuration.timeout = 10000;
        Configuration.startMaximized = true;
        Configuration.driverManagerEnabled = true;

    }

    default void initRemoteCapabilities(String grid, String browser) {

        switch (browser.trim().toLowerCase()) {
            case "firefox":
            case "ff":
                Configuration.browser = Browsers.FIREFOX;
                Configuration.browserVersion = "79.0";
                break;
            case "opera":
                Configuration.browser = Browsers.OPERA;
                Configuration.browserVersion = "69.0";
                break;
            case "chrome":
            default:
                Configuration.browser = Browsers.CHROME;
                Configuration.browserVersion = "85.0";
                break;
        }

        Configuration.timeout = 20000;
        Configuration.remote = grid.concat("/wb/hub");
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("enableVNC", true);
        Configuration.browserCapabilities = desiredCapabilities;
        Configuration.startMaximized = true;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).enableLogs(LogType.BROWSER, Level.ALL));

    }

}
