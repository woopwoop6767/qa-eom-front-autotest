package qa.eom.front.logic.pages.tasksAnswerTypes;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import qa.eom.front.logic.pages.TaskConstructorPage;

import static com.codeborne.selenide.Selenide.$x;

public class SingleAnswer {


    private SelenideElement h = $x("");
    private SelenideElement answerFieldInput = $x("//br[.//preceding::*[contains(text(),'Введите ответ')]]");

    @Step("Ввести ответ {symbols} в поле ввода ответа")
    public SingleAnswer enterSymbolsToAnswerFieldInput(String symbols) {
        answerFieldInput.sendKeys(symbols);
        return this;
    }
}
