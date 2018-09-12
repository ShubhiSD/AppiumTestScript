package QaPractice;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.sun.glass.events.KeyEvent;

public class StartApplication {

		private AndroidDriver driver;
		public static void main(String[] args) throws MalformedURLException, InterruptedException {

			File classpathRoot = new File(System.getProperty("user.dir"));
			File appDir = new File(classpathRoot, "/Apps/Amazon/");
			File app = new File(appDir, "in.amazon.mShop.android.shopping.apk");

			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("deviceName", "Mi Phone");
			capabilities.setCapability("platformVersion", "7.1.1");
			capabilities.setCapability("platformName", "Android");
			capabilities.setCapability("app", app.getAbsolutePath());
			capabilities.setCapability("noSign","True"); 
			capabilities.setCapability("appPackage", "com.amazon.mShop.android.shopping");
			capabilities.setCapability("appActivity", "com.amazon.mShop.home.HomeActivity");
			StartApplication appvar=new StartApplication();
			appvar.driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			appvar.driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
			Thread.sleep(10000);
			appvar.signIn();
			appvar.searchItem();
			appvar.selectFirstItemFromSearch();
			appvar.closeConnection();

	}
		private void signIn() {
			driver.findElementById("com.amazon.mShop.android.shopping:id/sign_in_button").click();
			driver.findElementById("login_accordion_header").click();
			driver.findElementById("ap_email_login").sendKeys("shubhi07@gmail.com\n");
			driver.findElementById("ap_password").sendKeys("eerer@123\n");
			if(driver.findElementById("com.amazon.mShop.android.shopping:id/action_bar_home_logo").isDisplayed())
			System.out.println("sign in successful");
			}
		private void searchItem()
		{
		
			WebElement searchBox=driver.findElementById("com.amazon.mShop.android.shopping:id/rs_search_src_text");
			searchBox.sendKeys("iPhone9\n");
			//driver.pressKeyCode(AndroidKeyCode.KEYCODE_NUMPAD_ENTER );
			//driver.pressKey(new KeyEvent(AndroidKey.ENTER));
			//driver.getKeyboard().sendKeys(Keys.RETURN);
		}
		private void selectFirstItemFromSearch() {
			List<WebElement> item=driver.findElementsByXPath("//android.widget.TextView[contains(@text='Apple iPhone')]");
			item.get(1).click();
			//add to cart
			driver.findElementById("add-to-cart-button").click();
		}
		private void closeConnection()
		{driver.quit();}

}
