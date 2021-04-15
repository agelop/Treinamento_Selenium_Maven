package modulo11;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;


public class AosStepDefinitions {
	
	// Declaração driver e wait no escopo da classe
    private static WebDriver driver;
    
    
	@Before
	public static void setUp() throws Exception {
		// Instanciando chrome driver com o WebDriverManager
        WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		// Adicionando Implicit Wait de 10 segundos para toda a sessão
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@After
	public static void tearDown() throws Exception {
        //Clean up and dispose of the driver
        //Good explanation of close, quit, dispose here http://stackoverflow.com/questions/15067107/difference-between-webdriver-dispose-close-and-quit
        if(null != driver) {
            driver.close();
            driver.quit();
        }
	}

	
	@Given("user entra no portal {string}")
	public void user_entra_no_portal(String url) {
		// Navega para o Advantage Online Shopping
		driver.get(url);
	}

	@When("seleciona {string}")
	public void seleciona(String categoria) {
		// Clicar na Categoria
		WebElement categoriaLink = driver.findElement(By.xpath("//*[.=\"" + categoria + "\"]"));
		categoriaLink.click();
	}

	@When("seleciona o primeiro produto")
	public void seleciona_o_primeiro_produto() {
		// Clicar no Botão BUY NOW
		WebElement buyButton = driver.findElement(By.linkText("BUY NOW"));
		buyButton.click();
	}

	@Then("preco mostrado {string}")
	public void preco_mostrado(String expectedPrice) {
		// Preço do Produto na aplicação
		WebElement actualPrice = driver.findElement(By.cssSelector("#Description > h2"));
		// Verifica Preço
		Assert.assertEquals(expectedPrice, actualPrice.getText());

	}

}
