package com.base;

import com.base.PageObject.HomePage;
import com.base.Util.TestBase;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
public class HomePageTestcase extends TestBase {
        public HomePageTestcase() {
        }

        @BeforeTest
        public void setup() {
            driver = this.openBrowser("chrome");
        }

        @Test
        public void ShoppingPage() {
            this.navigateTo("https://rahulshettyacademy.com/client");
            HomePage homePage = new HomePage(driver);
            homePage.registerPage();
            homePage.login();
        }

        @AfterClass
        public void tearDown() {
            this.quitBrowser();
        }
    }

