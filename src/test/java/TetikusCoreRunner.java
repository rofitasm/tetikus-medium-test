import com.github.ubaifadhli.pages.medium.*;
import com.github.ubaifadhli.runners.TetikusBaseRunner;
import com.github.ubaifadhli.util.*;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TetikusCoreRunner extends TetikusBaseRunner {
    private HomePage homePage;
    private LoginPage loginPage;

    @Test
    public void login() {
        String username = TetikusPropertiesReader.getPropertyAsString("login.twitter.username");
        String password = TetikusPropertiesReader.getPropertyAsString("login.twitter.password");

        homePage.openPage();

        homePage.goToTwitterLoginPage();

        loginPage.fillTwitterLoginCredentials(username, password);
        loginPage.waitForHomeTitle();
    }
    @Test
    public void searchForArticle() {
        String SEARCH_KEYWORD = "spring boot";
        String EXPECTED_FIRST_ARTICLE_NAME = "How to scale Microservices with Message Queues, Spring Boot, and Kubernetes";

        homePage.openPage();

        homePage.searchForArticle(SEARCH_KEYWORD);

        assertThat(homePage.getFirstArticleTitle(), equalTo(EXPECTED_FIRST_ARTICLE_NAME));
    }
}
