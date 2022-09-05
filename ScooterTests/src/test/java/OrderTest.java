import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import scooter.pageobjects.MainPage;
import scooter.pageobjects.OrderPage;
import scooter.pageobjects.RentPage;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@RunWith(Parameterized.class)
public class OrderTest {
    private WebDriver webDriver;
    private final String name;
    private final String surname;
    private final String address;
    private final String station;
    private final String phoneNumber;
    private final int deliveryDateOffset;
    private final String rentTerm;
    private final String scooterColor;
    private final String courierComment;

    public OrderTest(String name, String surname, String address, String station, String phoneNumber,
                     int deliveryDateOffset, String rentTerm, String scooterColor, String courierComment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.station = station;
        this.phoneNumber = phoneNumber;
        this.deliveryDateOffset = deliveryDateOffset;
        this.rentTerm = rentTerm;
        this.scooterColor = scooterColor;
        this.courierComment = courierComment;
    }

    @Parameterized.Parameters(name = "{index}. Тест: {0} {1}")
    public static Object[][] GetQuestionsAndAnswers() {
        return new Object[][]{
            {
                "Алла",
                "Пугачёва",
                "ул. Усачёва, 15",
                "Спортивная",
                "+79998887766",
                1,
                "сутки",
                "чёрный жемчуг",
                "позвоните в домофон"
            },
            {
                "Джанис",
                "Джоплин",
                "ул. Маросейка, 13",
                "Цветной бульвар",
                "+79998886677",
                4,
                "трое суток",
                "серая безысходность",
                "привезите без повреждений"
            }
        };
    }

    @Before
    public void Before() {
        webDriver = getChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.get("https://qa-scooter.praktikum-services.ru");
    }

    @Test
    public void testCreateOrder() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickCookieButton();
        mainPage.clickOrderButtonTop();

        OrderPage orderPage = new OrderPage(webDriver);
        orderPage.setScooterFor(name, surname, address, station, phoneNumber);

        RentPage rentPage = new RentPage(webDriver);
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, deliveryDateOffset);
        rentPage.setAboutRent(c.getTime(), rentTerm, scooterColor, courierComment);

        boolean orderCreatedSuccessfully = rentPage.orderCreatedSuccessfully();

        Assert.assertTrue("Заказ не создался", orderCreatedSuccessfully);
    }

    @After
    public void teardown() {
//        webDriver.quit();
    }

    private WebDriver getChromeDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    private WebDriver getFireFoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }
}
