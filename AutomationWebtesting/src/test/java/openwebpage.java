import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

public class openwebpage {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        //System.setProperty("testng.output.dir", System.getProperty("user.dir") + "/test-output");
        driver = new ChromeDriver();
        driver.get("https://demoqa.com/");
        driver.manage().window().maximize();

        Reporter.log("Browser launched and window maximized<br>");
    }

    @Test
    public void M1_openDemoQA() {

        System.out.println("✅ DemoQa opened successfully");
        // Verify navigation
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URl ; " +currentUrl);
        Assert.assertTrue(currentUrl.contains("https://demoqa.com/"),
                "User not navigated to DemoQA page!");
        Reporter.log("✅ Test Passed: GUser navigated to DemoQA page!");
    }

    @Test(dependsOnMethods = "M1_openDemoQA")
    public void M2_clickelement() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 250)");
        driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div/div[1]")).click();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://demoqa.com/elements"), "❌ Click did not navigate to the Elements page!");
        Reporter.log("✅ Test Passed: GUser Able to click element tab!");
    }
    @Test(dependsOnMethods = "M2_clickelement")
     public void M3_Element_tab() {
        driver.findElement(By.cssSelector("#item-0")).click();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://demoqa.com/text-box"), "❌ Click did not navigate to the Elements-->Tab page!");
        Reporter.log("✅ Test Passed: Clicked on tab!");
    }
    @Test(dependsOnMethods = "M3_Element_tab")
    public void M4_EnterDetailstoETab() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 300)");
        driver.findElement(By.cssSelector("#userName")).sendKeys("Shiyas Rasi");
        driver.findElement(By.cssSelector("#userEmail")).sendKeys("ShiyasRasi@123.com");
        driver.findElement(By.cssSelector("#currentAddress")).sendKeys("Test CurrentAddress\nteststreet\ntestpostcode");
        driver.findElement(By.cssSelector("#permanentAddress")).sendKeys("Test Permanent Address\nteststreet\ntestpostcode");

        js.executeScript("window.scrollBy(0, 400)");
        driver.findElement(By.cssSelector("#submit")).click();
        Reporter.log("✅ Test Passed: Clicked on tab!");
    }
    @AfterClass
    public void tearDown() {
        System.out.println("Project dir: " + System.getProperty("user.dir"));
    //driver.quit();
    }
}
