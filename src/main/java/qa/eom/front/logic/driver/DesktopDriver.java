package qa.eom.front.logic.driver;

import org.testng.annotations.BeforeMethod;
import qa.eom.front.logic.GetEnv;

import static com.codeborne.selenide.Selenide.open;

public interface DesktopDriver extends GetEnv, Capabilities {


    @BeforeMethod(alwaysRun = true)
    default void initDriver() {
        String grid = getEnv("grid_url");
        if (grid.equals("default")) {
            initLocalCapabilities();
        } else {
            initRemoteCapabilities(grid, getEnv("browser"));
        }
        open(getPropFromFile("baseUriStableOpk"));

    }
}
