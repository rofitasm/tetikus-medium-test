package com.github.ubaifadhli.pages.medium;

import com.github.ubaifadhli.annotations.Locator;
import com.github.ubaifadhli.annotations.WebLocator;
import com.github.ubaifadhli.pages.PageObject;
import com.github.ubaifadhli.util.Element;
import com.github.ubaifadhli.util.MobileElementFunction;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class FollowingPage extends PageObject {
    @Locator(webXPath = "//div[preceding-sibling::h2]//h2",
            mobileXPath = "//android.widget.TextView[@resource-id='com.medium.reader:id/creator_name']")
    private Element writersNameText;

    @Locator(webXPath = "//button[text()='Following' and following::h2[contains(text(), 'Publication')]]",
            mobileID = "com.medium.reader:id/follow_button")
    private Element followButton;

    @WebLocator(xpath = "//a[contains(@href, 'readinghistory')]")
    private Element readingHistoryTab;

    @WebLocator(xpath = "//a/div/h2")
    private Element readingHistoryArticleText;

    public List<String> getFollowingWritersName() {
        writersNameText.waitUntilVisible();

        return writersNameText.asElements()
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void clickReadingHistoryTab() {
        readingHistoryTab.waitUntilClickable().click();
    }

    public String getReadingHistoryArticle() {
        return readingHistoryArticleText.waitUntilVisible().getText();
    }

    public void unfollowWriter() {
        followButton.waitUntilClickable().click();

        if (isCurrentPlatformMobile())
            new MobileElementFunction(getMobileDriver()).goBack();

        waitFor(1);
    }

    public int getFollowedWritersCount() {
        return followButton.asElements().size();
    }
}
