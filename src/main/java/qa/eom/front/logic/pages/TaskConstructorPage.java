package qa.eom.front.logic.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import qa.eom.front.logic.dto.TaskFillData;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

 public class TaskConstructorPage {


    private SelenideElement elGoToSettingsBtn = $x("//button[.//span[contains(text(),'Перейти к настройкам')]]");
    private SelenideElement elGoToEdittingBtn = $x("//button[.//*[contains(text(),'Перейти к редактированию')]]");
    private SelenideElement elUndoBtn = $x("//button[contains(@title,'Отменить действие')]");
    private SelenideElement elRedoBtn = $x("//button[contains(@title,'Вернуть действие')]");
    private SelenideElement elPreviewTaskBtn = $x("//button[.//*[contains(text(),'Предпросмотр вопроса')]]");
    private SelenideElement elAddMaterialBtn = $x("//button[.//*[contains(text(),'Добавить материал')]]");
    private SelenideElement elAnswerFormSelector = $x("//*[contains(text(),'Выбор одного ответа')]");
    private ElementsCollection elsInSelectorAnswerFormsList = $$x("//li[contains(@data-value,'answer')]");
    private SelenideElement elAddDisciplineBtn = $x("//button[.//*[contains(text(),'Добавить предмет')]]");
    private SelenideElement elAddTagBtn = $x("//p[contains(text(),'Привязка тегов:')]//following::button[.//*[contains(text(),'Добавить')]]");
    private SelenideElement elOpenFolderTreeBtn = $x("//button[.//*[contains(text(),'Открыть дерево папок')]]");
    private SelenideElement elChooseThemeBtn = $x("//button[.//*[contains(text(),'Выбрать тему')]]");
    private SelenideElement elSaveTaskBtn = $x("//button[.//*[contains(text(),'Сохранить')]]");
    private SelenideElement elQuestionFieldInput = $x("//*[contains(text(),'Введите вопрос')]/..//span/../../../..");
    private SelenideElement elMaterialThemeInput = $x("//input[..//..//label[contains(text(),'Название материала. Тема')]]");
    private ElementsCollection elsParallelBtns = $$x("//button[ancestor::*/../label//span[contains(text(),'Параллель')]]");
    private SelenideElement elLearnCoursesSelector = $x("//*[contains(@role,'button')][ancestor::div//span[contains(text(),'Предмет')]]");
    private ElementsCollection elsInSelectorLearnCoursesList = $$x("//ul[contains(@role,'listbox')]//li[contains(@data-value,'')]");
    private ElementsCollection elsLessonThemesBtns = $$x("//div[@role='button'][ancestor::*/p[contains(text(),'Темы')]]");
    private ElementsCollection elsDedacticUnitBtns = $$x("//p[contains(text(),'Дидактические единицы')]/../..//div[@role='button']");
    private SelenideElement elAcceptThemeBtn = $x("//button[.//*[contains(text(),'Применить')]]");
    private SelenideElement elTaskSuccessfullySavedMessage = $x("//p[contains(text(),'Тестовое задание сохранено')]");


    @Step("Ввести текст {symbols} в поле ввода [Вопрос]")
    public TaskConstructorPage enterSymbolsToQuestionField(String symbols) {
        elQuestionFieldInput.sendKeys(symbols);
        return this;
    }

     @Step("Нажать на кнопку открытия селектора [Форма ответа]")
     public TaskConstructorPage clickAnswerFormSelector() {
         elAnswerFormSelector.click();
         return this;
     }

     @Step("Нажать кнопку выбора формы ответа {answerForm} в селекторе [Форма ответа]")
     public TaskConstructorPage clickAnswerFormBtnInSelector(String answerForm) {
         elsInSelectorAnswerFormsList.find(Condition.text(answerForm)).click();
         return this;
     }

    @Step("Нажать кнопку [Перейти к настройкам]")
     public TaskConstructorPage clickGoToSettingsBtn() {
        elGoToSettingsBtn.click();
        return this;
    }

     @Step("Нажать кнопку [Перейти к редактированию]")
     public TaskConstructorPage clickGoToEdittingBtn() {
         elGoToEdittingBtn.click();
         return this;
     }

    @Step("Нажать кнопку [Undo]")
     public TaskConstructorPage clickUndoBtn() {
        elUndoBtn.click();
        return this;
    }

     @Step("Нажать кнопку [Redo]")
     public TaskConstructorPage clickRedoBtn() {
         elRedoBtn.click();
         return this;
     }

     @Step("Нажать кнопку [Предпросмотр вопроса]")
     public TaskConstructorPage clickPreviewTaskBtn() {
         elPreviewTaskBtn.click();
         return this;
     }

     @Step("Нажать кнопку [Добавить материал]")
     public TaskConstructorPage clickAddMaterialBtn() {
         elAddMaterialBtn.click();
         return this;
     }

     @Step("Нажать кнопку [Добавить предмет]")
     public TaskConstructorPage clickAddDisciplineBtn() {
         elAddDisciplineBtn.click();
         return this;
     }

     @Step("Нажать кнопку [Добавить] тэг")
     public TaskConstructorPage clickAddTagBtn() {
         elAddTagBtn.click();
         return this;
     }

     @Step("Нажать кнопку [Открыть дерево папок]")
     public TaskConstructorPage clickOpenFolderTreeBtn() {
         elOpenFolderTreeBtn.click();
         return this;
     }

     @Step("Нажать кнопку [Сохранить] тестовое задание")
     public TaskConstructorPage clickSaveTaskBtn() {
         elSaveTaskBtn.click();
         return this;
     }

     @Step("Нажать кнопку [Выбрать тему]")
     public TaskConstructorPage clickChooseThemeBtn() {
         elChooseThemeBtn.click();
         return this;
     }

     @Step("Ввести символы {symbols} в поле [Название материала. Тема]")
     public TaskConstructorPage enterSymbolsToMaterialThemeInput(String symbols) {
         elMaterialThemeInput.sendKeys(symbols);
         return this;
     }

     @Step("Нажать кнопку параллели {parallelNumber}")
     public TaskConstructorPage clickParallelNumberBtn(int parallelNumber) {
         elsParallelBtns.get(parallelNumber).click();
         return this;
     }

     @Step("Нажать на кнопку открытия селектора [Предмет]")
     public TaskConstructorPage clickLearnCoursesSelector() {
         elLearnCoursesSelector.click();
         return this;
     }

     @Step("Выбрать предмет {learnCourse} из списка селектора [Предмет]")
     public TaskConstructorPage clickLearnCourseInSelector(String learnCourse) {
         elsInSelectorLearnCoursesList.find(Condition.text(learnCourse)).click();
         return this;
     }

     @Step("Выбрать тему урока {lessonTheme}")
     public TaskConstructorPage clickLessonThemeBtn(String lessonTheme) {
         elsLessonThemesBtns.find(Condition.text(lessonTheme)).click();
         return this;
     }

     @Step("Выбрать дидактическую единицу {dedacticUnit}")
     public TaskConstructorPage clickDedacticUnitBtn(String dedacticUnit) {
         elsDedacticUnitBtns.find(Condition.text(dedacticUnit)).click();
         return this;
     }

     @Step("Нажать кнопку [Применить] выбор темы")
     public TaskConstructorPage clickAcceptThemeBtn() {
         elAcceptThemeBtn.click();
         return this;
     }

     @Step("Проверить, что сообщение [Тестовое задание сохранено] отображается")
     public TaskConstructorPage checkTaskSuccessfullySavedMessageIsVisible() {
         elTaskSuccessfullySavedMessage.shouldBe(Condition.visible);
         return this;
     }

     @Step("Заполнить поля настроек и сохранить тестовое задание")
     public TaskConstructorPage fillAllSettingsFiedsAndSaveTask(TaskFillData taskFillData) {
         enterSymbolsToMaterialThemeInput(taskFillData.taskTheme);
         clickParallelNumberBtn(taskFillData.parallelNumber);
         clickLearnCoursesSelector();
         clickLearnCourseInSelector(taskFillData.learnCourse);
         clickChooseThemeBtn();
         clickLessonThemeBtn(taskFillData.lessonTheme);
         clickDedacticUnitBtn(taskFillData.dedacticUnit);
         clickAcceptThemeBtn();
         clickSaveTaskBtn();
         checkTaskSuccessfullySavedMessageIsVisible();
         return this;
     }

//    @Override
//    public abstract void doAnswerFormAction();

}
