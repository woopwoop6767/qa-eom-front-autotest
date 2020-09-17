package qa.eom.front.logic.pages.tasksAnswerTypes;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import qa.eom.front.logic.pages.TaskConstructorPage;

import static com.codeborne.selenide.Selenide.$x;

public class NumberAnswer extends TaskConstructorPage {


    private SelenideElement elAnswerFieldInput = $x("//input[contains(@placeholder,'Введите правильный ответ')]");


    @Step("Ввести ответ {symbols} в поле ввода ответа")
    public NumberAnswer enterSymbolsToAnswerFieldInput(String symbols) {
        elAnswerFieldInput.sendKeys(symbols);
        return this;
    }


}
