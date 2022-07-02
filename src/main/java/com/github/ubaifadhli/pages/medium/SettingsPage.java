package com.github.ubaifadhli.pages.medium;

import com.github.ubaifadhli.annotations.Locator;
import com.github.ubaifadhli.annotations.MobileLocator;
import com.github.ubaifadhli.pages.PageObject;
import com.github.ubaifadhli.util.CurrentThreadDriver;
import com.github.ubaifadhli.util.Element;
import com.github.ubaifadhli.util.MobileElementFunction;
import com.github.ubaifadhli.util.SwipeDirection;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class SettingsPage extends PageObject {
    @Locator(webXPath = "//button[@data-action='edit-bio']",
            mobileXPath = "(//android.widget.Button)[2]")
    private Element editBioButton;

    @Locator(webXPath = "//p[contains(@class, 'graf--p')]",
            mobileXPath = "//android.widget.EditText[@resource-id='editor_6']")
    private Element editBioField;

    @Locator(webXPath = "//button[@data-action='edit-username']",
            mobileXPath = "//android.widget.Button[preceding::android.view.View[@text='Username']]")
    private Element editUsernameButton;

    @Locator(webXPath = "//input[contains(@class, 'usernameInput')]",
            mobileXPath = "//android.view.View[@text='Username']/following-sibling::*/android.widget.EditText")
    private Element editUsernameField;

    @Locator(webXPath = "//td[contains(@class, 'profileUrl')]",
            mobileXPath = "//android.view.View[@text='URL']/following-sibling::*")
    private Element usernameURLText;

    @Locator(webXPath = "//button[contains(@class, 'saveUsername')]",
            mobileXPath = "//android.widget.Button[@text='Save']")
    private Element saveUsernameButton;

    @Locator(webXPath = "//button[@data-action='overlay-confirm']",
            mobileXPath = "//android.widget.Button[contains(@text, 'understand')]")
    private Element confirmSaveUsernameButton;

    @Locator(webXPath = "//button[@data-action='save-bio']",
            mobileXPath = "//android.widget.Button[@text='Save']")
    private Element saveBioButton;

    @Locator(webXPath = "//p[@data-default-value='Add your short bio']//following-sibling::div/a",
            mobileXPath = "(//android.view.View[@content-desc='Profile'])[2]/android.widget.TextView")
    private Element userPublicProfileLink;

    @MobileLocator(xpath = "//android.webkit.WebView")
    private Element mobileWebView;

    @Locator(webXPath = "//p[contains(@class, 'usernameSettingsItem')]",
            mobileXPath = "//android.widget.TextView[@text='Username is not available']")
    private Element usernameErrorText;

    public void editUsername(String newUsername) {
        mobileWebView.waitUntilVisible();

        if (isCurrentPlatformWeb()) {
            editUsernameButton.waitUntilClickable().click();
            editUsernameField.waitUntilClickable().clear();

        } else {
            MobileElementFunction mobileElementFunction = new MobileElementFunction(getMobileDriver());
            for (int i = 0; i < 3; i++) {
                mobileElementFunction.swipe(100, SwipeDirection.UP);
                waitFor(1);
            }

            while (!mobileElementFunction.isKeyboardShown()) {
                editUsernameButton.waitUntilClickable().click();
                waitFor(1);
            }

            editUsernameField.waitUntilVisible();
        }

        editUsernameField.typeIntoField(newUsername);


        if (isCurrentPlatformMobile()) {
            // Needed to trigger API call to validate new username.
            CurrentThreadDriver.getAndroidDriver().pressKey(new KeyEvent(AndroidKey.A));

            new MobileElementFunction(getMobileDriver()).goBack();
        }

        waitFor(2);


    }

    public void saveUsername() {
        saveUsernameButton.waitUntilClickable().click();

        confirmSaveUsernameButton.waitUntilClickable().click();

        waitFor(2);
    }

    public String getCurrentUsername() {
        if (isCurrentPlatformWeb())
            return editUsernameField.waitUntilVisible().asWebElement().getAttribute("value");
        else
            return editUsernameField.waitUntilVisible().getText();
    }

    public String getCurrentProfileURL() {
        return usernameURLText.waitUntilVisible().getText();
    }

    public void editBio(String bioText) {
        mobileWebView.waitUntilVisible().click();

        if (isCurrentPlatformWeb()) {
            editBioButton.waitUntilClickable().click();
            editBioField.waitUntilVisible().clear();

        } else {
            MobileElementFunction mobileElementFunction = new MobileElementFunction(getMobileDriver());

            while (!mobileElementFunction.isKeyboardShown()) {
                editBioButton.waitUntilClickable().click();
                waitFor(2);
            }

            editBioField.waitUntilVisible().click();
        }

        editBioField.typeIntoField(bioText);

        if (isCurrentPlatformMobile())
            new MobileElementFunction(getMobileDriver()).goBack();

        waitFor(2);

        saveBioButton.waitUntilClickable().click();

        waitFor(2);

        userPublicProfileLink.waitUntilClickable().click();
    }

    public String getUsernameErrorText() {
        return usernameErrorText.waitUntilVisible().getText();
    }
}
