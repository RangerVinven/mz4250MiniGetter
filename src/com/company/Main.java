package com.company;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Main {

    // Function to get the location of the forward button
    static WebElement forwardBtnGetter(WebDriver driver) {
        WebElement forwardBtn = driver.findElement(By.className("sw-pagination__nav-circle sw-grid-flex sw-grid-flex--align-center sw-grid-flex--justify-center sw--circle sw--border-grey-1 sw--margin-left-2 sw--text-decoration-none"));

        return forwardBtn;
    }

    public static void main(String[] args) throws InterruptedException {
        // Makes the driver
        WebDriver driver = new FirefoxDriver();

        // Login to the website (https://www.shapeways.com/)
        driver.get("https://www.shapeways.com/login");

        String username = "miniGetter";
        String password = "miniGetter123";

        WebElement usernameBox = driver.findElement(By.id("login_username"));
        WebElement passwordBox = driver.findElement(By.id("login_password"));
        WebElement signInButton = driver.findElement(By.id("sign_in_button"));

        usernameBox.sendKeys(username);
        passwordBox.sendKeys(password);

        signInButton.click();

        Thread.sleep(3000);

        // Goes to mz4250's minis (https://www.shapeways.com/designer/mz4250/creations?s=0#more-products)
        driver.get("https://www.shapeways.com/designer/mz4250/creations?s=0#more-products");

        // Calls the function to get the location of the forward button
        // Gets all the products with document.findElementsByClassName("product-url");
        // Removes each duplicate of the found products
        // Gets the current url and saves it
        // Loops through the found products
            // Goes to the url of the iteration
            // Find the "Download Product" with the class "btn sw--margin-bottom-2"
            // Clicks the found download product button
    }
}
