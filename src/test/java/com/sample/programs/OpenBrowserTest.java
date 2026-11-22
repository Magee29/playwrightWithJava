package com.sample.programs;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

import java.util.Arrays;

public class OpenBrowserTest {

    static Playwright playwright;
    static Browser browser;
    Page page;


    @BeforeAll
    static void browserSetUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(false)
                        .setArgs(Arrays.asList("--start-maximized")));

    }

    @BeforeEach
    void setUp() {


        BrowserContext browserContext = browser.newContext(new Browser.NewContextOptions().setViewportSize(null));
        page = browserContext.newPage();
        page.navigate("https://practicesoftwaretesting.com/");
    }

    @Test
    void verifyLocatorGetByPalceHolder() throws InterruptedException {
        System.out.println("title is - " + page.title());
        Assertions.assertTrue(page.title().contains("Practice"));
        page.getByPlaceholder("Search").fill("Pliers");
        page.locator("button:has-text('Search')").click();
        int cardCount = page.locator(".card").count();
        System.out.println("Card count is " + cardCount);


    }

    @Test
    void byAltText() {
        page.getByText(" Combination Pliers ").click();
        Assertions.assertTrue(page.getByText("Pliers").isVisible());
    }

    @AfterEach
    void tearDown() {
        page.close();
    }

    @AfterAll
    public static void tearDownBrowser() {
        browser.close();
        playwright.close();
    }
}
