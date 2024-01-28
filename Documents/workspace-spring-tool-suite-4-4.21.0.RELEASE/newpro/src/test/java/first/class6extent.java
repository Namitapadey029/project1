package first;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class class6extent {
    WebDriver driver;
    ExtentReports report;
    ExtentTest test;
    SoftAssert softAssert;

    @BeforeClass
    public void configReport() {
        report = new ExtentReports(System.getProperty("user.dir") + "\\ExtentReport1.html");// path of file
        test = report.startTest("https://demo.nopcommerce.com/");
    }

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\Namita Pandey\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        driver = new ChromeDriver();
        test.log(LogStatus.PASS, "Chrome diver is opened successfully");

        driver.get("https://demo.nopcommerce.com/");
        test.log(LogStatus.PASS, "Url is opened successfully");

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        // Initialize SoftAssert
        softAssert = new SoftAssert();
    }

    @Test(priority = 1,groups = {"Smoke"})
    public void addtocartproduct() throws InterruptedException, IOException {
        Actions actions = new Actions(driver);
        WebElement computerLink = driver.findElement(By.linkText("Computers"));
        actions.moveToElement(computerLink).build().perform();
        Thread.sleep(3000);
        WebElement notebooksLink = driver.findElement(By.linkText("Notebooks"));
        notebooksLink.click();
        Thread.sleep(3000);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,350)", "");
        Thread.sleep(3000);
        driver.findElement(By.linkText("Apple MacBook Pro 13-inch")).click();
        Thread.sleep(3000);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,350)", "");
        Thread.sleep(3000);
        driver.findElement(By.id("add-to-cart-button-4")).click();
        Thread.sleep(3000);
        driver.findElement(By.linkText("shopping cart")).click();
        Thread.sleep(2000);
        TakesScreenshot shots = (TakesScreenshot) driver;
        File file1 = shots.getScreenshotAs(OutputType.FILE);
        File file2 = new File("C:\\Users\\Namita Pandey\\Pictures\\take screenshot\\image.png");
        FileUtils.copyFile(file1, file2);

        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,350)", "");
        Thread.sleep(3000);
        driver.findElement(By.id("checkout")).click();
        Thread.sleep(3000);
        
        // Soft Assert URL contains "checkout"
        softAssert.assertTrue(driver.getCurrentUrl().contains("checkout"), "Checkout page not opened");
    }

    @Test(priority = 2,groups = {"Regression"})
    public void wishlist() throws IOException, InterruptedException {
        Actions actions = new Actions(driver);
        WebElement computerLink = driver.findElement(By.linkText("Computers"));
        actions.moveToElement(computerLink).build().perform();
        Thread.sleep(3000);
        WebElement notebooksLink = driver.findElement(By.linkText("Notebooks"));
        notebooksLink.click();
        Thread.sleep(3000);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,350)", "");
        Thread.sleep(3000);
        driver.findElement(By.linkText("Asus N551JK-XO076H Laptop")).click();
        Thread.sleep(3000);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,350)", "");
        Thread.sleep(3000);
        driver.findElement(By.id("add-to-wishlist-button-5")).click();
        Thread.sleep(3000);
        driver.findElement(By.linkText("wishlist")).click();
        Thread.sleep(2000);
       // WebDriverWait wait = new WebDriverWait(driver, 10);
       // WebElement wishlistLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("wishlist")));
        //wishlistLink.click();

        TakesScreenshot shots = (TakesScreenshot) driver;
        File file1 = shots.getScreenshotAs(OutputType.FILE);
        File file2 = new File("C:\\Users\\Namita Pandey\\Pictures\\take screenshot\\image.png");
        FileUtils.copyFile(file1, file2);
        
        // Soft Assert URL contains "wishlist"
       // softAssert.assertTrue(driver.getCurrentUrl().contains("wishlist"), "Wishlist page opened");
        //hard assert URL contains "wishlist"
        Assert.assertTrue(driver.getCurrentUrl().contains("wishlist"), "Wishlist page not opened");

    }
    @Test(priority = 3,groups = {"sanity"})
    public void Emailtofriend() throws IOException, InterruptedException {
        Actions actions = new Actions(driver);
        WebElement computerLink = driver.findElement(By.linkText("Computers"));
        actions.moveToElement(computerLink).build().perform();
        Thread.sleep(3000);
        WebElement notebooksLink = driver.findElement(By.linkText("Notebooks"));
        notebooksLink.click();
        Thread.sleep(3000);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,350)", "");
        Thread.sleep(3000);
        driver.findElement(By.linkText("HP Envy 6-1180ca 15.6-Inch Sleekbook")).click();
        Thread.sleep(3000);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,350)", "");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//button[normalize-space()='Email a friend']")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("FriendEmail")).sendKeys("namita.engg10@gmail.com");
        Thread.sleep(2000);
        driver.findElement(By.id("YourEmailAddress")).sendKeys("namitapandey029gmail.com");
        Thread.sleep(2000);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,350)", "");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//button[normalize-space()='Send email']")).click();
        Thread.sleep(2000);
    }

    @AfterMethod(alwaysRun = true)
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterClass(groups = "laststep")
    public void last() {
        // Perform soft assert
        softAssert.assertAll();
        report.endTest(test);
        report.flush();
    }
}
