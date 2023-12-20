package automation.appium;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class Base {
	public AndroidDriver driver;
	public AppiumDriverLocalService service;

	@BeforeMethod
	public void configureAppium() throws MalformedURLException {

		// run appium server automatically
		service = new AppiumServiceBuilder()
				.withAppiumJS(new File(
						"C:\\Users\\Prashant\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723).withTimeout(Duration.ofSeconds(300)).build();
		service.start();

		// create capabilities
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("Demo");
		options.setApp(System.getProperty("user.dir") + "\\src\\test\\java\\resources\\General-Store.apk");
		options.setChromedriverExecutable(System.getProperty("user.dir") + "\\src\\test\\java\\resources\\chromedriver.exe");

		// create object of andriod driver/IO driver
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		// andriod locators
		// xpath,id,className,accessibilityId,andriodUIautomator
	}

	public void longPress(WebElement ele) {

		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(), "duration", 3000));
	}

	public void ScrollToEnd() {

		boolean canScrollMore;
		do {
			canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap
					.of("left", 100, "top", 100, "width", 200, "height", 200, "direction", "down", "percent", 3.0));

		} while (canScrollMore);

	}

	public void ScrollToElement(String ele) {
		String scrollCommand = "new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + ele + "\"));";
		driver.findElement(AppiumBy.androidUIAutomator(scrollCommand));
	}

	// perform swipe action
	public void swipeAction(WebElement ele, String swipeDirection) {

		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of("elementId",
				((RemoteWebElement) ele).getId(), "direction", swipeDirection, "percent", 0.75));
	}

	@AfterMethod
	public void tearDown() {
		System.out.println("driver quit");
		driver.quit();
		service.stop();
	}
}
