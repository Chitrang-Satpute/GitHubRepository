package guru99;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Guru99.Util;

public class ChangePassword {
	static WebDriver driver;

	@BeforeMethod
	public void BeforeTest() throws Exception {
		System.setProperty("webdriver.chrome.driver", Util.chrom_Path);

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		ChromeOptions options = new ChromeOptions();
		options.addExtensions(new File("D:\\Selenium\\AdBlock — best ad blocker 4.44.0.0.crx"));
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		options.merge(capabilities);

		Thread.sleep(5000);
		driver.get(Util.baseUrl);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@Test
	public void changePassword() throws Exception {
		driver.findElement(By.name("uid")).sendKeys(Util.User_name);
		driver.findElement(By.name("password")).sendKeys("123456@");
		driver.findElement(By.name("btnLogin")).click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,150)", "");

		driver.findElement(By.xpath("//a[contains(text(),'Change Password')]")).click();

		driver.findElement(By.name("oldpassword")).sendKeys("123456");
		driver.findElement(By.name("newpassword")).sendKeys("123456@");
		driver.findElement(By.name("confirmpassword")).sendKeys("123456@");
		driver.findElement(By.name("sub")).click();
		Thread.sleep(2000);

		Alert alt = driver.switchTo().alert();
		alt.accept();

		Thread.sleep(2000);
		driver.findElement(By.name("oldpassword")).sendKeys("123456@");
		driver.findElement(By.name("newpassword")).sendKeys("123456@");
		driver.findElement(By.name("confirmpassword")).sendKeys("123456@");
		driver.findElement(By.name("sub")).click();
		Thread.sleep(2000);

		Alert alt1 = driver.switchTo().alert();
		alt1.accept();

		Thread.sleep(2000);
		driver.findElement(By.name("uid")).sendKeys(Util.User_name);
		driver.findElement(By.name("password")).sendKeys("123456@");
		driver.findElement(By.name("btnLogin")).click();
		Thread.sleep(2000);
	}

	@AfterClass
	public void terminate() {
		driver.close();
	}
}
