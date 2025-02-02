package qa.eom.front.logic.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;

public class TaskPreviewPage {


    private TaskConstructorPage taskConstructorPage = new TaskConstructorPage();
    private SelenideElement elAnswerIsCorrectMsg = $x("//p[contains(text(),'Ответ верен')]");
    private SelenideElement elAnswerIsWrongMsg = $x("//p[contains(text(),'Ответ неверен')]");
    private SelenideElement elTeacherVerificationMsg = $x("//p[contains(text(),'Ответ требует проверки учителем')]");
    private SelenideElement elAnswerBtn = $x("//button[.//*[contains(text(),'Ответить')]]");
    private SelenideElement elGoToEditBtn = $x("//button[.//*[contains(text(),'Назад к редактированию')]]");

    private SelenideElement elAnswerFieldInput = $x("//input");
    private ElementsCollection elsInlineChoiceAnswerSelectors = $$x("//*[@role='button']");
    private ElementsCollection elsInlineChoiceAnswerInSelectorListBtns = $$x("//li[@role='option'][//ul[@role='listbox']]");
    private ElementsCollection elsMultipleAnswerOptions = $$x("//*[contains(text(),'Укажите один или несколько')]/..//*[@title]//span");
    private ElementsCollection elsSingleAnswerOptions = $$x("//*[contains(text(),'Укажите правильный вариант ответа')]/..//*[@title]");
    private ElementsCollection elsTableAnswerCellInputs = $$x("//textarea");
    private ElementsCollection elsTimelineAnswerMarksPositions = $$x("//*[contains(@style,'min-width: calc(')]");
    private ElementsCollection elsTimelineAnswerOptionsInBlock = $$x("//p[contains(text(),'Ответы')]/..//*[@title]");
    private ElementsCollection elsTimelineAnswerMarksDescriptions = $$x("//*[contains(@class,'item-label-paper')]/..//*[@title]");
    private ElementsCollection elsTimelineAnswerOptionsInMarkList = $$x("//ul//*[@title]");
    private SelenideElement elsTimelineAnswerOptionsBlock = $x("//p[contains(text(),'Ответы:')]");
    private SelenideElement elFreeAnswerFieldInput = $x("//textarea[not (@readonly)]");
    private ElementsCollection elsGapTextMatchAnswerOptionsInBlock = $$x("//div[contains(@style,'solid')]//span");
    private ElementsCollection elsGapTextMatchAnswerLocations = $$x("//*[contains(text(),'Заполните пропуски в тексте:')]/../*/*/div[not (contains(@style,'solid'))]/div/span");
    private ElementsCollection elsGlobalAnswerOptionsInBlock = $$x("//span[contains(text(),'Ответы')]/../..//div[@title]//span");
    private ElementsCollection elsGroupAnswerGroupBlocks = $$x("//button[@title]/..//div[@title]//span");
    private ElementsCollection elsGroupAnswerOptionsInGroups = $$x("//button[contains(@title,'вернуть')]/../../..//*[contains(@style,'transition-duration')]//span");
    private ElementsCollection elsOrderAnswerOptions = $$x("//span[contains(text(),'Расположите элементы')]/..//div[@title]//span");
    private ElementsCollection elsMatchAnswerMatchings = $$x("//*[name()='path' and contains(@d,'M4')]/../..//*[@title]//span");
    private ElementsCollection elsMatchAnswerOptionsInMatchings = $$x("//*[contains(@style,'245')]//*[contains(@style,'opacity')]//span");



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

    @Step("Проверить, что сообщение [Ответ требует проверки учителем] отображается")
    public TaskPreviewPage checkTeacherVerificationMsgIsVisible() {
        elTeacherVerificationMsg.shouldBe(Condition.visible);
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
        elsInlineChoiceAnswerInSelectorListBtns
                .find(Condition.matchesText(optionText))
                .click();
        return this;
    }

    /**
     * Для формы ответа "Выбор нескольких вариантов ответа"
     */
    @Step("Нажать на кнопку варианта ответа {optionText}")
    public TaskPreviewPage clickMultipleAnswerOptionBtn(String optionText) {
        elsMultipleAnswerOptions.find(Condition.exactText(optionText)).click();
        return this;
    }

