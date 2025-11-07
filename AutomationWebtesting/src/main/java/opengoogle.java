import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;

import java.util.List;
public class opengoogle {
public static void main(String[] args){
    System.out.println("Hello World");
    WebDriverManager.chromedriver().driverVersion("142.0.7444.60").setup();
    WebDriver driver = new ChromeDriver();

    try {

        driver.get("https://demoqa.com/");
        driver.manage().window().maximize();
       //WebElement elementsLink = driver.findElement(By.xpath("//div[@class='card mt-4 top-card']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)");

        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div/div[1]")).click();
//       elementsLink.click();
      // System.out.println("elementsLink.isDisplayed(): " + elementsLink.isDisplayed());

//        searchBox.sendKeys("Selenium Java tutorial");
//        searchBox.submit();
        Thread.sleep(3000);

    } catch (Exception e) {
        e.printStackTrace();
    }
    finally{
      //  driver.close();
    }
}
}
