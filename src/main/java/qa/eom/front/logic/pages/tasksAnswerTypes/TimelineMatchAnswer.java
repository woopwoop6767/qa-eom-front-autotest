package qa.eom.front.logic.pages.tasksAnswerTypes;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import qa.eom.front.logic.pages.TaskConstructorPage;

import static com.codeborne.selenide.Selenide.*;

public class TimelineMatchAnswer extends TaskConstructorPage {


    private String jsScriptForClickToInvisibleElements = "arguments[0].click();";
    private SelenideElement elAddMarkBtn = $x("//button[.//*[contains(text(),'Добавить отметку')]]");
    private ElementsCollection elsAddAnswerBtns = $$x("//button[.//*[contains(text(),'добавить отметку')]]");
    private ElementsCollection elsSetDateOfMarkBtns = $$x("//button[.//*[contains(text(),'Установить дату отметки')]]");
    private SelenideElement elAddDistractorBtn = $x("//button[.//*[contains(text(),'Добавить дистрактор')]]");
    private ElementsCollection elsDeleteMarkBtns = $$x("//*[contains(@style,'display')]/div[@class]/button[not (@title)]");
    private ElementsCollection elsDeleteAnswerInMarkBtns = $$x("//*[contains(text(),'Ответ № ')]/../../../..//button[not (@title)]");
    private ElementsCollection elsMarkDescriptionsInputs = $$x("//*[contains(text(),'Отметка № ')]/..//*[@role='textbox']");
    private ElementsCollection elsMarkAnswersInputs = $$x("//*[contains(text(),'Ответ № ')]/..//*[@role='textbox']");
    private ElementsCollection elsEmptyFieldsMarksAndAnswersValidMsgs = $$x("//p[contains(text(),'Варианты ответа должны быть заполнены.')]");
    private SelenideElement elDateDayMarkInput = $x("//*[contains(text(),'День')]/following::input[1]");
    private SelenideElement elDateMonthMarkInput = $x("//*[contains(text(),'Месяц')]/following::input[1]");
    private SelenideElement elDateYearMarkInput = $x("//*[contains(text(),'Год')]/following::input[1]");
    private SelenideElement elSaveDateOfMarkBtn = $x("//button[.//*[contains(text(),'Сохранить')]]");
    private SelenideElement elPositionTypeSelector = $x("//*[contains(text(),'Тип вывода')]/..//button");
    private ElementsCollection elsPositionTypeSelectorOptions = $$x("//span[@role='menuitem']//div[text()]");
    private ElementsCollection elsDistractorsFieldsInputs = $$x("//*[contains(text(),'Дистрактор №')]/..//div[@role='textbox']");


    @Step("Нажать кнопку [Добавить дистрактор]")
    public TimelineMatchAnswer clickAddDistractorBtn() {
        elAddDistractorBtn.click();
        return this;
    }

    @Step("Нажать кнопку удаления отметки #{markNumber}")
    public TimelineMatchAnswer clickDeleteMarkBtn(int markNumber) {
        ((JavascriptExecutor) WebDriverRunner.getWebDriver())
                .executeScript(jsScriptForClickToInvisibleElements,
                        elsDeleteMarkBtns.shouldBe(CollectionCondition.sizeGreaterThan(0)).get(markNumber));
        return this;
    }

    @Step("Нажать кнопку удаления ответа #{answerNumber}")
    public TimelineMatchAnswer clickDeleteAnswerInMarkBtn(int answerNumber) {
        ((JavascriptExecutor) WebDriverRunner.getWebDriver())
                .executeScript(jsScriptForClickToInvisibleElements,
                        elsDeleteAnswerInMarkBtns.shouldBe(CollectionCondition.sizeGreaterThan(0)).get(answerNumber));
        return this;
    }

    @Step("Нажать кнопку [Установить дату отметки] для отметки #{markNumber}")
    public TimelineMatchAnswer clickSetDateOfMarkBtn(int markNumber) {
        elsSetDateOfMarkBtns.get(markNumber).click();
        return this;
    }

