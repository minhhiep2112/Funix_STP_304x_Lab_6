package lab_6;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Lab_6 {
	
	WebDriver driver;
	
	@BeforeMethod
	public void startBrowser()
	{
		
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.navigate().to("https://automationexercise.com/products");
		
	}
	
	@AfterMethod
	public void closeBrowser() {
		
		driver.quit();
	}
	
	@Test
	public void TC_001() throws InterruptedException
	{		
		Actions act = new Actions(driver);
		
		// nhập nội dung và click vào nút tìm kiếm
		act.sendKeys(driver.findElement(By.id("search_product")), "Stylish Dress").perform();
		act.moveToElement(driver.findElement(By.xpath("//button[@id='submit_search']"))).click().perform();
		
		// kiem tra chi 1 san pham tim thay
		if(driver.findElements(By.xpath("//div[@class='productinfo text-center']")).size()>1)
		{
			Assert.assertTrue(false, "[Error - Quality is not right!!]");
		}
				
		// hình ảnh được hiển thị
		Assert.assertTrue(driver.findElement(By.xpath("//img[@src='/get_product_picture/4']")).isDisplayed(),"[Error - Image is not displayed!]");
		
		// thông tin tìm kiếm được hiển thị đúng nội dung
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='productinfo text-center']/p")).getText(), "Stylish Dress","[Error - Text is not right!]");
		
		// hiển thị giá bán
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class=\"productinfo text-center\"]/h2")).getText(), "Rs. 1500","[Error - Price is not right!]");
		
		// đảm bảo button "Add to cart" hiển thị
		act.sendKeys(Keys.PAGE_DOWN).perform();
		act.moveToElement(driver.findElement(By.xpath("//div[@class='single-products']")));
		Assert.assertTrue(driver.findElement(By.xpath("//a[@data-product-id='4']")).isDisplayed(), "[Error - Price is not found!]");
		
		// click vào "Add to cart" ; thêm vào explicit wait để thông báo add cart thành công hiện ra
		
		act.click(driver.findElement(By.xpath("//a[@data-product-id='4']"))).perform();
		
	//	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
	//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='modal-content']")));
		Thread.sleep(1000);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='modal-content']")).isDisplayed(), "[Error - Add item fail!]");
		
		
	}
}