    /**
     * Для форм ответа: "Выбор нескольких вариантов ответа", "Подстановка слов в пропуски в тексте", "Распределение элементов по группам",
     * "Упорядочивание элементов".
     */
    @Step("Проверить перемешивание по тексту ответов для формы {answerOptionsOrder}")
    private boolean checkOptionsRandom(ElementsCollection answerOptionsOrder) {
        int randomFailCounter = 0;
        while (randomFailCounter < 5) {
            List<String> answerOptionsOrderOne = answerOptionsOrder.shouldBe(CollectionCondition.sizeGreaterThan(1)).stream()
                    .map(val -> val.getText()).collect(Collectors.toList());
            clickGoToEditBtn();
            taskConstructorPage.clickPreviewTaskBtn();
            List<String> answerOptionsOrderTwo = answerOptionsOrder.shouldBe(CollectionCondition.sizeGreaterThan(1)).stream()
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
     * Для форм ответа: "Подстановка слов в пропуски в тексте".
     */
    @Step("Проверить алфавитрый порядок вариантов ответа в блоке {answerOptionsOrder}")
    private boolean checkOptionsAlphabetOrder(ElementsCollection answerOptionsOrder) {
        List<String> optionsOrder = answerOptionsOrder.stream()
                .map(val -> val.getText()).collect(Collectors.toList());
        List<String> optionsToSort = answerOptionsOrder.stream()
                .map(val -> val.getText()).collect(Collectors.toList());
        Collections.sort(optionsToSort);

        if (optionsToSort.equals(optionsOrder)) {
            return true;
        }

        throw new RuntimeException("Варианты ответов в блоке не расставляются в алфавитном порядке");
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
        elsSingleAnswerOptions
                .find(Condition.attribute("title", optionText))
                .click();
        return this;
    }

    /**
     * Для формы ответа "Заполнение таблицы"
     */
    @Step("Ввести символы {symbols} в ячейку #{cellNumber}")
    public TaskPreviewPage enterTableAnswerSymbolsToCellInput(int cellNumber, String symbols) {
        elsTableAnswerCellInputs
                .get(cellNumber)
                .sendKeys(Keys.chord(Keys.CONTROL, "a"), symbols);
        return this;
    }

    /**
     * Для формы ответа "Лента времени"
     */
    @Step("Проверить, что отметка {markNumber} находится в позиции {percentPosition}")
    public TaskPreviewPage checkTimelineAnswerMarkPosition(int markNumber, String percentPosition) {
        elsTimelineAnswerMarksPositions
                .get(markNumber)
                .shouldHave(Condition.attribute("style",
                        "min-width: calc(".concat(percentPosition).concat("%);")));
        return this;
    }

    /**
     * Для формы ответа "Лента времени"
     */
    @Step("Нажать на вариант ответа {answerOption} в блоке ответов")
    public TaskPreviewPage clickTimelineAnswerOptionInBlock(String answerOption) {
        elsTimelineAnswerOptionsInBlock
                .find(Condition.attribute("title", answerOption))
                .click();
        return this;
    }

    /**
     * Для формы ответа "Лента времени"
     */
    @Step("Нажать на отметку {markText}")
    public TaskPreviewPage clickTimelineAnswerMark(String markText) {
        elsTimelineAnswerMarksDescriptions
                .find(Condition.attribute("title", markText))
                .click();
        return this;
    }

    /**
     * Для формы ответа "Лента времени"
     */
    @Step("Нажать на вариант ответа {option} в списке отметки")
    public TaskPreviewPage clickTimelineAnswerOptionInMark(String option) {
        elsTimelineAnswerOptionsInMarkList
                .find(Condition.attribute("title", option))
                .click();
        return this;
    }

    /**
     * Для формы ответа "Лента времени"
     */
    @Step("Нажать на блок вариантов ответа [Ответы:]")
    public TaskPreviewPage clickTimelineAnswerOptionsBlock() {
        elsTimelineAnswerOptionsBlock.click();
        return this;
    }

    /**
     * Для формы ответа "Открытый ответ"
     */
    @Step("Ввести символы {symbols} в ячейку #{cellNumber}")
    public TaskPreviewPage enterFreeAnswerSymbolsToAnswerFieldInput(String symbols) {
        elFreeAnswerFieldInput
                .sendKeys(Keys.chord(Keys.CONTROL, "a"), symbols);
        return this;
    }

    /**
     * Для формы ответа "Подстановка слов в пропуски в тексте"
     */
    @Step("Проверить видимость {shouldBeVisible} варианта ответа {optionText} в блоке ответов")
    public TaskPreviewPage checkGapTextMatchAnswerOptionVisibilityInBlock(String optionText, boolean shouldBeVisible) {
        if (shouldBeVisible == true) {
            elsGapTextMatchAnswerOptionsInBlock
                    .shouldHave(CollectionCondition.sizeGreaterThan(0))
                    .find(Condition.text(optionText))
                    .shouldBe(Condition.visible);
        } else {
            elsGapTextMatchAnswerOptionsInBlock
                    .shouldHave(CollectionCondition.sizeGreaterThanOrEqual(0))
                    .find(Condition.text(optionText))
                    .shouldNotBe(Condition.visible);
        }
        return this;
    }

    /**
     * Для формы ответа "Подстановка слов в пропуски в тексте"
     */
    @Step("Нажать на вариант {optionText} ответа в блоке ответов")
    public TaskPreviewPage clickGapTextMatchAnswerOptionInBlock(String optionText) {
        elsGapTextMatchAnswerOptionsInBlock
                .find(Condition.text(optionText))
                .click();
        return this;
    }

    /**
     * Для формы ответа "Подстановка слов в пропуски в тексте"
     */
    @Step("Нажать на поле ответа {answerLocationNumber}")
    public TaskPreviewPage clickGapTextMatchAnswerLocation(int answerLocationNumber) {
        elsGapTextMatchAnswerLocations
                .get(answerLocationNumber)
                .click();
        return this;
    }

    /**
     * Для формы ответа "Подстановка слов в пропуски в тексте"
     */
    @Step("Проверить перемешивание вариантов ответа для формы [Подстановка слов в пропуски в тексте]")
    public TaskPreviewPage checkGapTextMatchAnswerOptionsRandom() {
        checkOptionsRandom(elsGapTextMatchAnswerOptionsInBlock);
        return this;
    }

    /**
     * Для формы ответа "Подстановка слов в пропуски в тексте"
     */
    @Step("Проверить алфавитрый порядок вариантов ответа в блоке")
    public TaskPreviewPage checkGapTextMatchAnswerOptionsAlphabetOrder() {
        checkOptionsAlphabetOrder(elsGapTextMatchAnswerOptionsInBlock);
        return this;
    }

    /**
     * Для формы ответа "Подстановка слов в пропуски в тексте"
     */
    @Step("Проверить что варианты ответов не перемешиваются в блоке")
    public TaskPreviewPage checkGapTextMatchAnswerOptionsOrder(String optionText, int optionPosition) {
        elsGapTextMatchAnswerOptionsInBlock
                .shouldBe(CollectionCondition.sizeGreaterThanOrEqual(optionPosition + 1))
                .get(optionPosition)
                .shouldHave(Condition.text(optionText));
        return this;
    }

    /**
     * Для форм ответа: "Распределение элементов по группам", "Установление соответствия".
     */
    @Step("Проверить перемешивание вариантов ответа в блоке ответов")
    public TaskPreviewPage checkGlobalAnswerOptionsRandom() {
        checkOptionsRandom(elsGlobalAnswerOptionsInBlock);
        return this;
    }

    /**
     * Для форм ответа: "Распределение элементов по группам", "Установление соответствия".
     */
    @Step("Нажать на вариант ответа {answerText} в блоке ответов")
    public TaskPreviewPage clickGlobalAnswerOptionInBlock(String answerText) {
        elsGlobalAnswerOptionsInBlock
                .shouldBe(CollectionCondition.sizeGreaterThan(0))
                .find(Condition.exactText(answerText))
                .click();
        return this;
    }

    /**
     * Для формы ответа "Распределение элементов по группам"
     */
    @Step("Нажать на блок группы {groupText}")
    public TaskPreviewPage clickGroupAnswerGroupBlock(String groupText) {
        elsGroupAnswerGroupBlocks
                .shouldBe(CollectionCondition.sizeGreaterThan(0))
                .find(Condition.exactText(groupText))
                .click();
        return this;
    }

    /**
     * Для формы ответа "Распределение элементов по группам"
     */
    @Step("Нажать кнопку открытия/закрытия блока группы {groupText}")
    public TaskPreviewPage clickGroupAnswerListBtn(String groupText) {
        elsGroupAnswerGroupBlocks
                .shouldBe(CollectionCondition.sizeGreaterThan(0))
                .find(Condition.exactText(groupText))
                .$x("./ancestor::div/button[contains(@title,'вернуть')]")
                .click();
        return this;
    }

    /**
     * Для формы ответа "Распределение элементов по группам"
     */
    @Step("Нажать на вариант ответа {answerText} в группе")
    public TaskPreviewPage clickGroupAnswerOptionInGroup(String answerText) {
        elsGroupAnswerOptionsInGroups
                .shouldBe(CollectionCondition.sizeGreaterThan(0))
                .find(Condition.exactText(answerText))
                .click();
        return this;
    }

    /**
     * Для формы ответа "Упорядочивание элементов"
     */
    @Step("Проверить перемешивание вариантов ответа для формы [Упорядочивание элементов]")
    public TaskPreviewPage checkOrderAnswerAnswerOptionsRandom() {
        checkOptionsRandom(elsOrderAnswerOptions);
        return this;
    }

    /**
     * Для форм ответа: "Упорядочивание элементов".
     */
    @Step("Нажать стрелку [Вверх] или [Вниз] варианта ответа {optionText} в зависимости от позиции ответа ({optionLine}) и позиции перемещения ({moveOptionTo})")
    public TaskPreviewPage clickOptionArrowBtn(String optionText, int moveOptionTo) {
        String xpathArrowUp = "./ancestor::*[@style='opacity: 1;']//*[name()='svg' and ./*[contains(@d,'M13')]]";
        String xpathArrowDown = "./ancestor::*[@style='opacity: 1;']//*[name()='svg' and ./*[contains(@d,'M11')]]";
        int optionLine = elsOrderAnswerOptions.indexOf(elsOrderAnswerOptions.find(Condition.exactText(optionText)));
        if (optionLine > moveOptionTo) {
            elsOrderAnswerOptions
                    .find(Condition.exactText(optionText))
                    .$x(xpathArrowUp)
                    .click();
        } else if (optionLine < moveOptionTo) {
            elsOrderAnswerOptions
                    .find(Condition.exactText(optionText))
                    .$x(xpathArrowDown)
                    .click();
        }
        return this;
    }

    /**
     * Для формы ответа "Упорядочивание элементов"
     */
    @Step("Переместить вариант ответа {optionText} в позицию #{optionDestinationLine}")
    public TaskPreviewPage moveOrderAnswerOptionToPosition(String optionText, int optionDestinationLine) {
        int failCounter = 0;
        while (!elsOrderAnswerOptions.shouldBe(CollectionCondition.sizeGreaterThanOrEqual(optionDestinationLine + 1)).get(optionDestinationLine)
                .getText().equals(optionText) && failCounter <= 10) {
            clickOptionArrowBtn(optionText, optionDestinationLine);
            failCounter++;
        }
        if (failCounter >= 10) {
            throw new RuntimeException("Ошибка сортировки. Превышено число попыток перемещения варианта ответа.");
        }
        return this;
    }

    /**
     * Для формы ответа "Установление соответствия"
     */
    @Step("Проверить перемешивание соответствий для формы [Установление соответствия]")
    public TaskPreviewPage checkMatchAnswerMatchingsRandom() {
        checkOptionsRandom(elsMatchAnswerMatchings);
        return this;
    }

    /**
     * Для формы ответа "Установление соответствия"
     */
    @Step("Нажать на соответствие {matchingText}")
    public TaskPreviewPage clickMatchAnswerMatchingBlock(String matchingText) {
        elsMatchAnswerMatchings
                .shouldBe(CollectionCondition.sizeGreaterThan(0))
                .find(Condition.exactText(matchingText))
                .click();
        return this;
    }

    /**
     * Для формы ответа "Установление соответствия"
     */
    @Step("Нажать на вариант ответа {answerText} в соответствии")
    public TaskPreviewPage clickMatchAnswerOptionInMatching(String answerText) {
        elsMatchAnswerOptionsInMatchings
                .shouldBe(CollectionCondition.sizeGreaterThan(0))
                .find(Condition.exactText(answerText))
                .click();
        return this;
    }

}
