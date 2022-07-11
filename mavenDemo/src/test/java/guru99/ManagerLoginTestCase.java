package guru99;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Guru99.Util;

public class ManagerLoginTestCase {

	static WebDriver driver;

	@BeforeMethod
	public void setup() {

		System.setProperty("webdriver.chrome.driver", Util.chrom_Path);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		driver.get(Util.baseUrl);
		driver.manage().timeouts().implicitlyWait(Util.wait_Time, TimeUnit.SECONDS);
	}

	@Test(dataProvider = "LoginData")
	public void testLogin(String username, String password) throws Exception {

		String actualAltText;
		String actualTitle;
		String ManagerID;

		WebElement UserID = driver.findElement(By.name("uid"));
		UserID.clear();
		UserID.sendKeys(username);

		WebElement Password = driver.findElement(By.name("password"));
		Password.clear();
		Password.sendKeys(password);

		driver.findElement(By.name("btnLogin")).click();
		Thread.sleep(2000);

		try {
			Alert alt = driver.switchTo().alert();
			actualAltText = alt.getText();
			Assert.assertEquals(actualAltText, Util.expectedAltTest);
			alt.accept();
		} catch (Exception e) {

			actualTitle = driver.getTitle();
			ManagerID = driver.findElement(By.xpath("//td[@style ='color: green']")).getText();
			String[] part = ManagerID.split("\\s+");
			String actualMName = part[3];
			Assert.assertEquals(actualTitle, Util.EXPECT_TITLE);
			Assert.assertEquals(actualMName, username);
			this.takeSnapshot(driver, "D:\\Workspace\\mavenDemo\\test-output\\SnapShot\\ManagerID.png");

		}

	}

	@DataProvider(name = "LoginData")
	public String[][] getData() throws Exception {
		Util util = new Util();
		int totalRows = util.getRowcount(Util.sheet_name);
		int totalCols = util.getCellcount(Util.sheet_name, 1);

		String logindata[][] = new String[totalRows][totalCols];

		for (int i = 1; i <= totalRows; i++) {
			for (int j = 0; j < totalCols; j++) {
				logindata[i - 1][j] = util.getcellData(Util.sheet_name, i, j);
			}
		}

		return logindata;

	}

	@AfterMethod
	public void close() {
		driver.close();
	}

	public void takeSnapshot(WebDriver webdriver, String fileWithPath) throws Exception {
		TakesScreenshot scr = ((TakesScreenshot) webdriver);
		File scrFile = scr.getScreenshotAs(OutputType.FILE);
		File des_path = new File(fileWithPath);
		FileUtils.copyFile(scrFile, des_path);
	}
}
