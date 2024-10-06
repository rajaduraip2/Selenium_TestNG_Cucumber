package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    /*
    * This class just created to initialize the pageFactory,
    * This Pagefacoty is used to Initialize the Drive and PageObject.
    *
    * */

    WebDriver driver;
    public BasePage(WebDriver driver)
    {
        this.driver=driver;
        //The belo step is used to call initialize the element is the respective instance. "This" is object of the page.
        PageFactory.initElements(driver,this);

    }
}
