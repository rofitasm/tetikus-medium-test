package com.github.ubaifadhli.pages.medium;

import com.github.ubaifadhli.annotations.BaseURL;
import com.github.ubaifadhli.annotations.Locator;
import com.github.ubaifadhli.annotations.MobileLocator;
import com.github.ubaifadhli.annotations.WebLocator;
import com.github.ubaifadhli.pages.PageObject;
import com.github.ubaifadhli.util.Element;
import com.github.ubaifadhli.util.MobileElementFunction;
import com.github.ubaifadhli.util.SwipeDirection;

@BaseURL("https://www.medium.com")
public class HomePage extends PageObject {
    @Locator(webXPath = "//a[text()='Sign In']",
            mobileID = "com.medium.reader:id/sign_in_prompt")
    private Element signInButton;

    @Locator(webXPath = "//div[text()='Sign in with Twitter']",
            mobileID = "com.medium.reader:id/sign_in_twitter_button")
    private Element signInWithTwitterButton;

    @MobileLocator(accessibilityID = "Discover something new")
    private Element searchButton;

    @Locator(webXPath = "//input[@aria-label='search']",
            mobileID = "com.medium.reader:id/search_input")
    private Element searchInput;

    @Locator(webXPath = "//a[@aria-label='Post Preview Title']//h2",
            mobileXPath = "//android.widget.TextView[@resource-id='com.medium.reader:id/post_preview_title']")
    private Element searchResultTitle;


    public void goToTwitterLoginPage() {
        signInButton.waitUntilClickable().click();
        signInWithTwitterButton.waitUntilClickable().click();
    }

    public void searchForArticle(String keyword) {
        searchButton.waitUntilClickable().click();

        searchInput.waitUntilClickable().click();

        searchInput.waitUntilClickable().typeIntoField(keyword);

        searchInput.pressEnter();
    }

    public String getFirstArticleTitle() {
        return searchResultTitle.waitUntilVisible().getText();
    }

}
