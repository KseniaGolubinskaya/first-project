package scooter.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderPage {
    private final WebDriver webDriver;

    /**
     * Поле "Имя"
     */
    private final By nameField = By.xpath("//input[@placeholder = '* Имя']");
    /**
     * Поле "Фамилия"
     */
    private final By surnameField = By.xpath("//input[@placeholder = '* Фамилия']");
    /**
     * Поле "Адрес: куда привезти заказ"
     */
    private final By addressField = By.xpath("//input[@placeholder = '* Адрес: куда привезти заказ']");
    /**
     * Поле "Станция метро"
     */
    private final By stationField = By.xpath("//input[@placeholder = '* Станция метро']");
    /**
     * Поле "Телефон: на него позвонит курьер"
     */
    private final By phoneNumberField = By.xpath("//input[@placeholder = '* Телефон: на него позвонит курьер']");
    /**
     * Кнопка "Далее"
     */
    private final By nextButton = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");

    public OrderPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    /**
     * Заполнение поля "Имя"
     */
    public void setName(String name) {
        webDriver.findElement(nameField).sendKeys(name);
    }
    /**
     * Заполнение поля "Фамилия"
     */
    public void setSurname(String surname) {
        webDriver.findElement(surnameField).sendKeys(surname);
    }
    /**
     * Заполнение поля "Адрес: куда привезти заказ"
     */
    public void setAddress(String address) {
        webDriver.findElement(addressField).sendKeys(address);
    }
    /**
     * Заполнение поля "Станция метро"
     */
    public void setStation(String station) {
        webDriver.findElement(stationField).click();
        webDriver
            .findElement(By.xpath("//div[text()='" + station + "']"))
            .click();
    }
    /**
     * Заполнение поля "Телефон: на него позвонит курьер"
     */
    public void setPhoneNumber(String phoneNumber) {
        webDriver.findElement(phoneNumberField).sendKeys(phoneNumber);
    }

    /**
     * Клик по кнопке "Далее"
     */
    public void clickOnNextButton() {
        webDriver.findElement(nextButton).click();
    }
    /**
     * Заполнение формы на странице "Для кого самокат"
     */
    public void setScooterFor(String name, String surname, String address, String station, String phoneNumber) {
        setName(name);
        setSurname(surname);
        setAddress(address);
        setStation(station);
        setPhoneNumber(phoneNumber);
        clickOnNextButton();
    }
}
