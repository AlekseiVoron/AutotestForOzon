package autotests.pages;

import autotests.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.lang.Thread.sleep;

public class LogIn {
    private final By buttonLogIn = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/header/div[1]/div[4]/div[1]/div[1]");
    private final By startLogInButton = By.xpath("/html/body/div[1]/div/div[1]/div[3]/div[1]/div[2]/div[2]/div/button");
    private final By inputPhone = By.xpath("/html/body/div[3]/div/div/div/div/div/div/div/div/div[2]/label/div/input");
    private final By getPhoneCode = By.xpath("/html/body/div[3]/div/div/div/div/div/div/div/div/div[3]/button");
    public final String defaultText = "0Войти";

    public boolean logingIn(ChromeDriver chromeDriver, WebDriverWait waiting) throws InterruptedException {
        waiting.until(ExpectedConditions.presenceOfElementLocated(startLogInButton));
        chromeDriver.findElement(startLogInButton).click();
        waiting.until(ExpectedConditions.presenceOfElementLocated(inputPhone));
        chromeDriver.findElement(inputPhone).sendKeys(Config.PHONE_NUMBER);
        chromeDriver.findElement(getPhoneCode).click();
        sleep(40000);
        String isCabinet = chromeDriver.findElement(buttonLogIn).getAttribute("textContent");
        return !isCabinet.equals(defaultText);
    }
}
