package test;
import java.io.File;
import java.io.FileWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class ImageInput {
	


    private AndroidDriver<MobileElement> driver;
    private static List<String> imagesData = new ArrayList<String>();
    private List<Map<String,String>> imagesDataMap = new ArrayList<Map<String,String>>();
    
   
    
    static List<List<String>> records = new ArrayList<>();

    @BeforeTest
    public void setup() throws MalformedURLException {
    	
 
        //UIAutomator Setup
        //Change Values are per your Test Case Setup
        DesiredCapabilities capabilities = new DesiredCapabilities();
       // Path currentRelativePath = Paths.get("/data/user/0/ai.replika.app");
       // String appPath = currentRelativePath.toAbsolutePath().toString();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "ANDROID");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.0");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel3a");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        //capabilities.setCapability(MobileCapabilityType.APP, appPath);
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 9999);
        capabilities.setCapability("appPackage", "ai.replika.app");
        capabilities.setCapability("appActivity", "ai.replika.app.ui.activity.onboarding.LauncherActivity");
        capabilities.setCapability("autoGrantPermissions", true);
        //driver = new AndroidDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
        driver = new AndroidDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);



        // Code Push Images/Files to device
        pushFilesToDevice();
        
       
    }
    
    

    @Test
    public void test() {


        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);



        try{
            String arg1 =DEVICE_REMOTE_DIR_PATH+"*";
            ArrayList<String> argList = new ArrayList<String>();
            argList.add(arg1);

            Map<String, Object> args = new HashMap<>();
            args.put("command", "rm");
            args.put("args", argList);
            driver.executeScript("mobile: shell", args);
        }catch (Exception e){
            System.out.println("No files found");
        }



        try{
            //before loop statements
            //perform starting one time actions here
            /* --------------- Change Here ---------------- */
            MobileElement el1 = (MobileElement) driver.findElementByAccessibilityId("Send a picture");
            el1.click();
            MobileElement el2 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.TabHost/android.widget.LinearLayout/android.widget.FrameLayout/com.android.internal.widget.ViewPager/android.widget.RelativeLayout/com.android.internal.widget.RecyclerView/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.ImageView");
            el2.click();
            MobileElement el3 = (MobileElement) driver.findElementById("com.google.android.documentsui:id/icon_mime_lg");
            el3.click();
            MobileElement el4 = (MobileElement) driver.findElementById("ai.replika.app:id/crop_image_menu_crop");
            el4.click();
        } catch(Exception ex) {

            System.out.println("One time action execution failed");
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }



        try{
            System.out.println("################################################");
            System.out.println("Starting to fetch images from Folder");



            readFileData();
            System.out.println(records);


            int rd_size=records.size();
            int rd_cols=records.get(0).size();


                for(int i=1;i<rd_size;i++ ) {

                String fileName=records.get(i).get(rd_cols-1);



                System.out.println("file name : "+fileName);
                if(fileName != null){
                    fileName=fileName.replace("\"","");
                }
                File imageFile = new File(IMG_DIR_PATH + fileName);
               // driver.pushFile(DEVICE_REMOTE_DIR_PATH + fileName, imageFile);
                System.out.println("File pushed to device");
                Thread.sleep(1000);




                
                //try1: do mobile element rather use mobileBy 
               // driver.findElement(MobileBy.id("tuat.kr.sullivan:id/ivNext")).click();
                //driver.findElements(MobileBy.id("tuat.kr.sullivan:id/llItem")).get(2).click();
                
               // Thread.sleep(1000);
                try {
                    //driver.findElements(MobileBy.id("com.android.documentsui:id/icon_thumb")).get(0).click();
                    MobileElement el1 = (MobileElement) driver.findElementByAccessibilityId("Send a picture");
                    el1.click();
                    MobileElement el2 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.TabHost/android.widget.LinearLayout/android.widget.FrameLayout/com.android.internal.widget.ViewPager/android.widget.RelativeLayout/com.android.internal.widget.RecyclerView/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.ImageView");
                    el2.click();
                    MobileElement el3 = (MobileElement) driver.findElementById("com.google.android.documentsui:id/icon_mime_lg");
                    el3.click();
                    MobileElement el4 = (MobileElement) driver.findElementById("ai.replika.app:id/crop_image_menu_crop");
                    el4.click();
                }catch (Exception ex){
                    ex.printStackTrace();
                }
                /* --------------- END ---------------- */


                /* --------------- Template Standard Code ---------------- */

                String outputText = "";

                /* --------------- END ---------------- */


                /* Core application testing result Capture in "outputText" */
                /* --------------- Change Here ---------------- */
                /* --------------- Start ---------------- */
                Thread.sleep(1500);
                MobileElement textEle = (MobileElement) driver.findElement(MobileBy.id("ai.replika.app:id/crop_image_menu_crop"));
                while (textEle.getText().contains("progress")) {
                    Thread.sleep(1000);
                }
                System.out.println(textEle.getText());

                outputText = textEle.getText();
                /* --------------- END ---------------- */



                /* --------------- Template Standard Code ---------------- */
                Thread.sleep(100);
                records.get(i).set(rd_cols-4,outputText);
                Thread.sleep(1000);
                /* --------------- END ---------------- */

            }
            System.out.println("##############################  END     ########################");
        }
        catch(Exception e){
            System.out.println("Error transfering file to device");
            e.printStackTrace();
        }
        try{
            //System.out.println(records);
            /* --------------- Template Standard Code ---------------- */
            createOutputFileData();
            /* --------------- END ---------------- */

            System.out.println("END test");
            System.out.println("################################################");


        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }

    }

    public void readFileData() {
        try {
            Scanner scanner = new Scanner(new File(FILE_PATH));

            while (scanner.hasNextLine()) {
                records.add(getRecordFromLine(scanner.nextLine()));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void createOutputFileData() {
        try {

            File fold=new File(FILE_PATH);
            fold.delete();

            FileWriter csvWriter = new FileWriter(FILE_PATH,false);

            for (List<String> rowData : records) {
                csvWriter.append(String.join(",", rowData));
                csvWriter.append("\n");
            }
            csvWriter.flush();
            csvWriter.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


    private static List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<String>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(COMMA_DELIMITER);
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }

    // Code Push Images/Files to device @"/mnt/sdcard/Automation/" directory
    public void pushFilesToDevice(){
        //Remote Dir CleanUP
        try{
            String arg1 =DEVICE_REMOTE_DIR_PATH+"*";
            ArrayList<String> argList = new ArrayList<String>();
            argList.add(arg1);

            Map<String, Object> args = new HashMap<>();
            args.put("command", "rm");
            args.put("args", argList);
            driver.executeScript("mobile: shell", args);
        }catch (Exception e){
            System.out.println("No files found");
        }


//            Process process = Runtime.getRuntime().exec(adbCommand);

        try{
            System.out.println("################################################");
            System.out.println("Starting to fetch images from Folder");



            readFileData();
            System.out.println(records);


            int rd_size=records.size();
            int rd_cols=records.get(0).size();

            for(int i=rd_size-1;i>0;i-- ) {

                String fileName=records.get(i).get(rd_cols-1);
                imagesData.add(fileName);
                Map<String,String> tcData=new HashMap<String,String>();
                tcData.put("TC_ID",records.get(i).get(0));
                tcData.put("ROW_INDEX",String.valueOf(i));
                tcData.put("fileName",fileName);
                imagesDataMap.add(tcData);


                System.out.println("file name : "+fileName);
                if(fileName != null){
                    fileName=fileName.replace("\"","");
                }
                File imageFile = new File(IMG_DIR_PATH + fileName);
               // driver.pushFile(DEVICE_REMOTE_DIR_PATH + fileName, imageFile);
                System.out.println("File pushed to device");
                Thread.sleep(2000);
            }
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            System.out.println("##############################  END     ########################");
        }
        catch(Exception e){
            System.out.println("Error transfering file to device");
            e.printStackTrace();
        }
    }


    @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

}

