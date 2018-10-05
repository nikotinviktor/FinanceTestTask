import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTest {

    protected static WebDriver webDriver;

    @BeforeAll
    public static void setUp() {

        System.setProperty("webdriver.chrome.driver", "/Users/heima/Downloads/ChromeDriver/chromedriver");
        ChromeOptions opt = new ChromeOptions();
        webDriver = new ChromeDriver(opt);

    }

    @AfterAll
    public static void tearDown() {

        webDriver.quit();

    }

}
