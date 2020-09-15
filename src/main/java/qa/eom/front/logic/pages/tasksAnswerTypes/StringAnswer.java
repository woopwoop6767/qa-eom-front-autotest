package qa.eom.front.logic.pages.tasksAnswerTypes;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import qa.eom.front.logic.pages.TaskConstructorPage;

import static com.codeborne.selenide.Selenide.$x;

public class StringAnswer extends TaskConstructorPage {


    private SelenideElement h = $x("");
    private SelenideElement elAnswerFieldInput = $x("//input[contains(@placeholder,'Введите правильный ответ')]");

    @Step("Ввести ответ {symbols} в поле ввода ответа")
    public StringAnswer enterSymbolsToAnswerFieldInput(String symbols) {
        elAnswerFieldInput.sendKeys(symbols);
        return this;
    }

//    @Override
//    public void doAnswerFormAction() {
//
//    }
}
