package com.mycompany.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.*;

public class MailruTests extends BaseTest {
    private String emailName ="testsiiushhseufh2";
    private String emailAdress=emailName+"@mail.ru";
    private String password="quwqh9hd";
    private String subjectDefault="Тестовое письмо";
    private String fileName="2019-04-13_1942.png";
    private String filePath="C:\\automation\\mailruTests\\"+fileName;
    private String textDefault="текст письма";


    @BeforeMethod
    public void openBrowser(){
        open("http://mail.ru");
    }
    @AfterMethod
    public void close(){
        closeWebDriver();
    }

    @Test(description = "Проверка регистрации нового аккаунта")
    public void signUp() {
        homePage.goToSignupPage();
        String emailName="testmail"+String.valueOf(Math.random());
        signupPage.signup(emailName,password);
        assertEquals(mainEmailPage.getCurrentEmail(),emailName+"@mail.ru");
    }

    @Test(description = "Проверка появления ошибок при попытке регистрации нового аккаунта с незаполенными полями")
    public void signUpFail(){
        homePage.goToSignupPage();
         assertEquals(signupPage.getCountInputValidationErrors(),7);
    }

    @Test(description = "Проверка входа в заранее созданный аккаунт")
    public void loginSuccess(){
        homePage.signIn(emailName,password);
        assertEquals(mainEmailPage.getCurrentEmail(),emailAdress);
    }

    @Test(description = "Проверка появления ошибки при попытке войти с неверным логином")
    public void loginWithIncorectName(){
        homePage.signIn("testsi",password);
        assertTrue(homePage.isLoginErrorDisplayed());
    }
    @Test(description = "Проверка появления ошибки при попытке войти с неверным паролем")
    public void loginWithIncorectPassword(){
        homePage.signIn(emailName,"b94hfp");
        assertTrue(homePage.isLoginErrorDisplayed());
    }

    @Test(description = "Проверка того, что письмо отправленное на свой же ящик успешно дошло")
    public void sendNewEmail(){
        homePage.signIn(emailName,password);
        mainEmailPage.createNewEmailButtonClick();
        //Генерируем уникальную тему для письма
        String subject="Тестовое письмо "+Math.random()+"@mail.ru";
        mainEmailPage.setToAndSubject(emailAdress,subject);
        mainEmailPage.fillEmailText(textDefault);
        mainEmailPage.sendEmailButtonClick();
        mainEmailPage.closePupup();
        assertTrue(mainEmailPage.isEmailPresent(subject));
    }
    @Test(description = "Проверка работы кнопки 'Отметить все прочитанными'")
    public void markAllEmailsAsRead(){
        homePage.signIn(emailName,password);
        mainEmailPage.sendEmail(emailAdress,subjectDefault);
        mainEmailPage.markAllLetersAsRead();
        assertEquals(mainEmailPage.getCountUnreadEmails(),0);
    }
    @Test(description = "Проверка появления ошибки при попытке отправить письмо с незаолненным полем 'Кому'")
    public void sendNewEmailWithEmptyToInput(){
        homePage.signIn(emailName,password);
        //проверяем наличие ошибки при попытке отослать email с незаполенным полем 'to'
        mainEmailPage.createNewEmailButtonClick();
        mainEmailPage.setToAndSubject("","subjectDefault");
        mainEmailPage.sendEmailButtonClick();
        assertTrue(mainEmailPage.isInputEmptyValidationErrorDisplayed());
    }
    @Test(description = "Проверка появления ошибки при попытке отпавить письмо на email в неверном формате")
    public void sendNewEmailWithIncorrectToInput(){
        homePage.signIn(emailName,password);
        //проверяем наличие ошибки при попытке отослать email с  полем 'to' в неверном формате
        mainEmailPage.createNewEmailButtonClick();
        mainEmailPage.setToAndSubject("incorrectMail","subjectDefault");
        mainEmailPage.fillEmailText(textDefault);
        mainEmailPage.sendEmailButtonClick();
        assertTrue(mainEmailPage.isInputIncorrectValidationErrorDisplayed());
    }
    @Test(description = "Проверка удаления всех писем")
    public void deleteAllEmails(){
        homePage.signIn(emailName,password);
        mainEmailPage.sendEmail(emailAdress,"subjectDefault");
        mainEmailPage.deleteAllEmails();
        assertEquals(mainEmailPage.getCountEmails(),0);
    }
    @Test(description = "Проверка прикрепления файла")
    public void attachFile(){
        homePage.signIn(emailName,password);
        mainEmailPage.createNewEmailButtonClick();
        String subject="Тестовое письмо "+Math.random()+"@mail.ru";
        mainEmailPage.setToAndSubject(emailAdress,subject);
        mainEmailPage.fillEmailText(textDefault);
        mainEmailPage.attachFile(filePath);
        mainEmailPage.sendEmailButtonClick();
        mainEmailPage.closePupup();
        mainEmailPage.openEmail(subject);
        assertTrue(mainEmailPage.isAttachPresent(fileName));
    }
    @Test(description = "Проверка применения различных стилей для текста в письме")
    public void textStyles(){
        homePage.signIn(emailName,password);
        mainEmailPage.createNewEmailButtonClick();
        mainEmailPage.setToAndSubject(emailAdress,subjectDefault);
        mainEmailPage.fillEmailText(textDefault);
        mainEmailPage.fillEmailBoldText("Жирный текст");
        mainEmailPage.fillEmailItalicText("Курсив");
        mainEmailPage.fillEmailColoredText("Текст другого цвета");
        mainEmailPage.sendEmailButtonClick();
        mainEmailPage.closePupup();
        mainEmailPage.openEmail(subjectDefault);
        assertEquals(mainEmailPage.getTextFontWeight("Жирный текст"),"700");
        assertEquals(mainEmailPage.getTextFontStyle("Курсив"),"italic");
        assertEquals(mainEmailPage.getTextColor("Текст другого цвета"),"rgba(108, 239, 86, 1)");

    }
}
