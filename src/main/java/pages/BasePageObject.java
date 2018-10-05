package pages;

import org.openqa.selenium.WebDriver;

public class BasePageObject {

    protected WebDriver webDriver;

    public BasePageObject(WebDriver webDriver) {

        this.webDriver = webDriver;

    }
}
