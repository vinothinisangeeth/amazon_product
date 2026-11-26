package practicalAutomation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RatingValidation {

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
      	    
      	  //Rating
      	    WebElement rating=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@id='acrPopover']//span[@class='a-size-small a-color-base']")));
      	    String Rating=rating.getText();
      	    System.out.println("Rating:"+Rating);
      	    
      	//Numeric rating
      	  double rate=Double.parseDouble(Rating.substring(0,3).trim());
      	  
      	  if(rate >= 4.0)
      	  {
      		  System.out.println("The rating is 4.0 or above");
      	  }
      	  else
      	  {
      		System.out.println("The rating is less than 4.0");
      	  }
      	  driver.quit();
	}

}
