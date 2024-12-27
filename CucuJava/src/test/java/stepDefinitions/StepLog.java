package com.asianet.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static org.junit.Assert.*;

public class LoginSteps {
    WebDriver driver;

    // Initialize the WebDriver and open the login page
    @Given("User is on the login page")
    public void user_is_on_the_login_page() {
        // Using WebDriverManager to handle driver setup
        io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
        driver = new org.openqa.selenium.chrome.ChromeDriver();
        driver.get("https://www.asianet-tours.it/login");
    }

    // Simulate entering valid login credentials
    @When("User enters valid username and password")
    public void user_enters_valid_username_and_password() {
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));

        usernameField.sendKeys("validUser");
        passwordField.sendKeys("validPassword");
        passwordField.submit();
    }

    // Verify that the user is redirected to the homepage
    @Then("User should be redirected to the homepage")
    public void user_should_be_redirected_to_homepage() {
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/home")); // Update with the actual URL after login
        driver.quit();
    }

    // Simulate entering invalid login credentials
    @When("User enters invalid username and password")
    public void user_enters_invalid_username_and_password() {
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));

        usernameField.sendKeys("invalidUser");
        passwordField.sendKeys("invalidPassword");
        passwordField.submit();
    }

    // Verify that the user sees an error message
    @Then("User should see an error message")
    public void user_should_see_error_message() {
        WebElement errorMessage = driver.findElement(By.id("error-message"));
        assertTrue(errorMessage.isDisplayed());
        driver.quit();
    }
}
