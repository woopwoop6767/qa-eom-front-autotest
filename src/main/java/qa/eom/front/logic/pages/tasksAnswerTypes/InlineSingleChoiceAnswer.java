package qa.eom.front.logic.pages.tasksAnswerTypes;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import qa.eom.front.logic.pages.TaskConstructorPage;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class InlineSingleChoiceAnswer extends TaskConstructorPage {


    private SelenideElement elTaskFieldInput = $x("//*[contains(text(),'Введите текст задания')]/..//*[@role='textbox']");
    private SelenideElement elAnswerOptionsBtn = $x("//button[contains(@title,'Добавить выпадающий список в тексте')]");
    private ElementsCollection elsAnswerOptionsInputs = $$x("//input[@type='text'][//*[contains(text(),'Добавление выбора ответа')]]");
    private ElementsCollection elsAnswerOptionsRadios = $$x("//input[@type='radio'][//*[contains(text(),'Добавление выбора ответа')]]");
    private ElementsCollection elsAnswerOptionsDeleteBtns = $$x("//button[@title='Удалить вариант']");
    private SelenideElement elAddAnswerOptionBtn = $x("//button[.//*[contains(text(),'Добавить вариант ответа')]]");
    private SelenideElement elSaveAnswerOptionsBtn = $x("//button[.//*[contains(text(),'Сохранить')]]");
    private SelenideElement elDeleteAllAnswerOptionsBtn = $x("//button[.//*[contains(text(),'Удалить')]]");
    private ElementsCollection elsAnswerOptionsListInTaskFieldBtns = $$x("//span[ancestor::figure]");


    @Step("Нажать кнопку [Добавить выпадающий список в тексте]")
    public InlineSingleChoiceAnswer clickAsnwerOptionsBtn() {
        elAnswerOptionsBtn.click();
        return this;
    }

    @Step("Ввести символы {symbols} в поле ввода задания")
    public InlineSingleChoiceAnswer enterSymbolsToTaskFieldInput(String symbols) {
        elTaskFieldInput.sendKeys(symbols);
        return this;
    }

    @Step("Нажать кнопку [Добавить вариант ответа] в диалоговом окне [Добавление выбора ответа]")
    public InlineSingleChoiceAnswer clickAddAnswerOptionBtn() {
        elAddAnswerOptionBtn.click();
        return this;
    }

    @Step("Нажать кнопку [Сохранить] в диалоговом окне [Добавление выбора ответа]")
    public InlineSingleChoiceAnswer clickSaveAnswerOptionBtn() {
        elSaveAnswerOptionsBtn.click();
        return this;
    }

    @Step("Нажать кнопку [Удалить] в диалоговом окне [Добавление выбора ответа]")
    public InlineSingleChoiceAnswer clickDeleteAllAnswerOptionBtn() {
        elDeleteAllAnswerOptionsBtn.click();
        return this;
    }

    @Step("Ввести символы {symbols} в поле ввода варианта ответа #{optionNumber}")
    public InlineSingleChoiceAnswer enterSymbolsToAnswerOptionsFieldInputs(int optionNumber, String symbols) {
        elsAnswerOptionsInputs.get(optionNumber).sendKeys(symbols);
        return this;
    }

    @Step("Нажать на радио-кнопку варианта ответа #{optionNumber}")
    public InlineSingleChoiceAnswer clickAnswerOptionsRadio(int optionNumber) {
        elsAnswerOptionsRadios.get(optionNumber).click();
        return this;
    }

    @Step("Нажать на радио-кнопку варианта ответа #{optionNumber}")
    public InlineSingleChoiceAnswer clickAnswerOptionsDeleteBtn(int optionNumber) {
        elsAnswerOptionsDeleteBtns.get(optionNumber).click();
        return this;
    }

    @Step("Проверить, что текст варианта ответа {optionText} отображается")
    public InlineSingleChoiceAnswer checkAnswerOptionsInTaskFieldIsVisible(String optionText) {
        elsAnswerOptionsListInTaskFieldBtns.find(Condition.text(optionText))
                .shouldBe(Condition.visible);
        return this;
    }


}
