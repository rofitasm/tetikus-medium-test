package com.github.ubaifadhli.pages.medium;

import com.github.ubaifadhli.annotations.Locator;
import com.github.ubaifadhli.annotations.MobileLocator;
import com.github.ubaifadhli.pages.PageObject;
import com.github.ubaifadhli.util.Element;

public class UserPublicProfilePage extends PageObject {
    @Locator(webXPath = "//h2//following-sibling::div/p",
            mobileID = "com.medium.reader:id/tvBio")
    private Element userBio;

    @MobileLocator(accessibilityID = "About")
    private Element aboutSection;

    public String getUserBio() {
        aboutSection.waitUntilClickable().click();

        return userBio.waitUntilVisible().getText();
    }
}
