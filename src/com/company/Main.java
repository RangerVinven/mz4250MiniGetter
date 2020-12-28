package com.company;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        // Makes the driver
        System.setProperty("webdriver.chrome.driver", "D:\\chromeWebdriver2\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        // Login to the website (https://www.shapeways.com/)
        driver.get("https://www.shapeways.com/login");

        Thread.sleep(10000);

        driver.findElement(By.xpath("/html/body/ul[1]/li/div/div/span/div/div/div[2]/a[1]")).click();

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
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        // Goes to mz4250's minis (https://www.shapeways.com/designer/mz4250/creations?s=0#more-products)
        driver.get("https://www.shapeways.com/designer/mz4250/creations?s=48#more-products");

        Thread.sleep(5000);

        // Gets the amount of pages with the xpath
        int numOfPages = Integer.valueOf(driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[2]/div[2]/div/a[6]/span")).getText());

        // For the number in the url (...?s=pageNumber#more-products), it goes up in 48, e.g page 1 is 0, page 2 is 48, page 3 is 96, etc
        int pageNumber = 0;

        // Loops through the number of pages
        for (int i = 0; i < numOfPages; i++) {

            // Gets all the products with document.findElementsByClassName("product-url");
            List<WebElement> products = new ArrayList<>();
            products.addAll(driver.findElements(By.className("product-url")));

            // Removes each duplicate of the found products
            Set<WebElement> productsNoDuplicates = new LinkedHashSet<>(products);
            products.clear();
            products.addAll(productsNoDuplicates);

            // Gets the random characters in the url (e.g, "https://www.shapeways.com/product/8H9RUT284/aarakocra-bard-2?optionId=79381935&li=user-profile")
            // In the above url, the "8H9RUT284" is the random characters
            List<String> productIDs = new ArrayList<>();

            for (int q = 0; q < products.size(); q++) {

                // Removes the start of the string
                String productID = products.get(q).getAttribute("href").replace("https://www.shapeways.com/product/", "");

                // Takes out the / to the end (leaving only the product's id)
                productID = productID.replaceAll("\\/.*", "");

                productIDs.add(productID);

            }

            // Removes duplicates from the product IDs
            Set<String> productIDNoDuplicates = new LinkedHashSet<>(productIDs);
            productIDs.clear();
            productIDs.addAll(productIDNoDuplicates);
            System.out.println(productIDs);

            // Gets the current url and saves it
            String currentURL = driver.getCurrentUrl();

            // Loops through the download links
            for (int p = 0; p < productIDs.size(); p++) {
                Thread.sleep(3000);
                driver.get("https://www.shapeways.com/product/download/" + productIDs.get(p));
                Thread.sleep(1000);
                driver.get(currentURL);
            }

            Thread.sleep(3000);

            // Goes to the next page of minis
            driver.get("https://www.shapeways.com/designer/mz4250/creations?s=" + pageNumber + "#more-products");
        }
    }
}
