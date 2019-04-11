package com.academy.rozetka.page;

import com.academy.framework.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AuthFormPage  extends BasePage {

    private WebDriver driver;

    @FindBy(id = "auth_email")
    WebElement loginField;

    @FindBy(id = "auth_pass")
    WebElement passwordField;


    @FindBy(css = "body > app-root > div > div:nth-child(2) > div.app-rz-common > auth-modal > modal-window > div > div > div > auth-content > div > form > div > button")
    WebElement submitButton;

    public AuthFormPage(WebDriver driver) {
        super(driver);
    }


    public AuthFormPage enterLogin(String login){

//        loginField.click();
//        loginField.clear();
//        loginField.sendKeys("katerina.zhernovaya@gmail.com");

        enterText(loginField, login);
        return  this;

    }


    public  AuthFormPage enterPassword(String password){

        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys("lovemylife09");

        return  this;

    }


    public MainPage submit(){
        submitButton.click();

        return new MainPage(driver);
    }



}
