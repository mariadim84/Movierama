package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {
	private static String firstname = "maria";
	private static String lastname = "dim";
	private static String username = "mariadim84";
	private static String password = "12";
	private static String noteTitle="test";
	private static String noteDesc="test";
	private static String noteTitle1="test1";
	private static String noteDesc1="test1";
	private static String credentialUrl="test";
	private static String credentialUsername="maria";
	private static String credentialPassword="12345";
	private static String credentialPassword1="md12345";
	private  static String fileName="/Users/maria/Desktop/java udacity/SuperDuperDrive/starter/cloudstorage/test.txt";

	@LocalServerPort
	private int port;

	private WebDriver driver;

	public String baseURL;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
			baseURL = baseURL = "http://localhost:" + port;
		this.driver = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	@Order(1)
	public void getHomeWithoutLogin() {
		driver.get(String.format(baseURL +"/home", this.port));

		assertThat(driver.getTitle()).isEqualTo("Login");
	}

	@Test
	@Order(2)
	public void testUserSignup() {
		WebDriverWait wait = new WebDriverWait(driver, 10);

		driver.get(baseURL + "/signup");
		assertThat(driver.getTitle()).isEqualTo("Sign Up");
		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup(firstname, lastname, username, password);

		WebElement divSuccess = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("success-msg")));
		assertThat(divSuccess.getText().contains("You successfully signed up!"));

	}


	@Test
	@Order(3)
	public void testUserLogin() {
		WebDriverWait wait = new WebDriverWait(driver, 10);

		driver.get(baseURL + "/login");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);

		assertThat(driver.getTitle()).isEqualTo("Home");

	}

	@Test
	@Order(4)
	public void testUserLoginAddNote() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		testUserLogin();
		HomePage homePage =new HomePage(driver);
		homePage.addNote(noteTitle, noteDesc,driver);
		assertThat(driver.getTitle()).isEqualTo("Home");

	}

	@Test
	@Order(5)
	public void testUserLoginEditNote() {
//		testUserSignup();
//		testUserLoginAddNote();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		testUserLogin();

		HomePage homePage =new HomePage(driver);
		homePage.editNote(noteTitle1, noteDesc1,driver);
		assertThat(driver.getTitle()).isEqualTo("Home");
	}

	@Test
	@Order(6)
	public void testUserLoginDeleteNote() {
//		testUserSignup();
//		testUserLoginAddNote();

		WebDriverWait wait = new WebDriverWait(driver, 10);
		testUserLogin();

		HomePage homePage =new HomePage(driver);
		homePage.deleteNote( driver);
		assertThat(driver.getTitle()).isEqualTo("Home");
	}

	@Test
	@Order(7)
	public void testUserLoginAddCredential() {
		//testUserSignup();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		testUserLogin();

		HomePage homePage =new HomePage(driver);
		homePage.addCredential(credentialUrl, credentialUsername,credentialPassword,driver);
		assertThat(driver.getTitle()).isEqualTo("Home");

	}

	@Test
	@Order(8)
	public void testUserLoginEditCredential() {
//		testUserSignup();
//		testUserLoginAddCredential();

		WebDriverWait wait = new WebDriverWait(driver, 10);
		testUserLogin();

		HomePage homePage =new HomePage(driver);
		homePage.editCredential(credentialPassword1, driver);
		assertThat(driver.getTitle()).isEqualTo("Home");
	}

	@Test
	@Order(9)
	public void testUserLoginDeleteCredential() {
//		testUserSignup();
//		testUserLoginAddNote();

		WebDriverWait wait = new WebDriverWait(driver, 10);
		testUserLogin();
		HomePage homePage =new HomePage(driver);
		homePage.deleteCredential( driver);
		assertThat(driver.getTitle()).isEqualTo("Home");
	}

	@Test
	@Order(10)
	public void testUserLoginAddFile() {
		//testUserSignup();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		testUserLogin();
		HomePage homePage =new HomePage(driver);
		homePage.addFile(fileName, driver);
		assertThat(driver.getTitle()).isEqualTo("Home");

	}

	@Test
	@Order(11)
	public void testUserLoginViewFile() {
	//	testUserSignup();
	//	testUserLoginAddFile();

		WebDriverWait wait = new WebDriverWait(driver, 10);
		testUserLogin();
		HomePage homePage =new HomePage(driver);
		homePage.viewFile(driver);
		assertThat(driver.getTitle()).isEqualTo("Home");
	}

	@Test
	@Order(12)
	public void testUserLoginDeleteFile() {
	//	testUserSignup();
	//	testUserLoginAddFile();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		testUserLogin();
		HomePage homePage =new HomePage(driver);
		homePage.deleteFile( driver);
		assertThat(driver.getTitle()).isEqualTo("Home");
	}

	@Test
	@Order(13)
	public void testUserLogout() {
		//testUserSignup();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		testUserLogin();
		WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("logout-button")));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click()", logoutButton);

		WebElement logoutMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout-msg")));
		assertThat(driver.getTitle()).isEqualTo("Login");
	}
}
