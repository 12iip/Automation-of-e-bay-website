package boss;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class simp {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        long phaseStartTime, phaseEndTime;

        WebDriver driver = null;

        try {
            // Set the path to the ChromeDriver executable if it's not in the system PATH
            // System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

      // Initialize the ChromeDriver
            phaseStartTime = System.currentTimeMillis();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            phaseEndTime = System.currentTimeMillis();
            System.out.println("ChromeDriver initialization: " + (phaseEndTime - phaseStartTime) + " ms");
     // Navigate to eBay
            phaseStartTime = System.currentTimeMillis();
            driver.get("https://www.ebay.com");
            phaseEndTime = System.currentTimeMillis();
            System.out.println("Navigation to eBay: " + (phaseEndTime - phaseStartTime) + " ms");
            
   // Search for "disney pixar cars"
            phaseStartTime = System.currentTimeMillis();
            WebElement searchBox = driver.findElement(By.name("_nkw"));
            searchBox.sendKeys("disney pixar cars");
            searchBox.submit();
            phaseEndTime = System.currentTimeMillis();
            System.out.println("Search operation: " + (phaseEndTime - phaseStartTime) + " ms");

  // Store the main page handle
            phaseStartTime = System.currentTimeMillis();
            String mainPage = driver.getWindowHandle();
            phaseEndTime = System.currentTimeMillis();
            System.out.println("Store main page handle: " + (phaseEndTime - phaseStartTime) + " ms");

 // Click on a specific product
            phaseStartTime = System.currentTimeMillis();
            driver.findElement(By.xpath("//li[@id='item36d1d86d3c']//span[@role='heading'][contains(text(),'Disney Pixar Cars Lightning McQueen 1:55 Diecast M')]")).click();
            phaseEndTime = System.currentTimeMillis();
            System.out.println("Click on product: " + (phaseEndTime - phaseStartTime) + " ms");

            // Switch to the new tab
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

            // Print current URL
            phaseStartTime = System.currentTimeMillis();
            System.out.println(driver.getCurrentUrl());
            phaseEndTime = System.currentTimeMillis();
            System.out.println("Get current URL: " + (phaseEndTime - phaseStartTime) + " ms");

            // Find and print product elements
            phaseStartTime = System.currentTimeMillis();
            List<WebElement> products = driver.findElements(By.className("_21Ahn-"));
            System.out.println(products.size());
            for (WebElement product : products) {
                System.out.println(product.getText());
            }
            phaseEndTime = System.currentTimeMillis();
            System.out.println("Find and print products: " + (phaseEndTime - phaseStartTime) + " ms");

            // Select style from 
            phaseStartTime = System.currentTimeMillis();
            Select style = new Select(driver.findElement(By.cssSelector("#x-msku__select-box-1000")));
            style.selectByValue("2");
            phaseEndTime = System.currentTimeMillis();
            System.out.println("Select style from dropdown: " + (phaseEndTime - phaseStartTime) + " ms");

            // Add to cart
            phaseStartTime = System.currentTimeMillis();
            driver.findElement(By.xpath("//a[@id='atcBtn_btn_1']")).click();
            phaseEndTime = System.currentTimeMillis();
            System.out.println("Add to cart: " + (phaseEndTime - phaseStartTime) + " ms");

            // Go to checkout
            phaseStartTime = System.currentTimeMillis();
            driver.findElement(By.xpath("//button[normalize-space()='Go to checkout']")).click();
            driver.findElement(By.xpath("(//button[normalize-space()='Continue as guest'])[1]")).click();
            phaseEndTime = System.currentTimeMillis();
            System.out.println("Go to checkout: " + (phaseEndTime - phaseStartTime) + " ms");

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

        // Capture end time
        long endTime = System.currentTimeMillis();

        // Calculate total duration
        long totalDuration = endTime - startTime;

        // Convert duration to seconds
        double totalDurationInSeconds = totalDuration / 1000.0;

        // Display the total duration
        System.out.println("Total test duration: " + totalDurationInSeconds + " seconds");
    }
}
