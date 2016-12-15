package mkcoding.services.spider;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by mx on 16/12/15.
 */
public class SeleniumForSpider {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/mx/chromedriver");

        WebDriver driver = new ChromeDriver();
        driver.get("http://www.baidu.com");

        Thread.sleep(5000);  // Let the user actually see something!
        WebElement searchBox = driver.findElement(By.name("wd"));
        searchBox.sendKeys("ChromeDriver");
        searchBox.submit();

        System.out.println("Page title is: " + driver.getTitle());

        List<WebElement> elements = driver.findElements(By.cssSelector("h3.t > a"));
        for (WebElement we : elements) {
            System.out.println(we.getText());
            System.out.println(we.getAttribute("href"));
        }

        Thread.sleep(5000);  // Let the user actually see something!
        driver.quit();
    }
}
