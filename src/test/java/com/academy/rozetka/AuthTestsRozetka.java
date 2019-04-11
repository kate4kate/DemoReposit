package com.academy.rozetka;

import com.academy.framework.BaseTest;
import com.academy.rozetka.page.AuthFormPage;
import com.academy.rozetka.page.MainPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthTestsRozetka extends BaseTest {

    protected String baseUrl = "https://rozetka.com.ua/ua/";

    @Test
    public void testSuccessAuth() {
        System.out.println("***Rozetka test Auth start***");

        MainPage mainPage = new MainPage(driver);
        String oldMessage = mainPage.getEnterLinkText();
        AuthFormPage authFormPage = mainPage.clickEnterLink();

        authFormPage.enterLogin("katerina.zhernovaya@gmail.com");

        authFormPage.enterPassword("lovemylife09");
        mainPage = authFormPage.submit();

        mainPage = mainPage.waitUntilLinkTextChanged(oldMessage);

        String userName = mainPage.getEnterLinkText();
        Assert.assertEquals(userName, "Екатерина Кравченко");

    }
}