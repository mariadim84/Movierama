package com.workable;

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
class MovieRamaApplicationTests {
	private static String firstname = "maria";
	private static String lastname = "dim";
	private static String username = "mariadim84";
	private static String password = "12";
	private static String movieTitle="test";
	private static String movieDesc="test";
	private static String movieTitle1="test1";
	private static String movieDesc1="test1";


	private static String firstname1 = "maria";
	private static String lastname1 = "dim";
	private static String username1 = "mariadim";
	private static String password1 = "12";

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

		assertThat(driver.getTitle()).isEqualTo("Home");
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

	public void testUser1Login() {
		WebDriverWait wait = new WebDriverWait(driver, 10);

		driver.get(baseURL + "/login");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username1, password1);

		assertThat(driver.getTitle()).isEqualTo("Home");

	}

	@Test
	@Order(4)
	public void testUserLoginAddMovie() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		testUserLogin();
		HomePage homePage =new HomePage(driver);
		homePage.addMovie(movieTitle, movieDesc,driver);
		assertThat(driver.getTitle()).isEqualTo("Home");

	}

	@Test
	@Order(5)
	public void testUserLoginEditMovie() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		testUserLogin();

		HomePage homePage =new HomePage(driver);
		homePage.editMovie(movieTitle1, movieDesc1,driver);
		assertThat(driver.getTitle()).isEqualTo("Home");
	}

	@Test
	@Order(6)
	public void testUserLoginDeleteMovie() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		testUserLogin();

		HomePage homePage =new HomePage(driver);
		homePage.deleteMovie( driver);
		assertThat(driver.getTitle()).isEqualTo("Home");
	}
	@Test
	@Order(7)
	public void testUserLoginLikeMovie() {
		WebDriverWait wait = new WebDriverWait(driver, 10);

		driver.get(baseURL + "/signup");
		assertThat(driver.getTitle()).isEqualTo("Sign Up");
		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup(firstname1, lastname1, username1, password1);

		WebElement divSuccess = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("success-msg")));
		assertThat(divSuccess.getText().contains("You successfully signed up!"));

		wait = new WebDriverWait(driver, 10);

		testUserLoginAddMovie();
		testUser1Login();

		HomePage homePage =new HomePage(driver);
		homePage.likeMovie( driver);
		assertThat(driver.getTitle()).isEqualTo("Home");
	}

	@Test
	@Order(8)
	public void testUserLoginUnLikeMovie() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		testUser1Login();

		HomePage homePage =new HomePage(driver);
		homePage.unlikeMovie( driver);
		assertThat(driver.getTitle()).isEqualTo("Home");
	}

	@Test
	@Order(9)
	public void testUserLoginHateMovie() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		testUser1Login();

		HomePage homePage =new HomePage(driver);
		homePage.hateMovie( driver);
		assertThat(driver.getTitle()).isEqualTo("Home");
	}

	@Test
	@Order(10)
	public void testUserLoginUnHateMovie() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		testUser1Login();

		HomePage homePage =new HomePage(driver);
		homePage.unhateMovie( driver);
		assertThat(driver.getTitle()).isEqualTo("Home");
	}

	@Test
	@Order(11)
	public void testUserLogout() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		testUserLogin();
		WebElement logoutButton = driver.findElement(By.id("logout-link"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click()", logoutButton);

 		WebElement logoutMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout-msg")));
		assertThat(driver.getTitle()).isEqualTo("Login");
	}
}
