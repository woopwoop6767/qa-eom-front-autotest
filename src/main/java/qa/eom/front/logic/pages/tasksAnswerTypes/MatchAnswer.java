package qa.eom.front.logic.pages.tasksAnswerTypes;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import qa.eom.front.logic.pages.TaskConstructorPage;

import static com.codeborne.selenide.Selenide.*;

public class MatchAnswer extends TaskConstructorPage {


    private String jsScriptForClickToInvisibleElements = "arguments[0].click();";
    private SelenideElement elAddMatchingBtn = $x("//button[.//*[contains(text(),'добавить соответствие')]]");
    private ElementsCollection elsTaskInputs = $$x("//*[contains(text(),'Задание №')]/..//div[@role='textbox']");
    private ElementsCollection elsAnswerInputs = $$x("//*[contains(text(),'Ответ №')]/..//div[@role='textbox']");
    private ElementsCollection elsDeleteMatchingBtns = $$x("//*[contains(text(),'Задание №')]/../../../../../../../../..//button[not (@title)]");

    @Step("Нажать кнопку [добавить соответствие]")
    public MatchAnswer clickAddMatchingBtn() {
        elAddMatchingBtn.click();
        return this;
    }

    @Step("Ввести символы {symbols} в поле задания #{taskNumber}")
    public MatchAnswer enterSymbolsToTaskFieldInput(String symbols, int taskNumber) {
        elsTaskInputs
                .shouldBe(CollectionCondition.sizeGreaterThanOrEqual(taskNumber + 1))
                .get(taskNumber)
                .sendKeys(symbols);
        sleep(110);
        return this;
    }

    @Step("Ввести символы {symbols} в поле ответа #{answerNumber}")
    public MatchAnswer enterSymbolsToAnswerFieldInput(String symbols, int answerNumber) {
        elsAnswerInputs
                .shouldBe(CollectionCondition.sizeGreaterThanOrEqual(answerNumber + 1))
                .get(answerNumber)
                .sendKeys(symbols);
        sleep(110);
        return this;
    }

    @Step("Нажать кнопку удаления [x] соответствия #{matchingNumber}")
    public MatchAnswer clickDeleteMatchingBtn(int matchingNumber) {
        ((JavascriptExecutor) WebDriverRunner.getWebDriver())
                .executeScript(jsScriptForClickToInvisibleElements,
                        elsDeleteMatchingBtns
                                .shouldBe(CollectionCondition.sizeGreaterThanOrEqual(matchingNumber + 1))
                                .get(matchingNumber));
        return this;
    }

    @Step("Проверить, что количество соответствий равно {numberOfMatching}")
    public MatchAnswer checkNumberOfMatchingEquals(int numberOfMatching) {
        elsAnswerInputs.shouldHaveSize(numberOfMatching);
        return this;
    }
}
