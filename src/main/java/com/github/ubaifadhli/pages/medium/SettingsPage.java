package com.github.ubaifadhli.pages.medium;

import com.github.ubaifadhli.annotations.Locator;
import com.github.ubaifadhli.annotations.MobileLocator;
import com.github.ubaifadhli.pages.PageObject;
import com.github.ubaifadhli.util.Element;

public class SettingsPage extends PageObject {
    @Locator(webXPath = "//button[@data-action='edit-bio']",
            mobileXPath = "(//android.widget.Button)[2]")
    private Element editBioButton;

    @Locator(webXPath = "//p[contains(@class, 'graf--p')]",
            mobileID = "editor_6")
    private Element editBioField;

    @Locator(webXPath = "//button[@data-action='save-bio']",
            mobileXPath = "//android.widget.Button)[2]")
    private Element saveBioButton;

    @Locator(webXPath = "//p[@data-default-value='Add your bio']//following-sibling::div/a",
            mobileXPath = "(//android.view.View[@content-desc=\"Profile\"])[2]/android.widget.TextView")
    private Element userPublicProfileLink;

    public void editBio(String bioText) {
        editBioButton.waitUntilClickable().click();

        editBioField.waitUntilClickable().clearAndType(bioText);

        saveBioButton.waitUntilClickable().click();

        waitFor(2);

        userPublicProfileLink.waitUntilClickable().click();
    }
}
