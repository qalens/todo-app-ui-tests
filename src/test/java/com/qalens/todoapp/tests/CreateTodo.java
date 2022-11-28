package com.qalens.todoapp.tests;

import com.qalens.todoapp.helpers.PageStateHelpers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.http.HttpClient;
import org.openqa.selenium.remote.http.HttpMethod;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateTodo {
    public static List<String> ids=new ArrayList<>();
    @Test
    public void creationOfTodoShouldWork(){
        String todoTitle = "Todo Created from Selenium at " + new Date();
        WebDriver driver = PageStateHelpers.getWebDriverForApplication();
        WebDriverWait wait = PageStateHelpers.waitUntilHomePageLoaded(driver);
        WebElement e = driver.findElement(By.id("new-todo"));
        e.sendKeys(todoTitle);
        e.sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[text()='"+todoTitle+"']")));
        WebElement createdTodo = driver.findElement(By.xpath("//*[text()='"+todoTitle+"']/parent::*"));
        ids.add(createdTodo.getAttribute("id"));
        driver.quit();
    }

    @AfterAll
    public static void deleteTodo() throws MalformedURLException {
        URL url = new URL("http://localhost:3000");
        HttpClient client = HttpClient.Factory.createDefault().createClient(url);
        ids.forEach(id->{
            org.openqa.selenium.remote.http.HttpRequest req = new org.openqa.selenium.remote.http.HttpRequest(HttpMethod.DELETE,"http://localhost:3000/api/todo/"+id);
            client.execute(req);
        });
    }
}
