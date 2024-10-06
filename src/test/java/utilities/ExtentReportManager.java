package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testCases.Base;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExtentReportManager implements ITestListener {
    private static ExtentReports extent;
    private static ExtentSparkReporter reporter;
    private ExtentTest test;
    private String repName;
    private static boolean reportOpened = false;

    @Override
    public void onStart(ITestContext context) {
        if (extent == null) {
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            repName = "Test-Report-" + timeStamp + ".html";
            reporter = new ExtentSparkReporter(".\\reports\\" + repName);

            reporter.config().setDocumentTitle("Selenium Extent Report");
            reporter.config().setReportName("Automated Selenium TestNG");
            reporter.config().setTheme(Theme.DARK);

            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Application", "OpenCart");
            extent.setSystemInfo("Module", "My Account");
            extent.setSystemInfo("User Name", System.getProperty("user.name"));
            extent.setSystemInfo("Environment", "QA");
            String OS = context.getCurrentXmlTest().getParameter("OS");
            String browser = context.getCurrentXmlTest().getParameter("browser");
            extent.setSystemInfo("Browser", browser);
            extent.setSystemInfo("OS", OS);
            List<String> li = context.getCurrentXmlTest().getIncludedGroups();
            if (!li.isEmpty()) {
                extent.setSystemInfo("Groups", li.toString());
            }
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.PASS, result.getName() + " got successfully executed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.FAIL, result.getName() + " got failed");
        test.log(Status.INFO, result.getThrowable().getMessage());
        String imgPath = new Base().captureScreen(result.getName());
        test.addScreenCaptureFromPath(imgPath);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP, result.getName() + " got skipped");
        test.log(Status.INFO, result.getThrowable().getMessage());
    }

    @Override
    public void onFinish(ITestContext testContext) {
        extent.flush();
        if (!reportOpened) {
            String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + repName;
            File extentReport = new File(pathOfExtentReport);
            String pathOfCucuReport = System.getProperty("user.dir")+"\\testReports\\cucumber.html";
           File cucuReport = new File(pathOfCucuReport);
            try {
                Desktop.getDesktop().browse(extentReport.toURI());
                Desktop.getDesktop().browse(cucuReport.toURI());
                reportOpened = true; // Set the flag to true after opening the report
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


//https://www.getmailbird.com/setup/access-yopmail-com-via-imap-smtp

  //. Sendinblue
        //SMTP Server: smtp.sendinblue.com
        //Port: 587 (TLS)
        //Username: Your Sendinblue account email
        //Password: Your Sendinblue SMTP key (you can create one in your account)
//		 try {
//			  URL url = new  URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
//
//		  // Create the email message
//		  ImageHtmlEmail email = new ImageHtmlEmail();
//		  email.setDataSourceResolver(new DataSourceUrlResolver(url));
//		  email.setHostName("smtp.googlemail.com");
//		  email.setSmtpPort(465);
//		  email.setAuthenticator(new DefaultAuthenticator("pavanoltraining@gmail.com","password"));
//		  email.setSSLOnConnect(true);
//		  email.setFrom("pavanoltraining@gmail.com"); //Sender
//		  email.setSubject("Test Results");
//		  email.setMsg("Please find Attached Report....");
//		  email.addTo("pavankumar.busyqa@gmail.com"); //Receiver
//		  email.attach(url, "extent report", "please check report...");
//		  email.send(); // send the email
//		  }
//		  catch(Exception e)
//		  {
//			  e.printStackTrace();
//			  }
//

    }

}
