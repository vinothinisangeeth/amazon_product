package practicalAutomation;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AddMultiProduct {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.amazon.in/");
		//Implicity wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		
		
		//click search
	WebElement search=driver.findElement(By.id("twotabsearchtextbox"));
	search.click();
	search.sendKeys("keyboard");
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
	    
	    //Add to cart
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("window.scrollBy(0, 500);");
	    WebElement addcart=wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-button")));
	    try {
	    	addcart.click();
	    }catch(Exception e)
	    {
	   js.executeScript("arguments[0].click()",addcart);
	    }
	   System.out.println("Product add to cart successfully");
	   Thread.sleep(3000);
	   driver.close();
	    driver.switchTo().window(parentHandle);

	   //Second product
	    String parentHandle1 = driver.getWindowHandle();
	  	
	  	   js.executeScript("window.scrollBy(0, 500);");
	  		WebElement secondproduct = driver.findElement(By.xpath("//img[@data-image-index='2']"));
	  		 secondproduct.click();
	  		 String lasthandle="";
	  		 for (String handle : driver.getWindowHandles()) {
                   lasthandle=handle;

	  		       }
	  		   driver.switchTo().window(lasthandle);


	  	   WebElement pname=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("productTitle")));
	  	   String productName2=pname.getText();
	  	   System.out.println("Product name 2:"+productName2);
	  	   //price
	  	   WebElement productprice2=driver.findElement(By.xpath("//span[contains(@class,'a-price')]//span[contains(@class,'a-offscreen')][1]"));
	  	   String price2=productprice2.getAttribute("textContent");
	  	 System.out.println("Product price:"+price2);
	  
	  	 //Add to cart

		    js.executeScript("window.scrollBy(0, 500);");
		    WebElement addcart2=wait.until(ExpectedConditions.presenceOfElementLocated(By.id("add-to-cart-button")));
		    try {
		    	addcart2.click();
		    }catch(Exception e) {
		   js.executeScript("arguments[0].click()",addcart2);
		    }
		   System.out.println("Product add to cart successfully");
		   
		   Thread.sleep(3000);
		   
		 //Open cart
		   WebElement opencart=wait.until(ExpectedConditions.elementToBeClickable(By.id("sw-gtc")));
		   opencart.click();
		   System.out.println("Open cart");
		   
		   //Verify both item present

		   //  Locate all product title elements in the cart page
			 List<WebElement> cartItem = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
		                By.xpath("//div[@class='sc-list-item-content']//span[contains(@class,'sc-product-title')]")
		            ));
			 boolean isProduct1Present = false;
			 boolean isProduct2Present = false;

			 for (WebElement item : cartItem) {
				    String actualTitle = item.getText().trim().toLowerCase();
				    System.out.println("Found in Cart: " + actualTitle);
				    
				    // Clean up variables by taking the first 10 characters to bypass truncation/dots completely
				    String shortProd1 = productName.toLowerCase().substring(0, Math.min(productName.length(), 10));
				    String shortProd2 = productName2.toLowerCase().substring(0, Math.min(productName2.length(), 10));
				    
				    // Check for Product 1
				    if (!isProduct1Present && actualTitle.contains(shortProd1)) {
				        isProduct1Present = true;
				        System.out.println("1st Product confirmed in cart");
				    }
				    
				    // Check for Product 2
				    if (!isProduct2Present && actualTitle.contains(shortProd2)) {
				        isProduct2Present = true;
				        System.out.println("2nd Product confirmed in cart");
				    }
				}
               driver.quit();

	}

}
