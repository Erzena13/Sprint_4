package ru.yandex.praktikum.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static org.junit.Assert.assertTrue;

public class RentPage {
    private final WebDriver webDriver;
    public RentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    private final By inputDataLocator = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private final By inputRentPeriodLocator = By.xpath("//div[text()='* Срок аренды']");
    private final By rentPeriodMenuItemLocator = By.xpath("//div[text()='трое суток']");
    private final By blackScooterColorLocator = By.id("black");
    private final By orderButtonLocator = By.xpath("//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");
    private final By orderConfirmationLocator = By.xpath("//div[text()='Хотите оформить заказ?']");
    private final By confirmOrderLocator = By.xpath("//button[contains(@class, 'Button_Middle__1CSJM') and text()='Да']");
    private final By orderStatusSuccessLocator = By.xpath("//div[text()='Заказ оформлен']");
    public void fillDateRent(String data) {
        //Поле "Дата, когда привезти самокат"
        WebElement inputData = webDriver.findElement(inputDataLocator);
        inputData.click();
        inputData.sendKeys(data, Keys.ENTER);

        //Поле "Срок аренды"
        WebElement inputRentPeriod = webDriver.findElement(inputRentPeriodLocator);
        inputRentPeriod.click();

        //Выбираем срок аренды из выпадающего списка
        WebElement rentPeriodMenuItem = webDriver.findElement(rentPeriodMenuItemLocator);
        rentPeriodMenuItem.click();

        //Поле "Цвет самоката" - выбираем черный
        WebElement blackScooterColor = webDriver.findElement(blackScooterColorLocator);
        blackScooterColor.click();
    }

    public void clickOrderButton() {
        //Кнопка "Заказать"
        WebElement orderButton = webDriver.findElement(orderButtonLocator);
        orderButton.click();
    }

    public void orderConfirmationWindowIsDisplayed() {
        //Проверяем что после нажатия на Заказать, отображается окно подтверждения заказа
        WebElement orderConfirmationWindow = webDriver.findElement(orderConfirmationLocator);
        assertTrue(orderConfirmationWindow.isDisplayed());
    }

    public void clickConfirmOrder() {
        WebElement confirmOrder = webDriver.findElement(confirmOrderLocator);
        confirmOrder.click();
    }

    public void orderStatusSuccess() {
        WebElement orderStatusSuccess = webDriver.findElement(orderStatusSuccessLocator);
        assertTrue(orderStatusSuccess.isDisplayed());
    }
}
