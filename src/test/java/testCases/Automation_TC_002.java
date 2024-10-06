package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LogInPage;
import pageObjects.RegistrationPage;

public class Automation_TC_002 extends Base{

    @Test(groups ={"regression","Master"})
    public void verifyLogIn()
    {
        try {
            logger.info("****** Starting the Testcase TC_002");
            HomePage homePage = new HomePage(driver);
            logger.info("******Account Password got Retrived");
            homePage.clickMyAccount();
           homePage.clickLogin();
            logger.info("******Navigating to the Login page");
            LogInPage logInPage = new LogInPage(driver);
            logInPage.verifyReturningCustomer();
            logInPage.enterEmail(prop.getProperty("email"));
          //  logInPage.enterPassword("Pass");
           logInPage.enterPassword(prop.getProperty("password"));
            logInPage.clickLogin();
            Thread.sleep(5000);
            logger.info("******Customer filled the value and clicked the login button");
         Assert.assertTrue(logInPage.verifyMyaccountPage());
            logger.info("******Test Case completed.");
            logger.info("******Logged in to the registry");
        }
        catch (Exception e){
            logger.error(e.getStackTrace());
            Assert.fail();
        }
    }

}
