package boss;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.Set;

public class pommy {
    public static void main(String[] args) {
        WebDriver driver = null;

        try {
            // Initialize WebDriver
            driver = new ChromeDriver();
            driver.manage().window().maximize();

            // Example: Perform eBay automation steps
            performEBayAutomation(driver);

            // Calculate and display Chrome cache path
            String cachePath = getChromeCachePath();
            System.out.println("Chrome Cache Path: " + cachePath);

            // Example: Calculate and display Chrome cache size
            long cacheSize = getDirectorySize(Paths.get(cachePath));
            System.out.println("Chrome Cache Size: " + cacheSize + " bytes");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    private static void performEBayAutomation(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            // Navigate to eBay
            driver.get("https://www.ebay.com");

            // Click sign-in link
            long phaseStartTime = System.currentTimeMillis();
            WebElement signInLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@id='gh-ug']//a[contains(text(),'Sign in')]")));
            signInLink.click();
            long phaseEndTime = System.currentTimeMillis();
            System.out.println("Click Sign In: " + (phaseEndTime - phaseStartTime) + " ms");

            // Enter username
            phaseStartTime = System.currentTimeMillis();
            WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='userid']")));
            username.sendKeys("sudeepsiddugs@gmail.com");
            driver.findElement(By.cssSelector("#signin-continue-btn")).click();
            phaseEndTime = System.currentTimeMillis();
            System.out.println("Enter username: " + (phaseEndTime - phaseStartTime) + " ms");

            // Enter password
            phaseStartTime = System.currentTimeMillis();
            WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='pass']")));
            password.sendKeys("Tirupathi@123");
            driver.findElement(By.cssSelector("#sgnBt")).click();
            phaseEndTime = System.currentTimeMillis();
            System.out.println("Enter password: " + (phaseEndTime - phaseStartTime) + " ms");

            // Perform search
            phaseStartTime = System.currentTimeMillis();
            WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("_nkw")));
            Actions actions = new Actions(driver);
            actions.moveToElement(searchBox).click().perform();
            searchBox.clear();
            searchBox.sendKeys("disney pixar cars");
            searchBox.submit();
            phaseEndTime = System.currentTimeMillis();
            System.out.println("Search operation: " + (phaseEndTime - phaseStartTime) + " ms");

            // Click on a product
            String mainPage = driver.getWindowHandle();
            phaseStartTime = System.currentTimeMillis();
            WebElement productLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@id='item36d1d86d3c']//span[@role='heading'][contains(text(),'Disney Pixar Cars Lightning McQueen 1:55 Diecast M')]")));
            productLink.click();
            phaseEndTime = System.currentTimeMillis();
            System.out.println("Click on product: " + (phaseEndTime - phaseStartTime) + " ms");

            // Switch to new tab
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

            // Find and print products
            phaseStartTime = System.currentTimeMillis();
            List<WebElement> products = driver.findElements(By.className("_21Ahn-"));
            System.out.println("Number of products found: " + products.size());
            for (WebElement product : products) {
                System.out.println(product.getText());
            }
            phaseEndTime = System.currentTimeMillis();
            System.out.println("Find and print products: " + (phaseEndTime - phaseStartTime) + " ms");

            // Select style from dropdown
            phaseStartTime = System.currentTimeMillis();
            Select style = new Select(driver.findElement(By.cssSelector("#x-msku__select-box-1000")));
            style.selectByValue("5");
            phaseEndTime = System.currentTimeMillis();
            System.out.println("Select style from dropdown: " + (phaseEndTime - phaseStartTime) + " ms");

            // Add to cart
            phaseStartTime = System.currentTimeMillis();
            WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='atcBtn_btn_1']")));
            addToCartButton.click();
            phaseEndTime = System.currentTimeMillis();
            System.out.println("Add to cart: " + (phaseEndTime - phaseStartTime) + " ms");

            // Go to checkout
            phaseStartTime = System.currentTimeMillis();
            WebElement checkoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Go to checkout')]")));
            checkoutButton.click();
            phaseEndTime = System.currentTimeMillis();
            System.out.println("Go to checkout: " + (phaseEndTime - phaseStartTime) + " ms");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getChromeCachePath() {
        String cacheDir = null;
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            // Windows path
            cacheDir = System.getenv("LOCALAPPDATA") + "\\Google\\Chrome\\User Data\\Default\\Cache";
        } else if (os.contains("mac")) {
            // Mac path
            cacheDir = System.getProperty("user.home") + "/Library/Caches/Google/Chrome/Default/Cache";
        } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
            // Linux path
            cacheDir = System.getProperty("user.home") + "/.cache/google-chrome/Default/Cache";
        } else {
            System.out.println("Unsupported operating system!");
        }

        return cacheDir;
    }

    private static long getDirectorySize(Path path) {
        try {
            return Files.walk(path)
                        .filter(Files::isRegularFile)
                        .mapToLong(p -> p.toFile().length())
                        .sum();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
