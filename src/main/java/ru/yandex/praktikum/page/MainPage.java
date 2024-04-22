package ru.yandex.praktikum.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static java.lang.String.format;

public class MainPage {
    private final WebDriver webDriver;
    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    private final By createOrderButtonTopLocator =
            By.xpath("//div[contains(@class, 'Header')]//button[text()='Заказать']");
    private final By createOrderButtonLowerLocator =
            By.xpath("//div[contains(@class, 'Home_FinishButton__1_cWm')]/button[contains(@class, 'Button_Middle__1CSJM')]");
    private final By cookiesButtonLocator = By.id("rcc-confirm-button");
    private final String questionLocator = "accordion__heading-%s";
    private final String answerLocator = "//div[contains(@id, 'accordion__panel')][.='%s']";

    public void clickOrderButtonTop() {
        //кнопка "Заказать" сверху
        WebElement createOrderButtonTop =
                webDriver.findElement(createOrderButtonTopLocator);
        createOrderButtonTop.click();
    }

    public void clickOrderLowerButton() {
        //кнопка "Заказать" снизу
        WebElement createOrderButtonLower = webDriver.findElement(createOrderButtonLowerLocator);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", createOrderButtonLower);
        createOrderButtonLower.click();
    }

    public void closeCookieWindow() {
        webDriver.findElement(cookiesButtonLocator).click();
    }

    public void expandQuestion(int index) {
        WebElement element = webDriver.findElement(By.id(format(questionLocator,  index)));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", element);
        new WebDriverWait(webDriver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public boolean checkAnswerIsDisplayed(String expectedAnswer) {
        WebElement element = webDriver.findElement(By.xpath(String.format(answerLocator, expectedAnswer)));
        return element.isDisplayed();
    }
}
