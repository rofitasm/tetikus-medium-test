package com.github.ubaifadhli.pages.medium;

import com.github.ubaifadhli.annotations.BaseURL;
import com.github.ubaifadhli.annotations.Locator;
import com.github.ubaifadhli.pages.PageObject;
import com.github.ubaifadhli.util.Element;

@BaseURL("https://www.medium.com")
public class MediumHomePage extends PageObject {
    @Locator(webXPath = "//a[text()='Sign In']",
             mobileID = "com.medium.reader:id/sign_in_prompt")
    private Element signInButton;

    @Locator(webXPath = "//div[text()='Sign in with Twitter']",
             mobileID = "com.medium.reader:id/sign_in_twitter_button")
    private Element signInWithTwitterButton;

    @Locator(webXPath = "//button[@aria-label='Search']",
             mobileAccessibilityID = "Discover something new")
    private Element searchButton;

    @Locator(webXPath = "//button[@aria-label='Search']",
             mobileAccessibilityID = "Discover something new")
    private Element searchInput;

    public void goToTwitterLoginPage() {
        signInButton.waitUntilClickable().click();
        signInWithTwitterButton.waitUntilClickable().click();
    }
}
