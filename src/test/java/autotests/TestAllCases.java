package autotests;

import autotests.pages.ChooseCity;
import autotests.pages.Juicers;
import autotests.pages.LogIn;
import org.junit.Assert;
import org.junit.Test;

public class TestAllCases extends ShellWebDriver {
    @Test
    public void loginTest() throws InterruptedException {
        boolean result = (new LogIn()).logingIn(chromeDriver, webDriverWait);
        Assert.assertTrue(result);
    }

    @Test
    public void chooseCityTest() throws InterruptedException {
        new LogIn().logingIn(chromeDriver, webDriverWait);
        boolean chooseCity = (new ChooseCity()).chooseCity(chromeDriver, webDriverWait);
        Assert.assertTrue(chooseCity);
    }

    @Test
    public void cheapestJuicerTest() throws InterruptedException {
        Juicers juicers = new Juicers();
        juicers.openingCatalog(chromeDriver, webDriverWait);
        boolean checkJuicerOnPrice = juicers.changePrices(chromeDriver, webDriverWait);
        juicers.sortByPriceChooseJucier(chromeDriver, webDriverWait);
        boolean checkPrice = juicers.multipleJuciers(chromeDriver, webDriverWait);
        Assert.assertTrue(checkJuicerOnPrice && checkPrice);
    }

    @Test
    public  void powerfulJuicerTest() throws InterruptedException {
        Juicers juicers = new Juicers();
        juicers.openingCatalog(chromeDriver, webDriverWait);
        boolean checkJuicerOnPrice = juicers.changePrices(chromeDriver, webDriverWait);
        juicers.sortByPower(chromeDriver, webDriverWait);
        juicers.sortByPriceChooseJucier(chromeDriver, webDriverWait);
        boolean checkPrice = juicers.multipleJuciers(chromeDriver, webDriverWait);
        Assert.assertTrue(checkJuicerOnPrice && checkPrice);
    }
}
