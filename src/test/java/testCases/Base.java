package testCases;

import com.github.javafaker.Faker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class Base {
    public WebDriver driver;
   public Logger logger;
   public Properties prop;

    @BeforeClass(groups ={"Master","regression"} )
    @Parameters({"OS","browser"})
    public void setup(String os,String br) throws FileNotFoundException, MalformedURLException {
       try {
           FileReader file = new FileReader("./src//test//resources//config.properties");
           prop = new Properties();
           prop.load(file);

       }
       catch (Exception e)
       {

       }
        logger= LogManager.getLogger(this.getClass());
        DesiredCapabilities desiredCapabilities=new DesiredCapabilities();
      if(prop.getProperty("execution_env").equalsIgnoreCase("remote"))
        {

            if(os.equalsIgnoreCase("windows"))
            {
                desiredCapabilities.setPlatform(Platform.WIN11);
            }
            else if(os.equalsIgnoreCase("MAC"))
            {
                desiredCapabilities.setPlatform(Platform.MAC);
            }
            else if(os.equalsIgnoreCase("LINUX"))
            {
                desiredCapabilities.setPlatform(Platform.LINUX);
            }
            else {
                System.out.println("No Matching OS");
                return;
            }
            switch (br.toLowerCase()){
                case "chrome": desiredCapabilities.setBrowserName("chrome");break;
                case "edge": desiredCapabilities.setBrowserName("MicrosoftEdge");break;
                case "firefox":desiredCapabilities.setBrowserName("firefox"); break;
                default:System.out.println("No Matching browser found");return;
            }
            //Docker config
//            driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),desiredCapabilities);
            //Kubenetes config
            driver=new RemoteWebDriver(new URL("http://localhost:53946/wd/hub"),desiredCapabilities);
        }


        else if(prop.getProperty("execution_env").equalsIgnoreCase("local"))
        {
            logger.info("**************** Driver got initialized *******************************");
            switch (br.toLowerCase())
            {
                case "chrome": driver=new ChromeDriver(); break;
                case "firefox": driver=new FirefoxDriver(); break;
                case "edge" : driver=new EdgeDriver(); break;
                default:System.out.println("Invalid Browser Name");return;
            }
        }





        //driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(prop.getProperty("APP_URL"));
        driver.manage().window().maximize();

    }

    @AfterClass (groups ={"Master","regression"} )
    public void teardown(){
        driver.quit();
    }
    public String randomName(){
        Faker faker= new Faker();
        return faker.name().firstName();
    }

    public String randomPassword(){
        Faker faker=new Faker();
        return faker.internet().password();
    }

    public String randomMobile(){
        Faker faker=new Faker();
        return faker.phoneNumber().cellPhone();
    }
    public String captureScreen(String testname){
        String timeStamp= new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot takesScreenshot=(TakesScreenshot) driver;
        File sourceFile=takesScreenshot.getScreenshotAs(OutputType.FILE);
        String targetFilePath= System.getProperty("user.dir")+"\\screenshots\\"+testname+"_"+timeStamp+".png";
        File targetFile= new File(targetFilePath);
        sourceFile.renameTo(targetFile);
        return targetFilePath;
    }

    public String randomEmail(){
        Faker faker=new Faker();
        return faker.internet().emailAddress();
    }


}
