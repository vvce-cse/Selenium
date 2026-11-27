import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VvceLoginTest {
    public static void main(String[] args) {
        // Add your credentials here later
        String username = "YOUR_USERNAME";
        String password = "YOUR_PASSWORD";

        // Selenium 4+ automatically manages ChromeDriver
        WebDriver driver = new ChromeDriver();

        try {
            driver.manage().window().maximize();
            driver.get("https://vvce.ion-education.in/login");

            // Explicit wait for dynamic frontend frameworks (React/Vue/Tailwind)
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // 1. Locate and fill Username (id="username")
            WebElement userField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("username"))
            );
            userField.clear();
            userField.sendKeys(username);

            // 2. Locate and fill Password (id="password")
            WebElement passField = driver.findElement(By.id("password"));
            passField.clear();
            passField.sendKeys(password);

            // 3. Locate and click the "Sign In" button using XPath
            WebElement signInBtn = driver.findElement(
                By.xpath("//button[@type='submit' and normalize-space()='Sign In']")
            );
            signInBtn.click();

            // Optional: pause briefly to observe the result before closing
            Thread.sleep(3000);
            System.out.println("Page title after login: " + driver.getTitle());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
