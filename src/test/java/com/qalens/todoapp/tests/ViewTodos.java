package com.qalens.todoapp.tests;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.qalens.todoapp.helpers.PageStateHelpers;
import com.qalens.todoapp.models.TodoItem;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.NetworkInterceptor;
import org.openqa.selenium.remote.http.HttpResponse;
import org.openqa.selenium.remote.http.Route;
import java.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.remote.http.Contents.utf8String;
public class ViewTodos {
    @Test
    public void viewAllTodos() throws JsonProcessingException {

        WebDriver driver = PageStateHelpers.getWebDriverForApplication();

        List<TodoItem> items = new ArrayList<>();
        for (int i=0;i<20;i++){
            items.add(TodoItem.builder().id(i+1).caption("Todo Item "+i).isDone(i>10).build());
        }

        String body = new ObjectMapper().writeValueAsString(items);

        NetworkInterceptor interceptor = new NetworkInterceptor(driver,
                Route.matching(req->req.getUri().contains("/api/todo")).to(
                        ()-> req-> new HttpResponse()
                                .setStatus(200)
                                .addHeader("Content-Type","application/json; charset=utf-8")
                                .addHeader("Accept-Encoding", "gzip, deflate")
                                .setContent(utf8String(body))
                )
        );
        driver.get("http://localhost:3000");
        PageStateHelpers.waitUntilHomePageLoaded(driver);
        items.forEach(item->{
            WebElement e = driver.findElement(By.id(Integer.toString(item.getId())));
            assertEquals(item.getCaption(),e.getText());
            WebElement text_cell = driver.findElement(By.cssSelector("[id=\""+item.getId()+"\"] .text-2xl"));
            if(item.isDone())
                assertTrue(text_cell.getAttribute("class").contains("line-through"),"Todo Item is not marked done on UI despite it is done");
            else
                assertTrue(!text_cell.getAttribute("class").contains("line-through"),"Todo Item is marked done on UI despite it is not done");
        });
        driver.quit();
    }
}
