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

public class StepNoLog {
    WebDriver driver;

    @Given("there user logs in through Login Window by using wrong Username as {string} and wrong Password as {string}")
    public void there_user_logs_in_through_login_window_by_using_wrong_username_as_and_wrong_password_as(String username, String password) {
        // Set up FirefoxDriver with the correct path to geckodriver
        System.setProperty("webdriver.gecko.driver", "E:\\besanttech\\geckodriver-v0.35.0-win64\\geckodriver.exe");
        driver = new FirefoxDriver();

        // Open the website
        driver.get("https://asianet-tours.it");
        driver.manage().window().maximize();

        // Wait for preloader to disappear (if it exists)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("preloader")));

        // Wait for the "Sign In" button to be clickable
        WebElement signInButton = driver.findElement(By.linkText("Accedi"));
        wait.until(ExpectedConditions.elementToBeClickable(signInButton));

        // Click the "Sign In" button
        signInButton.click();

        // Wait for the username and password fields to be visible and enabled
        WebElement usernameField = driver.findElement(By.name("user_name"));
        WebElement passwordField = driver.findElement(By.name("password"));

        // Ensure that the fields are visible and interactable
        wait.until(ExpectedConditions.visibilityOf(usernameField));
        wait.until(ExpectedConditions.visibilityOf(passwordField));

        // Clear any pre-existing text in the fields before typing
        usernameField.clear();
        passwordField.clear();

        // Enter wrong credentials (username and password)
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);

        // Wait for 10 seconds before automatically clicking the login button
        try {
            Thread.sleep(10000); // Wait for 10 seconds before clicking login
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Click the login button after 10 seconds
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        submitButton.click();
    }

    @Then("login must fail with an error message")
    public void login_must_fail_with_an_error_message() {
        // Wait for the error message to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='alert alert-danger each_message_row' and contains(text(), 'Email or mobile number is invalid!')]"))
        );

        // Verify the error message is displayed
        if (errorMessage != null && errorMessage.isDisplayed()) {
            System.out.println("Error message displayed: " + errorMessage.getText());
        } else {
            System.out.println("Error message not displayed!");
        }

        // Close the browser after the test
        driver.quit();
    }
}