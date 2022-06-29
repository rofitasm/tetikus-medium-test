package com.github.ubaifadhli.pages.medium;

import com.github.ubaifadhli.annotations.Locator;
import com.github.ubaifadhli.pages.PageObject;
import com.github.ubaifadhli.util.Element;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class FollowingPage extends PageObject {
    @Locator(webXPath = "//div[preceding-sibling::h2]//h2",
            mobileXPath = "//android.widget.TextView[@resource-id='com.medium.reader:id/creator_name']")
    private Element writersNameText;

    public List<String> getFollowingWritersName() {
        writersNameText.waitUntilVisible();

        return writersNameText.asElements()
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
