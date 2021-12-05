import com.github.ubaifadhli.pages.MediumPageObject;
import com.github.ubaifadhli.runner.TetikusBaseRunner;
import org.testng.annotations.Test;

public class TetikusCoreRunner extends TetikusBaseRunner {
    private MediumPageObject mediumPageObject;

    @Test
    public void firstTest() {
        mediumPageObject.openPage();
        mediumPageObject.goToTwitterLoginPage();
        mediumPageObject.fillGoogleLoginCredentials("", "");
    }
}

