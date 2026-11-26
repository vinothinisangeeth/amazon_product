package practicalAutomation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartValidation {

	public static void main(String[] args) {
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.amazon.in/");
		//Implicity wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		
		
		//click search
	WebElement search=driver.findElement(By.id("twotabsearchtextbox"));
	search.click();
	search.sendKeys("Bluetooth speaker");
	//get value
	String Entertext=search.getAttribute("value");
	System.out.println("Enter search text:"+Entertext);
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
 //Quantity
   WebElement quantity=wait.until(ExpectedConditions.visibilityOfElementLocated
   		(By.xpath("//span[@data-a-selector='value']/span[@data-a-selector='inner-value']")));
   String quantityCount=quantity.getText();
   System.out.println("quantity:"+quantityCount);
   
   //Capture the product price from the cart.
   WebElement cartprice =driver.findElement(By.xpath("//span[contains(@class,'a-price apex-price-to-pay-value')]/span[contains(@class,'a-offscreen')]"));
   String Cprice=cartprice.getAttribute("textContent");
   System.out.println("Cart price:"+ Cprice);
   
  //  Remove the ₹ symbol and commas from the price.
   String cleanPrice = Cprice.replace("₹", "").replace(",", "");
   int numericPrice = (int)Double.parseDouble(cleanPrice);
   System.out.println("cart price Without Amount Symbol:"+numericPrice);
   //Increase the quantity to 2.
   WebElement Increasequanity=driver.findElement(By.xpath("//button[contains(@aria-label,'Increase quantity by one')]"));
   Increasequanity.click();
   WebElement quantity2=wait.until(ExpectedConditions.visibilityOfElementLocated
	   		(By.xpath("//span[@data-a-selector='value']/span[@data-a-selector='inner-value']")));
	   String quanCount=quantity2.getText();
	   System.out.println("quantity:"+quanCount);
	   
	   //Calculate the expected total using Java.
	   int OriginalQuantity = Integer.parseInt(quanCount);
	   int Expectedtotal = numericPrice * OriginalQuantity;
	   System.out.println("Expected Total Amount:"+Expectedtotal);
	   
	   //Compare it with the displayed total.
	   WebElement Cardtotal=driver.findElement(By.xpath("//span[@id='sc-subtotal-amount-buybox']/span"));
	   String GrandTotal=Cardtotal.getText();
	   String cleanPrice1 = GrandTotal.replace("₹", "").replace(",", "");
	   int Grandtotal = (int)Double.parseDouble(cleanPrice1);
	   System.out.println("Actual total:"+Grandtotal);
	   
	   if(Expectedtotal==Grandtotal)
	   {
		   System.out.println("Expected total and displayed total both are same");
	   }
	   else
	   {
		   System.out.println("Expected total and displayed total both are not same");
	   }
	   
      driver.quit();

	  
	 
   

	}

}
