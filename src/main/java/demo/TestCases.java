package demo;

import java.time.Duration;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCases {
    ChromeDriver driver;

    public TestCases() {
        System.out.println("Constructor: TestCases");

        WebDriverManager.chromedriver().timeout(30).setup();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("start-maximized");
        options.addArguments("--disable-blink-features=AutomationControlled");

        // Set path for log file
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

        driver = new ChromeDriver(options);

        // Set browser to maximize and wait
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    public void testCase01() throws InterruptedException {
        System.out.println("Start Test case: testCase01");
        driver.get("https://www.makemytrip.com/");
        String currentURL = driver.getCurrentUrl();
        if(currentURL.contains("makemytrip")){
            System.out.println("URL contains: "+currentURL);
        }else{
            System.out.println("URL is not contains MakeMyTrip");
        }
        Thread.sleep(3000);
        System.out.println("end Test case: testCase01");
    }

    
    public  void testCase02() throws InterruptedException{
        System.out.println("Start Test case: testCase02");
        driver.get("https://www.makemytrip.com/");
        Thread.sleep(3000);
        
        WebElement departure = driver.findElement(By.id("fromCity"));
        departure.click();
        Thread.sleep(3000);
        WebElement searchTextFrom = driver.findElement(By.xpath("//input[@placeholder='From']"));
        searchTextFrom.sendKeys("blr");
        Thread.sleep(3000);
        searchTextFrom.sendKeys(Keys.ARROW_DOWN);
        searchTextFrom.sendKeys(Keys.ENTER);
        System.out.println("Selected the departure location is: "+departure.getAttribute("value"));
        WebElement arrival = driver.findElement(By.id("toCity"));
        arrival.click();
        WebElement searchTextToCity = driver.findElement(By.xpath("//input[@placeholder='To']"));
        Thread.sleep(3000);
        searchTextToCity.sendKeys("del");
        Thread.sleep(3000);
        searchTextToCity.sendKeys(Keys.ARROW_DOWN);
        searchTextToCity.sendKeys(Keys.ENTER);
        System.out.println("Selected the arrival location is: "+arrival.getAttribute("value"));
        WebElement depDate = driver.findElement(By.xpath("//div[@class='DayPicker-Day'][contains(@aria-label, '29')]"));
        depDate.click();
        Thread.sleep(3000);
        WebElement departureDate = driver.findElement(By.xpath("//p[@data-cy='departureDate']"));
        String text = departureDate.getText();
        System.out.println("selected departure date is: "+text);
        WebElement searchButton = driver.findElement(By.xpath("//a[text()='Search']"));
        searchButton.click();
        if(driver.getCurrentUrl().contains("/flight/search")){
            System.out.println("Getting Flight Details from Bangalore to New Delhi Successfully");
        }else{
            System.out.println("Not Getting Flight Details from Bangalore to New Delhi");
        }
        Thread.sleep(3000);
        WebElement prices = driver.findElement(By.xpath("//div[@class='listingCard  appendBottom5'][1]/div[2]/div[2]/div/div[1]/div[1]"));
        System.out.println("Flight Price: "+prices.getText());
        Thread.sleep(3000);
        System.out.println("end Test case: testCase02");
    }

    public  void testCase03() throws InterruptedException{
        System.out.println("Start Test case: testCase03");
        driver.get("https://www.makemytrip.com/");
        Thread.sleep(3000);
        // WebElement closElement = driver.findElement(By.className("commonModal__close"));
        // closElement.click();
          JavascriptExecutor js = (JavascriptExecutor) driver;

        Thread.sleep(3000);
        WebElement trainBtn = driver.findElement(By.xpath("//ul[@class='makeFlex font12 headerIconsGap']/li[5]"));
        trainBtn.click();
        Thread.sleep(3000);
        WebElement departure = driver.findElement(By.id("fromCity"));
        departure.click();
        Thread.sleep(3000);
        WebElement searchTextFrom = driver.findElement(By.xpath("//input[@placeholder='From']"));
        searchTextFrom.sendKeys("ypr");
        Thread.sleep(3000);
        searchTextFrom.sendKeys(Keys.ARROW_DOWN);
        searchTextFrom.sendKeys(Keys.ENTER);
        System.out.println("Selected the departure location is: "+departure.getAttribute("value"));
        
        WebElement searchTextToCity = driver.findElement(By.xpath("//input[@placeholder='To']"));
        Thread.sleep(3000);
        searchTextToCity.sendKeys("ndls");
        Thread.sleep(3000);
        searchTextToCity.sendKeys(Keys.ARROW_DOWN);
        searchTextToCity.sendKeys(Keys.ENTER);
        WebElement arrival = driver.findElement(By.id("toCity"));
        System.out.println("Selected the arrival location is: "+arrival.getAttribute("value"));
        Thread.sleep(3000);
        WebElement depDate = driver.findElement(By.xpath("//div[@class='DayPicker-Day'][contains(@aria-label, '29')]"));
        js.executeScript("arguments[0].click();", depDate);
        Thread.sleep(3000);
        WebElement departureDate = driver.findElement(By.xpath("//p[@data-cy='departureDate']"));
        String text = departureDate.getText();
        System.out.println("selected departure date is: "+text);
        WebElement classElement = driver.findElement(By.xpath("//span[@class='appendBottom5 downArrow']"));
        js.executeScript("arguments[0].click();", classElement);
        Thread.sleep(3000);
        WebElement selectCls = driver.findElement(By.xpath("//ul[@class='travelForPopup']/li[3]"));
        js.executeScript("arguments[0].click();", selectCls);
        Thread.sleep(3000);
        WebElement acClass = driver.findElement(By.id("travelClass"));
        System.out.println("selected TravelClass is: "+acClass.getAttribute("value"));
        WebElement searchButton = driver.findElement(By.xpath("//a[text()='Search']"));
        searchButton.click();
        Thread.sleep(3000);
        if(driver.getCurrentUrl().contains("/railways/listing")){
            System.out.println("Getting train Details from Bangalore to New Delhi Successfully");
        }else{
            System.out.println("Not Getting train Details from Bangalore to New Delhi");
        }
        Thread.sleep(3000);
        WebElement price = driver.findElement(By.xpath("//div[@class='single-train-detail single-train-padding'][2]/div[2]/div[1]/div/div[1]/div[2]"));
        System.out.println("Train Price: "+price.getText());
        System.out.println("end Test case: testCase03");
    }

    public  void testCase04() throws InterruptedException{
        System.out.println("Start Test case: testCase04");
        driver.get("https://www.makemytrip.com/");
        Thread.sleep(3000);
        // WebElement closElement = driver.findElement(By.className("commonModal__close"));
        // closElement.click();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        Thread.sleep(3000);
        WebElement busBtn = driver.findElement(By.xpath("//ul[@class='makeFlex font12 headerIconsGap']/li[6]"));
        busBtn.click();
        Thread.sleep(3000);
        WebElement departure = driver.findElement(By.id("fromCity"));
        departure.click();
        Thread.sleep(3000);
        WebElement searchTextFrom = driver.findElement(By.xpath("//input[@placeholder='From']"));
        searchTextFrom.sendKeys("bangl");
        Thread.sleep(3000);
        searchTextFrom.sendKeys(Keys.ARROW_DOWN);
        searchTextFrom.sendKeys(Keys.ENTER);
        System.out.println("Selected the departure location is: "+departure.getAttribute("value"));
        Thread.sleep(3000);
        WebElement searchTextToCity = driver.findElement(By.xpath("//input[@placeholder='To']"));
        Thread.sleep(3000);
        searchTextToCity.sendKeys("kathma");
        Thread.sleep(3000);
        searchTextToCity.sendKeys(Keys.ARROW_DOWN);
        searchTextToCity.sendKeys(Keys.ENTER);
        Thread.sleep(3000);
        WebElement arrival = driver.findElement(By.id("toCity"));
        System.out.println("Selected the arrival location is: "+arrival.getAttribute("value"));
        Thread.sleep(3000);

        WebElement depDate = driver.findElement(By.xpath("//div[@class='DayPicker-Day'][contains(@aria-label, '29')]"));
        js.executeScript("arguments[0].click();", depDate);
        Thread.sleep(3000);
        WebElement departureDate = driver.findElement(By.xpath("//p[@data-cy='departureDate']"));
        String text = departureDate.getText();
        System.out.println("selected departure date is: "+text);

        WebElement searchButton = driver.findElement(By.id("search_button"));
        js.executeScript("arguments[0].click();", searchButton);
        Thread.sleep(3000);
        String text1 = driver.findElement(By.xpath("//div[@class='error-view makeFlex column hrtlCenter vrtlCenter']")).getText();
        if(text1.contains("No buses found for 29 Jun")){
            System.out.println("No Bus Found");
        }else{
            System.out.println("Bus Found");
        }
        System.out.println("end Test case: testCase04");
    }

}