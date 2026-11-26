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

public class compareProduct {

	public static void main(String[] args) {
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.amazon.in/");
		//Implicity wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		
		
		//click search
	WebElement search=driver.findElement(By.id("twotabsearchtextbox"));
	search.click();
	search.sendKeys("laptop");
	WebElement searchicon=driver.findElement(By.id("nav-search-submit-button"));
	searchicon.click();
	System.out.println("First product");
	WebElement firstproduct = driver.findElement(By.xpath("//h2[contains(@aria-label,'Sponsored Ad')][1]"));		
	String parentHandle = driver.getWindowHandle();
	   firstproduct.click();
	   for (String handle : driver.getWindowHandles()) {
	       if (!handle.equals(parentHandle)) {
	           driver.switchTo().window(handle);
	           break;
	       }
	   }
	   //First product name 
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
	WebElement title=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("productTitle")));
    String productName=title.getText();
        //price
    WebElement productprice=driver.findElement(By.xpath("//span[contains(@class,'a-price')]//span[contains(@class,'a-offscreen')][1]"));
    String price=productprice.getAttribute("textContent");


    //Rating
    WebElement rating=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@id='acrPopover']//span[@class='a-size-small a-color-base']")));
    String frating=rating.getText();
    
    //Review count
    WebElement reviewcount=wait.until(ExpectedConditions.elementToBeClickable(By.id("acrCustomerReviewLink")));
    String reviewcount1=reviewcount.getText();
    
    //First product details
   List<String> product1=new ArrayList<>();
   product1.add("Name:"+productName);
   product1.add("Price:"+price);
   product1.add("Rating:"+frating); 
   product1.add("review count:"+reviewcount1);
   System.out.println("First product details: "+product1);
   
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
	 //second product name
   WebElement pname=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("productTitle")));
   String productName2=pname.getText();
//   System.out.println(productName2);
   //price
   WebElement productprice2=driver.findElement(By.xpath("//span[contains(@class,'a-price')]//span[contains(@class,'a-offscreen')][1]"));
   String price2=productprice2.getAttribute("textContent");
   
   //Rating
   WebElement rating2=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@id='acrPopover']//span[@class='a-size-small a-color-base']")));
   String frating2=rating2.getText();
   
 //Review count
   WebElement reviewcountt=wait.until(ExpectedConditions.elementToBeClickable(By.id("acrCustomerReviewLink")));
   String reviewcount2=reviewcountt.getText();
   
   //second product details
   List<String> product2=new ArrayList<>();
   product2.add("Name:"+productName2);
   product2.add("Price:"+price2);
   product2.add("Rating:"+frating2); 
   product2.add("review count:"+reviewcount2);
   System.out.println("Second product details: "+product2);
   
//   conver price into integer
// Remove the ₹ symbol and commas from the price.
 String Price1 = price.replace("₹", "").replace(",", "");
 int intAmount=(int) Double.parseDouble(Price1);
 String Price2 = price2.replace("₹", "").replace(",", "");
 int intAmount2=(int) Double.parseDouble(Price2);
 
 //Numeric rating
 double rate1=Double.parseDouble(frating.substring(0,3).trim());
 double rate2=Double.parseDouble(frating2.substring(0,3).trim()); 
 
 //remove() in review count
 String Rcount = reviewcount1.replaceAll("[()]", "").trim();
 String Rcount2 = reviewcount2.replaceAll("[()]", "").trim();
 Rcount=Rcount.replace(",", "");
 Rcount2=Rcount2.replace(",", "");
 int review1=Integer.parseInt(Rcount);
 int review2=Integer.parseInt(Rcount2);
 
   //compare
// Which has lower price?
 System.out.println("Comparison result");
 if(intAmount < intAmount2)
 {
	 System.out.println("First product has lower price="+intAmount);
 }else if(intAmount2 < intAmount)
 {
	 System.out.println("Second product has lower price="+intAmount2);
 }else
 {
	 System.out.println("Both product has same price");
 }
// Which has higher rating?
 if(rate1 > rate2)
 {
	 System.out.println("First product has higher rating="+rate1);
 }else if(rate1 <rate2)
 {
	 System.out.println("Second product has higher rating="+rate2);
 }
 else
 {
	 System.out.println("Both product has same rating");
 }
 
// Which has more reviews?
 if(review1 >review2)
 {
	 System.out.println("First product has more reviews="+review1);

   }else if(review1 < review2)
   {
		 System.out.println("Second product has more reviews="+review2);

	   }
   else {
	   System.out.println("Both product has same reviews");
   }
   

 driver.quit();
 
 
  


	}}


