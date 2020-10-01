package qa.eom.front.logic.pages.tasksAnswerTypes;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import qa.eom.front.logic.pages.TaskConstructorPage;

import static com.codeborne.selenide.Selenide.*;

public class SingleAnswer extends TaskConstructorPage {


    private SelenideElement answerFieldInput = $x("//*[contains(text(),'Ответ')]/..//*[@role='textbox']");
    private ElementsCollection elsDistractorFieldsInput = $$x("//*[contains(text(),'Дистрактор')]/..//*[@role='textbox']");
    private SelenideElement elAddDistractorBtn = $x("//button[.//*[contains(text(),'Добавить дистрактор')]]");


    @Step("Ввести ответ {symbols} в поле ввода ответа")
    public SingleAnswer enterSymbolsToTaskFieldInput(String symbols) {
        answerFieldInput.sendKeys(symbols);
        sleep(110);
        return this;
    }

    @Step("Нажать кнопку [Добавить дистрактор]")
    public SingleAnswer clickAddDistractorBtn() {
        elAddDistractorBtn.click();
        return this;
    }

    @Step("Ввести символы {symbols} в поле ввода дистрактора #{distractorNumber}")
    public SingleAnswer enterSymbolsToDistractorFieldInput(int distractorNumber,String symbols) {
        elsDistractorFieldsInput.get(distractorNumber).sendKeys(symbols);
        sleep(110);
        return this;
    }
}
