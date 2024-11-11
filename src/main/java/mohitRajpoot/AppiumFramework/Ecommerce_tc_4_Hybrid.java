package mohitRajpoot.AppiumFramework;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;



import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class Ecommerce_tc_4_Hybrid extends BaseTest{
	
	@Test
	public void fillForm() throws InterruptedException  {
		
		Thread.sleep(3000);
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("john");
		driver.hideKeyboard();
    	driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
		driver.findElement(By.id("android:id/text1")).click();
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Brazil\"));"));
		driver.findElement(By.xpath("//android.widget.TextView[@text='Brazil']")).click();
		
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		
		Thread.sleep(3000);
		driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
		driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
//		
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		
		// wait untill the next page is not loaded
//		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(8));
//		wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart")); 
		Thread.sleep(8000);
		
		List<WebElement> productPrices = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
		int count = productPrices.size();
		double totalSum = 0.0;
		for(int i=0;i<count;i++) {
			String amount = productPrices.get(i).getText();
			Double price = Double.parseDouble(amount.substring(1));
			totalSum += price;
		}
		String displaySum = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
		Double price = Double.parseDouble(displaySum.substring(1));
		

		Assert.assertEquals(totalSum, price.doubleValue());
		WebElement ele = driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
		longPress(ele);
		driver.findElement(By.id("android:id/button1")).click();
		driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
		Thread.sleep(10000);
		
		
		
		Set<String> contexts = driver.getContextHandles();
		for(String s:contexts) {
			System.out.println("============contexts============"+s);
			if (s.startsWith("WEBVIEW")) {
					driver.context(s);
					System.out.println("============After contexts============"+s);
			}
		}
		
		((AndroidDriver) driver).activateApp("com.android.chrome");
		
		
//		driver.context("WEBVIEW_com.androidsample.generalstore"); // chrome driver
//		driver.findElement(By.name("q")).sendKeys("wikipedia");
//		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
//		driver.pressKey(new KeyEvent(AndroidKey.BACK));
//		driver.context("NATIVE_APP");
	}
	
}
