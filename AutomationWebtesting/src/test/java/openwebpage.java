import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.automationwebtesting.utils.ExtentManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.util.List;

public class openwebpage {

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public void setup() {
        // Initialize ExtentReports
        extent = ExtentManager.getInstance();

        // Launch browser
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/");

        Reporter.log("Browser launched and maximized<br>");
    }

    @Test
    public void M1_openDemoQA() {
        test = extent.createTest("Launch DemoQA Website");

        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL: " + currentUrl);

        try {
            Assert.assertTrue(currentUrl.contains("https://demoqa.com/"),
                    "User not navigated to DemoQA page!");
            test.pass("✅ Users navigated to DemoQA page successfully");
        } catch (AssertionError e) {
            test.fail("❌ Navigation to DemoQA failed: " + e.getMessage());
            throw e;
        }
    }

    @Test(dependsOnMethods = "M1_openDemoQA")
    public void M2_clickElement() {
        test = extent.createTest("Clicks on Elements Card");

        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0, 250)");
            driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div/div[1]")).click();

            Assert.assertTrue(driver.getCurrentUrl().equals("https://demoqa.com/elements"),
                    "❌ Click did not navigate to the Elements page!");
            test.pass("✅ Clicked on Elements card successfully");
        } catch (AssertionError e) {
            test.fail("❌ Click on Elements card failed: " + e.getMessage());
            throw e;
        }
    }

    @Test(dependsOnMethods = "M2_clickElement")
    public void M3_ElementTab() {
        test = extent.createTest("Open Text Box Tabs");

        try {
            driver.findElement(By.cssSelector("#item-0")).click();
            Assert.assertTrue(driver.getCurrentUrl().equals("https://demoqa.com/text-box"),
                    "❌ Click did not navigate to the Text Box tab!");
            test.pass("✅ Text Box tab opened successfully");
        } catch (AssertionError e) {
            test.fail("❌ Opening Text Box tab failed: " + e.getMessage());
            throw e;
        }
    }

    @Test(dependsOnMethods = "M3_ElementTab")
    public void M4_EnterDetailsInTab() {
        test = extent.createTest("Enter Details in Text Box");

        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0, 300)");

            driver.findElement(By.cssSelector("#userName")).sendKeys("Shiyas Rasi");
            driver.findElement(By.cssSelector("#userEmail")).sendKeys("ShiyasRasi@123.com");
            driver.findElement(By.cssSelector("#currentAddress")).sendKeys("Test CurrentAddress\nteststreet\ntestpostcode");
            driver.findElement(By.cssSelector("#permanentAddress")).sendKeys("Test Permanent Address\nteststreet\ntestpostcode");

            js.executeScript("window.scrollBy(0, 300)");
            driver.findElement(By.cssSelector("#submit")).click();

            test.pass("✅ Entered details and submitted form successfully");
        } catch (Exception e) {
            test.fail("❌ Failed to enter details or submit: " + e.getMessage());
            throw e;
        }
    }

    @Test(dependsOnMethods = "M4_EnterDetailsInTab")
    public void M5_CheckBoxClick() {
        test = extent.createTest("Click on CheckBox Tab");

        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            driver.findElement(By.cssSelector("#item-1")).click();

            Assert.assertTrue(driver.getCurrentUrl().equals("https://demoqa.com/checkbox"),
                    "❌ Click did not navigate to CheckBox page!");

            js.executeScript("window.scrollBy(0,200)");
            WebElement checkbox = driver.findElement(By.xpath("//span[@class='rct-checkbox']"));

            if (!checkbox.isSelected()) {
                checkbox.click();
            }

            test.pass("✅ Checkbox clicked successfully");
        } catch (AssertionError | Exception e) {
            test.fail("❌ CheckBox click failed: " + e.getMessage());
            throw e;
        }
    }

    @Test(dependsOnMethods = "M5_CheckBoxClick")
    public void M6_PrintListOfCheckBoxes() {
        test = extent.createTest("Print List of Checkboxes");

        try {
            driver.findElement(By.xpath("//button[@title='Expand all']")).click();
            List<WebElement> checkboxes = driver.findElements(By.cssSelector("span.rct-checkbox"));

            for (WebElement checkbox : checkboxes) {
                WebElement label = checkbox.findElement(By.xpath("./following-sibling::span[@class='rct-title']"));
                System.out.println(label.getText());
            }

            test.pass("✅ List of checkboxes printed successfully");
        } catch (Exception e) {
            test.fail("❌ Failed to print list of checkboxes: " + e.getMessage());
            throw e;
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
        if (extent != null) extent.flush();
        System.out.println("Test execution completed. Report generated at /test-output/ExtentReport.html");
    }
}
