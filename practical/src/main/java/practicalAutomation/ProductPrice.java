package practicalAutomation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPrice {
	public static void main(String[] args) {
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.amazon.in/");
		//Implicity wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		
		
		//click search
	WebElement search=driver.findElement(By.id("twotabsearchtextbox"));
	search.click();
	search.sendKeys("headphone");
	WebElement searchicon=driver.findElement(By.id("nav-search-submit-button"));
	searchicon.click();
	WebElement firstproduct = driver.findElement(By.xpath("//h2[contains(@aria-label,'Sponsored Ad')][1]"));		
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
    
//  Remove the ₹ symbol and commas from the price.
  String Price = price.replace("₹", "").replace(",", "");
  int intAmount=(int) Double.parseDouble(Price);
  System.out.println("Product price Without Amount Symbol:"+intAmount);
  
    
    //Add to the cart
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("window.scrollBy(0, 500);");
    WebElement addcart=wait.until(ExpectedConditions.presenceOfElementLocated(By.id("add-to-cart-button")));		
   js.executeScript("arguments[0].click()",addcart);
   System.out.println("Product add to cart successfully");
   
   //Open cart
   WebElement opencart=driver.findElement(By.id("sw-gtc"));
   opencart.click();
   System.out.println("Open cart");
   
   //Capture the product price from the cart.
   WebElement cartprice =driver.findElement(By.xpath("//span[contains(@class,'a-price apex-price-to-pay-value')]/span[contains(@class,'a-offscreen')]"));
   String Cprice=cartprice.getAttribute("textContent");
   System.out.println("Cart price:"+ Cprice);
   
//   Remove the ₹ symbol and commas from the price.
   String cleanPrice = Cprice.replace("₹", "").replace(",", "");
   int numericPrice = (int)Double.parseDouble(cleanPrice);
   System.out.println("cart price Without Amount Symbol:"+numericPrice);
   
   //Compare the product-page price with the cart price.
   if(intAmount==numericPrice) {
	   System.out.println("product-page price and the cart price both prices are equal.");
   }else
   {
	   System.out.println("product-page price and the cart price both prices are not equal.");
   }
  
   driver.quit();
    
	}

}

