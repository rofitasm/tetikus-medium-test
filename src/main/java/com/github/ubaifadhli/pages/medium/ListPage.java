package com.github.ubaifadhli.pages.medium;

import com.github.ubaifadhli.annotations.Locator;
import com.github.ubaifadhli.pages.PageObject;
import com.github.ubaifadhli.util.Element;

public class ListPage extends PageObject {
    @Locator(webXPath = "//button[text()='New list']",
            mobileID = "com.medium.reader:id/btn_new_list")
    private Element createNewListButton;

    @Locator(webXPath = "//input[@placeholder='Give it a name']",
            mobileID = "com.medium.reader:id/textinput_placeholder")
    private Element newListNameField;

    @Locator(webXPath = "//button[child::span[text()='Create']]",
            mobileID = "com.medium.reader:id/btn_create")
    private Element popupCreateNewListButton;

    @Locator(webXPath = "(//a/div/div/h2)[2]",
            mobileXPath = "(//android.widget.TextView[@resource-id='com.medium.reader:id/tv_name'])[2]")
    private Element secondListName;

    @Locator(webXPath = "//div/div/p", mobileID = "com.medium.reader:id/tv_counters")
    private Element listArticleCount;

    public void createNewList(String newListName) {
        createNewListButton.waitUntilClickable().click();

        newListNameField.waitUntilClickable().typeIntoField(newListName);

        popupCreateNewListButton.waitUntilClickable().click();

        waitFor(2);
    }

    public String getSecondListName() {
        return secondListName.waitUntilVisible().getText();
    }

    public int getFirstListArticleCount() {
        String articleCount = listArticleCount.waitUntilVisible().getText().split(" ")[0];

        return Integer.parseInt(articleCount);
    }
}
