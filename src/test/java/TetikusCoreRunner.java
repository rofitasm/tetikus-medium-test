import com.github.ubaifadhli.pages.medium.*;
import com.github.ubaifadhli.runners.TetikusBaseRunner;
import com.github.ubaifadhli.util.*;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TetikusCoreRunner extends TetikusBaseRunner {
    private HomePage homePage;
    private LoginPage loginPage;
    private CreateArticlePage createArticlePage;
    private ArticlePage articlePage;
    private SettingsPage settingsPage;
    private ListPage listPage;
    private UserPublicProfilePage userPublicProfilePage;
    private ProfilePage profilePage;

    @Test
    public void login() {
        String username = TetikusPropertiesReader.getPropertyAsString("login.twitter.username");
        String password = TetikusPropertiesReader.getPropertyAsString("login.twitter.password");

        homePage.openPage();

        homePage.goToTwitterLoginPage();

        loginPage.fillTwitterLoginCredentials(username, password);
    }

    @Test
    public void searchForArticle() {
        String SEARCH_KEYWORD = "spring boot";
        String EXPECTED_FIRST_ARTICLE_NAME = "How to scale Microservices with Message Queues, Spring Boot, and Kubernetes";

        homePage.openPage();

        homePage.searchForArticle(SEARCH_KEYWORD);

        assertThat(homePage.getFirstArticleTitle(), equalTo(EXPECTED_FIRST_ARTICLE_NAME));
    }

    @Test
    public void createNewArticle() {
        String ARTICLE_TITLE = "Test article " + DatetimeHelper.getCurrentDatetime();

        homePage.openPage();

//        homePage.waitFor(600);

        homePage.createNewArticle();
        createArticlePage.fillAndPublishArticle(ARTICLE_TITLE);

        homePage.refreshProfilePage();

        assertThat(homePage.getFirstUserArticleTitle(), equalTo(ARTICLE_TITLE));
    }

    @Test
    public void createNewComment() {
        String COMMENT = "Try to comment " + DatetimeHelper.getCurrentDatetime();

        homePage.openPage();

//        homePage.waitFor(600);

//        homePage.openFirstUserArticle();
        homePage.goToPublishedArticlePage();
        profilePage.clickUserArticleTitle();

        articlePage.createComment(COMMENT);

        assertThat(articlePage.getFirstCommentText(), equalTo(COMMENT));
    }

    @Test
    public void deleteArticle() {
        createNewArticle();

        String firstArticleTitle = profilePage.getFirstUserArticleTitle();

        homePage.deleteArticle();

        String currentFirstArticleTitle = profilePage.getFirstUserArticleTitle();

        assertThat(firstArticleTitle, not(currentFirstArticleTitle));
    }

    @Test
    public void editBio() {
        String BIO = "Current bio " + DatetimeHelper.getCurrentDatetime();

//        homePage.waitFor(600);

        homePage.openPage();

        homePage.goToSettingsPage();

        settingsPage.editBio(BIO);

        assertThat(userPublicProfilePage.getUserBio(), equalTo(BIO));
    }

    @Test
    public void createNewList() {
        String NEW_LIST_NAME = "List name " + DatetimeHelper.getCurrentDatetime();

        homePage.openPage();

        homePage.goToListsPage();

        listPage.createNewList(NEW_LIST_NAME);

        homePage.goToListsPage();

        assertThat(listPage.getSecondListName(), equalTo(NEW_LIST_NAME));
    }

    @Test
    public void addArticleToList() {
//        homePage.waitFor(600);
        homePage.openPage();

        homePage.goToListsPage();

        int currentArticleCount = listPage.getFirstListArticleCount();

        homePage.openPage();

        homePage.openHomeArticle();

        articlePage.clickAddToBookmarkButton();

        homePage.openPage();

        homePage.goToListsPage();

        int finalArticleCount = listPage.getFirstListArticleCount();

        assertThat(finalArticleCount, equalTo(currentArticleCount + 1));
    }
}
