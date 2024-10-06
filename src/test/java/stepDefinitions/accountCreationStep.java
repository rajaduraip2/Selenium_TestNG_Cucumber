package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import testCases.Automation_TC_001;
import testCases.Base;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

public class accountCreationStep {
    //Base base=new Base();
    Automation_TC_001 tc001=new Automation_TC_001();
    @Given("User navigates to tutorial ninja website")
    public void navigationToHomePage() throws MalformedURLException, FileNotFoundException {

        tc001.setup("windows","chrome");

    }


    @When("create a Account in tutorial ninja website")
    public void createAccount(){
        tc001.verify_account_registration();
        tc001.teardown();

    }


}
