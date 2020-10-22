package qa.eom.front.logic.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class SpecConstructorPage {


    private SelenideElement elTestNameInput = $x("//label[contains(text(),'Название теста')]/..//input");
    private SelenideElement elTestDescriptionInput = $x("//label[contains(text(),'Описание')]/..//input");
}
