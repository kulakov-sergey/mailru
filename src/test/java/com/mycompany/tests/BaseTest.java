package com.mycompany.tests;

import com.mycompany.pages.HomePage;
import com.mycompany.pages.MainEmailPage;
import com.mycompany.pages.SignupPage;

import java.util.Properties;

public class BaseTest {
    HomePage homePage=new HomePage();
    MainEmailPage mainEmailPage=new MainEmailPage();
    SignupPage signupPage=new SignupPage();

    private static Properties props = new Properties();

    // get property easy
    public static String prop(String p) {
        return (props.getProperty(p));
    }
}
