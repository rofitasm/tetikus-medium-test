
package com.github.ubaifadhli.pages.medium;

import com.github.ubaifadhli.annotations.BaseURL;
import com.github.ubaifadhli.annotations.Locator;
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

    @Locator(webXPath = "//button[@aria-label='Search']",
            mobileAccessibilityID = "Discover something new")
    private Element searchButton;

    @Locator(webXPath = "//button[child::img]",
            mobileID = "com.medium.reader:id/profile_image")
    private Element profileButton;

    @Locator(webXPath = "//a[text()='Write a story']",
            mobileAccessibilityID = "Write a story")
    private Element createNewArticleButton;

    @Locator(webXPath = "//a[contains(@href, 'your_stories_page') and parent::h3]",
            mobileID = "com.medium.reader:id/tvTitle")
    private Element userArticleTitle;

    @Locator(webXPath = "//input[@aria-label='search']",
            mobileID = "com.medium.reader:id/search_input")
    private Element searchInput;

    @Locator(mobileAccessibilityID = "More options")
    private Element articleEllipsis;

    @Locator(mobileXPath = "//android.widget.TextView[@text='Delete']")
    private Element deleteArticleButton;

    @Locator(mobileID = "android:id/button1")
    private Element confirmDeleteArticleButton;

    @Locator(webXPath = "//div[@class='postArticle-content']//h3",
            mobileXPath = "//android.widget.TextView[@resource-id='com.medium.reader:id/post_preview_title']")
    private Element searchResultTitle;

    public void goToTwitterLoginPage() {
        if (isCurrentPlatformWeb())
            getWebDriver().manage().window().maximize();

        signInButton.waitUntilClickable().click();
        signInWithTwitterButton.waitUntilClickable().click();
    }

    public void searchForArticle(String keyword) {
        searchButton.waitUntilClickable().click();

        searchInput.waitUntilClickable().click();

        searchInput.waitUntilClickable().typeIntoField(keyword);

        searchInput.pressEnter();
    }

    public void openFirstUserArticle() {
        profileButton.waitUntilClickable().click();

        userArticleTitle.waitUntilClickable().click();
    }

    public void createNewArticle() {
        profileButton.waitUntilClickable().click();
        createNewArticleButton.waitUntilClickable().click();
    }

    public void deleteArticle() {
        articleEllipsis.waitUntilClickable().click();

        deleteArticleButton.waitUntilClickable().click();

        confirmDeleteArticleButton.waitUntilClickable().click();
    }

    public void refreshProfilePage() {
        profileButton.waitUntilClickable();

        new MobileElementFunction(getMobileDriver()).swipe(25, SwipeDirection.DOWN);

        waitFor(2);
    }

    public String getFirstArticleTitle() {
        return searchResultTitle.waitUntilVisible().getText();
    }

    public String getFirstUserArticleTitle() {
        return userArticleTitle.waitUntilVisible().getText();
    }
}