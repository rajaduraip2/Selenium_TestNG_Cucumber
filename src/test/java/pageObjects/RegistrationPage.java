package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage {

   public RegistrationPage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(css="#input-firstname")
    WebElement txtFirstName;

    @FindBy(xpath = "//input[@id='input-lastname']")
    WebElement txtLastName;

    @FindBy(xpath = "//input[@id='input-email']")
    WebElement txtEmail;

    @FindBy(xpath = "//input[@id='input-telephone']")
    WebElement txtTelePhone;

    @FindBy(xpath = "//input[@id='input-password']")
    WebElement txtPassWord;
    @FindBy(xpath = "//input[@id='input-confirm']")
    WebElement txtPassWordConfirm;
    @FindBy(xpath = "//input[@name='agree']")
    WebElement chkPrivacy;

    @FindBy(xpath = "//input[@value='Continue']")
    WebElement btnContinue;

    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
    WebElement txtConfirmMsg;

    public void setFirstName(String firstNameData){
        txtFirstName.sendKeys(firstNameData);
    }

    public void setLastName(String lastNameData){
        txtLastName.sendKeys(lastNameData);
    }

    public void setEmail(String emailData){
        txtEmail.sendKeys(emailData);
    }

    public void setPassWord(String passWordData){
        txtPassWord.sendKeys(passWordData);
    }

    public void setPassWordConfirm(String passWordData){
        txtPassWordConfirm.sendKeys(passWordData);
    }

    public void setTelePhone(String telePhone){
        txtTelePhone.sendKeys(telePhone);
    }


    public void clickPrivacyCheckBox(){
        chkPrivacy.click();
    }

    public void continueBtnMethod()
    {
        btnContinue.click();
    }

    public String validateConfirmationMessage()
    {
        try {
           return (txtConfirmMsg.getText());
        }
        catch (Exception e)
        {
            return (e.getMessage());
        }
    }














}
