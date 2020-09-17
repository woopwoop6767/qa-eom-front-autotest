package qa.eom.front.logic.pages.tasksAnswerTypes;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import qa.eom.front.logic.pages.TaskConstructorPage;

import static com.codeborne.selenide.Selenide.$x;

public class StringAnswer extends TaskConstructorPage {


    private SelenideElement h = $x("");
    private SelenideElement elAnswerFieldInput = $x("//input[contains(@placeholder,'Введите правильный ответ')]");
    private SelenideElement elAnswerIsCaseSensitiveCheckbox = $x("//input[@type='checkbox' and //label//p[contains(text(),'Учитывать регистр')]]");


    @Step("Ввести ответ {symbols} в поле ввода ответа")
    public StringAnswer enterSymbolsToAnswerFieldInput(String symbols) {
        elAnswerFieldInput.sendKeys(symbols);
        return this;
    }

    @Step("Нажать на чек-бокс [Учитывать регистр]")
    public StringAnswer clickAnswerIsCaseSensitiveCheckbox() {
        elAnswerIsCaseSensitiveCheckbox.click();
        return this;
    }

//    @Override
//    public void doAnswerFormAction() {
//
//    }
}
