import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import java.math.BigInteger;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.*;

public class debug_test {
    private AndroidDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability("platformName", "Android");//设备的系统
        capabilities.setCapability("deviceName", "meizu-m5_note-621QEBPQ2F42W");//设备名称meizu-m5_note-621QEBPQ2F42W
        capabilities.setCapability("Version", "7.0");//系统版本号
        capabilities.setCapability("appPackage", "com.fcmcoin.fcm");//需要启动的包名
        capabilities.setCapability("appActivity", ".ui.activity.MainActivity");//需要启动的Activity
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    //滑动的方法,屏幕大小(1080,1920)
    public class Swipe {
        public void SwipetoUp(AndroidDriver driver) {
            int height = driver.manage().window().getSize().height;
            int width = driver.manage().window().getSize().width;
            new TouchAction(driver).longPress(PointOption.point(width / 2, 100))
                    .moveTo(PointOption.point(width / 2, height - 100)).release().perform();
        }

        public void SwipetoDown(AndroidDriver driver) {
            int height = driver.manage().window().getSize().height;
            int width = driver.manage().window().getSize().width;
            new TouchAction(driver).longPress(PointOption.point(width / 2, height / 4 * 3))
                    .moveTo(PointOption.point(width / 2, 100)).release().perform();
        }

        public void SwipetoLeft(AndroidDriver driver) {
            int height = driver.manage().window().getSize().height;
            int width = driver.manage().window().getSize().width;
            new TouchAction(driver).longPress(PointOption.point(width - 100, height / 2))
                    .moveTo(PointOption.point(100, height / 2)).release().perform();
        }

        public void SwipetoRight(AndroidDriver driver) {
            int height = driver.manage().window().getSize().height;
            int width = driver.manage().window().getSize().width;
            new TouchAction(driver).longPress(PointOption.point(100, height / 2))
                    .moveTo(PointOption.point(width - 100, height / 2)).release().perform();
        }
    }

    @Test
    //首页
    public void HomePage() throws InterruptedException {
        //关注发帖者
        driver.findElementById("com.fcmcoin.fcm:id/textView").click();
        Thread.sleep(3000);
        String oldcomment=driver.findElementById("com.fcmcoin.fcm:id/textView").getAttribute("text");
        int oldcomment1=Integer.parseInt(oldcomment.substring(0,1));
        driver.findElementById("com.fcmcoin.fcm:id/comment_box").sendKeys("1");
        driver.findElementById("com.fcmcoin.fcm:id/publish").click();
        Thread.sleep(3000);
        String newcomment=driver.findElementById("com.fcmcoin.fcm:id/textView").getAttribute("text");
        int newcomment1 = Integer.parseInt(newcomment.substring(0,1));

        if(oldcomment1==newcomment1+1){
            System.out.println("评论成功");
        }else {
            System.out.println("评论失败");
        }





    }
}