package com.qalens.todoapp.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageStateHelpers {
    public static WebDriverWait waitUntilHomePageLoaded(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".todo-item")));
        return wait;
    }

    public static WebDriver getWebDriverForApplication() {
        WebDriver driver=new ChromeDriver();
        driver.get("http://localhost:3000");
        return driver;
    }
}
