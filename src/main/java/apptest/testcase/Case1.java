package apptest.testcase;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import apptest.common.BaseTest;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;


public class Case1 extends BaseTest{
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private String caseName;
	private AndroidDriver<WebElement> driver;
	
    @DataProvider(name="testData")
    public Object[][] getProductSearchData(){
        return new Object[][]{{"case001"}};
    }
    
    @BeforeClass
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("appPackage", "com.wanda.kuaiyihua");
        capabilities.setCapability("appActivity", ".module.startup.StartupActivity");
        driver = new AndroidDriver<WebElement>(getServiceUrl(), capabilities);
    }
    
    @AfterClass
    public void tearDown() {
        driver.quit();
    }
    
    @Test(groups = { "demotest"},dataProvider = "testData")
    public void test(String caseName) throws InterruptedException{
        this.caseName = caseName;
        logger.info("caseName:{}",this.caseName);
        
        String activity = driver.currentActivity();
        String pkg = driver.getCurrentPackage();
        
        logger.info("activity:{},pkg:{}",activity,pkg);
        System.out.println("===============sleeping===================");
        Thread.sleep(5000L);
        //permission_allow
/*        for(int i = 0 ; i < 5 ;i++) {
	        if(driver.getCurrentPackage().equals("com.android.packageinstaller")) {
	        	driver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button")).click();
	        	Thread.sleep(1000);
	        }
        }*/
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.id("com.android.packageinstaller:id/permission_allow_button"))).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.id("com.android.packageinstaller:id/permission_allow_button"))).click();

        //swipe left
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        for(int i = 0 ; i < 5 ;i++) {
        	new TouchAction(driver).press(PointOption.point(width*9/10, height/2)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(PointOption.point(width/10, height/2)).release().perform();;
        }
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.id("com.wanda.kuaiyihua:id/tv_go_main"))).click();
/*        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.id("com.wanda.kuaiyihua:id/btn_apply"))).click();
        if(new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(By.id("com.wanda.kuaiyihua:id/btn_ok"))).getText().equals("我知道了")) {
        	driver.findElementById("com.wanda.kuaiyihua:id/btn_ok").click();
        }
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.id("com.android.packageinstaller:id/permission_allow_button"))).click();*/
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.id("com.wanda.kuaiyihua:id/login"))).click();
        Thread.sleep(3000);
        System.out.println(driver.getPageSource());
        
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.id("com.wanda.kuaiyihua:id/phone_number"))).sendKeys("19999990001");
        
        //下一步
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.id("com.wanda.kuaiyihua:id/t1"))).click();


        
        
    }
}
