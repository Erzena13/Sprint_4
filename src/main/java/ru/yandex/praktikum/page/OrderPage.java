package ru.yandex.praktikum.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderPage {
    private final WebDriver webDriver;
    private final By inputNameLocator = By.xpath("//input[@placeholder='* Имя']");
    private final By inputSurnameLocator = By.xpath("//input[@placeholder='* Фамилия']");
    private final By inputAddressLocator = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By inputSubwayStationLocator = By.xpath("//input[@placeholder='* Станция метро']");
    private final By inputPhoneLocator = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By clickButtonNextLocator =
            By.xpath("//div[contains(@class, 'Order_NextButton__1_rCA')]/button[contains(@class, 'Button_Middle__1CSJM')]");
    private final String stationMenuItemLocator = "//div[text()='%s']";

    public OrderPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    public void fillCustomerInfo(String name, String surname, String address, String subwayTitle, String phone) {
        //Поле "Имя"
        WebElement inputName = webDriver.findElement(inputNameLocator);
        inputName.click();
        inputName.sendKeys(name);

        //Поле "Фамилия"
        WebElement inputSurname = webDriver.findElement(inputSurnameLocator);
        inputSurname.click();
        inputSurname.sendKeys(surname);

        //Поле "Адресс"
        WebElement inputAddress = webDriver.findElement(inputAddressLocator);
        inputAddress.click();
        inputAddress.sendKeys(address);

        //Поле "Метро"
        WebElement inputSubwayStation = webDriver.findElement(inputSubwayStationLocator);
        inputSubwayStation.click();

        //Выбираем определенную станцию
        WebElement subwayStation = webDriver.findElement(By.xpath(String.format(stationMenuItemLocator, subwayTitle)));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", subwayStation);
        subwayStation.click();

        //Поле "Телефон"
        WebElement inputPhone = webDriver.findElement(inputPhoneLocator);
        inputPhone.click();
        inputPhone.sendKeys(phone);
    }

    public void clickButtonNext() {
        //Кнопка "Далее"
        WebElement buttonNext = webDriver.findElement(clickButtonNextLocator);
        buttonNext.click();
    }
}
