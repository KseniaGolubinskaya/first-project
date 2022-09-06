package scooter.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {
    private final WebDriver webDriver;

    /**
     * Блок "Вопросы о важном"
     */
    private final By faqHeader = By.className("Home_FourPart__1uthg");
    /**
     * Префикс локатора вопроса
     */
    private final String questionDivPrefix = "accordion__heading-";
    /**
     * Кнопка "да все привыкли"
     */
    private final By cookieButton = By.id("rcc-confirm-button");
    /**
     * Кнопка "Заказать в верхней части страницы"
     */
    private final By orderTopButton = By.className("Button_Button__ra12g");
    /**
     * Кнопка "Заказать в нижней части страницы"
     */
    private final By orderDownButton = By.className("Home_FourPart__1uthg");

    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    /**
     * Скролл до блока "Вопросы о важном"
     */
    public void scrollToQuestion(int questionNumber) {
        WebElement headerSection = webDriver.findElement(By.id(questionDivPrefix + questionNumber));
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].scrollIntoView();", headerSection);
    }
    /**
     * Получение текста каждого вопроса
     */
    public String getQuestionHeaderText(int questionNumber) {
        WebElement questionHeader = webDriver.findElement(By.id(questionDivPrefix + questionNumber));
        return questionHeader.getText();
    }
    /**
     * Получение текста каждого ответа
     */
    public String getAnswerHeaderText(int answerNumber) {
        WebElement questionHeader = webDriver.findElement(By.id("accordion__panel-" + answerNumber));
        return questionHeader.getText();
    }
    /**
     * Клик по вопросу номер questionNumber.
     */
    public void clickQuestionArrow(int questionNumber) {
        WebElement questionHeader = webDriver.findElement(By.id(questionDivPrefix + questionNumber));
        questionHeader.click();
    }
    /**
     * Клик по кнопке "да все привыкли"
     */
    public void clickCookieButton() {
        webDriver.findElement(cookieButton).click();
    }
    /**
     * Клик по кнопке "Заказать" в верхней части страницы
     */
    public void clickOrderButtonTop() {
        webDriver.findElement(orderTopButton).click();
    }
    /**
     * Клик по кнопке "Заказать" в нижней части страницы
     */
    public void clickOrderButtonDown() {
        webDriver.findElement(orderDownButton).click();
    }
}
