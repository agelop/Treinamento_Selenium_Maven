package modulo12;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckSpeakerTest {
	
	private static RemoteWebDriver driver;
    private static WebDriverWait wait;

	@Test
	public void test() throws Exception {
		
		// Instanciando RemoteWebDriver - Chrome no Windows10
		String remoteURL = "http://192.168.0.21:4444/wd/hub";
		DesiredCapabilities capability = DesiredCapabilities.chrome();
		capability.setBrowserName("chrome");
		//capability.setPlatform(Platform.WIN10);
		//capability.setVersion("89.0.4389.23");
		driver = new RemoteWebDriver(new URL(remoteURL), capability);
		
		
		// Instanciando WebDriverWait 10 segundos
		wait = new WebDriverWait(driver, 10);
		
		// Navega para o Advantage Online Shopping
		driver.get("https://www.advantageonlineshopping.com/");
		
        //Waiting Page To Fully Load - Wait Explicito
        wait.until(ExpectedConditions.attributeToBe(By.className("loader"),"style", "display: none; opacity: 0;"));
        
		// Clicar no Segmento de TABLETS
		WebElement segmentLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[.=\"SPEAKERS\"]")));
		segmentLink.click();
		
		// Clicar no Botão BUY NOW
		WebElement buyButton = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("BUY NOW")));
		buyButton.click();
		
		// Preço do Produto na aplicação
		WebElement actualPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#Description > h2")));
		
		// Verifica preço
		String expectedPrice = "$200.00";
		Assert.assertEquals(expectedPrice, actualPrice.getText());
		
		
		driver.close();
        driver.quit();
		
		
	}

}
