package com.academy.framework;

import com.academy.rozetka.page.MainPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
private final int EXPLICITY_WAIT_TIMEOUT = 5;

    protected  WebDriver driver;


    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);

    }

    protected void enterTextField(WebElement textField, String value){

        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys("lovemylife09");
    }



    protected  waitUntilLinkTextChanged(String locator, String oldmessage){

        WebDriverWait webDriverWait = new WebDriverWait(driver, EXPLICITY_WAIT_TIMEOUT);

        webDriverWait.until(ExpectedConditions.not(
                ExpectedConditions.textToBe(By.cssSelector(enterLinkLocator), oldMessage)));
    }

}
