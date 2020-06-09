package autotests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static java.lang.Thread.sleep;

public class Juicers {
    private final By electrics = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/header/div[2]/div/ul/li[7]/a");
    private final By households = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/div[3]/div[3]/div/div/div/div/div[6]");
    private final By kitchen = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/div[3]/div/div[1]/aside/div[2]/a");
    private final By juicers = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/div[3]/div[2]/div[1]/div/aside/section/main" +
            "/div[2]/div[2]/div[6]/div/a");

    // price and power
    private final By range = By.cssSelector("input[class=\"ui-av9 ui-av4 ui-c7\"]");

    public void openingCatalog(ChromeDriver chromeDriver, WebDriverWait waiting) throws InterruptedException {

        chromeDriver.findElement(electrics).click();
        waiting.until(ExpectedConditions.presenceOfElementLocated(households));

        chromeDriver.findElement(households).click();
        waiting.until(ExpectedConditions.presenceOfElementLocated(kitchen));

        sleep(3000);
        chromeDriver.findElement(kitchen).click();
        waiting.until(ExpectedConditions.presenceOfElementLocated(juicers));

        chromeDriver.findElement(juicers).click();
    }

    public boolean changePrices(ChromeDriver chromeDriver, WebDriverWait waiting) throws InterruptedException {
        waiting.until(ExpectedConditions.presenceOfElementLocated(range));
        ((JavascriptExecutor) chromeDriver).executeScript("arguments[0].scrollIntoView();"
                , chromeDriver.findElements(range).get(0));
        sleep(1000);
        WebElement minRange = chromeDriver.findElements(range).get(0);
        sleep(1000);
        minRange.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        sleep(100);
        minRange.sendKeys("3000");  // min
        sleep(2000);
        WebElement maxRange = chromeDriver.findElements(range).get(1);
        maxRange.click();
        maxRange.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        sleep(1000);
        maxRange.sendKeys("4000", Keys.ENTER);  // max
        sleep(2000);
        return checkJuicers(chromeDriver, waiting);
    }

    public void sortByPriceChooseJucier(ChromeDriver chromeDriver, WebDriverWait waiting) throws InterruptedException {
        By sorterBy = By.cssSelector("input[class=\"ui-a1f3\"]");
        waiting.until(ExpectedConditions.presenceOfElementLocated(sorterBy));
        WebElement sorterWeb = chromeDriver.findElement(sorterBy);
        sorterWeb.click();
        sorterWeb.sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER);
        sleep(2000);
        List<WebElement> lst = chromeDriver.findElements(By.cssSelector("div[class=\"ui-b ui-f8\"]"));
        WebElement firstJuicer = lst.get(1);
        waiting.until(ExpectedConditions.elementToBeClickable(firstJuicer));
        firstJuicer.click();
        sleep(2000);
    }

    public boolean multipleJuciers(ChromeDriver chromeDriver, WebDriverWait waiting) throws InterruptedException {
        By openBusket = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/header/div[1]/div[4]/a[2]");
        waiting.until(ExpectedConditions.presenceOfElementLocated(openBusket));
        List<WebElement> lst = chromeDriver.findElements(openBusket);
        lst.get(lst.size() - 1).click();
        By priceBy = By.xpath("//*[@id=\"__ozon\"]/div/div[1]/div/div/div[3]/div[4]/div[2]/div/section/div[2]");
        waiting.until(ExpectedConditions.presenceOfElementLocated(priceBy));
        WebElement priceWeb = chromeDriver.findElement(priceBy);
        String priceStr = priceWeb.getAttribute("innerText");
        int price = parser(priceStr.substring(priceStr.length() - 10));
        WebElement changeCount = chromeDriver.findElement(By.className("ui-a1f3"));
        changeCount.click();
        changeCount.sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER);
        sleep(3000);
        priceStr = priceWeb.getAttribute("innerText");
        int priceNow = parser(priceStr.substring(priceStr.length() - 10));
        String count = chromeDriver.findElement(By.xpath("//*[@id=\"__ozon\"]/div/div[1]/div/div/div[3]/div[4]" +
                "/div[1]/div[1]/div/div[2]/div[3]/div[4]/div/div[1]/div/div[1]/div/div/div"))
                .getAttribute("innerText");
        int counter = parser(count);
        return price * counter == priceNow;
    }

    public void sortByPower(ChromeDriver chromeDriver, WebDriverWait waiting){
        WebElement minPower = chromeDriver.findElements(range).get(2);
        minPower.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        minPower.sendKeys("1000", Keys.ENTER);  // >= 1000
    }

    private int parser(String inputString){
        StringBuilder sB1 = new StringBuilder();
        for (int i = 0; i < inputString.length(); i++) {
            if (Character.isDigit(inputString.charAt(i))) {
                sB1.append(inputString.charAt(i));
            }
        }
        return Integer.parseInt(sB1.toString());
    }

    public boolean checkJuicers(ChromeDriver chromeDriver, WebDriverWait waiting) {
        List<WebElement> juicers = chromeDriver.findElementsByCssSelector("div[class=\"a3s3\"]");
        boolean flag = true;
        for (int i = 0; i < juicers.size() / 5; i++) {
            String cost = juicers.get(i).getAttribute("textContent");
            StringBuilder price = new StringBuilder();
            cost = cost.substring(cost.indexOf("₽") - 6, cost.indexOf("₽"));
            for (int j = 0; j < cost.length(); j++) {
                if (Character.isDigit(cost.charAt(j))) {
                    price.append(cost.charAt(j));
                }
            }
            int intPrice = Integer.parseInt(price.toString());
            if (intPrice < 3000 || intPrice > 4000) {
                flag = false;
            }
        }
        return flag;
    }
}
