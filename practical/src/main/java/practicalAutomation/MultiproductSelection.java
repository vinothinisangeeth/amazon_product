package practicalAutomation;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MultiproductSelection {

	public static void main(String[] args) {
		 WebDriver driver=new ChromeDriver();
         driver.get("https://www.amazon.in/");
 		//Implicity wait
 		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
 		driver.manage().window().maximize();
         WebElement Search=driver.findElement(By.id("twotabsearchtextbox"));
         Search.click();
         Search.sendKeys("Smart Watch");
         WebElement Searchicon=driver.findElement(By.xpath("//span[@id='nav-search-submit-text']//input"));
         Searchicon.click();
         //firstproduct
         WebElement firstproduct=driver.findElement(By.xpath("//a[contains(@class,'a-link-normal')]//h2[1]"));
         String parentHandle = driver.getWindowHandle();
  	     firstproduct.click();
  	     for (String handle : driver.getWindowHandles()) {
  	       if (!handle.equals(parentHandle)) {
  	           driver.switchTo().window(handle);
  	           break;
  	       }
  	   }
  	   //Title and price
  		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
  		WebElement title=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("productTitle")));
  	    String productName=title.getText();
  	    System.out.println("Product name:"+productName);
  	    WebElement productprice=driver.findElement(By.xpath("//span[contains(@class,'a-price')]//span[contains(@class,'a-offscreen')][1]"));
  	    String price=productprice.getAttribute("textContent");
  	    System.out.println("Product price:"+price);
  	    driver.close();
  	    driver.switchTo().window(parentHandle);
  	   
  	   //Second product name
  	   String parentHandle1 = driver.getWindowHandle();
  	 
  	   JavascriptExecutor js = (JavascriptExecutor) driver;
  	   js.executeScript("window.scrollBy(0, 500);");
  		WebElement secondproduct = driver.findElement(By.xpath("//img[@data-image-index='2']"));
  		 secondproduct.click();
  		 for (String handle : driver.getWindowHandles()) {
  		       if (!handle.equals(parentHandle1)) {
  		           driver.switchTo().window(handle);
  		           break;
  		       }
  		   }


  	   WebElement pname=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("productTitle")));
  	   String productName2=pname.getText();
  	   System.out.println(productName2);
  	   //price
  	   WebElement productprice2=driver.findElement(By.xpath("//span[contains(@class,'a-price')]//span[contains(@class,'a-offscreen')][1]"));
  	   String price2=productprice2.getAttribute("textContent");
  	 System.out.println("Product price:"+price2);
  	 
  	 //Third product
  	driver.close();
	    driver.switchTo().window(parentHandle);
	   
	   String parentHandle2 = driver.getWindowHandle();
	   js.executeScript("window.scrollBy(0, 500);");
		WebElement thirdproduct = driver.findElement(By.xpath("//img[@data-image-index='3']"));
		thirdproduct.click();
		 for (String handle : driver.getWindowHandles()) {
		       if (!handle.equals(parentHandle)) {
		           driver.switchTo().window(handle);
		           break;
		       }
		   }
  	WebElement pname3=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("productTitle")));
	   String productName3=pname3.getText();
	   System.out.println("Third product:"+productName3);
	   //price
	   WebElement productprice3=driver.findElement(By.xpath("//span[contains(@class,'a-price')]//span[contains(@class,'a-offscreen')][1]"));
	   String price3=productprice3.getAttribute("textContent");
	   System.out.println("Third Product price:"+price3);
	 
	//   conver price into integer
	// Remove the ₹ symbol and commas from the price.
	 String Price1 = price.replace("₹", "").replace(",", "");
	 int intAmount=(int) Double.parseDouble(Price1);
	 String Price2 = price2.replace("₹", "").replace(",", "");
	 int intAmount2=(int) Double.parseDouble(Price2);
	 String Price3 = price3.replace("₹", "").replace(",", "");
	 int intAmount3=(int) Double.parseDouble(Price3);
	 
	 
	 //Product List
	 List<String> productname=new ArrayList<>();	
	 productname.add("Name:"+productName);
	 productname.add("Second product:"+productName2);
	 productname.add("Third product:"+productName3);
	 System.out.println("Product list:"+productname);
	 
	 //Product price
	 List<Integer> productPrice=new ArrayList<>();
	 productPrice.add(intAmount);
	 productPrice.add(intAmount2);
	 productPrice.add(intAmount3);
	 System.out.println("Product Price list:"+productPrice);
	 
   //max and min price
	 int maxPrice=Collections.max(productPrice);
	 int minPrice=Collections.min(productPrice);
	 
	 System.out.println("Highest price:"+maxPrice);
	 System.out.println("Lowest price:"+minPrice);
      
	 
	 
	}

}
