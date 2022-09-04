package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RentPage {
    private final WebDriver webDriver;

    /**
     * Поле "Когда привезти самокат"
     */
    private final By dateField = By.xpath("//input[@placeholder = '* Когда привезти самокат']");

    /**
    * Поле "Срок аренды"
    */
    private final By rentTermField = By.xpath("//div[text() = '* Срок аренды']");

    /**
     * Поле "Комментарий для курьера"
     */
    private final By commentForCourierField = By.xpath("//input[@placeholder = 'Комментарий для курьера']");

    /**
     * Кнопка "Заказать"
     */
    private final By buttonOrderRent = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");

    /**
     * Кнопка "Да" во всплывающем окне "Хотите оформить заказ?"
     */
    private final By buttonYes = By.xpath("//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM' and text() = 'Да']");

    private final By orderStatusDiv = By.xpath("//div[@class='Order_ModalHeader__3FDaJ' and contains(text(), 'Заказ оформлен')]");

    public RentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    /**
     * Выбор даты из календаря в поле "Когда привезти самокат"
     */
    public void setDeliveryDate(Date dateToSet) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
        webDriver.findElement(dateField).sendKeys(dateFormatter.format(dateToSet));
//        webDriver.findElement(By.className("Order_Header__BZXOb")).click();
    }

    /**
     * Клик по полю "Срок аренды"
     */
    public void setRentTerm(String rentTermValue) {
        webDriver.findElement(rentTermField).click();
        webDriver
            .findElement(By.xpath("//div[text() = '" + rentTermValue + "']"))
            .click();
    }

    /**
     * Клик по чекбоксу в поле "Цвет самоката"
     */
    public void selectScooterColor(String colorValue) {
        webDriver
            .findElement(By.xpath("//*[contains(text(), '" + colorValue + "')]"))
            .click();
    }


    public void setCourierComment(String comment) {
        webDriver.findElement(commentForCourierField).sendKeys(comment);
    }

    public void clickOrderButton() {
        webDriver.findElement(buttonOrderRent).click();
    }

    public void clickConfirm() {
        webDriver.findElement(buttonYes).click();
    }

    public boolean orderCreatedSuccessfully() {
        return webDriver.findElements(orderStatusDiv).size() == 1;
    }
}


