package qa.eom.front.logic.pages.tasksAnswerTypes;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import qa.eom.front.logic.pages.TaskConstructorPage;

import static com.codeborne.selenide.Selenide.*;

public class GroupAnswer extends TaskConstructorPage {


    private String jsScriptForClickToInvisibleElements = "arguments[0].click();";
    SelenideElement elAddGroupBtn = $x("//button[.//*[contains(text(),'добавить группу')]]");
    ElementsCollection elsAddAnswerBtns = $$x("//button[.//*[contains(text(),'добавить ответ')]]");
    ElementsCollection elsGroupNameInputs = $$x("//*[contains(text(),'Введите название группы')]/..//*[@role='textbox']");
    ElementsCollection elsGroupAnswerInputs = $$x("//*[contains(text(),'Введите ответ')]/..//*[@role='textbox']");
    ElementsCollection elsDeleteGroupBtns = $$x("//*[contains(text(),'Ответов в группе')]/../../../../div[2]//button");
    ElementsCollection elsDeleteAnswerBtns = $$x("//*[contains(text(),'Ответ № ')]/../../../..//button[not (@title)]");



    @Step("Нажать кнопку [добавить группу]")
    public GroupAnswer clickAddGroupBtn() {
        elAddGroupBtn.click();
        return this;
    }

    @Step("Нажать кнопку [добавить ответ] для группы #{groupNumber}")
    public GroupAnswer clickAddAnswerBtn(int groupNumber) {
        elsAddAnswerBtns
                .shouldBe(CollectionCondition.sizeGreaterThan(0))
                .get(groupNumber)
                .click();
        return this;
    }

    @Step("Ввести символы {symbols} в поле названия группы #{groupNumber}")
    public GroupAnswer enterSymbolsToGroupNameFieldInput(String symbols, int groupNumber) {
        elsGroupNameInputs
                .shouldBe(CollectionCondition.sizeGreaterThan(0))
                .get(groupNumber)
                .sendKeys(symbols);
        sleep(110);
        return this;
    }

    @Step("Ввести символы {symbols} в поле ответа группы #{answerNumber}")
    public GroupAnswer enterSymbolsToAnswerFieldInput(String symbols, int answerNumber) {
        elsGroupAnswerInputs
                .shouldBe(CollectionCondition.sizeGreaterThan(0))
                .get(answerNumber)
                .sendKeys(symbols);
        sleep(110);
        return this;
    }

    @Step("Нажать кнопку удаления [x] группы #{groupNumber}")
    public GroupAnswer clickDeleteGroupBtn(int groupNumber) {
        ((JavascriptExecutor) WebDriverRunner.getWebDriver())
                .executeScript(jsScriptForClickToInvisibleElements,
                        elsDeleteGroupBtns
                                .shouldBe(CollectionCondition.sizeGreaterThan(0))
                                .get(groupNumber));
        return this;
    }

    @Step("Нажать кнопку удаления [x] ответа #{groupNumber}")
    public GroupAnswer clickDeleteAnswerBtn(int answerNumber) {
        ((JavascriptExecutor) WebDriverRunner.getWebDriver())
                .executeScript(jsScriptForClickToInvisibleElements,
                        elsDeleteAnswerBtns
                                .shouldBe(CollectionCondition.sizeGreaterThan(0))
                                .get(answerNumber));
        return this;
    }

    @Step("Проверить, что количество групп равно {numberOfGroups}")
    public GroupAnswer checkNumberOfGroupsEquals(int numberOfGroups) {
        elsGroupNameInputs.shouldHaveSize(numberOfGroups);
        return this;
    }

    @Step("Проверить, что количество ответов равно {numberOfAnswers}")
    public GroupAnswer checkNumberOfAnswersEquals(int numberOfAnswers) {
        elsGroupAnswerInputs.shouldHaveSize(numberOfAnswers);
        return this;
    }
}
