
package com.github.ubaifadhli.pages.medium;

import com.github.ubaifadhli.annotations.Locator;
import com.github.ubaifadhli.pages.PageObject;
import com.github.ubaifadhli.util.Element;

public class ArticlePage extends PageObject {
    @Locator(webXPath = "//div[child::*[local-name()='svg' and @aria-label='responses']]",
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

    public void createComment(String commentText) {
        commentButton.waitUntilClickable().click();

        commentField.waitUntilClickable().click();

        commentField.typeIntoField(commentText);

        publishCommentButton.waitUntilClickable().click();

        waitFor(2);
    }

    public String getFirstCommentText() {
        return firstComment.getText();
    }
}