package test;
import java.net.MalformedURLException;

import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;


public class Selenium {

	public static void main(String[] args) throws MalformedURLException {
		
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5544");
		dc.setCapability("platformName", "android");
		dc.setCapability("appActivity", "com.chromium.chrome.browser.LauncherShortcutActivity");
		
		
		AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(new URL("http://0.0.0.0:4723/wd/hub/"), dc);
		
		MobileElement el1 = (MobileElement) driver.findElementByAccessibilityId("Chrome");
		el1.click();
		MobileElement el2 = (MobileElement) driver.findElementByAccessibilityId("Home");
		el2.click();
		MobileElement el3 = (MobileElement) driver.findElementById("com.android.chrome:id/search_box_text");
		el3.click();
		
		
	}

}
