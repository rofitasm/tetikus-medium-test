package com.github.ubaifadhli.pages.medium;

import com.github.ubaifadhli.annotations.Locator;
import com.github.ubaifadhli.pages.PageObject;
import com.github.ubaifadhli.util.Element;
import com.github.ubaifadhli.util.MobileElementFunction;
import com.github.ubaifadhli.util.SwipeDirection;

public class ListPage extends PageObject {
    @Locator(webXPath = "//button[text()='New list']",
            mobileID = "com.medium.reader:id/btn_new_list")
    private Element createNewListButton;

    @Locator(webXPath = "//input[@placeholder='Give it a name']",
            mobileID = "com.medium.reader:id/text_field_input_list_name")
    private Element newListNameField;

    @Locator(webXPath = "//button[child::span[text()='Create']]",
            mobileID = "com.medium.reader:id/btn_create")
    private Element popupCreateNewListButton;

    @Locator(webXPath = "(//a/div/div/h2)[2]",
            mobileXPath = "(//android.widget.TextView[@resource-id='com.medium.reader:id/tv_name'])[2]")
    private Element secondListName;

    @Locator(webXPath = "//a[contains(@href, 'reading-list')]//p", mobileID = "com.medium.reader:id/tv_counters")
    private Element listArticleCount;

    @Locator(webXPath = "//h2[text()='Reading list']",
            mobileXPath = "//android.widget.TextView[@text='Reading List']")
    private Element readingListButton;

    public void createNewList(String newListName) {
        createNewListButton.waitUntilClickable().click();

        newListNameField.waitUntilClickable().typeIntoField(newListName);

        popupCreateNewListButton.waitUntilClickable().click();

        waitFor(2);
    }

    public void clickSecondList() {
        if (isCurrentPlatformMobile()) {
            new MobileElementFunction(getMobileDriver()).swipe(50, SwipeDirection.UP);
            waitFor(1);
        }

        secondListName.waitUntilClickable().click();
    }

    public String getSecondListName() {
        return secondListName.waitUntilVisible().getText();
    }

    public int getReadingListArticleCount() {
        String articleCount = listArticleCount.waitUntilVisible().getText().split(" ")[0];

        return articleCount.equals("Nothing") ? 0 : Integer.parseInt(articleCount);
    }

    public void clickReadingList() {
        readingListButton.waitUntilClickable().click();
    }
}
