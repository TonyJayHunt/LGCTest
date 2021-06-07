package StepDefs;


import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.io.File;

public class MySteps {

    WebDriver driver;

    public String baseUrl = "https://www.lgcstandards.com/GB/en/orderRequest";

    public String path = new File("CustomQuoteUpload_15_CAS_Only.xls").getAbsolutePath();

    @Given("^Launch the browser$")
    public void launchTheBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);
    }

    @Given("^Website has loaded$")
    public void websiteHasLoaded() {
        driver.get(baseUrl);
        }

    @And("^I have Order file \"([^\"]*)\"$")
    public void iHaveOrderFile(String FileName){
        String path = new File(FileName).getAbsolutePath();
        boolean pathChecker;
        if (path.length()>0) {
            pathChecker = true;
        } else {
            pathChecker = false;
        }
        Assert.assertTrue(pathChecker);
    }

    @Then("^webpage shows \"([^\"]*)\"$")
    public void webpageShows(String expected){

        String endtitle = driver.findElement(By.xpath("//*[@id=\"custom-quote\"]/div[1]/div[1]/h1")).getText();

        Assert.assertEquals(endtitle,expected);
    }

    @And("^Custom solution shows \"([^\"]*)\"$")
    public void customSolutionShows(String expected){
        String custsol = driver.findElement(By.xpath("//*[@id=\"custom-quote\"]/div[2]/div[2]/div/div[2]/div/div/p[1]")).getText();

        Assert.assertEquals(custsol,expected);
    }

    @And("^additional notes show \"([^\"]*)\"$")
    public void additionalNotesShow(String expected){
        String addnotes = driver.findElement(By.xpath("//*[@id=\"custom-quote\"]/div[2]/div[3]/div/div[2]/div/div/p")).getText();

        Assert.assertEquals(addnotes,expected);

    }

    @When("^Organic Order is processed$")
    public void organicOrderIsProcessed() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver,30);
        System.out.println("Select Organic");
        driver.findElement(By.cssSelector("#organicRadioBtn > .el-radio__label")).click();
        driver.findElement(By.id("cqSelectCustomSolutionTypeBtn")).click();


        System.out.println("upload file");
        WebElement chooseFile = driver.findElement(By.name("file"));
        //js.executeScript("arguments[0].scrollIntoView();", chooseFile);
        chooseFile.sendKeys(path);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cq-select__small .el-select__caret")));
        driver.findElement(By.cssSelector(".cq-select__small .el-select__caret")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(.,'Carbutamide')]")));
        driver.findElement(By.xpath("//li[contains(.,'Carbutamide')]")).click();
        driver.findElement(By.id("cqShouldAllowAddingUnexistedItemBtnResults")).click();


        System.out.println("select concentration");
        driver.findElement(By.id("orderRequestTopAnalyteForm")).click();
        driver.findElement(By.id("orderRequestTopAnalyteForm")).sendKeys("10");
        driver.findElement(By.id("orderRequestTopAnalyteForm")).sendKeys(Keys.ENTER);


        System.out.println("how many units");
        WebElement howmany = driver.findElement(By.xpath("//*[@id=\"cqOrganicDataUnitQuantity\"]/div/input"));
        js.executeScript("arguments[0].scrollIntoView();", howmany);
        howmany.sendKeys("5");
        howmany.sendKeys(Keys.ENTER);


        System.out.println("analytical technique");
        WebElement atechnique = driver.findElement(By.xpath("//label[@id='cqOrganicDataAnalyticalTechniqueGCLC']"));
        js.executeScript("arguments[0].click();", atechnique);
        WebElement savencont =  driver.findElement(By.id("cqConfigureCustomSolutionTypeBtn"));
        js.executeScript("arguments[0].click();", savencont);

        System.out.println("additional notes");
        js.executeScript("arguments[0].click();", driver.findElement(By.id("cqAdditionalNotesTextArea")));
        driver.findElement(By.id("cqAdditionalNotesTextArea")).sendKeys("this is me adding further details");
        driver.findElement(By.id("cqAddAdditionalNotesBtn")).click();

        System.out.println("customer deets");
        driver.findElement(By.id("cqCustomerDetailsTitle")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cqCustomerTitle2"))).click();
        driver.findElement(By.id("cqCustomerDetailsFirstName")).click();
        driver.findElement(By.id("cqCustomerDetailsFirstName")).sendKeys("Tester");
        driver.findElement(By.id("cqCustomerDetailsLastName")).sendKeys("MCtestface");
        driver.findElement(By.id("cqCustomerDetailsOrganization")).sendKeys("pretend");
        driver.findElement(By.id("cqCustomerDetailsCountry")).sendKeys("uni");
        driver.findElement(By.cssSelector(".customer-details-step")).click();
        driver.findElement(By.id("cqCustomerDetailsCountry")).click();
        driver.findElement(By.cssSelector(".customer-details-step > .cq-form > .el-form-item:nth-child(3) > .el-form-item__label")).click();
        driver.findElement(By.id("cqCustomerDetailsCountry")).click();
        driver.findElement(By.id("cqCustomerDetailsCountry")).sendKeys("united king");
        driver.findElement(By.id("cqCustomerCountry220")).click();
        driver.findElement(By.id("cqCustomerDetailsEmailAddress")).sendKeys("someone@somewhere.com");
        driver.findElement(By.id("cqCustomerDetailsPhone")).click();
        driver.findElement(By.id("cqCustomerDetailsCountryCode")).sendKeys("+44");
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@id=\"cqCustomerDetailsPhoneNumber223\"]")));
        driver.findElement(By.id("cqCustomerDetailsPhone")).sendKeys("07111111111");
        driver.findElement(By.cssSelector(".customer-details-step")).click();
        WebElement jobrole =  driver.findElement(By.id("cqCustomerDetailsJobRole"));
        js.executeScript("arguments[0].scrollIntoView();", jobrole);
        js.executeScript("arguments[0].click();", jobrole);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cqjobRole3"))).click();
        WebElement acceptnc =  driver.findElement(By.cssSelector("#cqCustomerDetailsPolicy .el-checkbox__inner"));
        js.executeScript("arguments[0].click();", acceptnc);
        WebElement custsavncont =  driver.findElement(By.id("cqCustomerDetailsSaveBtn"));
        js.executeScript("arguments[0].click();", custsavncont);

        System.out.println("Billing Address");
        driver.findElement(By.id("cqBillingAddressLine1")).click();
        driver.findElement(By.id("cqBillingAddressLine1")).sendKeys("1 Testing Close");
        driver.findElement(By.id("cqBillingAddressLine2")).sendKeys("Testing Place");
        driver.findElement(By.id("cqBillingAddressCountry")).sendKeys("united kingdom");
        driver.findElement(By.cssSelector("#cqBillingCountry220 > .outline")).click();
        driver.findElement(By.id("cqBillingAddressCounty")).sendKeys("Dorset");
        driver.findElement(By.id("cqBillingAddressCity")).click();
        driver.findElement(By.id("cqBillingAddressCity")).sendKeys("Testing City");
        driver.findElement(By.id("cqBillingAddressPostCode")).click();
        driver.findElement(By.id("cqBillingAddressPostCode")).sendKeys("DT179FE");
        WebElement saveandview =  driver.findElement(By.id("cqDeliveryInformationBtn"));
        js.executeScript("arguments[0].click();", saveandview);
    }

    @When("^Inorganic is processed$")
    public void inorganicIsProcessed() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver,30);
        System.out.println("Select Inorganic");
        driver.findElement(By.cssSelector("#cqInorganicRadioBtn > .el-radio__label")).click();
        driver.findElement(By.id("cqSelectCustomSolutionTypeBtn")).click();
        driver.findElement(By.cssSelector(".icon-plus-light")).click();
        driver.findElement(By.cssSelector("#cqPeriodicElement52 > .cq-ptable--abbr")).click();
        driver.findElement(By.id("cqSelectPeriodicTableElementsBtn")).click();


        System.out.println("select concentration");
        js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.id("orderRequestTopAnalyteForm")));
        js.executeScript("arguments[0].click();", driver.findElement(By.id("orderRequestTopAnalyteForm")));
        driver.findElement(By.id("orderRequestTopAnalyteForm")).sendKeys("10");
        driver.findElement(By.id("orderRequestTopAnalyteForm")).sendKeys(Keys.ENTER);

        System.out.println("select all config order");
        js.executeScript("arguments[0].click();", driver.findElement(By.id("cqInorganicPackaginType1")));
        js.executeScript("arguments[0].click();", driver.findElement(By.cssSelector(".configure-solution--setting .el-select__caret")));
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@id=\"inorganicDataUnitItem2\"]")));
        js.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("#cqInorganicDataUnitQuantity .el-input__inner")));
        driver.findElement(By.cssSelector("#cqInorganicDataUnitQuantity .el-input__inner")).sendKeys(Keys.CLEAR);
        driver.findElement(By.cssSelector("#cqInorganicDataUnitQuantity .el-input__inner")).sendKeys("42");
        js.executeScript("arguments[0].click();", driver.findElement(By.id("cqAnalyticalTechniqueICPMS")));

        WebElement savencont =  driver.findElement(By.id("cqConfigureCustomSolutionTypeBtn"));
        js.executeScript("arguments[0].click();", savencont);

        System.out.println("additional notes");
        js.executeScript("arguments[0].click();", driver.findElement(By.id("cqAdditionalNotesTextArea")));
        driver.findElement(By.id("cqAdditionalNotesTextArea")).sendKeys("this is me adding further details");
        driver.findElement(By.id("cqAddAdditionalNotesBtn")).click();

        System.out.println("customer deets");
        driver.findElement(By.id("cqCustomerDetailsTitle")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cqCustomerTitle2"))).click();
        driver.findElement(By.id("cqCustomerDetailsFirstName")).click();
        driver.findElement(By.id("cqCustomerDetailsFirstName")).sendKeys("Tester");
        driver.findElement(By.id("cqCustomerDetailsLastName")).sendKeys("MCtestface");
        driver.findElement(By.id("cqCustomerDetailsOrganization")).sendKeys("pretend");
        driver.findElement(By.id("cqCustomerDetailsCountry")).sendKeys("uni");
        driver.findElement(By.cssSelector(".customer-details-step")).click();
        driver.findElement(By.id("cqCustomerDetailsCountry")).click();
        driver.findElement(By.cssSelector(".customer-details-step > .cq-form > .el-form-item:nth-child(3) > .el-form-item__label")).click();
        driver.findElement(By.id("cqCustomerDetailsCountry")).click();
        driver.findElement(By.id("cqCustomerDetailsCountry")).sendKeys("united king");
        driver.findElement(By.id("cqCustomerCountry220")).click();
        driver.findElement(By.id("cqCustomerDetailsEmailAddress")).sendKeys("someone@somewhere.com");
        driver.findElement(By.id("cqCustomerDetailsPhone")).click();
        driver.findElement(By.id("cqCustomerDetailsCountryCode")).sendKeys("+44");
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@id=\"cqCustomerDetailsPhoneNumber223\"]")));
        driver.findElement(By.id("cqCustomerDetailsPhone")).sendKeys("07111111111");
        driver.findElement(By.cssSelector(".customer-details-step")).click();
        WebElement jobrole =  driver.findElement(By.id("cqCustomerDetailsJobRole"));
        js.executeScript("arguments[0].scrollIntoView();", jobrole);
        js.executeScript("arguments[0].click();", jobrole);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cqjobRole3"))).click();
        WebElement acceptnc =  driver.findElement(By.cssSelector("#cqCustomerDetailsPolicy .el-checkbox__inner"));
        js.executeScript("arguments[0].click();", acceptnc);
        WebElement custsavncont =  driver.findElement(By.id("cqCustomerDetailsSaveBtn"));
        js.executeScript("arguments[0].click();", custsavncont);

        System.out.println("Billing Address");
        driver.findElement(By.id("cqBillingAddressLine1")).click();
        driver.findElement(By.id("cqBillingAddressLine1")).sendKeys("1 Testing Close");
        driver.findElement(By.id("cqBillingAddressLine2")).sendKeys("Testing Place");
        driver.findElement(By.id("cqBillingAddressCountry")).sendKeys("united kingdom");
        driver.findElement(By.cssSelector("#cqBillingCountry220 > .outline")).click();
        driver.findElement(By.id("cqBillingAddressCounty")).sendKeys("Dorset");
        driver.findElement(By.id("cqBillingAddressCity")).click();
        driver.findElement(By.id("cqBillingAddressCity")).sendKeys("Testing City");
        driver.findElement(By.id("cqBillingAddressPostCode")).click();
        driver.findElement(By.id("cqBillingAddressPostCode")).sendKeys("DT179FE");
        WebElement saveandview =  driver.findElement(By.id("cqDeliveryInformationBtn"));
        js.executeScript("arguments[0].click();", saveandview);

    }
    @After
	public void tearDown(){
      	driver.quit();
	}

}
