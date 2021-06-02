package LGCSTtest;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.support.ui.Select;
import java.awt.AWTException;
import org.openqa.selenium.JavascriptExecutor;
//import java.awt.Robot;
//import java.awt.Toolkit;
//import java.awt.datatransfer.StringSelection;
//import java.awt.event.KeyEvent;
import java.io.File;
import java.util.concurrent.TimeUnit;

public class organic{

    public String baseUrl = "https://www.lgcstandards.com/GB/en/orderRequest";
    
    String driverPath = "C:\\chromedriver.exe";
    
    public WebDriver driver ; 
    
    String path = new File("CustomQuoteUpload_15_CAS_Only.xls").getAbsolutePath();
    
    
    JavascriptExecutor js = (JavascriptExecutor) driver;
    
    String file = "CustomQuoteUpload_15_CAS_Only.xls";
    
  	@BeforeTest
  	public void openpage(){
        System.out.println("launching Chrome browser"); 
        
        System.setProperty("webdriver.chrome.driver", driverPath);
        
        driver = new ChromeDriver();
        
        driver.manage().window().maximize();
  	}
  
  @Test
  public void verifypage() {
      
      driver.get(baseUrl);
      
      String expectedTitle = "LGC Standards";
      
      String actualTitle = driver.getTitle();
      
      Assert.assertEquals(actualTitle, expectedTitle);    
  }
  
  @Test
  public void selectorganic() {
	  
	  driver.get(baseUrl);
	  
	  String organicbutton = driver.findElement(By.xpath("//*[@id=\"organicRadioBtn\"]/span[2]/h3")).getText();
	  
      String expectedText = "Organic";
      
      Assert.assertEquals(organicbutton, expectedText);
	  
      driver.findElement(By.id("organicRadioBtn")).isSelected();
      
      driver.findElement(By.id("cqSelectCustomSolutionTypeBtn")).click();
  }
  
  @Test
  public void uploadfile() throws AWTException, InterruptedException {
	  
	  driver.get(baseUrl);
	  

	  //unable to get the file to upload 
//	  driver.findElement(By.xpath("//*[@id=\"cqUploadFile\"]/div/div/i")).click();
//	  
//	  driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
//	  
//	  StringSelection str = new StringSelection(path);
//	  Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
//	  
//	  
//	  Robot rb = new Robot();
//	  
//	     // press Contol+V for pasting
//	  rb.keyPress(KeyEvent.VK_CONTROL);
//	  rb.keyPress(KeyEvent.VK_V);
//	 
//	    // release Contol+V for pasting
//	  rb.keyRelease(KeyEvent.VK_CONTROL);
//	  rb.keyRelease(KeyEvent.VK_V);
//	 
//	    // for pressing and releasing Enter
//	  rb.keyPress(KeyEvent.VK_ENTER);
//	  rb.keyRelease(KeyEvent.VK_ENTER);	  
//	  
//	  driver.findElement(By.xpath("//*[@id=\"cqUploadFile\"]/div/div/i")).click();
//	  
//	  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//	  
//	  StringSelection str2 = new StringSelection(file);
//	  Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str2, null);
//	  
//	  rb.keyPress(KeyEvent.VK_CONTROL);
//	  rb.keyPress(KeyEvent.VK_V);
//	 
//	    // release Contol+V for pasting
//	  rb.keyRelease(KeyEvent.VK_CONTROL);
//	  rb.keyRelease(KeyEvent.VK_V);
//	 
//	    // for pressing and releasing Enter
//	  rb.keyPress(KeyEvent.VK_ENTER);
//	  rb.keyRelease(KeyEvent.VK_ENTER);	 
	  
	  driver.findElement(By.id("customQuotePasteCodes")).isSelected();
	  
	  String casnumbers ="53949-54-5\n53949-53-4\n51146-55-5\n121662-14-4\n15687-27-1\n7782-50-5\n590-46-5\n7664-41-7\n10043-35-3\n73590-58-6\n34381-68-5\n59-66-5\n339-43-5\n107-21-1\n66357-59-3\n100-01-6\n100-02-7";
	  casnumbers = casnumbers.replace("\n", Keys.chord(Keys.SHIFT, Keys.ENTER));			  
	  
	  TimeUnit.SECONDS.sleep(5);
	  
	  driver.findElement(By.id("customQuotePasteCodes")).sendKeys(casnumbers);
	  
	  driver.findElement(By.xpath("//*[@id=\"custom-quote\"]/div[2]/div[2]/div[2]/div/div[1]/div[1]/div[2]/div[1]/button")).click();

	  
	  TimeUnit.SECONDS.sleep(1);
	  	  
	  
	  Select dropdown = new Select(driver.findElement(By.className("el-input el-input--suffix")));
	  
	  dropdown.selectByVisibleText("Carbutamide");
	  
	  //driver.findElement(By.linkText("339-43-5")).isSelected();
	  
	  //driver.findElement(By.xpath("//input[@placeholder='This component has multiple matches found. Please select your preferred option']")).isSelected();
	  
	  //driver.findElement(By.linkText("Carbutamide")).isSelected();
	  	   

  }
  
  
	@AfterTest
	public void tearDown(){
      	driver.quit();
	}

}
