package com.company;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        // Makes the driver
        //System.setProperty("webdriver.gecko.driver", "D:\\webdriver\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();

        // Login to the website (https://www.shapeways.com/)
        driver.get("https://www.shapeways.com/login");

        Thread.sleep(10000);

        String username = "miniGetter";
        String password = "miniGetter123";

        WebElement usernameBox = driver.findElement(By.id("login_username"));
        WebElement passwordBox = driver.findElement(By.id("login_password"));

        WebElement signInButton = driver.findElement(By.id("sign_in_button"));

        usernameBox.sendKeys(username);
        passwordBox.sendKeys(password);

        Thread.sleep(3000);

        signInButton.click();

        Thread.sleep(3000);

        // Stops the program to allow the user to do the capcha
//        Scanner scanner = new Scanner(System.in);
//        scanner.nextLine();

        // Goes to mz4250's minis (https://www.shapeways.com/designer/mz4250/creations?s=0#more-products)
        driver.get("https://www.shapeways.com/designer/mz4250/creations?s=0#more-products");

        Thread.sleep(5000);

        // Gets the amount of pages with the xpath
        int numOfPages = Integer.valueOf(driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[2]/div[2]/div/a[6]/span")).getText());

        // Loops through the number of pages
        for (int i = 0; i < numOfPages; i++) {

            // Gets all the products with document.findElementsByClassName("product-url");
            List<WebElement> products = new ArrayList<>();
            products.addAll(driver.findElements(By.className("product-url")));

            // Removes each duplicate of the found products
            Set<WebElement> productsNoDuplicates = new LinkedHashSet<>(products);
            products.clear();
            products.addAll(productsNoDuplicates);

            // Gets the current url and saves it
            String currentURL = driver.getCurrentUrl();

            // Loops through the found products
            for (int x = 0; x < products.size(); x++) {
                // Goes to the url of the iteration
                driver.get(products.get(x).getAttribute("href"));

                Thread.sleep(5000);

                // Find the "Download Product" with the class "btn sw--margin-bottom-2"
                WebElement downloadProduct = driver.findElement(By.className("btn sw--margin-bottom-2"));

                // Clicks the found download product button
                downloadProduct.click();
            }

            // Goes back to the saved url
            driver.get(currentURL);

            Thread.sleep(3000);

            // Gets the url of the next page and goes to it
            String nextPage = driver.findElement(By.className("sw-pagination__nav-circle sw-grid-flex sw-grid-flex--align-center sw-grid-flex--justify-center sw--circle sw--border-grey-1 sw--margin-left-2 sw--text-decoration-none")).getAttribute("href");
            driver.get("https://www.shapeways.com" + nextPage);
        }
    }
}
