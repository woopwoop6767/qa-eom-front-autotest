package qa.eom.front.logic.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

 public class TaskConstructorPage {


    private SelenideElement goToSettingsBtn = $x("//button[.//span[contains(text(),'Перейти к настройкам')]]");
    private SelenideElement goToEdittingBtn = $x("//button[.//*[contains(text(),'Перейти к редактированию')]]");
    private SelenideElement undoBtn = $x("//button[contains(@title,'Отменить действие')]");
    private SelenideElement redoBtn = $x("//button[contains(@title,'Вернуть действие')]");
    private SelenideElement previewTaskBtn = $x("//button[.//*[contains(text(),'Предпросмотр вопроса')]]");
    private SelenideElement addMaterialBtn = $x("//button[.//*[contains(text(),'Добавить материал')]]");
    private SelenideElement answerFormSelector = $x("");
    private SelenideElement addDisciplineBtn = $x("//button[.//*[contains(text(),'Добавить предмет')]]");
    private SelenideElement addTagBtn = $x("//p[contains(text(),'Привязка тегов:')]//following::button[.//*[contains(text(),'Добавить')]]");
    private SelenideElement openFolderTreeBtn = $x("//button[.//*[contains(text(),'Открыть дерево папок')]]");
    private SelenideElement chooseThemeBtn = $x("//button[.//*[contains(text(),'Выбрать тему')]]");
    private SelenideElement saveTaskBtn = $x("//button[.//*[contains(text(),'Сохранить')]]");


    @Step("Нажать кнопку [Перейти к настройкам]")
     public TaskConstructorPage clickGoToSettingsBtn() {
        goToSettingsBtn.click();
        return this;
    }

     @Step("Нажать кнопку [Перейти к редактированию]")
     public TaskConstructorPage clickGoToEdittingBtn() {
         goToEdittingBtn.click();
         return this;
     }

    @Step("Нажать кнопку [Undo]")
     public TaskConstructorPage clickUndoBtn() {
        undoBtn.click();
        return this;
    }

     @Step("Нажать кнопку [Redo]")
     public TaskConstructorPage clickRedoBtn() {
         redoBtn.click();
         return this;
     }

     @Step("Нажать кнопку [Предпросмотр вопроса]")
     public TaskConstructorPage clickPreviewTaskBtn() {
         previewTaskBtn.click();
         return this;
     }

     @Step("Нажать кнопку [Добавить материал]")
     public TaskConstructorPage clickAddMaterialBtn() {
         addMaterialBtn.click();
         return this;
     }

     @Step("Нажать кнопку [Добавить предмет]")
     public TaskConstructorPage clickAddDisciplineBtn() {
         addDisciplineBtn.click();
         return this;
     }

     @Step("Нажать кнопку [Добавить] тэг")
     public TaskConstructorPage clickAddTagBtn() {
         addTagBtn.click();
         return this;
     }

     @Step("Нажать кнопку [Открыть дерево папок]")
     public TaskConstructorPage clickOpenFolderTreeBtn() {
         openFolderTreeBtn.click();
         return this;
     }

     @Step("Нажать кнопку [Сохранить] тестовое задание")
     public TaskConstructorPage clickSaveTaskBtn() {
         saveTaskBtn.click();
         return this;
     }

     @Step("Нажать кнопку [Выбрать тему]")
     public TaskConstructorPage clickChooseThemeBtn() {
         chooseThemeBtn.click();
         return this;
     }

//    @Override
//    public abstract void doAnswerFormAction();

}
