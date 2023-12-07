package seleniumAdvanced_LambdaTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class TestScenario2 {
	
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		
		EdgeOptions browserOptions = new EdgeOptions();
		browserOptions.setPlatformName("Windows 10");
		browserOptions.setBrowserVersion("87.0");
		HashMap<String, Object> ltOptions = new HashMap<String, Object>();
		ltOptions.put("username", "saijahnavithummala");
		ltOptions.put("accessKey", "d9wR3G0utBwPB4Xqgmj75OlWJFICMZIQkmHv78UMT0v6TjEzRL");
		ltOptions.put("visual", true);
		ltOptions.put("video", true);
		ltOptions.put("network", true);
		ltOptions.put("project", "Selenium_Advanced");
		ltOptions.put("name", "TestScenario2");
		ltOptions.put("selenium_version", "4.0.0");
		ltOptions.put("w3c", true);
		
		browserOptions.setCapability("LT:Options", ltOptions);
		
		WebDriver driver = new RemoteWebDriver(new URL("https://hub.lambdatest.com/wd/hub"), browserOptions);
        driver.manage().window().maximize();
			
        //Step1
        driver.get("https://www.lambdatest.com/");
        
        //Step2
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("__next")));
        
        //Step3
        WebElement BitBucketImage = driver.findElement(By.xpath("//*[@class='block my-0 mx-auto']"));
        WebElement seeAllIntegrationsLink = driver.findElement(By.xpath("//*[text()='See All Integrations']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", BitBucketImage);
        
        //Step4
        Actions actions = new Actions(driver); 
        actions.keyDown(Keys.LEFT_CONTROL)
        	   .click(seeAllIntegrationsLink)
        	   .keyUp(Keys.LEFT_CONTROL)
        	   .build()
        	   .perform();
        
        //Step5
        Set<String> windowHandles = driver.getWindowHandles();
        List<String> handlesList = new ArrayList<>(windowHandles);
        // Print the window handles
        for (String handle : handlesList) {
            System.out.println("Window Handle: " + handle);
        }
        // Ensure that there are two windows open
        if (handlesList.size() == 2) {
            System.out.println("Two windows are in open.");
        } 
        else {
            System.out.println("Unexpected number of windows open.");
        }
        
        //Step6
        driver.switchTo().window(handlesList.get(1));
        String actualURL = "https://www.lambdatest.com/integrations";
        String expectedURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL, expectedURL, "expected URL is Verified");
        //System.out.printf(actualURL + expectedURL + "expected URL is Verified");
        
        //Step7
        WebElement codelessAutomationElement = driver.findElement(By.xpath("//*[text()='Codeless Automation']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", codelessAutomationElement);
        
        //Step 8 & 9
        driver.findElement(By.xpath("//*[text()='Integrate Testing Whiz with LambdaTest']")).click();
        boolean verifyLink = driver.getTitle().contains("TestingWhiz Integration | LambdaTest");
        System.out.println("Current Title: " +driver.getTitle());
        SoftAssert ss = new SoftAssert();
        ss.assertTrue(verifyLink, "TestingWhiz Integration | LambdaTest does not match to the current Title.");
        System.out.println("TestingWhiz Integration | LambdaTest does not match to the current Title.");
        
        //Step 10
        driver.close();
        
        //Step 11
        driver.switchTo().window(handlesList.get(0));
        System.out.println("Current window count: "+ driver.getWindowHandles().size());
        
        //Step 12
        driver.get("https://www.lambdatest.com/blog/");
        
        //Step 13
        WebElement communityLink = driver.findElement(By.xpath("//*[text()='Community']"));
        communityLink.click();
        boolean verifyURL = driver.getCurrentUrl().contains("https://community.lambdatest.com/");
        Assert.assertTrue(verifyURL, "Community URL is verified Successfully.");
        System.out.println("Community URL is verified Successfully.");
        
        //Step 14
        driver.quit();
        
	}

}
