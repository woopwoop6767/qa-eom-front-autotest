package qa.eom.front.logic.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;

public class TaskPreviewPage {


    private TaskConstructorPage taskConstructorPage = new TaskConstructorPage();
    private SelenideElement elAnswerIsCorrectMsg = $x("//p[contains(text(),'Ответ верен')]");
    private SelenideElement elAnswerIsWrongMsg = $x("//p[contains(text(),'Ответ неверен')]");
    private SelenideElement elAnswerBtn = $x("//button[.//*[contains(text(),'Ответить')]]");
    private SelenideElement elGoToEditBtn = $x("//button[.//*[contains(text(),'Назад к редактированию')]]");

    private SelenideElement elAnswerFieldInput = $x("//input");
    private ElementsCollection elsInlineChoiceAnswerSelectors = $$x("//*[@role='button']");
    private ElementsCollection elsInlineChoiceAnswerInSelectorListBtns = $$x("//li[@role='option'][//ul[@role='listbox']]");
    private ElementsCollection elsMultipleAnswerOptions = $$x("//*[@title and @ style]");
    private ElementsCollection elsSingleAnswerOptions = $$x("//*[contains(text(),'Укажите правильный вариант ответа')]/..//*[@title]");
    private ElementsCollection elsTableAnswerCellInputs = $$x("//textarea");


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
     * Для форм ответа: "Выбор нескольких вариантов ответа"
     */
    @Step("Проверить перемешивание по тексту ответов для формы {answerOptionsOrder}")
    private boolean checkOptionsRandom(ElementsCollection answerOptionsOrder) {
        int randomFailCounter = 0;
        while (randomFailCounter < 5) {
            List<String> answerOptionsOrderOne = answerOptionsOrder.stream()
                    .map(val -> val.getText()).collect(Collectors.toList());
            clickGoToEditBtn();
            taskConstructorPage.clickPreviewTaskBtn();
            List<String> answerOptionsOrderTwo = answerOptionsOrder.stream()
                    .map(val -> val.getText()).collect(Collectors.toList());
            for (int i = 0; i < answerOptionsOrder.size(); i++) {
                if (!answerOptionsOrderOne.get(i).equals(answerOptionsOrderTwo.get(i))) {
                    return true;
                }
            }
            randomFailCounter++;
        }
        throw new RuntimeException("Варианты ответов не перемешиваются");
    }

    /**
     * Для формы ответа "Выбор нескольких вариантов ответа"
     */
    @Step("Проверить перемешивание вариантов ответа для формы [Выбор нескольких вариантов ответа]")
    public TaskPreviewPage checkMultipleAnswerOptionsRandom() {
        checkOptionsRandom(elsMultipleAnswerOptions);
        return this;
    }

    /**
     * Для формы ответа "Выбор одного ответа"
     */
    @Step("Проверить перемешивание вариантов ответа для формы [Выбор одного ответа]")
    public TaskPreviewPage checkSingleAnswerOptionsRandom() {
        checkOptionsRandom(elsSingleAnswerOptions);
        return this;
    }

    /**
     * Для формы ответа "Выбор одного ответа"
     */
    @Step("Нажать на кнопку варианта ответа {optionText}")
    public TaskPreviewPage clickSingleAnswerOptionBtn(String optionText) {
        elsSingleAnswerOptions.find(Condition.attribute("title", optionText)).click();
        return this;
    }

    /**
     * Для формы ответа "Заполнение таблицы"
     */
    @Step("Ввести символы {symbols} в ячейку #{cellNumber}")
    public TaskPreviewPage enterSymbolsToCellInput(int cellNumber, String symbols) {
        elsTableAnswerCellInputs.get(cellNumber).sendKeys(Keys.chord(Keys.CONTROL, "a"), symbols);
        return this;
    }


}
