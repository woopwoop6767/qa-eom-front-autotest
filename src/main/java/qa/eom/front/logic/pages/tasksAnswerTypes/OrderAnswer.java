package qa.eom.front.logic.pages.tasksAnswerTypes;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import qa.eom.front.logic.pages.TaskConstructorPage;

import static com.codeborne.selenide.Selenide.*;

public class OrderAnswer extends TaskConstructorPage {


    private String jsScriptForClickToInvisibleElements = "arguments[0].click();";
    private SelenideElement elAddAnswerBtn = $x("//button[.//*[contains(text(),'добавить ответ')]]");
    private ElementsCollection elsAnswerInputs = $$x("//*[contains(text(),'Ответ №')]/..//*[@role='textbox']");
    private ElementsCollection elsDeleteAnswerBtns = $$x("//*[contains(text(),'Ответ №')]/../../../..//button[not (@title)]");



    @Step("Нажать кнопку [добавить ответ]")
    public OrderAnswer clickAddAnswerBtn() {
        elAddAnswerBtn.click();
        return this;
    }

    @Step("Ввести символы {symbols} в поле ответа #{answerNumber}")
    public OrderAnswer enterSymbolsToAnswerFieldInput(String symbols, int answerNumber) {
        elsAnswerInputs
                .shouldBe(CollectionCondition.sizeGreaterThanOrEqual(answerNumber + 1))
                .get(answerNumber)
                .sendKeys(symbols);
        sleep(110);
        return this;
    }

    @Step("Нажать кнопку удаления [x] ответа #{answerNumber}")
    public OrderAnswer clickDeleteAnswerBtn(int answerNumber) {
        ((JavascriptExecutor) WebDriverRunner.getWebDriver())
                .executeScript(jsScriptForClickToInvisibleElements,
                        elsDeleteAnswerBtns
                                .shouldBe(CollectionCondition.sizeGreaterThanOrEqual(answerNumber + 1))
                                .get(answerNumber));
        return this;
    }

    @Step("Проверить, что количество ответов равно {numberOfAnswers}")
    public OrderAnswer checkNumberOfAnswersEquals(int numberOfAnswers) {
        elsAnswerInputs.shouldHaveSize(numberOfAnswers);
        return this;
    }
}
