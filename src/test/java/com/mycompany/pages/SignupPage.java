package com.mycompany.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class SignupPage {
    public static SelenideElement name= $(By.name("firstname"));
    public static SelenideElement lastName= $(By.name("lastname"));
    public static SelenideElement dropDownDay=  $(By.xpath("//div[@class='b-date__day']//div[@class='b-dropdown__arrow']"));
    public static SelenideElement valueDay=  $(By.xpath("//div[@class='b-date__day']//a[@data-value='13']"));
    public static SelenideElement dropDownMonth= $(By.xpath("//div[@class='b-date__month']//div[@class='b-dropdown__arrow']"));
    public static SelenideElement valueMonth= $(By.xpath("//div[@class='b-date__month']//a[@data-text='Сентябрь']"));
    public static SelenideElement dropDownYear=  $(By.xpath("//div[@class='b-date__year']//div[@class='b-dropdown__arrow']"));
    public static SelenideElement valueYear= $(By.xpath("//div[@class='b-date__year']//a[@data-text='1995']"));
    public static SelenideElement valueSex= $(By.xpath("//label[@data-mnemo='sex-male']//div[@class='b-radiogroup__radio-border']"));
    public static SelenideElement email= $(By.xpath("//input[@data-blockid='email_name'][@required='true']"));
    public static SelenideElement password= $(By.name("password"));
    public static SelenideElement passwordRetry=  $(By.name("password_retry"));
    public static SelenideElement registrationButton=  $(By.xpath("//button[contains(@class,'btn_main')]"));
    public static SelenideElement signupButton=  $(By.xpath("//button[@data-test-id='onboarding-button-start']"));
    public static SelenideElement nextStepButton=   $(By.xpath("//button[@data-test-id='onboarding-button-step']"));
    public static SelenideElement darkTheme=   $(By.xpath("//div[@data-test-id='onboarding-theme-t5000']"));
    public static SelenideElement submitButton=    $(By.xpath("//button[@type='submit']"));
    private ElementsCollection inputValidationErrors = $$(By.xpath("//div[@class='b-form-field__errors__error js-required js-error b-form-field__errors__error_visible']"));


    public void signup(String emailName,String pass) {

        //задаем фамилию, имя
        name.setValue("Ivan");
        lastName.setValue("Petrov");

        //задаем день
        dropDownDay.click();
        valueDay.click();

        //задаем месяц
        dropDownMonth.click();
        valueMonth.click();

        //задаем год
        dropDownYear.click();
        valueYear.click();

        //выбираем пол
        valueSex.click();

        //выбираем email, пароль
        email.setValue(emailName);
        password.setValue(pass);
        passwordRetry.setValue(pass);

        //жмем кнопку регистрации
        registrationButton.click();

        //!!!Ввод CAPCHA вручную
        //жмем кнопку Зарегистрироваться
        signupButton.click();

        //жмем кнопку Продолжить
        nextStepButton.click();

        //выбираем темную тему
        darkTheme.click();

        //нажмаем кнопку Приступить к работе
        submitButton.click();
    }
    public int getCountInputValidationErrors(){
        registrationButtonClick();
        return inputValidationErrors.size();

    }
    public void registrationButtonClick(){
        registrationButton.click();
    }
}
