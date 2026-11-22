package com.sample.programs;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

import java.util.Arrays;

public class OpenBrowserBrowserContextTest {

    private static Playwright playwright;
    private static Browser browser;
    private static Page page;
    private static BrowserContext browserContext;

    @BeforeAll
    public static void setUpBrowser() {

        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(false)
                        .setArgs(Arrays.asList("--start-maximized")));
        browserContext = browser.newContext(new Browser.NewContextOptions().setViewportSize(null));
    }

    @BeforeEach
    void setUp() {
        page = browserContext.newPage();
    }

    @Test
    void openBrowser() throws InterruptedException {

        page.navigate("https://practicesoftwaretesting.com/");
        System.out.println("title is - " + page.title());
        Assertions.assertTrue(page.title().contains("Practice"));
        page.locator("[placeholder='Search']").fill("Pliers");
        page.getByPlaceholder("Search").fill("Pliers");
        page.locator("button:has-text('Search')").click();
        int cardCount = page.locator(".card").count();
        System.out.println("Card count is " + cardCount);

    }

    @Test
    void openBrowser2() throws InterruptedException {

        page.navigate("https://practicesoftwaretesting.com/");
        System.out.println("title is - " + page.title());
        Assertions.assertTrue(page.title().contains("Practice"));
        page.locator("[placeholder='Search']").fill("Pliers");
        page.locator("button:has-text('Search')").click();
        int cardCount = page.locator(".card").count();
        System.out.println("Card count is " + cardCount);

    }

    @AfterEach
    void tearDownTab() {
        page.close();
    }

    @AfterAll
    public static void tearDown() {


        browser.close();
        playwright.close();
    }
}
