package autotests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShellWebDriver {
    public ChromeDriver chromeDriver;
    WebDriverWait webDriverWait;

    @Before
    public void openCite(){
        System.setProperty(Config.WEB_DRIVER, Config.PATH_TO_CHROME_DRIVER);
        chromeDriver = new ChromeDriver();
        chromeDriver.get(Config.START_URL);
        String title = chromeDriver.getTitle();
        Assert.assertEquals(title, Config.START_TITLE);

        webDriverWait = new WebDriverWait(chromeDriver, 60);

//        File folder = new File("target\\allure-results");
//        if (!folder.exists()) {
//            folder.mkdir();
//        }
    }

    @After
    public void closeCite(){
        chromeDriver.quit();
    }
}
