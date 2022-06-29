package com.github.ubaifadhli.pages.medium;

import com.github.ubaifadhli.annotations.Locator;
import com.github.ubaifadhli.pages.PageObject;
import com.github.ubaifadhli.util.Element;

public class MembershipPage extends PageObject {
    @Locator(webXPath = "//h2[contains(text(), 'Monthly')]//following-sibling::p",
            mobileID = "com.medium.reader:id/subs_upgrade_monthly_title")
    private Element monthlySubsTitle;

    @Locator(webXPath = "//h2[contains(text(), 'Annual')]//following-sibling::p",
            mobileID = "com.medium.reader:id/subs_upgrade_yearly_title")
    private Element annualSubsTitle;

    public double getMonthlySubsPrice() {
        String priceNumber = monthlySubsTitle.waitUntilVisible().getText().split(" ")[0].replaceAll("[$,]", "");
        return Double.parseDouble(priceNumber);
    }

    public double getAnnualSubsPrice() {
        String priceNumber = annualSubsTitle.waitUntilVisible().getText().split(" ")[0].replaceAll("[$,]", "");
        return Double.parseDouble(priceNumber);
    }
}
