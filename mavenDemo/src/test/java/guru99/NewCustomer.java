package guru99;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Guru99.Util;

public class NewCustomer {
	static WebDriver driver;
	String newCuID;
	String AccID;

	@BeforeMethod
	public void setup() {

		System.setProperty("webdriver.chrome.driver", Util.chrom_Path);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		driver.get(Util.baseUrl);
		driver.manage().timeouts().implicitlyWait(Util.wait_Time, TimeUnit.SECONDS);
	}

	@Test
	public void addNewCustomer() throws Exception {
		driver.findElement(By.name("uid")).sendKeys(Util.User_name);
		driver.findElement(By.name("password")).sendKeys("123456@");
		driver.findElement(By.name("btnLogin")).click();

		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(text(),'New Customer')]")).click();
		driver.findElement(By.name("name")).sendKeys("Virendra");
		driver.findElement(By.xpath("//tbody/tr[5]/td[2]/input[1]")).click();
		driver.findElement(By.id("dob")).sendKeys("04112013");
		driver.findElement(By.name("addr")).sendKeys("Jamnagar");
		driver.findElement(By.name("city")).sendKeys("Jamnagar");
		driver.findElement(By.name("state")).sendKeys("Gujarat");
		driver.findElement(By.name("pinno")).sendKeys("567321");
		driver.findElement(By.name("telephoneno")).sendKeys("8000439024");
		driver.findElement(By.name("emailid")).sendKeys("VirendraG@gmail.com");
		driver.findElement(By.name("password")).sendKeys("123456");
		driver.findElement(By.name("sub")).click();

		Thread.sleep(2000);
		newCuID = driver.findElement(By.xpath("//tbody/tr[4]/td[2]")).getText();
		System.out.println("" + newCuID);
		driver.findElement(By.linkText("Continue")).click();

		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(text(),'New Account')]")).click();
		driver.findElement(By.name("cusid")).sendKeys(newCuID);
		Select accType = new Select(driver.findElement(By.name("selaccount")));
		accType.selectByVisibleText("Savings");
		driver.findElement(By.name("inideposit")).sendKeys("500");
		driver.findElement(By.name("button2")).click();

		AccID = driver.findElement(By.xpath("//tbody/tr/td[2]")).getText();
		System.out.println("" + AccID);

	}

	@AfterClass
	public void terminate() {
		driver.close();
	}

}