    @Step("Нажать кнопку [добавить отметку] для отметки #{markNumber}")
    public TimelineMatchAnswer clickAddAnswerBtn(int markNumber) {
        elsAddAnswerBtns.get(markNumber).click();
        return this;
    }

    @Step("Нажать кнопку [Добавить отметку]")
    public TimelineMatchAnswer clickAddMarkBtn() {
        elAddMarkBtn.click();
        return this;
    }

    @Step("Ввести символы {symbols} в поле описания отметки #{markNumber}")
    public TimelineMatchAnswer enterSymbolsToMarkDescriptionField(int markNumber, String symbols) {
        elsMarkDescriptionsInputs.get(markNumber).sendKeys(Keys.chord(Keys.CONTROL, "a"), symbols);
        sleep(110);
        return this;
    }

    @Step("Ввести символы {symbols} в поле ответа #{answerNumber}")
    public TimelineMatchAnswer enterSymbolsToMarkAnswerField(int answerNumber, String symbols) {
        elsMarkAnswersInputs.get(answerNumber).sendKeys(Keys.chord(Keys.CONTROL, "a"), symbols);
        sleep(110);
        return this;
    }

    @Step("Проверить, что валидационные сообщения [Варианты ответа должны быть заполнены.] отображаются")
    public TimelineMatchAnswer checkEmptyFieldsMarksAndAnswersValidMsgs(int messagesNumber) {
        elsEmptyFieldsMarksAndAnswersValidMsgs.shouldHaveSize(messagesNumber)
                .stream().forEach(el -> el.shouldBe(Condition.visible));
        return this;
    }

    @Step("Проверить, что количество отметок равно {marksNumber}")
    public TimelineMatchAnswer checkNumberOfMarksEquals(int marksNumber) {
        elsMarkDescriptionsInputs.shouldHaveSize(marksNumber);
        return this;
    }

    @Step("Проверить, что количество полей ответа равно {answersNumber}")
    public TimelineMatchAnswer checkNumberOfAnswersEquals(int marksNumber) {
        elsMarkAnswersInputs.shouldHaveSize(marksNumber);
        return this;
    }

    @Step("Ввести число {number} в поле даты отметки [День]")
    public TimelineMatchAnswer enterNumberToDayOfMarkField(String number) {
        elDateDayMarkInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), number);
        sleep(110);
        return this;
    }

    @Step("Ввести число {number} в поле даты отметки [Месяц]")
    public TimelineMatchAnswer enterNumberToMonthOfMarkField(String number) {
        elDateMonthMarkInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), number);
        sleep(110);
        return this;
    }

    @Step("Ввести число {number} в поле даты отметки [Год]")
    public TimelineMatchAnswer enterNumberToYearOfMarkField(String number) {
        elDateYearMarkInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), number);
        sleep(110);
        return this;
    }

    @Step("Нажать кнопку [Сохранить] в диалоговом окне установки даты отметки")
    public TimelineMatchAnswer clickSaveDateOfMarkBtn() {
        elSaveDateOfMarkBtn.click();
        return this;
    }

    @Step("Нажать кнопку открытия селектора [Тип вывода]")
    public TimelineMatchAnswer clickPositionTypeSelector() {
        elPositionTypeSelector.click();
        return this;
    }

    @Step("Нажать кнопку {option} в селекторе [Тип вывода]")
    public TimelineMatchAnswer clickOptionInPositionTypeSelector(String option) {
        elsPositionTypeSelectorOptions.find(Condition.text(option)).click();
        return this;
    }

    @Step("Ввести символы {symbols} в поле дистрактора #{distractorNumber}")
    public TimelineMatchAnswer enterSymbolsToDistractorField(int distractorNumber, String symbols) {
        elsDistractorsFieldsInputs
                .get(distractorNumber)
                .sendKeys(Keys.chord(Keys.CONTROL, "a"), symbols);
        sleep(110);
        return this;
    }




}
