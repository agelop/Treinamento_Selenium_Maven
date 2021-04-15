package modulo10;

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
import page.objects.HomePage;


public class AosPageObjectTest {
	
	// Declaração driver e wait no escopo da classe
    private static WebDriver driver;
    private static HomePage page;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		// Instanciando chrome driver com o WebDriverManager
        WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
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
		
        // Instancia a Home Page - definida como Page Objects
        page = new HomePage(driver);
		
	}

	@After
	public void tearDown() throws Exception {
		
		page.clickUserIcon();

		page.clickSignout();
		
	}

	@Test
	public void loggedUserTest() {
		
        
		page.clickUserIcon();
        page.fillUsername("Mercury");
        page.fillPassword("Mercury");
        page.clickSignin();

        String loggedUser = page.getLoggedUser();
        Assert.assertEquals("Mercury", loggedUser);


	}

}
