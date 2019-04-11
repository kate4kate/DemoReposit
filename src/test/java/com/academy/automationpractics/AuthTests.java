package com.academy.automationpractics;

import model.AuthData;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import com.academy.framework.BaseTest;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

    public class AuthTests  extends BaseTest{

        private WebDriver driver;
        private String baseUrl;
        private StringBuffer verificationErrors = new StringBuffer();
        private String errorMessageLocator = "#center_column > div.alert.alert-danger > ol > li";


        @Parameters("browser")




        public void initDrivers (String browser) throws IllegalAccessException {
            //Здесь читаю путь к файлу конфигурации
          String commonProperties = System.getProperty("common.cfg");
           Properties properties = new Properties();
           try {
               properties.load(new FileReader(commonProperties));
           }
           catch (IOException e) {
               e.printStackTrace();
           }

            switch (browser){
                case "chrome":
                System.setProperty("webdriver.chrome.driver", properties.getProperty("chrome.driver"));
               driver = new ChromeDriver();
               break;
                case "firefox":
                    System.setProperty("webdriver.gecko.driver",  properties.getProperty("gecko.driver"));
                    driver = new FirefoxDriver();
                    break;
                default:
                        throw new IllegalAccessException("Unknown browser"+ browser);
            }
        }


       @Test(dataProvider = "autDataProvider")
        public void testLoginErrors(String login, String password, String expectedMessage ) throws Exception {
            driver.get(baseUrl);
            driver.findElement(By.linkText("Sign in")).click();

            // Заполняем форму логин/пароль
            WebElement loginField = driver.findElement(By.id("email"));
            loginField.click();
            loginField.clear();
            loginField.sendKeys(login);

            driver.findElement(By.id("passwd")).click();
            driver.findElement(By.id("passwd")).clear();
            driver.findElement(By.id("passwd")).sendKeys(password);
            driver.findElement(By.id("SubmitLogin")).click();

            WebElement webElementWithErrorMessage = driver.findElement(By.cssSelector(errorMessageLocator));
            String actualErrorMessage = webElementWithErrorMessage.getText();
            try {
                assertEquals(actualErrorMessage, expectedMessage);
            } catch (Error e) {
                verificationErrors.append(e.toString());
            }
        }



        private List<AuthData> readTestData() {
            List<AuthData> testDataList = new ArrayList<>();

            // Прочитать из excel файла
            return testDataList;
        }

        @AfterClass(alwaysRun = true)
        public void tearDown() throws Exception {
            driver.quit();
            String verificationErrorString = verificationErrors.toString();
            if (!"".equals(verificationErrorString)) {
                fail(verificationErrorString);
            }
        }

        // TODO FROM EXCEL
        @DataProvider(name="authDataProvider")
        public Object[][] authDataProvider() {
            String pathData = "D:/Automation/MAVENproject/src/main/resources/common.properties";
                      return new Object[][] {
                    {"", "123", "An email address required."},
                    {"qwertyLogin", "qwertyPassword", "Invalid email address."}
            };
        }



}
