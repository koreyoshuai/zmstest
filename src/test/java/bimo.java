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

public class bimo {
    private AndroidDriver driver;

    @Before
    public void setUp() throws Exception{
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
    public void tearDown() throws Exception{
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
        //页面往下浏览
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
        Swipe swipe =new Swipe();
        Thread.sleep(3000);
        //轮播图测试（case:001-003）
        try{
            driver.findElementById("com.fcmcoin.fcm:id/bannerViewPager").click();
            Thread.sleep(5000);
            swipe.SwipetoDown(driver);
            swipe.SwipetoDown(driver);
            System.out.println("资讯详情浏览----pass");
            driver.navigate().back();
            System.out.println("轮播图详情浏览----pass");
        }catch (Exception e1){
            System.out.println("当前无轮播图");
        }

        //判断是否登录账号
        Thread.sleep(3000);
        driver.findElementById("com.fcmcoin.fcm:id/textView").click();
        Thread.sleep(3000);
        driver.findElementById("com.fcmcoin.fcm:id/imageView").click();
        try{
            driver.findElementByName("请输入手机号").sendKeys("15558207835");
            driver.findElementByName("请输入登录密码").sendKeys("123456a");
            driver.findElementById("com.fcmcoin.fcm:id/button2").click();
            System.out.println("登录成功");
        }catch (Exception e1){
            System.out.println("已登录账号");
        }

        //关注发帖者
        try{
            driver.findElementById("+ 关注").click();
            System.out.print("关注成功");
        }catch (Exception e2){
            try{
                driver.findElementById("已关注").click();
                System.out.print("已取消关注");
            }catch (Exception e3){
                System.out.println("关注异常，请排查");
            }
        }

        //评论
        Thread.sleep(3000);
        driver.findElementById("com.fcmcoin.fcm:id/comment_box").sendKeys("1");
        driver.findElementById("com.fcmcoin.fcm:id/publish").click();
        Thread.sleep(3000);
        String Comment=driver.findElementById("com.fcmcoin.fcm:id/textView").getAttribute("text");
        int comment1 = Integer.parseInt(Comment.substring(0,1));
        if(comment1!=0){
            System.out.println("评论成功");
        }else {
            System.out.println("评论失败");
        }
        //分享
        Thread.sleep(3000);
        driver.findElementById("com.fcmcoin.fcm:id/share").click();
        driver.findElementByName("QQ").click();
        System.out.println("分享成功");
        driver.navigate().back();
        driver.navigate().back();
        //快讯
        Thread.sleep(3000);
        driver.findElementByName("快讯").click();
        driver.findElementById("com.fcmcoin.fcm:id/expandable_text").click();
        swipe.SwipetoDown(driver);
        Thread.sleep(3000);
        driver.findElementById("com.fcmcoin.fcm:id/expandable_text").click();
        swipe.SwipetoDown(driver);
        System.out.println("快讯测试----pass");
        //区块链
        Thread.sleep(3000);
        driver.findElementByName("区块链").click();
        driver.findElementById("com.fcmcoin.fcm:id/textView").click();
        swipe.SwipetoDown(driver);
        Thread.sleep(2000);
        swipe.SwipetoDown(driver);
        System.out.println("区块链测试----pass");
        driver.navigate().back();
        //比特币
        Thread.sleep(3000);
        driver.findElementByName("比特币").click();
        driver.findElementById("com.fcmcoin.fcm:id/textView").click();
        swipe.SwipetoDown(driver);
        Thread.sleep(2000);
        swipe.SwipetoDown(driver);
        System.out.println("比特币测试----pass");
        driver.navigate().back();

        //政策
        Thread.sleep(3000);
        driver.findElementByName("政策").click();
        driver.findElementById("com.fcmcoin.fcm:id/textView").click();
        swipe.SwipetoDown(driver);
        Thread.sleep(2000);
        swipe.SwipetoDown(driver);
        System.out.println("政策测试----pass");
        driver.navigate().back();
        //矿圈
        Thread.sleep(3000);
        driver.findElementByName("矿圈").click();
        driver.findElementById("com.fcmcoin.fcm:id/textView").click();
        swipe.SwipetoDown(driver);
        Thread.sleep(2000);
        swipe.SwipetoDown(driver);
        System.out.println("矿圈测试----pass");
        driver.navigate().back();
        //技术指导
        Thread.sleep(3000);
        driver.findElementByName("技术指导").click();
        driver.findElementById("com.fcmcoin.fcm:id/textView").click();
        swipe.SwipetoDown(driver);
        Thread.sleep(2000);
        swipe.SwipetoDown(driver);
        System.out.println("技术指导测试----pass");
        driver.navigate().back();


    }
    //行情
    public void Quotation() throws InterruptedException{

    }
    //社群
    public void Community() throws InterruptedException{

    }
    //我的模块
    public void MyPage() throws InterruptedException{

    }


}

