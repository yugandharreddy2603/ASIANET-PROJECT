package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StepLog {
	WebDriver driver;

	@Given("user navigates to the website www.asianet-tours.it\\/")
	public void user_navigates_to_the_website_www_asianet_tours_it() {
		// Set up the FirefoxDriver with the correct path to geckodriver
		System.setProperty("webdriver.gecko.driver", "E:\\besanttech\\geckodriver-v0.35.0-win64\\geckodriver.exe");
		driver = new FirefoxDriver();

		// Open the website
		driver.get("https://asianet-tours.it");
		driver.manage().window().maximize();

		// Wait for preloader to disappear (if it exists)
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("preloader")));
	}

	@Given("there user logs in through Login Window by using Username as {string} and Password as {string}")
	public void there_user_logs_in_through_login_window_by_using_username_as_and_password_as(String username, String password) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		// Wait for the "Sign In" button to be clickable
		WebElement signInButton = driver.findElement(By.linkText("Accedi"));
		wait.until(ExpectedConditions.elementToBeClickable(signInButton));

		// Click the "Sign In" button
		signInButton.click();

		// Wait for the username and password fields to be visible and interactable
		WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("user_name")));
		WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));

		// Wait until the username and password fields are enabled (interactable)
		wait.until(ExpectedConditions.elementToBeClickable(usernameField));
		wait.until(ExpectedConditions.elementToBeClickable(passwordField));

		// Clear any existing values in the fields before typing
		usernameField.clear();
		passwordField.clear();

		// Enter the provided credentials
		usernameField.sendKeys(username);
		passwordField.sendKeys(password);

		// Wait for 10 seconds before automatically clicking the login button (for debugging)
		try {
			Thread.sleep(10000); // Wait for 10 seconds before clicking login
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Click the login button
		WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
		submitButton.click();
	}


	@Then("login must be successful.")
	public void login_must_be_successful() {
		// Wait for the success message to be visible
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//h2[@class='sec__title font-size-30 text-white' and contains(text(), 'Hi,')]"))
		);

		// Verify the success message is displayed
		if (successMessage != null && successMessage.isDisplayed()) {
			System.out.println("Login successful: " + successMessage.getText());
		} else {
			System.out.println("Login failed!");
		}

		// Close the browser after the test
		driver.quit();
	}
}
