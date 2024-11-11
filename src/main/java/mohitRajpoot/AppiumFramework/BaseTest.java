package mohitRajpoot.AppiumFramework;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest {

	AndroidDriver driver;
	AppiumDriverLocalService service;
	
	@BeforeClass
	public void configureAppium() throws MalformedURLException, URISyntaxException {
		String chromePath = System.getProperty("user.dir");
		String path = chromePath+"\\src\\main\\java\\driver\\chromedriver.exe";
		System.out.println("-------------chrome path--------"+path);
		service = new AppiumServiceBuilder().withAppiumJS(new File("C://Users//Mohit//AppData//Roaming//npm//node_modules//appium//build//lib//main.js")).withIPAddress("127.0.0.1").usingPort(4723).build();         
		service.start();
		
		UiAutomator2Options options = new UiAutomator2Options();

		options.setDeviceName("Pixel 7"); // Device name
//		System.setProperty("webdriver.chrome.driver", "C://Users//Mohit//eclipse//Downloads//chromedriver_win32 (1)//chromedriver.exe");
//		options.setCapability("browserName", "Chrome");
		options.setChromedriverExecutable(path);
//		System.setProperty("webdriver.chrome.driver","C://Users//Mohit//eclipse//Downloads//chromedriver-win64//chromedriver-win64.exe");
		options.setApp("D://mvnappium//appium//src//test//java//resources//ApiDemos-debug.apk"); // Path to the app
//		options.setApp("D://mvnappium//appium//src//test//java//resources//General-Store.apk");
		options.setPlatformName("Android"); // Platform
		options.setAutomationName("UiAutomator2"); // Automation engine


		// Initialize the driver
		driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);
	}

	
	@AfterClass
	public void classTearDown() {
		driver.quit();
		service.stop();
	}
	
	public void longPress(WebElement ele) {
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", 
                ImmutableMap.of(
                        "elementId", ((RemoteWebElement) ele).getId(),
                        "duration", 2000
                ));
	}
}
