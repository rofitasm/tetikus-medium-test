package com.github.ubaifadhli.pages.medium;

import com.github.ubaifadhli.annotations.Locator;
import com.github.ubaifadhli.pages.PageObject;
import com.github.ubaifadhli.util.Element;
import com.github.ubaifadhli.util.MobileElementFunction;

public class ListDetailPage extends PageObject {
    @Locator(webXPath = "//button[@aria-label='More options']",
            mobileAccessibilityID = "More options")
    private Element listEllipsis;

    @Locator(webXPath = "//p[text()='Delete list']",
            mobileXPath = "//android.widget.TextView[@text='Delete list']")
    private Element deleteListButton;

    @Locator(webXPath = "//span[text()='Delete']",
            mobileID = "com.medium.reader:id/btn_delete")
    private Element confirmDeleteListButton;

    @Locator(webXPath = "//button[contains(@aria-label, 'List item menu')]",
            mobileID = "com.medium.reader:id/post_preview_overflow_menu_button")
    private Element listArticleEllipsis;

    @Locator(webXPath = "//button[text()='Remove item']",
            mobileXPath = "//android.widget.TextView[@text='Remove from list']")
    private Element removeItemFromListButton;

    public void deleteList() {
        listEllipsis.waitUntilClickable().click();
        deleteListButton.waitUntilClickable().click();
        confirmDeleteListButton.waitUntilClickable().click();

        waitFor(2);
    }

    public void removeFirstArticleFromList() {
        listArticleEllipsis.waitUntilClickable().click();
        removeItemFromListButton.waitUntilClickable().click();

        if (isCurrentPlatformMobile())
            new MobileElementFunction(getMobileDriver()).goBack();
    }
}
