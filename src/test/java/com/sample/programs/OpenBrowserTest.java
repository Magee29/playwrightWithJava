package com.sample.programs;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class OpenBrowserTest {

    Playwright playwright;
    Browser browser;
    Page page;

    @BeforeEach
    void setUp() {

        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(false)
                        .setArgs(Arrays.asList("--start-maximized")));
        page = browser.newPage();
    }

    @Test
    void openBrowser() throws InterruptedException {

        page.navigate("https://practicesoftwaretesting.com/");
        System.out.println("title is - " + page.title());
        Assertions.assertTrue(page.title().contains("Practice"));
        page.locator("[placeholder='Search']").fill("Pliers");
        Thread.sleep(5000);
        page.locator("button:has-text('Search')").click();

        int cardCount = page.locator(".card").count();
        System.out.println("Card count is " + cardCount);
        Thread.sleep(5000);
  
    }

    @AfterEach
    void tearDown() {
        page.close();
        browser.close();
        playwright.close();
    }
}
