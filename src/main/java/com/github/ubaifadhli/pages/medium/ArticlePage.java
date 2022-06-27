
package com.github.ubaifadhli.pages.medium;

import com.github.ubaifadhli.annotations.Locator;
import com.github.ubaifadhli.annotations.MobileLocator;
import com.github.ubaifadhli.pages.PageObject;
import com.github.ubaifadhli.util.Element;
import com.github.ubaifadhli.util.MobileElementFunction;

public class ArticlePage extends PageObject {
    @Locator(webXPath = "(//button[@aria-label='responses'])[2]",
            mobileAccessibilityID = "Read responses")
    private Element commentButton;

    @Locator(webXPath = "//div[@role='textbox']",
            mobileID = "com.medium.reader:id/response_editor")
    private Element commentField;

    @Locator(webXPath = "//button[text()='Respond']",
            mobileAccessibilityID = "Publish response")
    private Element publishCommentButton;

    // Cannot find better locator for web.
    @Locator(webXPath = "//pre/div/div/div",
            mobileID = "com.medium.reader:id/response_text")
    private Element firstComment;

    @Locator(webXPath = "//button[child::*[local-name()='svg' ] and @aria-label='Add to list bookmark button']",
            mobileXPath = "//android.widget.ImageView[@content-desc='Save']")
    private Element addToBookmarkButton;

    @MobileLocator(id = "com.medium.reader:id/btn_save_to")
    private Element confirmAddToBookmarkButton;

    public void createComment(String commentText) {
        commentButton.waitUntilClickable().click();

        commentField.waitUntilClickable().click();

        commentField.typeIntoField(commentText);

        publishCommentButton.waitUntilClickable().click();

        waitFor(2);
    }

    public void clickAddToBookmarkButton() {
        waitFor(1);

        addToBookmarkButton.waitUntilClickable().click();

        waitFor(2);

        confirmAddToBookmarkButton.waitUntilClickable().click();

        if (isCurrentPlatformMobile())
            new MobileElementFunction(getMobileDriver()).goBack();
    }

    public String getFirstCommentText() {
        return firstComment.getText();
    }
}
