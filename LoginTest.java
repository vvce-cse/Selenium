package com.vvce.lab.selenium_demo;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {
    public static void main(String[] args) throws InterruptedException {
        // 1. Launch real Chrome browser
        WebDriver driver = new ChromeDriver();

        try {
            // 2. Open the local HTML page in Chrome
            File htmlFile = new File("login.html");
            driver.get(htmlFile.toURI().toString());

            // 3. Verify Page Title
            String title = driver.getTitle();
            if (!title.contains("VVCE Student Portal")) {
                throw new AssertionError("Title mismatch! Found: " + title);
            }
            System.out.println("PASS: Page title verified -> " + title);

            // 4. Locate UI Elements by ID
            WebElement usernameBox = driver.findElement(By.id("username"));
            WebElement passwordBox = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.id("loginBtn"));
            WebElement messageText = driver.findElement(By.id("message"));

            // 5. Test Case 1: Invalid Login Behavior
            usernameBox.sendKeys("student");
            passwordBox.sendKeys("wrongpass");
            loginButton.click();
            Thread.sleep(1000); // Pause 1 second so students can see the browser action

            if (!messageText.getText().equals("Invalid Credentials!")) {
                throw new AssertionError("Expected Invalid Credentials, got: " + messageText.getText());
            }
            System.out.println("PASS: Invalid login error message verified -> " + messageText.getText());

            // 6. Test Case 2: Valid Login Behavior
            usernameBox.clear();
            passwordBox.clear();
            usernameBox.sendKeys("vvce");
            passwordBox.sendKeys("1234");
            loginButton.click();
            Thread.sleep(1000);

            if (!messageText.getText().equals("Login Successful! Welcome, vvce.")) {
                throw new AssertionError("Expected Login Successful, got: " + messageText.getText());
            }
            System.out.println("PASS: Valid login success message verified -> " + messageText.getText());

        } finally {
            // 7. Close the browser cleanly
            driver.quit();
        }
    }
}
