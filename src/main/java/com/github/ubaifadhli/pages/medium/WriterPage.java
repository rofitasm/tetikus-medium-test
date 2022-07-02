package com.github.ubaifadhli.pages.medium;

import com.github.ubaifadhli.annotations.Locator;
import com.github.ubaifadhli.pages.PageObject;
import com.github.ubaifadhli.util.Element;

public class WriterPage extends PageObject {
    @Locator(webXPath = "(//button[text()='Follow'])[2]",
            mobileID = "com.medium.reader:id/btnFollow")
    private Element followButton;

    @Locator(webXPath = "//h2[contains(@class, 'pw-author-name')]",
            mobileID = "com.medium.reader:id/creator_name")
    private Element writerNameText;

    public void followWriter() {
        followButton.waitUntilClickable().click();
    }

    public String getWriterName() {
        return writerNameText.waitUntilVisible().getText();
    }
}
