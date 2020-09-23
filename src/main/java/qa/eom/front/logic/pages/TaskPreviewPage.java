package qa.eom.front.logic.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.WebElementsCollection;
import io.qameta.allure.Step;
import org.apache.commons.lang3.ObjectUtils;
import org.openqa.selenium.Keys;
import qa.eom.front.logic.RandomChecker;

import static com.codeborne.selenide.Selenide.*;

public class TaskPreviewPage implements RandomChecker {


    private TaskConstructorPage taskConstructorPage = new TaskConstructorPage();
    private SelenideElement elAnswerIsCorrectMsg = $x("//p[contains(text(),'Ответ верен')]");
    private SelenideElement elAnswerIsWrongMsg = $x("//p[contains(text(),'Ответ неверен')]");
    private SelenideElement elAnswerBtn = $x("//button[.//*[contains(text(),'Ответить')]]");
    private SelenideElement elGoToEditBtn = $x("//button[.//*[contains(text(),'Назад к редактированию')]]");

    private SelenideElement elAnswerFieldInput = $x("//input");
    private ElementsCollection elsInlineChoiceAnswerSelectors = $$x("//*[@role='button']");
    private ElementsCollection elsInlineChoiceAnswerInSelectorListBtns = $$x("//li[@role='option'][//ul[@role='listbox']]");
    private ElementsCollection elsMultipleAnswerOptions = $$x("//*[@title and @ style]");


    @Step("Проверить, что сообщение [Ответ верен] отображается")
    public String[] getMultipleAnswerOptionsText() {
        String answerOptionsText[] = new String[elsMultipleAnswerOptions.size()];
        for (int i = 0; i < elsMultipleAnswerOptions.size(); i++) {
            answerOptionsText[i] = elsMultipleAnswerOptions.get(i).getText();
        }
        return answerOptionsText;
    }

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

    /**
     * Для формы ответа "Выбор нескольких вариантов ответа"
     */
    @Step("Нажать на кнопку варианта ответа {optionText}")
    public TaskPreviewPage clickMultipleAnswerOptionBtn(String optionText) {
        elsMultipleAnswerOptions.find(Condition.attribute("title", optionText)).click();
        return this;
    }

    /**
     * Для формы ответа "Выбор нескольких вариантов ответа"
     */
    @Step("{optionText}")
    public TaskPreviewPage checkMultipleAnswerOptionsRandom() {
        int randomFailCounter = 0;
        while (randomFailCounter < 5) {
            String[] answerOptionsOrderOne = getMultipleAnswerOptionsText();
            clickGoToEditBtn();
            taskConstructorPage.clickPreviewTaskBtn();
            String[] answerOptionsOrderTwo = getMultipleAnswerOptionsText();
            if (isOptionsOrderDifferent(answerOptionsOrderOne, answerOptionsOrderTwo) == true) {
                return this;
            }
            randomFailCounter++;
        }
        throw new RuntimeException("Варианты ответов не перемешиваются");
    }



}
