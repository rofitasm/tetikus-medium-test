import com.github.ubaifadhli.pages.medium.*;
import com.github.ubaifadhli.runners.TetikusBaseRunner;
import com.github.ubaifadhli.util.CurrentThreadDriver;
import com.github.ubaifadhli.util.DatetimeHelper;
import com.github.ubaifadhli.util.RandomGenerator;
import com.github.ubaifadhli.util.TetikusPropertiesReader;
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
    private MembershipPage membershipPage;
    private WriterPage writerPage;

    private FollowingPage followingPage;

    private ListDetailPage listDetailPage;

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

        homePage.createNewArticle();
        createArticlePage.fillAndPublishArticle(ARTICLE_TITLE);

        homePage.refreshProfilePage();

        assertThat(homePage.getFirstUserArticleTitle(), equalTo(ARTICLE_TITLE));
    }

    @Test
    public void createNewComment() {
        String COMMENT = "Try to comment " + DatetimeHelper.getCurrentDatetime();

        homePage.openPage();
        homePage.goToPublishedArticlePage();
        profilePage.clickUserArticleTitle();

        articlePage.createComment(COMMENT);

        assertThat(articlePage.getFirstCommentText(), equalTo(COMMENT));
    }

    @Test
    public void deleteArticle() {
        createNewArticle();

        String firstArticleTitle = profilePage.getFirstUserArticleTitle();

        profilePage.deleteArticle();

        String currentFirstArticleTitle = profilePage.getFirstUserArticleTitle();

        assertThat(firstArticleTitle, not(currentFirstArticleTitle));
    }

    @Test
    public void editBio() {
        String BIO = "Current bio " + DatetimeHelper.getCurrentDatetime();

        homePage.openPage();

        homePage.goToSettingsPage();
        homePage.goToAccountSettingsPage();

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
        homePage.openPage();

        homePage.goToListsPage();

        int currentArticleCount = listPage.getReadingListArticleCount();

        homePage.openPage();

        homePage.openHomeArticle();

        articlePage.clickAddToBookmarkButton();

        homePage.openPage();

        homePage.goToListsPage();

        int finalArticleCount = listPage.getReadingListArticleCount();

        assertThat(finalArticleCount, equalTo(currentArticleCount + 1));
    }

    @Test
    public void userChecksMembershipOffering() {
        homePage.openPage();

        homePage.goToMembershipPage();

        assertThat(membershipPage.getMonthlySubsPrice(), is(both(greaterThan(4.98)).and(lessThan(5.01))));
        assertThat(membershipPage.getAnnualSubsPrice(), is(both(greaterThan(49.98)).and(lessThan(50.01))));
    }

    @Test
    public void userEditsAnArticle() {
        String ARTICLE_TITLE = "Edited article " + DatetimeHelper.getCurrentDatetime();

        homePage.openPage();

        homePage.goToPublishedArticlePage();
        profilePage.editArticle();

        createArticlePage.fillAndRepublishArticle(ARTICLE_TITLE);

        homePage.refreshProfilePage();

        assertThat(homePage.getFirstUserArticleTitle(), equalTo(ARTICLE_TITLE));
    }

    @Test
    public void userChecksFollowingInProfile() {
        homePage.openPage();

        homePage.openHomeArticle();
        articlePage.goToWriterProfile();

        writerPage.followWriter();

        String followedWriter = writerPage.getWriterName();

        homePage.backToHomeFromArticleWriter();
        homePage.openFollowingPage();

        assertThat(followedWriter, in(followingPage.getFollowingWritersName()));
    }

    @Test
    public void userChangesUsername() {
        String NEW_USERNAME = "username" + RandomGenerator.generateNumberString();
        homePage.openPage();

        homePage.goToSettingsPage();
        homePage.goToAccountSettingsPage();

        settingsPage.editUsername(NEW_USERNAME);
        settingsPage.saveUsername();

        if (CurrentThreadDriver.isCurrentPlatformMobile())
            NEW_USERNAME += "a";

        assertThat(settingsPage.getCurrentUsername(), equalTo(NEW_USERNAME));
        assertThat(settingsPage.getCurrentProfileURL(), containsString(NEW_USERNAME));
    }

    @Test
    public void userChangesToMaximumBio() {
        String MAX_LIMIT_BIO = RandomGenerator.generateStringByLength(160);

        homePage.openPage();

        homePage.goToSettingsPage();
        homePage.goToAccountSettingsPage();

        settingsPage.editBio(MAX_LIMIT_BIO);

        assertThat(userPublicProfilePage.getUserBio(), equalTo(MAX_LIMIT_BIO));
    }

    @Test
    public void userDeletesAnArticleList() {
        homePage.openPage();

        homePage.goToListsPage();

        String previousSecondListName = listPage.getSecondListName();

        listPage.clickSecondList();

        listDetailPage.deleteList();

        homePage.refreshProfilePage();

        String currentSecondListName = listPage.getSecondListName();

        assertThat(currentSecondListName, not(previousSecondListName));
    }

    @Test
    public void userRemovesArticleFromList() {
        homePage.openPage();

        homePage.goToListsPage();

        int previousArticleCount = listPage.getReadingListArticleCount();

        listPage.clickReadingList();

        listDetailPage.removeFirstArticleFromList();

        homePage.goToListsPage();

        int currentArticleCount = listPage.getReadingListArticleCount();

        assertThat(currentArticleCount, equalTo(previousArticleCount - 1));
    }

    @Test
    public void userChecksFeedFromFollowedWriter() {
        homePage.openPage();
        homePage.waitFor(500);
    }

    @Test
    public void userUnfollowsWriter() {
        homePage.openPage();
        homePage.waitFor(500);
    }

    @Test
    public void userDeletesAComment() {
        homePage.openPage();
        homePage.waitFor(500);
    }

    @Test
    public void userChangesToExistingUsername() {
        String EXISTING_USERNAME = "john";
        homePage.openPage();

        homePage.goToSettingsPage();
        homePage.goToAccountSettingsPage();

        settingsPage.editUsername(EXISTING_USERNAME);

        assertThat(settingsPage.getUsernameErrorText(), equalTo("Username is not available"));
    }

    @Test
    public void userChecksFeedFromFollowingPublication() {
        homePage.openPage();
        homePage.waitFor(500);
    }
}
