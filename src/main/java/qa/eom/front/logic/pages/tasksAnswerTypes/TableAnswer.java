package qa.eom.front.logic.pages.tasksAnswerTypes;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import qa.eom.front.logic.pages.TaskConstructorPage;

import static com.codeborne.selenide.Selenide.*;

public class TableAnswer extends TaskConstructorPage {


    private SelenideElement elSetTableRowsInput = $x("//input[@id='tableRows']");
    private SelenideElement elSetTableColumnsInput = $x("//input[@id='tableColumns']");
    private SelenideElement elInsertTableBtn = $x("//button[.//span[contains(text(),'вставить таблицу')]]");
    private ElementsCollection elsCellsMenuBtns = $$x("//td//button");
    private ElementsCollection elsCellsFieldInputs = $$x("//td//textarea");
    private ElementsCollection elsColumnMenuOptionsBtns = $$x("//*[@role='menu' and .//*[contains(text(),'Добавить колонку')]]//div[@style]//*");
    private ElementsCollection elsRowMenuOptionsBtns = $$x("//*[@role='menu' and .//*[contains(text(),'Добавить строку')]]//div[@style]//*");
    private SelenideElement elCorrectAnswerTableModeBtn = $x("//button[.//*[contains(text(),'Режим выбора правильного ответа')]]");
    private SelenideElement elEditTableModeBtn = $x("//button[.//*[contains(text(),'Редактирование таблицы')]]");


    @Step("Ввести символы {symbols} в ячейку таблицы #{cellNumber}")
    public TableAnswer enterSymbolsToTableCellInput(int cellNumber, String symbols) {
        elsCellsFieldInputs.get(cellNumber).sendKeys(symbols);
        sleep(110);
        return this;
    }

    @Step("Ввести количество строк {rowsNumber} для таблицы")
    public TableAnswer enterNumberOfTableRowsInput(String rowsNumber) {
        elSetTableRowsInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), rowsNumber);
        sleep(110);
        return this;
    }

    @Step("Ввести количество столбиков {columnsNumber} для таблицы")
    public TableAnswer enterNumberOfTableColumnsInput(String columnsNumber) {
        elSetTableColumnsInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), columnsNumber);
        sleep(110);
        return this;
    }

    @Step("Нажать кнопку [Вставить таблицу]")
    public TableAnswer clickInsertTableBtn() {
        elInsertTableBtn.click();
        return this;
    }

    @Step("Нажать кнопку [Меню столбика/строки]")
    public TableAnswer clickCellMenuBtn(int cellNumber) {
        elsCellsMenuBtns.get(cellNumber).click();
        return this;
    }

    @Step("Нажать кнопку {option} в меню ячейки строки")
    public TableAnswer clickRowMenuOptionBtn(String option) {
        elsRowMenuOptionsBtns.find(Condition.text(option)).click();
        return this;
    }

    @Step("Нажать кнопку {option} в меню ячейки столбика")
    public TableAnswer clickColumnMenuOptionBtn(String option) {
        elsColumnMenuOptionsBtns.find(Condition.text(option)).click();
        return this;
    }

    @Step("Проверить, что количество ячеек таблицы равно {cellsAmount}")
    public TableAnswer checkTableCellsNumberEquals(int cellsAmount) {
        elsCellsFieldInputs.shouldHaveSize(cellsAmount);
        return this;
    }

    @Step("Нажать кнопку [Режим выбора правильного ответа]")
    public TableAnswer clickCorrectAnswerTableModeBtn() {
        elCorrectAnswerTableModeBtn.click();
        return this;
    }

    @Step("Нажать кнопку [Редактирование таблицы]")
    public TableAnswer clickEditTableModeBtn() {
        elEditTableModeBtn.click();
        return this;
    }

    @Step("Нажать на ячейку {cellText} в режиме выбора правильного ответа")
    public TableAnswer clickCellInCorrectAnswerMode(String cellText) {
        elsCellsFieldInputs.find(Condition.exactText(cellText)).click();
        return this;
    }

}
