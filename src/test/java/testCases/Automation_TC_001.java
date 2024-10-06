package testCases;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.RegistrationPage;

import java.time.Duration;

public class Automation_TC_001 extends Base {


    @Test(groups = {"sanity","Master"})
    public void verify_account_registration(){
       try {
           logger.info("****** Starting the Testcase TC_001");
           HomePage homePage = new HomePage(driver);
           String randPassword = randomPassword();
           logger.info("******Random Password got created");
           homePage.clickMyAccount();
           homePage.clickRegister();
           logger.info("******Navigating to the Register file");
           RegistrationPage regPage = new RegistrationPage(driver);
           regPage.setFirstName(randomName());
           regPage.setLastName(randomName());
           regPage.setEmail(randomEmail());
           regPage.setPassWord(randPassword);
           regPage.setPassWordConfirm(randPassword);
           regPage.setTelePhone(randomMobile());
           regPage.clickPrivacyCheckBox();
           regPage.continueBtnMethod();
           logger.info("******Filled the Register page");
           String validationMessage = regPage.validateConfirmationMessage();
           logger.info(validationMessage);
           Assert.assertEquals(validationMessage, "Your Account Has Been Created!");
           logger.info("******Test Case completed.");
       }
       catch (Exception e){
           logger.error(e.getStackTrace());
       }
    }



}
