package automation.appium;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class Base {
	@Test
	public void configureAppium() throws MalformedURLException {

		// run appium server automatically
		AppiumDriverLocalService service = new AppiumServiceBuilder()
				.withAppiumJS(new File(
						"C:\\Users\\Prashant\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723).withTimeout(Duration.ofSeconds(300)).build();
		service.start();

		// create capabilities
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("Demo");
		System.out.println(System.getProperty("user.dir"));
		options.setApp(System.getProperty("user.dir") + "\\src\\test\\java\\resources\\ApiDemos-debug.apk");

		// C:\\Users\\Prashant\\automation\\appium

		// create object of andriod driver/IO driver
		AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		// andriod locators
		// xpath,id,className,accessibilityId,andriodUIautomator
		// Preference
		// driver.findElement(By.xpath())
		driver.findElement(AppiumBy.accessibilityId("Preference")).click();
		driver.quit();
		service.stop();
	}
}
