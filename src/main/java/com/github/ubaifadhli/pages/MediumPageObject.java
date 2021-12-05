package com.github.ubaifadhli.pages;

import com.github.ubaifadhli.annotations.BaseURL;
import com.github.ubaifadhli.annotations.WebLocator;
import com.github.ubaifadhli.page.PageObject;
import com.github.ubaifadhli.util.Element;

@BaseURL("https://www.medium.com")
public class MediumPageObject extends PageObject {
    @WebLocator(xpath = "//a[text()='Sign In']")
    private Element navbarSignInButton;

    @WebLocator(xpath = "//div[text()='Sign in with Twitter']")
    private Element signInWithTwitterButton;

    @WebLocator(id = "username_or_email")
    private Element twitterUsernameField;

    @WebLocator(id = "password")
    private Element twitterPasswordField;

    @WebLocator(xpath = "//input[@value='Sign In']")
    private Element twitterSignInButton;

    public void goToTwitterLoginPage() {
        navbarSignInButton.waitUntilClickable().click();
        signInWithTwitterButton.waitUntilClickable().click();
    }

    public void fillGoogleLoginCredentials(String email, String password) {
        waitFor(2);

        twitterUsernameField.waitUntilVisible().typeIntoField(email);
        twitterPasswordField.waitUntilVisible().typeIntoField(password);

        twitterSignInButton.waitUntilClickable().click();

        waitFor(2);
    }
}
