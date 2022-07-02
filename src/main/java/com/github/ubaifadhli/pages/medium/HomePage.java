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

    @Locator(webXPath = "//button[@aria-label='user options menu']",
            mobileID = "com.medium.reader:id/image")
    private Element profileButton;

    @Locator(webXPath = "//a[contains(@href, 'settings')]",
            mobileXPath = "//android.widget.TextView[contains(@text, 'Settings')]")
    private Element settingsButton;

    @Locator(webXPath = "//*[local-name()='svg' and @aria-label='Home']", mobileAccessibilityID = "Home")
    private Element homeButton;

    @MobileLocator(id = "com.medium.reader:id/item_account")
    private Element accountButton;

    @Locator(webXPath = "//a[contains(@href, 'lists')]",
            mobileAccessibilityID = "Reading List")
    private Element listsButton;

    @Locator(webXPath = "//a[@aria-label='Post Preview Title']",
            mobileXPath = "//android.widget.TextView[@resource-id='com.medium.reader:id/post_preview_title' and count(following-sibling::*)=6 and following-sibling::*[@content-desc='Unsave']]")
    private Element firstHomeArticle;

    @Locator(webXPath = "//a[contains(@href, 'new-story')]",
            mobileAccessibilityID = "Write a story")
    private Element createNewArticleButton;

    @Locator(webXPath = "//a[contains(@href, 'your_stories_page') and parent::h3]",
            mobileXPath = "//android.widget.TextView[@resource-id='com.medium.reader:id/common_item_paragraph_text']")
    private Element userArticleTitle;

    @Locator(webXPath = "//input[@aria-label='search']",
            mobileID = "com.medium.reader:id/search_input")
    private Element searchInput;

    @WebLocator(xpath = "//a[contains(@href, '/@') and descendant::img]")
    private Element dropdownProfileButton;

    @WebLocator(xpath = "//div[contains(text(), 'Published')]")
    private Element publishedArticleSection;

    @Locator(webXPath = "//a[@aria-label='Post Preview Title']//h2",
            mobileXPath = "//android.widget.TextView[@resource-id='com.medium.reader:id/post_preview_title']")
    private Element searchResultTitle;

    @MobileLocator(xpath = "//android.widget.FrameLayout[@resource-id='com.medium.reader:id/you_tab_header_settings']/android.widget.ImageView")
    private Element profileEllipsis;

    @Locator(webXPath = "(//a[text()='Get unlimited access'])[2]",
            mobileID = "com.medium.reader:id/item_become_a_member")
    private Element getMembershipButton;

    @Locator(webXPath = "//a[contains(@href, 'following')]//p",
            mobileID = "com.medium.reader:id/creator_groupie_header_following_count")
    private Element followingButton;

    public void clickProfileEllipsis() {
        profileEllipsis.waitUntilClickable().click();
    }

    public void goToTwitterLoginPage() {
        signInButton.waitUntilClickable().click();
        signInWithTwitterButton.waitUntilClickable().click();
    }

    public void openFollowingPage() {
        waitFor(1);

        profileButton.waitUntilClickable().click();
        followingButton.waitUntilClickable().click();

        waitFor(1);
    }

    public void backToHomeFromArticleWriter() {
        if (isCurrentPlatformWeb())
            homeButton.waitUntilClickable().click();
        else {
            new MobileElementFunction(getMobileDriver()).goBack();
            new MobileElementFunction(getMobileDriver()).goBack();
        }
    }

    public void searchForArticle(String keyword) {
        searchButton.waitUntilClickable().click();

        searchInput.waitUntilClickable().click();

        searchInput.waitUntilClickable().typeIntoField(keyword);

        searchInput.pressEnter();
    }

    public void goToSettingsPage() {
        profileButton.waitUntilClickable().click();

        profileEllipsis.waitUntilClickable().click();

        settingsButton.waitUntilClickable().click();
    }

    public void goToMembershipPage() {
        if (isCurrentPlatformMobile())
            goToSettingsPage();

        getMembershipButton.waitUntilClickable().click();
    }

    public void goToAccountSettingsPage() {
        accountButton.waitUntilClickable().click();
    }

    public void goToListsPage() {
        if (isCurrentPlatformMobile())
            profileButton.waitUntilClickable().click();

        waitFor(1);
        listsButton.waitUntilClickable().click();
        waitFor(1);
    }

    public void mobileGoBack() {
        new MobileElementFunction(getMobileDriver()).goBack();
    }

    public void goToPublishedArticlePage() {
        profileButton.waitUntilClickable().click();

        dropdownProfileButton.waitUntilClickable().click();
    }

    public void openFirstUserArticle() {
        goToPublishedArticlePage();

        waitFor(1);
    }

    public void createNewArticle() {
        if (isCurrentPlatformWeb())
            waitFor(2);
        else
            profileButton.waitUntilClickable().click();

        createNewArticleButton.waitUntilClickable().click();
    }

    public void openHomeArticle() {
        homeButton.waitUntilClickable().click();

        waitFor(1);

        firstHomeArticle.waitUntilClickable().click();
    }

    public void refreshPage() {
        new MobileElementFunction(getMobileDriver()).swipe(50, SwipeDirection.DOWN);
        waitFor(2);
    }

    public void refreshProfilePage() {
        if (isCurrentPlatformMobile()) {
            profileButton.waitUntilClickable();

            waitFor(2);

            MobileElementFunction mobileElementFunction = new MobileElementFunction(getMobileDriver());

            mobileElementFunction.swipe(50, SwipeDirection.DOWN);
            waitFor(1);
            mobileElementFunction.swipe(50, SwipeDirection.DOWN);
        }

        waitFor(2);
    }

    public String getFirstArticleTitle() {
        return searchResultTitle.waitUntilVisible().getText();
    }

    public String getFirstUserArticleTitle() {
        return userArticleTitle.waitUntilVisible().getText();
    }
}
