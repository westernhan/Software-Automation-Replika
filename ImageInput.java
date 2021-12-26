package test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.PushesFiles;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;
import io.appium.java_client.remote.MobileCapabilityType;

/**
 * Assumes replika and images are on android studio 
 */
public class ImageInputTest {
	

	public static void main(String[] args)throws IOException{
		
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
		dc.setCapability("platformname", "android");
		dc.setCapability("appActivity", "com.chromium.browser.LauncherShortcutActivity");
        	dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "ANDROID");
        	dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.0");
        	dc.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 4 XL API 30");
        	dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
		AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(new URL("http://0.0.0.0:4723/wd/hub"), dc);
		driver.manage().timeouts().implicitlyWait(600, TimeUnit.SECONDS);
		
		try {
			//get file of images
			File imgFolder = new File(System.getProperty("user.home") + "/Downloads/images/");
			ArrayList<String> img = new ArrayList<String>(Arrays.asList(imgFolder.list()));
			
			//opens Replika app
			MobileElement el1 = (MobileElement) driver.findElementByAccessibilityId("Replika");
			el1.click();
			
			//opens chat
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			MobileElement el2 = (MobileElement) driver.findElementById("ai.replika.app:id/chatButton");
			el2.click();
			
			for(int i=0; i<img.size(); i++) {
				
				//clicks on send picture icon to send image to Replika
				MobileElement el3 = (MobileElement) driver.findElementByAccessibilityId("Send a picture");
				driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
				el3.click();
				
				
				MobileElement el4 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.TabHost/android.widget.LinearLayout/android.widget.FrameLayout/com.android.internal.widget.ViewPager/android.widget.RelativeLayout/com.android.internal.widget.RecyclerView/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.ImageView");
				el4.click();
				driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
				
				try {
					//select recent image
					driver.findElements(MobileBy.id("com.google.android.documentsui:id/thumbnail")).get(i).click();
					driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	                
					//crop image
					MobileElement el5 = (MobileElement) driver.findElementById("ai.replika.app:id/crop_image_menu_crop");
					el5.click();
					
					if(driver.findElements(MobileBy.id("ai.replika.app:id/crop_image_menu_crop")).size() > 0) {
						driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
						el5.click();
					}
					
					driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
					Thread.sleep(1000);
					driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
					

					System.out.println("Image Test Case: " + img.get(i) + " passed");
				} catch (Exception e) {
					System.out.println("Image Test Case: " + img.get(i) + " failed");
				}
				
			}
			
			driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
			

			driver.navigate().back();
			driver.navigate().back();
			driver.pressKey(new KeyEvent(AndroidKey.HOME));
			
			System.out.println("DONE");
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
}
	
