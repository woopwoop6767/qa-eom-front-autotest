package qa.eom.front.logic.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class TaskPreviewPage {


    private SelenideElement elAnswerIsCorrectMsg = $x("//p[contains(text(),'Ответ верен')]");
    private SelenideElement elAnswerIsWrongMsg = $x("//p[contains(text(),'Ответ неверен')]");
    private SelenideElement elAnswerBtn = $x("//button[.//*[contains(text(),'Ответить')]]");
    private SelenideElement elGoToEditBtn = $x("//button[.//*[contains(text(),'Назад к редактированию')]]");

    private SelenideElement elAnswerFieldInput = $x("//input");
    private ElementsCollection elsInlineChoiceAnswerSelectors = $$x("//*[@role='button']");
    private ElementsCollection elsInlineChoiceAnswerInSelectorListBtns = $$x("//li[@role='option'][//ul[@role='listbox']]");


    @Step("Проверить, что сообщение [Ответ верен] отображается")
    public TaskPreviewPage checkAnswerIsCorrectMsgIsVisible() {
        elAnswerIsCorrectMsg.shouldBe(Condition.visible);
        return this;
    }

    @Step("Проверить, что сообщение [Ответ неверен] отображается")
    public TaskPreviewPage checkAnswerIsWrongMsgIsVisible() {
        elAnswerIsWrongMsg.shouldBe(Condition.visible);
        return this;
    }

    @Step("Нажать на кнопку [Ответить]")
    public TaskPreviewPage clickAnswerBtn() {
        elAnswerBtn.click();
        return this;
    }

    @Step("Нажать на кнопку [Назад к редактированию]")
    public TaskPreviewPage clickGoToEditBtn() {
        elGoToEditBtn.click();
        return this;
    }

    /**
     *
     * @param symbols
     * @return
     * Работает с формами ответов: "Ввод строки", "Ввод числа"
     */
    @Step("Очистить и ввести символы {symbols} в поле ввода ответа")
    public TaskPreviewPage enterSymbolsToAnswerFieldInput(String symbols) {
        elAnswerFieldInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), symbols);
        return this;
    }

    /**
     * Для формы ответа "Выпадающий список в тексте"
     */
    @Step("Нажать на кнопку селектора #{selectorNumber}")
    public TaskPreviewPage clickInlineChoiceAnswerSelector(int selectorNumber) {
        elsInlineChoiceAnswerSelectors.get(selectorNumber).click();
        return this;
    }

    /**
     * Для формы ответа "Выпадающий список в тексте"
     */
    @Step("Нажать на кнопку варианта ответа {optionText} в селекторе")
    public TaskPreviewPage clickInlineChoiceAnswerOptionInSelectorBtn(String optionText) {
        elsInlineChoiceAnswerInSelectorListBtns.find(Condition.matchesText(optionText)).click();
        return this;
    }





}
