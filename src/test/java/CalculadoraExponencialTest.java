import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.Duration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CalculadoraExponencialTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.get("https://www.calculadoraonline.com.br/basica");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) driver.quit();
    }

    private void executarFluxo(String base, String exp, String esperado) {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("b31"))).click();
        
        WebElement campoBase = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cx31_0")));
        WebElement campoExp = driver.findElement(By.id("cx31_1"));
        
        campoBase.clear();
        campoBase.sendKeys(base);
        campoExp.clear();
        campoExp.sendKeys(exp);
        
        driver.findElement(By.xpath("//div[@id='dpb31']//button[text()='Calcular']")).click();
        
        WebElement visor = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("TIExp")));
        wait.until(d -> !visor.getAttribute("value").isEmpty());
        
        assertEquals(esperado, visor.getAttribute("value").trim());
    }

    @Test
    public void testExponenciacaoCompleta() {
        executarFluxo("2", "3", "8");
        executarFluxo("5", "0", "1");
        executarFluxo("-3", "2", "9");
        executarFluxo("-2", "3", "-8");
        executarFluxo("4", "-1", "0.25");
        executarFluxo("0", "5", "0");
        executarFluxo("1.5", "2", "2.25");
        executarFluxo("1", "99", "1");
    }
            }

