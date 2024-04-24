package ru.yandex.praktikum.plain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import ru.yandex.praktikum.WebDriverFactory;
import ru.yandex.praktikum.page.MainPage;
import ru.yandex.praktikum.page.OrderPage;
import ru.yandex.praktikum.page.RentPage;

@RunWith(Parameterized.class)
public class OrderTest {
    public WebDriver webDriver;
    private final String name;
    private final String surname;
    private final String address;
    private final String subwayStation;
    private final String phone;

    public OrderTest(String name, String surname, String address, String subwayStation, String phone) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.subwayStation = subwayStation;
        this.phone = phone;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"Эржена", "Бар", "Москва, ул. Комсомольская 57", "Римская", "79999999999"},
                {"Иван", "Иванович", "ул. Огородная 9", "Смоленская", "70000000000"},
        };
    }

    @Before
    public void setup() {
        webDriver = WebDriverFactory.getWebDriver(System.getProperty("browser", "firefox"));
        webDriver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void createOrderTopButton() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickOrderButtonTop();

        OrderPage orderPage = new OrderPage(webDriver);
        orderPage.fillCustomerInfo(name, surname, address, subwayStation, phone);
        orderPage.clickButtonNext();

        RentPage rentPage = new RentPage(webDriver);
        rentPage.fillDateRent("01.01.2025");
        rentPage.clickOrderButton();

        rentPage.orderConfirmationWindowIsDisplayed();
        rentPage.clickConfirmOrder();
        rentPage.orderStatusSuccess();
    }

    @Test
    public void createOrderLowerButton() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickOrderLowerButton();

        OrderPage orderPage = new OrderPage(webDriver);
        orderPage.fillCustomerInfo(name, surname, address, subwayStation, phone);
        orderPage.clickButtonNext();

        RentPage rentPage = new RentPage(webDriver);
        rentPage.fillDateRent("01.05.2025");
        rentPage.clickOrderButton();

        rentPage.orderConfirmationWindowIsDisplayed();
        rentPage.clickConfirmOrder();
        rentPage.orderStatusSuccess();
    }

    @After
    public void tearDown() {
        webDriver.quit();
    }
}
