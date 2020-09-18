package qa.eom.front.logic.pages.tasksAnswerTypes;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import qa.eom.front.logic.pages.TaskPreviewPage;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class MultipleAnswer extends TaskPreviewPage {


    private SelenideElement elAddAnswerBtn = $x("//button[.//*[contains(text(),'добавить ответ')]]");
    private SelenideElement elAddDistractorBtn = $x("//button[.//*[contains(text(),'Добавить дистрактор')]]");
    private ElementsCollection elsAnswerFieldsInputs = $$x("//*[text()='Ответ:']//following::div[1]//*[@role='textbox']");
    private ElementsCollection elsDistractorFieldsInput = $$x("//*[contains(text(),'Дополнительные ответы - дистракторы:')]//following::div[1]//*[@role='textbox']");


    @Step("Ввести символы {symbols} в поле ввода ответа #{answerNumber}")
    public MultipleAnswer enterSymbolsToAnswerFieldInput(int answerNumber,String symbols) {
        elsAnswerFieldsInputs.get(answerNumber).sendKeys(symbols);
        return this;
    }

    @Step("Ввести символы {symbols} в поле ввода ответа-дистрактора #{distractorNumber}")
    public MultipleAnswer enterSymbolsToDistractorFieldInput(int distractorNumber,String symbols) {
        elsDistractorFieldsInput.get(distractorNumber).sendKeys(symbols);
        return this;
    }

    @Step("Нажать кнопку [Добавить ответ]")
    public MultipleAnswer clickAddAnswerBtn() {
        elAddAnswerBtn.click();
        return this;
    }

    @Step("Нажать кнопку [Добавить дистрактор]")
    public MultipleAnswer clickAddDistractorBtn() {
        elAddDistractorBtn.click();
        return this;
    }


}
