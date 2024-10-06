package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LogInPage;
import utilities.DataProviders;

public class Automation_TC_003 extends Base {

    @Test (dataProvider = "loginData",dataProviderClass = DataProviders.class,groups ="Master")
    public void VerifyDataProvider(String email,String password, String result) {
        try {
            logger.info("****** Starting the Testcase TC_003***");
            HomePage homePage = new HomePage(driver);
            logger.info("******Account Password got Retrived");
            homePage.clickMyAccount();
            homePage.clickLogin();


            logger.info("******Navigating to the Login page");
            LogInPage logInPage = new LogInPage(driver);
            logInPage.verifyReturningCustomer();
            logInPage.enterEmail(email);
            logInPage.enterPassword(password);
            logInPage.clickLogin();


            logger.info("******Customer filled the value and clicked the login button");
            Assert.assertTrue(logInPage.verifyMyaccountPage());

            if (result.equalsIgnoreCase("Valid")) {
                if (logInPage.verifyMyaccountPage()) {
                    logInPage.clickLogout();
                    Assert.assertTrue(true);
                    logger.info("******Logged in to the registry");
                } else {
                    Assert.fail();
                }
            } else {
                if (logInPage.verifyMyaccountPage()) {
                    logInPage.clickLogout();
                    Assert.assertTrue(false);
                    logger.info("******Logged in to the registry");
                } else {
                    Assert.assertTrue(true);
                }
            }


            logger.info("******Test Case completed.");
        }
        catch (Exception e)
        {
          System.out.println(e.getMessage());
            Assert.fail();
        }
    }

}
