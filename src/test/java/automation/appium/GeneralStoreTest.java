package automation.appium;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class GeneralStoreTest extends Base {

	@Test(enabled=false)
	public void generalStoreHomePageTest() {
		// Scroll and select Canada option from dropDown
		driver.findElement(By.id("android:id/text1")).click();
		driver.findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Canada\"));"));
		driver.findElement(By.xpath("//android.widget.TextView[@text='Canada']")).click();

		// Type name in a text field
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Jeniffer");
		driver.hideKeyboard(); // hide keyboard

		// Select radio option
		driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

	}

	@Test
	public void ToastMsgTest() {
		driver.findElement(By.id("android:id/text1")).click();
		driver.findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Canada\"));"));
		driver.findElement(By.xpath("//android.widget.TextView[@text='Canada']")).click();

		driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		
		String toastMsg=driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
		//System.out.println(toastMsg);
		Assert.assertEquals(toastMsg, "Please enter your name");

	}
}
