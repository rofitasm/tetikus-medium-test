import com.github.ubaifadhli.pages.medium.*;
import com.github.ubaifadhli.runners.TetikusBaseRunner;
import com.github.ubaifadhli.util.PropertiesReader;
import com.github.ubaifadhli.util.RandomGenerator;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

public class TetikusCoreRunner extends TetikusBaseRunner {
    private HomePage homePage;
    private LoginPage loginPage;
    private CreateArticlePage createArticlePage;
    private ArticlePage articlePage;
    private SettingsPage settingsPage;
    private ListPage listPage;
    private UserPublicProfilePage userPublicProfilePage;

    @Test
    public void login() {
        PropertiesReader reader = new PropertiesReader("application.properties");

        String username = reader.getPropertyAsString("login.twitter.username");
        String password = reader.getPropertyAsString("login.twitter.password");

        homePage.openPage();
        homePage.goToTwitterLoginPage();

        loginPage.fillTwitterLoginCredentials(username, password);
    }

    @Test
    public void searchForArticle() {
        String SEARCH_KEYWORD = "spring boot";
        String EXPECTED_FIRST_ARTICLE_NAME = "How to scale Microservices with Message Queues, Spring Boot, and Kubernetes";

        login();

        homePage.searchForArticle(SEARCH_KEYWORD);

        assertThat(homePage.getFirstArticleTitle(), equalTo(EXPECTED_FIRST_ARTICLE_NAME));
    }

    @Test
    public void createNewArticle() {
        String ARTICLE_TITLE = "Test article " + RandomGenerator.generateString();

        login();

        homePage.createNewArticle();
        createArticlePage.fillAndPublishArticle(ARTICLE_TITLE);

        homePage.refreshProfilePage();

        assertThat(homePage.getFirstUserArticleTitle(), equalTo(ARTICLE_TITLE));
    }

    @Test
    public void createNewComment() {
        String COMMENT = "Try to comment " + RandomGenerator.generateString();

        login();

        homePage.openFirstUserArticle();

        articlePage.createComment(COMMENT);

        assertThat(articlePage.getFirstCommentText(), equalTo(COMMENT));
    }

    @Test
    public void deleteArticle() {
        createNewArticle();

        String firstArticleTitle = homePage.getFirstUserArticleTitle();

        homePage.deleteArticle();

        String currentFirstArticleTitle = homePage.getFirstUserArticleTitle();

        assertThat(firstArticleTitle, not(currentFirstArticleTitle));
    }

    @Test
    public void editBio() {
        String BIO = "Current bio " + RandomGenerator.generateString();

        login();

        homePage.goToSettingsPage();

        settingsPage.editBio(BIO);

        assertThat(userPublicProfilePage.getUserBio(), equalTo(BIO));
    }

    @Test
    public void createNewList() {
        String NEW_LIST_NAME = "List name " + RandomGenerator.generateString();

        login();

        homePage.goToListsPage();

        listPage.createNewList(NEW_LIST_NAME);

        homePage.goToListsPage();

        assertThat(listPage.getSecondListName(), equalTo(NEW_LIST_NAME));
    }

    @Test
    public void addArticleToList() {
        login();

        homePage.goToListsPage();

        int currentArticleCount = listPage.getFirstListArticleCount();

        homePage.openPage();

        homePage.openFirstHomeArticle();

        articlePage.clickAddToBookmarkButton();

        homePage.goToListsPage();

        int finalArticleCount = listPage.getFirstListArticleCount();

        assertThat(finalArticleCount, equalTo(currentArticleCount  + 1));
    }
}