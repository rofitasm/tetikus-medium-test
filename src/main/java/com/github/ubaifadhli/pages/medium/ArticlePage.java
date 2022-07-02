
package com.github.ubaifadhli.pages.medium;

import com.github.ubaifadhli.annotations.Locator;
import com.github.ubaifadhli.annotations.MobileLocator;
import com.github.ubaifadhli.annotations.WebLocator;
import com.github.ubaifadhli.pages.PageObject;
import com.github.ubaifadhli.util.Element;
import com.github.ubaifadhli.util.MobileElementFunction;

public class ArticlePage extends PageObject {
    @Locator(webXPath = "(//button[@aria-label='responses'])[2]",
            mobileAccessibilityID = "Read responses")
    private Element commentButton;

    @Locator(webXPath = "//h2[contains(@class, 'pw-author-name')]",
            mobileAccessibilityID = "View Author Profile")
    private Element writerProfile;

    @Locator(webXPath = "//div[@role='textbox']",
            mobileID = "com.medium.reader:id/response_editor")
    private Element commentField;

    @Locator(webXPath = "//button[text()='Respond']",
            mobileAccessibilityID = "Publish response")
    private Element publishCommentButton;

    // Cannot find better locator for web.
    @Locator(webXPath = "(//pre/div/div/div)[last()]",
            mobileID = "com.medium.reader:id/response_text")
    private Element firstComment;

    @Locator(webXPath = "//button[child::*[local-name()='svg' ] and @aria-label='Add to list bookmark button']",
            mobileXPath = "//android.widget.ImageView[@content-desc='Save']")
    private Element addToBookmarkButton;

    @MobileLocator(id = "com.medium.reader:id/btn_save_to")
    private Element confirmAddToBookmarkButton;

    @Locator(webXPath = "(//button[child::*[name()='svg' and contains(@class, 'overflow')]])[2]",
            mobileID = "com.medium.reader:id/overflow_menu")
    private Element commentEllipsisButton;

    @Locator(webXPath = "//button[text()='Delete']",
            mobileXPath = "//android.widget.TextView[@text='Delete']")
    private Element deleteCommentButton;

    @Locator(webXPath = "//p[child::button[text()='Reply']]",
            mobileID = "com.medium.reader:id/reply_button")
    private Element replyCommentButton;

    @WebLocator(xpath = "//button[text()='Delete Response']")
    private Element confirmDeleteCommentButton;

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

    public void goToWriterProfile() {
        writerProfile.waitUntilClickable().click();
    }

    public String getFirstCommentText() {
        return firstComment.getText();
    }

    public int getReplyCommentCount() {
        return replyCommentButton.asElements().size();
    }

    public void deleteComment() {
        commentEllipsisButton.waitUntilClickable().click();
        deleteCommentButton.waitUntilClickable().click();

        confirmDeleteCommentButton.waitUntilClickable().click();

        waitFor(2);

        if (isCurrentPlatformWeb()) {
            getWebDriver().navigate().refresh();
            commentButton.waitUntilClickable().click();
        }

        waitFor(1);
    }
}
