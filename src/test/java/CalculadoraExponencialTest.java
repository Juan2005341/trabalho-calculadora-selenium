import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CalculadoraExponencialTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private final String URL = "http://www.calculadoraonline.com.br/basica";

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        
        // 1. Acessar a página http://www.calculadoraonline.com.br/basica
        driver.get(URL);
    }

    @AfterEach
    public void tearDown() {
        // 7. Fechar a página
        if (driver != null) {
            driver.quit();
        }
    }

    private void executarFluxoCalculo(String base, String expoente, String resultadoEsperado) {
        // 2. Clicar no botão x^n (ID 'b27' na interface básica do site)
        WebElement botaoExp = wait.until(ExpectedConditions.elementToBeClickable(By.id("b27")));
        botaoExp.click();

        // Aguarda a renderização dos inputs dinâmicos na tela
        WebElement inputBase = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("bX")));
        WebElement inputExpoente = driver.findElement(By.id("bN"));
        WebElement botaoCalcular = driver.findElement(By.id("bC"));

        // 3. Digitar o valor da base (x)
        inputBase.clear();
        inputBase.sendKeys(base);

        // 4. Digitar o valor do expoente (n)
        inputExpoente.clear();
        inputExpoente.sendKeys(expoente);

        // 5. Clicar em calcular
        botaoCalcular.click();

        // 6. Verificar se o valor resultante equivale ao esperado
        WebElement visorResultado = driver.findElement(By.id("visor"));
        
        // 6.1. Uso mandatório do getAttribute("value")
        String resultadoObtido = visorResultado.getAttribute("value");

        // 6.2. Validação obrigatória com assertEquals
        assertEquals(resultadoEsperado, resultadoObtido);
    }

    @Test
    public void test1_ExponenciacaoPadraoPositiva() {
        executarFluxoCalculo("2", "3", "8");
    }

    @Test
    public void test2_BasePositivaExpoenteZero() {
        executarFluxoCalculo("5", "0", "1");
    }

    @Test
    public void test3_BaseNegativaExpoentePar() {
        executarFluxoCalculo("-3", "2", "9");
    }

    @Test
    public void test4_BaseNegativaExpoenteImpar() {
        executarFluxoCalculo("-2", "3", "-8");
    }

    @Test
    public void test5_ExpoenteNegativo() {
        executarFluxoCalculo("4", "-1", "0.25");
    }

    @Test
    public void test6_BaseZeroExpoentePositivo() {
        executarFluxoCalculo("0", "5", "0");
    }

    @Test
    public void test7_BaseDecimal() {
        executarFluxoCalculo("1.5", "2", "2.25");
    }

    @Test
    public void test8_BaseUmQualquerExpoente() {
        executarFluxoCalculo("1", "99", "1");
    }
          }
          
