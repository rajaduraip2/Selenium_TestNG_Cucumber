package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogInPage extends BasePage {
   public LogInPage(WebDriver driver)
   {
       super(driver);
   }

   @FindBy(xpath = "//h2[normalize-space()='Returning Customer']")
    WebElement txtLoginModal;

   @FindBy(xpath ="//*[@id='input-email']" )
    WebElement txtEmail;

    @FindBy(xpath ="//*[@id='input-password']" )
    WebElement txtPassword;

    @FindBy(xpath = "(//div[@id='content']/h2)[1]")
    WebElement txtMyaccount;

    @FindBy(xpath = "//*[@value='Login']")
    WebElement btnLogin;

    @FindBy(xpath = "(//a[normalize-space()='Logout'])[2]")
    WebElement btnLogOut;

    public void clickLogin()
    {
        btnLogin.click();
    }

    public void enterEmail(String email)
    {
        txtEmail.sendKeys(email);
    }
    public void enterPassword(String password)
    {
        txtPassword.sendKeys(password);

    }

    public void verifyReturningCustomer()
    {
        txtLoginModal.isDisplayed();
    }
    public boolean verifyMyaccountPage()
    {
        return txtMyaccount.isDisplayed();
    }
    public void clickLogout()
    {
        JavascriptExecutor js=(JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,500)");
        btnLogOut.click();
    }




}
