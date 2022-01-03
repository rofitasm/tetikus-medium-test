
package com.github.ubaifadhli.pages.medium;

import com.github.ubaifadhli.annotations.Locator;
import com.github.ubaifadhli.annotations.WebLocator;
import com.github.ubaifadhli.pages.PageObject;
import com.github.ubaifadhli.util.Element;

public class ArticlePage extends PageObject {
    @Locator(mobileAccessibilityID = "Read responses")
    private Element commentButton;

    @Locator(mobileID = "com.medium.reader:id/response_editor")
    private Element commentField;

    @Locator(mobileAccessibilityID = "Publish response")
    private Element publishCommentButton;

    @Locator(mobileID = "com.medium.reader:id/response_text")
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