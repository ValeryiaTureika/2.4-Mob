package ru.netology.tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;


import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.netology.pages.MainActivityPage;

import static io.appium.java_client.remote.AndroidMobileCapabilityType.APP_ACTIVITY;
import static io.appium.java_client.remote.AndroidMobileCapabilityType.APP_PACKAGE;
import static io.appium.java_client.remote.MobileCapabilityType.DEVICE_NAME;
import static org.openqa.selenium.remote.CapabilityType.PLATFORM_NAME;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ActivityTest {
    private AppiumDriver driver;

    @BeforeAll

    public void setUp() throws MalformedURLException {
        String platform = System.getProperty("platform");
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(PLATFORM_NAME, "android");
        desiredCapabilities.setCapability(DEVICE_NAME, "any name");
        desiredCapabilities.setCapability(APP_PACKAGE, "ru.netology.testing.uiautomator");
        desiredCapabilities.setCapability(APP_ACTIVITY, "ru.netology.testing.uiautomator.MainActivity");
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);

        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AppiumDriver(remoteUrl, desiredCapabilities);
    }


   /* @Test
    public void dontSetEmptyString() {
        MobileElement el1 = (MobileElement) driver.findElementById("userInput");
        el1.click();

        el1.sendKeys(" ");
        MobileElement el2 = (MobileElement) driver.findElementById("buttonChange");
        el2.click();
        MobileElement el3 = (MobileElement) driver.findElementById("textToBeChanged");

        Assertions.assertEquals("Hello UiAutomator!", el3.getText());
    }

    @Test
    public void activityTest() {
        MobileElement el1 = (MobileElement) driver.findElementById("userInput");
        el1.click();
        el1.sendKeys(" test");
        MobileElement el4 = (MobileElement) driver.findElementById("buttonActivity");
        el4.click();
        MobileElement el5 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.widget.TextView");

        Assertions.assertEquals(" test", el5.getText());
    }*/



    @Test
    public void dontSetEmptyString() {
        MainActivityPage page = new MainActivityPage(driver);
        page.userInput.sendKeys("     ");
        page.buttonChange.click();
        Assertions.assertEquals("Привет, UiAutomator!", page.textToBeChanged.getText());

    }

    @Test
    public void activityTest() {
        MainActivityPage page = new MainActivityPage(driver);
        page.userInput.sendKeys("test");
        page.buttonActivity.click();
        Assertions.assertEquals("test", page.activityText.getText());

    }

    @AfterAll
    public void tearDown() {
        driver.quit();
    }
}




