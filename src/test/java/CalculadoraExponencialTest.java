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
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.get("http://www.calculadoraonline.com.br/basica");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) driver.quit();
    }

    private void clicarEVerificar(String base, String exp, String esperado) {
        // 1. Clica no botão de potência (b27)
        wait.until(ExpectedConditions.elementToBeClickable(By.id("b27"))).click();
        
        // 2. Espera os campos ficarem visíveis e interage
        WebElement campoBase = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("bX")));
        campoBase.clear();
        campoBase.sendKeys(base);
        
        driver.findElement(By.id("bN")).clear();
        driver.findElement(By.id("bN")).sendKeys(exp);
        
        // 3. Clica em calcular
        driver.findElement(By.id("bC")).click();
        
        // 4. Aguarda resultado no visor
        WebElement visor = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("visor")));
        
        // Loop de espera para garantir que o texto foi atualizado no visor
        wait.until(d -> !visor.getAttribute("value").isEmpty());
        
        assertEquals(esperado, visor.getAttribute("value").trim());
    }

    @Test
    public void testExponenciacao() {
        clicarEVerificar("2", "3", "8");
        clicarEVerificar("5", "0", "1");
        clicarEVerificar("-3", "2", "9");
        clicarEVerificar("-2", "3", "-8");
        clicarEVerificar("4", "-1", "0.25");
        clicarEVerificar("0", "5", "0");
        clicarEVerificar("1.5", "2", "2.25");
        clicarEVerificar("1", "99", "1");
    }
}
