package boss;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ebay {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        long phaseStartTime, phaseEndTime;

        WebDriver driver = null;

        try {
            phaseStartTime = System.currentTimeMillis();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            phaseEndTime = System.currentTimeMillis();
            System.out.println("ChromeDriver initialization: " + (phaseEndTime - phaseStartTime) + " ms");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // 20 seconds timeout
//invoke ebay
            phaseStartTime = System.currentTimeMillis();
            driver.get("https://www.ebay.com");
            phaseEndTime = System.currentTimeMillis();
            System.out.println("Navigation to eBay: " + (phaseEndTime - phaseStartTime) + " ms");
//sign in link
            phaseStartTime = System.currentTimeMillis();
            WebElement linkByText = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@id='gh-ug']//a[contains(text(),'Sign in')]")));
            linkByText.click();
            phaseEndTime = System.currentTimeMillis();
            System.out.println("Click Sign In: " + (phaseEndTime - phaseStartTime) + " ms");
//username and password
            phaseStartTime = System.currentTimeMillis();
            WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='userid']")));
            username.sendKeys("sudeepsiddugs@gmail.com");
            driver.findElement(By.cssSelector("#signin-continue-btn")).click();
            phaseEndTime = System.currentTimeMillis();
            System.out.println("Enter username: " + (phaseEndTime - phaseStartTime) + " ms");

            phaseStartTime = System.currentTimeMillis();
            WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='pass']")));
            password.sendKeys("Tirupathi@123");
            driver.findElement(By.cssSelector("#sgnBt")).click();
            phaseEndTime = System.currentTimeMillis();
            System.out.println("Enter password: " + (phaseEndTime - phaseStartTime) + " ms");

         
            phaseStartTime = System.currentTimeMillis();
            WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("_nkw")));
            Actions actions = new Actions(driver);
            actions.moveToElement(searchBox).click().perform();
            searchBox.clear();
            searchBox.sendKeys("disney pixar cars");
            searchBox.submit();
            phaseEndTime = System.currentTimeMillis();
            System.out.println("Search operation: " + (phaseEndTime - phaseStartTime) + " ms");

            phaseStartTime = System.currentTimeMillis();
            String mainPage = driver.getWindowHandle();
            phaseEndTime = System.currentTimeMillis();
            System.out.println("Store main page handle: " + (phaseEndTime - phaseStartTime) + " ms");
//next tab
            phaseStartTime = System.currentTimeMillis();
            WebElement productLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@id='item36d1d86d3c']//span[@role='heading'][contains(text(),'Disney Pixar Cars Lightning McQueen 1:55 Diecast M')]")));
            productLink.click();
            phaseEndTime = System.currentTimeMillis();
            System.out.println("Click on product: " + (phaseEndTime - phaseStartTime) + " ms");

            phaseStartTime = System.currentTimeMillis();
            Set<String> allPages = driver.getWindowHandles();
            for (String page : allPages) {
                if (!page.equals(mainPage)) {
                    driver.switchTo().window(page);
                    break;
                }
            }
            phaseEndTime = System.currentTimeMillis();
            System.out.println("Switch to new tab: " + (phaseEndTime - phaseStartTime) + " ms");

            phaseStartTime = System.currentTimeMillis();
            System.out.println(driver.getCurrentUrl());
            phaseEndTime = System.currentTimeMillis();
            System.out.println("Get current URL: " + (phaseEndTime - phaseStartTime) + " ms");

            phaseStartTime = System.currentTimeMillis();
            List<WebElement> products = driver.findElements(By.className("_21Ahn-"));
            System.out.println(products.size());
            for (WebElement product : products) {
                System.out.println(product.getText());
            }
            phaseEndTime = System.currentTimeMillis();
            System.out.println("Find and print products: " + (phaseEndTime - phaseStartTime) + " ms");

            phaseStartTime = System.currentTimeMillis();
            Select style = new Select(driver.findElement(By.cssSelector("#x-msku__select-box-1000")));
            style.selectByValue("5");
            phaseEndTime = System.currentTimeMillis();
            System.out.println("Select style from dropdown: " + (phaseEndTime - phaseStartTime) + " ms");

            phaseStartTime = System.currentTimeMillis();
            WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='atcBtn_btn_1']")));
            addToCartButton.click();
            phaseEndTime = System.currentTimeMillis();
            System.out.println("Add to cart: " + (phaseEndTime - phaseStartTime) + " ms");

            phaseStartTime = System.currentTimeMillis();
            WebElement checkoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Go to checkout')]")));
            checkoutButton.click();
            phaseEndTime = System.currentTimeMillis();
            System.out.println("Go to checkout: " + (phaseEndTime - phaseStartTime) + " ms");

            
//adding new address and phone numbers it's for new login id's

         
         phaseStartTime = System.currentTimeMillis();
         WebElement w=driver.findElement(By.xpath("//select[@id='countryId']"));
 		Select dropdown=new Select(w);
 		dropdown.selectByIndex(0);
         phaseEndTime = System.currentTimeMillis();
         System.out.println("Select country: " + (phaseEndTime - phaseStartTime) + " ms");

         phaseStartTime = System.currentTimeMillis();
         WebElement address = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='addressSugg']")));
         address.sendKeys("Mr John Smith. 132, My Street, Kingston, New York 12401.");
         phaseEndTime = System.currentTimeMillis();
         System.out.println("Fill address: " + (phaseEndTime - phaseStartTime) + " ms");

         phaseStartTime = System.currentTimeMillis();
         WebElement phone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("phoneFlagComp1")));
         phone.sendKeys("5852826937");
         driver.findElement(By.xpath("//input[@id='sbtBtn']")).click();
         phaseEndTime = System.currentTimeMillis();
         System.out.println("Fill phone number: " + (phaseEndTime - phaseStartTime) + " ms");
         
         driver.findElement(By.xpath("//input[@id='sbtBtn']")).click();
         WebElement additional = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("WebElement phone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(\"phoneFlagComp1\")));\r\n"
         		+ "         phone.sendKeys(\"5852826937\");\r\n"
         		+ "         driver.findElement(By.xpath(\"//input[@id='sbtBtn']\")).click();")));
         phone.sendKeys("hurray");
         driver.findElement(By.xpath("//input[@id='sbtBtn']")).click();
         driver.findElement(By.xpath("//button[normalize-space()='Go to checkout]")).click();
    // till here     
        } catch (Exception e) {
            e.printStackTrace();
       
        } finally {
            if (driver != null) {
                // Close the browser
                phaseStartTime = System.currentTimeMillis();
                driver.quit();
                phaseEndTime = System.currentTimeMillis();
                System.out.println("Close browser: " + (phaseEndTime - phaseStartTime) + " ms");
            }
        }

        long endTime = System.currentTimeMillis();

        long totalDuration = endTime - startTime;

        double totalDurationInSeconds = totalDuration / 1000.0;

        // Display the total duration
        System.out.println("Total test duration: " + totalDurationInSeconds + " seconds");
    }

	private static WebElement findElement(By id) {
		// TODO Auto-generated method stub
		return null;
	}
}
