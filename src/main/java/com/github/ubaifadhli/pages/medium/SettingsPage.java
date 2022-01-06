package com.github.ubaifadhli.pages.medium;

import com.github.ubaifadhli.annotations.Locator;
import com.github.ubaifadhli.annotations.MobileLocator;
import com.github.ubaifadhli.pages.PageObject;
import com.github.ubaifadhli.util.Element;
import com.github.ubaifadhli.util.MobileElementFunction;

public class SettingsPage extends PageObject {
    @Locator(webXPath = "//button[@data-action='edit-bio']",
            mobileXPath = "(//android.widget.Button)[2]")
    private Element editBioButton;

    @Locator(webXPath = "//p[contains(@class, 'graf--p')]",
            mobileXPath = "//android.widget.EditText")
    private Element editBioField;

    @Locator(webXPath = "//button[@data-action='save-bio']",
            mobileXPath = "(//android.widget.Button)[2]")
    private Element saveBioButton;

    @Locator(webXPath = "//p[@data-default-value='Add your bio']//following-sibling::div/a",
            mobileXPath = "(//android.view.View[@content-desc='Profile'])[2]/android.widget.TextView")
    private Element userPublicProfileLink;

    @MobileLocator(xpath = "//android.webkit.WebView")
    private Element mobileWebView;

    public void editBio(String bioText) {
        mobileWebView.waitUntilVisible().click();

        if (isCurrentPlatformWeb())
            editBioButton.clear();

        editBioButton.waitUntilClickable().click();

        editBioField.waitUntilVisible().typeIntoField(bioText);

        if (isCurrentPlatformMobile())
            new MobileElementFunction(getMobileDriver()).goBack();

        waitFor(2);

        saveBioButton.waitUntilClickable().click();

        waitFor(2);

        userPublicProfileLink.waitUntilClickable().click();
    }
}
