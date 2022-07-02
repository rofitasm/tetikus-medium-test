package com.github.ubaifadhli.pages.medium;

import com.github.ubaifadhli.annotations.Locator;
import com.github.ubaifadhli.annotations.MobileLocator;
import com.github.ubaifadhli.pages.PageObject;
import com.github.ubaifadhli.util.Element;

public class LoginPage extends PageObject {
    @Locator(webID = "username_or_email",
            mobileXPath = "//android.widget.EditText[@resource-id='username_or_email']")
    private Element twitterUsernameField;

    @Locator(webID = "password",
            mobileXPath = "//android.widget.EditText[@resource-id='password']")
    private Element twitterPasswordField;

    @Locator(webXPath = "//input[@value='Sign In']",
            mobileXPath = "//android.widget.Button[@resource-id='allow']")
    private Element twitterSignInButton;

    @MobileLocator(id = "com.medium.reader:id/title")
    private Element mobileHomeTitle;

    @Locator(webXPath = "//span[contains(text(), 'did not match')]",
            mobileXPath = "//android.widget.TextView[contains(@text, 'did not match')]")
    private Element loginErrorText;

    public void fillTwitterLoginCredentials(String email, String password) {
        twitterUsernameField.waitUntilVisible().typeIntoField(email);
        twitterPasswordField.waitUntilVisible().typeIntoField(password);

        twitterSignInButton.waitUntilClickable().click();
    }

    public void waitForHomeTitle() {
        mobileHomeTitle.waitUntilVisible();
    }

    public String getLoginErrorText() {
        return loginErrorText.waitUntilVisible().getText();
    }
}
