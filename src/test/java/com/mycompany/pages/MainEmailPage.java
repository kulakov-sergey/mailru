package com.mycompany.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainEmailPage  {
    public static SelenideElement createButton= $(By.xpath("//span[contains(@class,'compose-button compose-button_white')]"));
    public static SelenideElement toInput= $(By.xpath("//div[@data-type='to']//div[contains(@class,'inputContainer')]/input"));
    public static SelenideElement subjectInput= $(By.xpath("//input[@name='Subject']"));
    public static SelenideElement div1TextInput= $(By.xpath("//div[@role='textbox']/div/div"));
    public static SelenideElement div2TextInput= $(By.xpath("//div[@role='textbox']/div/div[2]"));
    public static SelenideElement div3TextInput= $(By.xpath("//div[@role='textbox']/div/div[3]"));
    public static SelenideElement div4TextInput= $(By.xpath("//div[@role='textbox']/div/div[4]"));
    public static SelenideElement ckeditorBoldButton= $(By.xpath("//button[@title='Жирный текст']"));
    public static SelenideElement ckeditorItalicButton= $(By.xpath("//button[@title='Наклонный текст']"));
    public static SelenideElement ckeditorDropdownColorButton= $(By.xpath("//button[@title='Цвет текста']"));
    public static SelenideElement ckeditorGrenColorButton= $(By.xpath("//div[@style='background-color: rgb(108, 239, 86);']"));
    public static SelenideElement fileInput= $(By.xpath("//input[@type='file']"));
    public static SelenideElement sendLetterButton = $(By.xpath("//span[contains(@title,'Отправить')]"));
   // public static SelenideElement stringText = $(By.xpath("//*[contains(text(),'Жирный')]"));
    public static SelenideElement selectAllLettersButton = $(By.xpath("//span[@class='button2 button2_has-ico button2_status_read button2_pure button2_compact button2_hover-support']"));
    public static SelenideElement setAllLettersAsReadButton = $(By.xpath("//span[@class='button2 button2_has-ico button2_status_read button2_pure button2_compact button2_hover-support']"));
    public static SelenideElement submitButton =  $(By.xpath("//span[@class='button2 button2_base button2_primary button2_fluid button2_hover-support']/span"));
    public static SelenideElement currentEmailLabel=$(By.xpath("//i[@id='PH_user-email']"));
    private static SelenideElement inputEmptyValidationError =$(By.className("rowError--O4k-g"));
    private static SelenideElement inputIncorrectValidationError =$(By.xpath("//h1[@class='c2120 c2121 c2139']"));
    private static SelenideElement selectAllItemsButton=$(By.xpath("//span[@data-title-shortcut='Ctrl+A']"));
    private static SelenideElement deleteButton=$(By.xpath("//span[contains(@class,'button2_delete')]"));
    private static SelenideElement acceptDeleteButton=$(By.xpath("//div[@class='layer__submit-button']"));
    private static SelenideElement closePopupButton=$(By.xpath("//span[@class='button2 button2_has-ico button2_close button2_pure button2_clean button2_short button2_hover-support']"));
    private ElementsCollection mailItemsList=$$(By.xpath("//a[contains(@class,'llc js-tooltip-direction_letter-bottom js-letter-list-item llc_pony-mode llc_normal')]"));
    private ElementsCollection unreadEmail = $$(By.xpath("//span[@class='llc__subject llc__subject_unread']"));
    //private String subject="Тестовое письмо "+String.valueOf(hashCode());


    public void deleteAllEmails() {

        if(selectAllItemsButton.isDisplayed()){
            //выделяем все
            selectAllItemsButton.click();
            deleteButton.click();
            //подтверждаем удаление
            acceptDeleteButton.click();
            selectAllItemsButton.shouldNotBe(Condition.visible);
        }

    }
    public void createNewEmailButtonClick(){
         createButton.waitUntil(Condition.visible,5000).click();
    }
    public void setToAndSubject(String to, String subject){
        toInput.setValue(to);
        subjectInput.setValue(subject);

    }
    public void fillEmailText(String text){
        div1TextInput.sendKeys(text);
        div1TextInput.pressEnter();
    }
    public void fillEmailBoldText (String text)
    {
        ckeditorBoldButton.click();
        div2TextInput.sendKeys(text);
        div2TextInput.pressEnter();
    }

    public void fillEmailItalicText (String text)
    {
        ckeditorItalicButton.click();
        div3TextInput.sendKeys(text);
        div3TextInput.pressEnter();
    }

    public void fillEmailColoredText (String text)
    {
        ckeditorDropdownColorButton.click();
        ckeditorGrenColorButton.click();
        div4TextInput.sendKeys(text);
    }
    public void attachFile(String filePath){
        fileInput.sendKeys(filePath);
    }
    public void sendEmailButtonClick(){
        sendLetterButton.click();
    }
    public void closePupup(){
        //Закрываем попап с уведомление об успешной отправке
        closePopupButton.waitUntil(Condition.visible,3000).click();
    }
    public void sendEmail(String to, String subject){
        createNewEmailButtonClick();
        setToAndSubject(to,subject);
        fillEmailText("Текст письма");
        sendEmailButtonClick();
        closePupup();
    }
    public void openEmail(String subject){
        String subjectLocator = String.format("//span[@class='ll-sj__normal'][contains(.,'%s')]/../../../../..", subject);
        $(By.xpath(subjectLocator)).click();

    }
    public String getTextFontWeight(String text){
        String textLocator = String.format("//*[contains(text(),'%s')]", text);
        return $(By.xpath(textLocator)).getCssValue("font-weight");
    }
    public String getTextFontStyle(String text){
        String textLocator = String.format("//*[contains(text(),'%s')]", text);
        return $(By.xpath(textLocator)).getCssValue("font-style");
    }
    public String getTextColor(String text){
        String textLocator = String.format("//*[contains(text(),'%s')]", text);
        return $(By.xpath(textLocator)).getCssValue("color");
    }
    public boolean isAttachPresent(String fileName){
        //убеждаемся, что файл прикреплен к письму
        String fileNameLocator = String.format("//div[@title='%s']", fileName);
        return $(By.xpath(fileNameLocator)).waitUntil(Condition.visible,4000).isDisplayed();
    }
    public boolean isEmailPresent(String subject){
        //проверяем, есть ли письмо с определенной темой
        String subjectValueLocator = String.format("//span[contains(.,'%s')]", subject);
        return $(By.xpath(subjectValueLocator)).isDisplayed();
    }
    public int getCountUnreadEmails() throws InterruptedException {
        Thread.sleep(2000);
        return unreadEmail.size();
    }

    public  void markAllLetersAsRead(){
          //делаем письма прочитанными
        if(selectAllLettersButton.isDisplayed()){
            //нажимаем кнопку Отметить прочитанными
            setAllLettersAsReadButton.click();
            //подтверждаем действие в попапе
            submitButton.click();

        }
    }
    public String getCurrentEmail(){
    return currentEmailLabel.waitUntil(Condition.visible,5000).getText();
    }
    public int getCountEmails(){
        return mailItemsList.size();
    }

    public boolean isInputEmptyValidationErrorDisplayed(){
        return inputEmptyValidationError.isDisplayed();
    }
    public boolean isInputIncorrectValidationErrorDisplayed(){
        return inputIncorrectValidationError.isDisplayed();
    }
}
