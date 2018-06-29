package apptest.testcase;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import apptest.common.BaseTest;
import io.appium.java_client.android.AndroidDriver;


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
    }
}
