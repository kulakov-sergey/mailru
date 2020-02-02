package com.mycompany.pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class HomePage  {
    public static SelenideElement signupButton=$(By.id("signup"));
    public static SelenideElement loginInput= $(By.xpath("//input[@name='login']"));
    public static SelenideElement interPassButton= $(By.xpath("//input[@type='submit'][@value='Ввести пароль']"));
    public static SelenideElement passwordInput= $(By.name("password"));
    public static SelenideElement submitButton= $(By.xpath("//input[@value='Ввести пароль']"));
    public static SelenideElement loginErrorMessage=$(By.xpath("//div[@class='mailbox__row mailbox__row_condensed i-font-md i-color-coral']"));


    public void goToSignupPage(){
         signupButton.click();
        //переключаемся на новую вкладку
        switchTo().window("Регистрация");
    }
    public void signIn(String login, String password){

        //вводим логин
        loginInput.setValue(login);
        //жмем кнопку Ввести пароль
        interPassButton.click();
        //вводим пароль
        passwordInput.setValue(password);
        //жмем кнопку Войти
        submitButton.click();
    }
    public boolean isLoginErrorDisplayed(){
        return loginErrorMessage.shouldBe(Condition.visible).isDisplayed();
    }
}
