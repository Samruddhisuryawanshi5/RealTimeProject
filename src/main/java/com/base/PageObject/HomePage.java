package com.base.PageObject;
import com.base.Util.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends TestBase {
    WebDriver driver = null;
    @FindBy(
            xpath = "//input[@id=\"userEmail\"]"
    )
    public WebElement email;
    @FindBy(
            xpath = "//input[@id=\"login\"]"
    )
    public WebElement submit;
    @FindBy(
            id = "login"
    )
    public WebElement password;
    @FindBy(
            xpath = "//a[text()=\"Register\"]"
    )
    public WebElement login;
    @FindBy(
            id = "firstName"
    )
    public WebElement firstName;
    @FindBy(
            id = "lastName"
    )
    public WebElement lastName;
    @FindBy(
            id = "userEmail"
    )
    public WebElement userEmail;
    @FindBy(
            id = "userMobile"
    )
    public WebElement mobileNo;
    @FindBy(
            xpath = "//select[@class=\"custom-select ng-untouched ng-pristine ng-valid\"]"
    )
    public WebElement options;
    @FindBy(
            xpath = "//input[@value=\"Female\"]"
    )
    public WebElement radioButton;
    @FindBy(
            id = "userPassword"
    )
    public WebElement registerPassword;
    @FindBy(
            id = "confirmPassword"
    )
    public WebElement confirmPassword;
    @FindBy(
            xpath = "//input[@type=\"checkbox\"]"
    )
    public WebElement checkBox;
    @FindBy(
            id = "login"
    )
    public WebElement register;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void login() {
        this.typeUsingWebElement(this.email, "samruddhi@gmail.com", false);
        this.clickUsingWebElement(this.submit, false);
        this.typeUsingWebElement(this.password, "12345", false);
    }

    public void registerPage() {
        this.clickUsingWebElement(this.login, true);
        this.typeUsingWebElement(this.firstName, "ak", true);
        this.typeUsingWebElement(this.lastName, "chavan", true);
        this.typeUsingWebElement(this.email, "ak@gmail.com", false);
        this.typeUsingWebElement(this.mobileNo, "7522968281", false);
        this.selectFromDropdown(this.options, "visibleText", "Engineer", true);
        this.clickUsingWebElement(this.radioButton, true);
        this.typeUsingWebElement(this.password, "1234@12", true);
        this.typeUsingWebElement(this.confirmPassword, "1234@12", true);
        this.clickUsingWebElement(this.checkBox, true);
    }
}