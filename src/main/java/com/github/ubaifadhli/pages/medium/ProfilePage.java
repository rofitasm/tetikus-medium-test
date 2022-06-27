package com.github.ubaifadhli.pages.medium;

import com.github.ubaifadhli.annotations.Locator;
import com.github.ubaifadhli.pages.PageObject;
import com.github.ubaifadhli.util.Element;

public class ProfilePage extends PageObject {
    @Locator(webXPath = "//article//h1/a",
            mobileXPath = "//android.widget.TextView[@resource-id='com.medium.reader:id/common_item_paragraph_text']")
    private Element userArticleTitle;


    public void clickUserArticleTitle() {
        userArticleTitle.waitUntilClickable().click();
    }

    public String getFirstUserArticleTitle() {
        return userArticleTitle.waitUntilVisible().getText();
    }


}
