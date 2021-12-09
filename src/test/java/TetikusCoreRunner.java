import com.github.ubaifadhli.pages.medium.MediumHomePage;
import com.github.ubaifadhli.pages.medium.MediumLoginPage;
import com.github.ubaifadhli.runners.TetikusBaseRunner;
import org.testng.annotations.Test;

public class TetikusCoreRunner extends TetikusBaseRunner {
    private MediumHomePage homePage;
    private MediumLoginPage loginPage;

    @Test
    public void login() {
        homePage.openPage();
        homePage.goToTwitterLoginPage();

        loginPage.fillTwitterLoginCredentials("_chz24", ";I_.~_AZl9FyN>#~Yp");
    }

    @Test
    public void searchForArticle() {
        String SEARCH_KEYWORD = "spring boot";
        String EXPECTED_FIRST_ARTICLE_NAME = "How to scale Microservices with Message Queues, Spring Boot, and Kubernetes";

        login();

    }
}