How to start appium server automatically

Creating capabilities

Creating object of andriod driver/IO driver

**Inspecting elements with the help of appium inspectors
andriod locators:**
xpath,id,className,accessibilityId,andriodUIautomator

<img width="960" alt="image" src="https://github.com/PrashantSinghT99/appium/assets/52065013/38ad231f-b6ff-4d1c-b1b1-906cca703562">

{
  "app": "C:\\\\Users\\\\Prashant\\\\automation\\\\appium\\\\src\\\\test\\\\java\\\\resources\\\\ApiDemos-debug.apk",
  "deviceName": "Demo",
  "platformName": "Andriod",
  "automationName": "UiAutomator2"
}
driver.findElement(AppiumBy.accessibilityId("Preference")).click();


Separating configuration logic with test case logic
making use of @BeforeMethod @AfterMethod @Test(dependsOnMethods = "configureAppium")
