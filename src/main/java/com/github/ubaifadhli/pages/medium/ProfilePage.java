package com.github.ubaifadhli.pages.medium;

import com.github.ubaifadhli.annotations.Locator;
import com.github.ubaifadhli.pages.PageObject;
import com.github.ubaifadhli.util.Element;

public class ProfilePage extends PageObject {
    @Locator(webXPath = "(//a[contains(@href, 'your_stories_page') and parent::h3] | //h1[@data-selectable-paragraph]/a)",
            mobileXPath = "//android.widget.TextView[@resource-id='com.medium.reader:id/common_item_paragraph_text']")
    private Element userArticleTitle;

    @Locator(webXPath = "//a[contains(@href, 'edit')]",
            mobileXPath = "//android.widget.TextView[@text='Edit']")
    private Element editArticleButton;

    @Locator(webXPath = "//button[@aria-controls='creatorActionOverflowMenu' or @aria-controls='yourStoryActionsMenu']",
            mobileAccessibilityID = "More options")
    private Element articleEllipsis;

    @Locator(webXPath = "//div[@id='yourStoryActionsMenu']//button",
            mobileXPath = "//android.widget.TextView[@text='Delete']")
    private Element deleteArticleButton;

    @Locator(webXPath = "//button[text()='Delete']",
            mobileID = "android:id/button1")
    private Element confirmDeleteArticleButton;

    public void clickUserArticleTitle() {
        userArticleTitle.waitUntilClickable().click();
    }

    public String getFirstUserArticleTitle() {
        return userArticleTitle.waitUntilVisible().getText();
    }

    public void editArticle() {
        articleEllipsis.waitUntilClickable().click();

        editArticleButton.waitUntilClickable().click();

        waitFor(3);
    }

    public void deleteArticle() {
        articleEllipsis.waitUntilClickable().click();

        deleteArticleButton.waitUntilClickable().click();

        confirmDeleteArticleButton.waitUntilClickable().click();

        waitFor(3);
    }
}
