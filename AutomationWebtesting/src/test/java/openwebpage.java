import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.sql.Driver;
import java.util.List;

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

        js.executeScript("window.scrollBy(0, 300)");
        driver.findElement(By.cssSelector("#submit")).click();
        Reporter.log("✅ Test Passed: Clicked on tab!");
    }

    @Test(dependsOnMethods = "M4_EnterDetailstoETab")
    public void M5_CheckBoxclick() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //js.executeScript("window.scroll(0,-500 )");
        driver.findElement(By.cssSelector("#item-1")).click();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://demoqa.com/checkbox"), "❌ Click did not navigate to the Elements-->CheckBox page!");

        Reporter.log("✅ Test Passed: Clicked on CheckBox!");
        js.executeScript("window.scroll(0,200 )");
        boolean Cflag = false;
//        try {
//            Thread.sleep(5000); // 2 seconds
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        WebElement checkbox = driver.findElement(By.xpath("//span[@class='rct-checkbox']"));
        //driver.findElement(By.xpath("//span[@class='rct-checkbox']")).click();


        if (!checkbox.isSelected()) {
            checkbox.click();
            Cflag = true;
        }
        Assert.assertTrue(Cflag,"User is not able to click CheckBox!");
    }
    @Test(dependsOnMethods = "M5_CheckBoxclick")
    public void M6_PrinttheListofcheckboxes() {
        driver.findElement(By.xpath("//button[@title='Expand all']")).click();
        List<WebElement> checkboxes = driver.findElements(By.cssSelector("span.rct-checkbox"));
        for (WebElement checkbox : checkboxes)
            {
                WebElement label = checkbox.findElement(By.xpath("./following-sibling::span[@class='rct-title']"));
            System.out.println(label.getText());
            }
//        for (WebElement checkbox : checkboxes) {
//            WebElement input = checkbox.findElement(By.xpath("./ancestor::label/input"));
//            if (!input.isSelected()) {
//                checkbox.click();
//                Assert.assertTrue(input.isSelected());
//            }
//        }

    }
    @AfterClass
    public void tearDown() {
        System.out.println("Project dir: " + System.getProperty("user.dir"));
    //driver.quit();
    }
}
