import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.util.List;
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
        
        // COMENTADO O HEADLESS: Agora o navegador vai abrir fisicamente na tela para podermos ver o teste rodar!
        // options.addArguments("--headless=new"); 
        
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--start-maximized"); // Abre o navegador em tela cheia

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        // 1. Acessar a página
        driver.get(URL);

        // BYPASS DE COOKIES: Se aparecer o banner de consentimento do Google/LGPD, nós clicamos nele para liberar a tela
        try {
            List<WebElement> botoesConsentimento = driver.findElements(By.cssSelector(".fc-button-label, .fc-agree, .cookie-consent"));
            if (!botoesConsentimento.isEmpty() && botoesConsentimento.get(0).isDisplayed()) {
                botoesConsentimento.get(0).click();
                Thread.sleep(1000); // Aguarda sumir o banner
            }
        } catch (Exception e) {
            // Se não aparecer o banner, segue o fluxo normalmente
        }
    }

    @AfterEach
    public void tearDown() {
        // 7. Fechar a página
        if (driver != null) {
            driver.quit();
        }
    }

    private void executarFluxoCalculo(String base, String expoente, String resultadoEsperado) {
        // 2. Clicar no botão x^n (b27)
        WebElement botaoExp = wait.until(ExpectedConditions.elementToBeClickable(By.id("b27")));
        botaoExp.click();

        // Aguarda os campos ficarem visíveis e prontos para receber texto
        WebElement inputBase = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("bX")));
        WebElement inputExpoente = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("bN")));
        WebElement botaoCalcular = driver.findElement(By.id("bC"));

        // 3. Digitar o valor da base (x)
        inputBase.clear();
        inputBase.sendKeys(base);

        // 4. Digitar o valor do expoente (n)
        inputExpoente.clear();
        inputExpoente.sendKeys(expoente);

        // 5. Clicar em calcular
        botaoCalcular.click();

        // Aguarda um instante para o visor atualizar o resultado
        try { Thread.sleep(500); } catch (InterruptedException e) {}

        // 6. Verificar o valor resultante
        WebElement visorResultado = driver.findElement(By.id("visor"));
        
        // 6.1. Uso do getAttribute("value")
        String resultadoObtido = visorResultado.getAttribute("value");

        // 6.2. Verificação com assertEquals
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
                
    
          
