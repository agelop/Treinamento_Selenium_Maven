package modulo9;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;


public class AosTest {
	
	// Declaração driver e wait no escopo da classe
    private static WebDriver driver;
    private static WebDriverWait wait;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// Instanciando chrome driver com o WebDriverManager
        WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
		// Instanciando WebDriverWait 10 segundos
		wait = new WebDriverWait(driver, 10);
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
        //Clean up and dispose of the driver
        //Good explanation of close, quit, dispose here http://stackoverflow.com/questions/15067107/difference-between-webdriver-dispose-close-and-quit
        if(null != driver) {
            driver.close();
            driver.quit();
        }
	}

	@Before
	public void setUp() throws Exception {
		
		// Navega para o Advantage Online Shopping
		driver.get("https://www.advantageonlineshopping.com/");
		
        //Waiting Page To Fully Load - Wait Explicito
        wait.until(ExpectedConditions.attributeToBe(By.className("loader"),"style", "display: none; opacity: 0;"));
		
	}

	@After
	public void tearDown() throws Exception {
		
        //Sign out User
        WebElement userButton2 = wait.until(ExpectedConditions.elementToBeClickable(By.id("hrefUserIcon")));
        userButton2.click();

        //Click Signout Menu
        WebElement signoutMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"loginMiniTitle\"]/label[3]")));
        signoutMenu.click();
		
	}

	@Test
	public void loggedUserTest() {
		
        
        //SignIn to Advantage - Click User Icon
        WebElement userButton1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("hrefUserIcon")));
        userButton1.click();

        //Enter User Name
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        usernameField.sendKeys("Mercury");

        //Enter Password
        WebElement passwdField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
        passwdField.sendKeys("Mercury");

        //Click SignIn Button
        WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("sign_in_btnundefined")));
        signInButton.click();

  
        //Waiting Page to Refresh
        wait.until(ExpectedConditions.attributeToBe(By.className("PopUp"),"style", "display: none;"));

        //Verify Logged USer on the Page
        WebElement loggedUserMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"menuUserLink\"]/span")));
        String loggedUser = loggedUserMenu.getText();

        // Verificar se username correto - via Assert JUnit
        Assert.assertEquals("Mercury", loggedUser);


	}

}
