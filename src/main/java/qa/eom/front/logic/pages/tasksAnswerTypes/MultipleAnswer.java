package qa.eom.front.logic.pages.tasksAnswerTypes;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import qa.eom.front.logic.pages.TaskConstructorPage;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class MultipleAnswer extends TaskConstructorPage {


    private SelenideElement elAddAnswerBtn = $x("//button[.//*[contains(text(),'добавить ответ')]]");
    private SelenideElement elAddDistractorBtn = $x("//button[.//*[contains(text(),'Добавить дистрактор')]]");
    private ElementsCollection elsAnswerFieldsInputs = $$x("//*[contains(text(),'Ответ')]/..//*[@role='textbox']");
    private ElementsCollection elsDistractorFieldsInput = $$x("//*[contains(text(),'Дистрактор')]/..//*[@role='textbox']");
    private ElementsCollection elEnterAnswerInfo = $$x("//*[contains(text(),'Введите ответ')]");


    @Step("Ввести символы {symbols} в поле ввода ответа #{answerNumber}")
    public MultipleAnswer enterSymbolsToAnswerFieldInput(int answerNumber,String symbols) {
        elsAnswerFieldsInputs.get(answerNumber).sendKeys(symbols);
        for (SelenideElement el : elEnterAnswerInfo) {
            el.shouldNotBe(Condition.visible);
        }
        return this;
    }

    @Step("Ввести символы {symbols} в поле ввода дистрактора #{distractorNumber}")
    public MultipleAnswer enterSymbolsToDistractorFieldInput(int distractorNumber,String symbols) {
        elsDistractorFieldsInput.get(distractorNumber).sendKeys(symbols);
        for (SelenideElement el : elEnterAnswerInfo) {
            el.shouldNotBe(Condition.visible);
        }
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
