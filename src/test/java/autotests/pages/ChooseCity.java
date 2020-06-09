package autotests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.lang.Thread.sleep;

public class ChooseCity {
    By currentCity;
    WebElement currentCityWeb;
    By boxInput;
    WebElement boxInputWeb;
    By firstRowInCities;
    By deliveryPoints;
    By cityToDelivery;

    public boolean chooseCity(ChromeDriver chromeDriver, WebDriverWait waiting) throws InterruptedException {
        currentCity = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/div[1]/div/button/span");
        waiting.until(ExpectedConditions.presenceOfElementLocated(currentCity));
        currentCityWeb = chromeDriver.findElement(currentCity);
        currentCityWeb.click();
        boxInput = By.xpath("//*[@id=\"__ozon\"]/div/div[2]/div/div/div/div/div/label/div/input");
        waiting.until(ExpectedConditions.presenceOfElementLocated(boxInput));
        boxInputWeb = chromeDriver.findElement(boxInput);
        boxInputWeb.click();
        boxInputWeb.sendKeys("Вольск");
        firstRowInCities = By.xpath("//*[@id=\"__ozon\"]/div/div[2]/div/div/div/div/ul/li[1]/a");
        waiting.until(ExpectedConditions.presenceOfElementLocated(firstRowInCities));
        waiting.until(ExpectedConditions.attributeToBe(chromeDriver.findElement(firstRowInCities),
                "innerText",
                "Вольск, Саратовская область"));
        boxInputWeb.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
        deliveryPoints = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/div[1]/div/ul/li[5]/div/a");
        sleep(2000);
        waiting.until(ExpectedConditions.elementToBeClickable(deliveryPoints));
        chromeDriver.findElement(deliveryPoints).click();
        cityToDelivery = By.className("city-name");
        waiting.until(ExpectedConditions.presenceOfElementLocated(cityToDelivery));
        return chromeDriver.findElement(cityToDelivery).getAttribute("innerText")
                .equals("Вольск");
    }
}
