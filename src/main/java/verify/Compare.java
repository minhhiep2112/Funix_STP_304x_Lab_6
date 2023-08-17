package verify;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Compare {
	
	// validate attribute
		public static boolean validateAttribute(WebDriver driver, By by, String act_att, String exp_att)
		{
			boolean result = false;
			try {
			if(driver.findElement(by).getAttribute(act_att).equals(exp_att))
			{
				result = true;
			}
			}
			catch(NoSuchElementException e) {
				
			}
			return result;
		}
	
	public static boolean validateTextonElement(WebDriver driver, By by, String expText) 
	{
		boolean result = false;
		if(driver.findElement(by).getText().equals(expText))
		{
			result = true;
		}
		return result;
	}
	
	public static boolean validateElementExists(WebDriver driver, By by)
	{
		boolean result=!driver.findElements(by).isEmpty();
		return result;
		
	}
	
	public static boolean validateURL(WebDriver driver,String eURL) 
	{
		boolean result=false;
		if(driver.getCurrentUrl().equalsIgnoreCase(eURL)) 
		{
			result=true;
		}
		return result;
	}
	
	public static boolean validateTitle(WebDriver driver, String eTitle) 
	{
		boolean result=false;
		if(driver.getTitle().equalsIgnoreCase(eTitle))
		{
			result = true;
		}
		return result;
	}
}
