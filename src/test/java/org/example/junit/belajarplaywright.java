package org.example.junit;

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.DisplayName;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


public class belajarplaywright {
    @Test
    @DisplayName("Test case 1 - Login")
    public void loginPositiveTest() {
        Playwright playwright = Playwright.create();
        BrowserType.LaunchOptions setHeadless = new BrowserType.LaunchOptions().setHeadless(false);
        Page page = playwright.chromium().launch(setHeadless).newPage();

        page.navigate("https://bookcart.azurewebsites.net/");

        page.getByText("Login").click();
        page.getByLabel("Username").fill("yuji");
        page.getByLabel("Password").fill("Yuji2023!");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions()
                .setName("Login")).last().click();
        page.getByPlaceholder("Search books", new Page.GetByPlaceholderOptions()
                .setExact(false)).type("The Hookup");
        page.getByRole(AriaRole.OPTION).first().click();
        page.getByAltText("Book cover image").click();

        //screenshot
        Path screenshotPath = Paths.get(System.currentTimeMillis() + ".jpg");
        page.screenshot(new Page.ScreenshotOptions().setPath(screenshotPath));

        //close browser and playwright instances
        playwright.close();

    }

    @Test
    @DisplayName("Add to Book Cart")
    public void addtoBookCart() {
        Playwright playwright = Playwright.create();
        BrowserType.LaunchOptions setHeadless = new BrowserType.LaunchOptions().setHeadless(false);
        Page page = playwright.chromium().launch(setHeadless).newPage();

        page.navigate("https://bookcart.azurewebsites.net/");
        System.out.println("=======================");
        assertThat(page.locator("body > app-roor > div > app-home > div > div.col.mb-3 > " +
                "div > div:nth-child(3) > app-book-card > mat-card > " +
                "mat-card-content:nth-child(2) > div > a > strong")).containsText("HP4");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions()
                .setName("Add to Cart")).last().click();

        assertThat(page.getByAltText("One Item Added to cart")).isVisible();
        System.out.println("Ada..");
        System.out.println("=========================");

        Path screenshotPath = Paths.get("TEST CASE 2_" + System.currentTimeMillis() + ".jpg");
        page.screenshot(new Page.ScreenshotOptions().setPath(screenshotPath));
        playwright.close();
    }
}
