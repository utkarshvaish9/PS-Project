package com.ideas.ibot.Selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by idnutv on 9/27/2017.
 */
public class GetResumes {
    public static void fetchResumesFromIndeed(String category, String department){
        System.setProperty("webdriver.chrome.driver","C:\\folder\\chromedriver.exe");
        String downloadFilepath = "C:\\Users\\idnutv\\Downloads\\allResumes" + "\\" + department + "\\" + category;
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        File theDir = new File(category);
        if(!theDir.exists())
        {
            theDir.mkdir();
        }
        chromePrefs.put("download.default_directory", downloadFilepath);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        cap.setCapability(ChromeOptions.CAPABILITY, options);
        WebDriver driver = new ChromeDriver(cap);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to("https://www.indeed.co.in/");
        driver.findElement(By.xpath("//*[@id=\"userOptionsLabel\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"signin_email\"]")).sendKeys("utkarshvaish9@gmail.com");
        driver.findElement(By.xpath("//*[@id=\"signin_password\"]")).sendKeys("9919598181");
        driver.findElement(By.xpath("//*[@id=\"loginform\"]/button")).click();
        driver.findElement(By.xpath("//*[@id=\"rezLink\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"query\"]")).sendKeys(category);
        driver.findElement(By.xpath("//*[@id=\"location\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"location\"]")).sendKeys("India");
        driver.findElement(By.xpath("//*[@id=\"submit\"]/span")).click();
        int PageNumber = 1;
        for(int i=0;i<PageNumber-1;i++)
            driver.findElement(By.xpath("//*[@id=\"pagination\"]/a[5]")).click();
        List<WebElement> results = driver.findElements(By.xpath("//ol[@id='results']/li//a"));
        String parentWindow = driver.getWindowHandle();//Getting parent window handle
        for (int i=0;i<results.size();i++) {

            results.get(i).click();
            for(String childs: driver.getWindowHandles()){
                driver.switchTo().window(childs);
            }

            driver.findElement(By.linkText("Download Resume")).click();

            driver.close();
            driver.switchTo().window(parentWindow);
        }

    }


}
