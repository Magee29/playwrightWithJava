package com.sample.programs;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.junit.Options;
import com.microsoft.playwright.junit.OptionsFactory;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;


@UsePlaywright(OpenBrowserWithOptionsTest.MyOptions.class)
public final class OpenBrowserWithOptionsTest {

    public static class MyOptions implements OptionsFactory{

        @Override
        public Options getOptions() {
            return new Options()
                    .setHeadless(false)
                    .setLaunchOptions(new BrowserType.LaunchOptions()
                            .setArgs(List.of("--start-maximized")));
        }
    }

   /* Playwright playwright;
    Browser browser;
    Page page;

    @BeforeEach
    void setUp() {

        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(false)
                        .setArgs(Arrays.asList("--maximized")));
        page = browser.newPage();
    }*/

    @Test
    void openBrowser(Page page) throws InterruptedException {

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

    /*@AfterEach
    void tearDown() {
        page.close();
        browser.close();
        playwright.close();
    }*/
}
