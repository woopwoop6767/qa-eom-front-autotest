package qa.eom.front.logic.pages.tasksAnswerTypes;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import qa.eom.front.logic.pages.TaskConstructorPage;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class GapTextMatchAnswer extends TaskConstructorPage {


    SelenideElement elTaskFieldInput = $x("//*[contains(text(),'Введите задание')]/..//div[@role='textbox']");
    ElementsCollection elsAnswerModeRadioBtns = $$x("//*[@role='radiogroup']//p");
    SelenideElement elAddOptionFieldInTaskFieldBtn = $x("//button[@title='Добавить место для подстановки внутри текста']");
    SelenideElement elEditAnswerOptionsBtn = $x("//button[.//*[contains(text(),'Редактировать подписи')]]");
    ElementsCollection elsOptionFieldInputs = $$x("//input[contains(@id,'select')]");
    SelenideElement elAddOptionBtn = $x("//button[.//*[contains(text(),'Добавить подпись')]]");
    SelenideElement elCloseModalEditOptionsBtn = $x("//button[.//*[contains(text(),'Закрыть')]]");
    ElementsCollection elsDeleteOptionBtns = $$x("//button[@title='Удалить вариант']");
    ElementsCollection elsLocationsForAnswerOptionsBtns = $$x("//figure");
    SelenideElement elSortSelector = $x("//*[contains(text(),'Сортировка:')]//following::*[@aria-haspopup][1]");
    ElementsCollection elsOptionsInSortSelectorList = $$x("//li[@role='option']");
    ElementsCollection elsAnswerOptionsInBlock = $$x("//div[@title and @style]");


    @Step("Ввести символы {symbols} в поле ввода задания")
    public GapTextMatchAnswer enterSymboslToTaskField(String symbols) {
        elTaskFieldInput.sendKeys(symbols);
        sleep(110);
        return this;
    }

    @Step("Нажать кнопку [Добавить отметку]")
    public GapTextMatchAnswer clickAnswerModeRadioBtn(String btnName) {
        elsAnswerModeRadioBtns
                .find(Condition.text(btnName))
                .click();
        return this;
    }

    @Step("Нажать кнопку [Добавить место для подстановки внутри текста]")
    public GapTextMatchAnswer clickAddOptionFieldInTaskFieldBtn() {
        elAddOptionFieldInTaskFieldBtn.click();
        return this;
    }

    @Step("Нажать кнопку [Редактировать подписи]")
    public GapTextMatchAnswer clickEditAnswerOptionsBtn() {
        elEditAnswerOptionsBtn.click();
        return this;
    }

    @Step("Ввести символы {symbols} в поле варианта ответа #{optionNumber}")
    public GapTextMatchAnswer enterSymbolsToOptionField(int optionNumber, String symbols) {
        elsOptionFieldInputs
                .get(optionNumber)
                .sendKeys(Keys.chord(Keys.CONTROL, "a"), symbols);
        sleep(110);
        return this;
    }

    @Step("Нажать кнопку [Добавить подпись]")
    public GapTextMatchAnswer clickAddOptioBtn() {
        elAddOptionBtn.click();
        return this;
    }

    @Step("Нажать кнопку [Закрыть] модальное окно [Редактирование подписей]")
    public GapTextMatchAnswer clickCloseModalEditOptionsBtn() {
        elCloseModalEditOptionsBtn.click();
        return this;
    }

    @Step("Нажать кнопку [Удалить вариант] для варианта #{optionNumber}")
    public GapTextMatchAnswer clicksDeleteOptionBtn(int optionNumber) {
        elsDeleteOptionBtns
                .get(optionNumber)
                .click();
        return this;
    }

    @Step("Нажать кнопку варианта ответа в поле [Задание] для варианта #{optionNumber}")
    public GapTextMatchAnswer clickLocationForAnswerOptionsBtn(int locationNumber) {
        elsLocationsForAnswerOptionsBtns
                .get(locationNumber)
                .click();
        return this;
    }

    @Step("Удалить вариант ответа #{optionNumber} в поле [Задание]")
    public GapTextMatchAnswer deleteLocationForAnswerOptions(int locationNumber) {
        elsLocationsForAnswerOptionsBtns
                .get(locationNumber)
                .click();
        elTaskFieldInput
                .sendKeys(Keys.DELETE);
        return this;
    }

    @Step("Проверить, что число полей для ввода ответа в поле [Задание] равно {numberOfOptions}")
    public GapTextMatchAnswer checkNumberOfLocationsForAnswerOptionsInTaskFieldEquals(int numberOfLocations) {
        elsLocationsForAnswerOptionsBtns.shouldHaveSize(numberOfLocations);
        return this;
    }

    @Step("Нажать кнопку открытия селектора [Сортировка]")
    public GapTextMatchAnswer clickSortSelectorBtn() {
        elSortSelector.click();
        return this;
    }

    @Step("Нажать кнопку {optionName} в списке селектора [Сортировка]")
    public GapTextMatchAnswer clickOptionInSortSelectorBtn(String optionName) {
        elsOptionsInSortSelectorList
                .find(Condition.text(optionName))
                .click();
        return this;
    }

    @Step("Переместить вариант ответа {optionName} из блока ответов в поле ввода ответа #{locationNumber}")
    public GapTextMatchAnswer moveAnswerOptionFromOptionsBlock(String optionName, int locationNumber) {
        Actions actions = new Actions(WebDriverRunner.getWebDriver());
        actions
                .moveToElement(elsAnswerOptionsInBlock
                        .find(Condition.attribute("title", optionName)))
                .pause(Duration.ofMillis(100))
                .clickAndHold()
                .moveToElement(elsLocationsForAnswerOptionsBtns.get(locationNumber))
                .moveByOffset(1, 0)
                .release().perform();
        return this;
    }



}
