package com.github.ubaifadhli.pages.medium;

import com.github.ubaifadhli.annotations.Locator;
import com.github.ubaifadhli.annotations.MobileLocator;
import com.github.ubaifadhli.pages.PageObject;
import com.github.ubaifadhli.util.Element;

public class MediumLoginPage extends PageObject {
    // Should have had the same locator except WebView problem with UIAutomator2 just came to ruin it.
    @Locator(webID = "username_or_email",
            mobileXPath = "//android.widget.EditText[@resource-id='username_or_email']")
    private Element twitterUsernameField;

    // Should have had the same locator except WebView problem with UIAutomator2 just came to ruin it.
    @Locator(webID = "password",
            mobileXPath = "//android.widget.EditText[@resource-id='password']")
    private Element twitterPasswordField;

    @Locator(webXPath = "//input[@value='Sign In']",
            mobileXPath = "//android.widget.Button[@resource-id='allow']")
    private Element twitterSignInButton;

    @MobileLocator(id = "com.medium.reader:id/tw__web_view")
    private Element mobileTwitterWebView;

    @MobileLocator(id = "com.medium.reader:id/title")
    private Element mobileHomeTitle;

    public void fillTwitterLoginCredentials(String email, String password) {
        mobileTwitterWebView.waitUntilVisible().click();

        twitterUsernameField.waitUntilVisible().typeIntoField(email);
        twitterPasswordField.waitUntilVisible().typeIntoField(password);

        twitterSignInButton.waitUntilClickable().click();

        mobileHomeTitle.waitUntilVisible();
    }
}
