package steps;

import com.github.javafaker.Faker;
import cucumber.api.java.Before;
import cucumber.api.java.en.*;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import pages.SmartBearLoginPage;
import pages.SmartBearWebOrderPage;
import utils.Driver;
import utils.RandomNumberGenerator;
import utils.Waiter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;


public class SmartBearSteps {

    WebDriver driver;
    SmartBearLoginPage smartBearLoginPage;
    SmartBearWebOrderPage smartBearWebOrderPage;

    @Before
    public void setup() {
        driver = Driver.getDriver();
        smartBearLoginPage = new SmartBearLoginPage();
        smartBearWebOrderPage = new SmartBearWebOrderPage();
    }

    @Given("user is on {string}")
    public void userIsOn(String url) {
        driver.get(url);
    }

    @When("user enters username as {string}")
    public void userEntersUsernameAs(String username) {
        smartBearLoginPage.userNameInput.sendKeys(username);
    }

    @And("user enters password as {string}")
    public void userEntersPasswordAs(String password) {
        smartBearLoginPage.passwordInput.sendKeys(password);
    }

    @And("user clicks on Login button")
    public void userClicksOnLoginButton() {
        smartBearLoginPage.loginButton.click();
    }


    @Then("user should see {string} message")
    public void userShouldSeeMessage(String message) {
        Assert.assertEquals(smartBearLoginPage.errorMessage.getText(), message);
    }

    @Then("user should be routed to {string}")
    public void userShouldBeRoutedTo(String expectedUrl) {
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
    }

    @And("validate below menu items are displayed")
    public void validateBelowMenuItemsAreDisplayed(DataTable info) {
        List<String> orderList = info.asList();
        for (int i = 0; i < orderList.size(); i++) {
            Assert.assertEquals(orderList.get(i), smartBearWebOrderPage.orderOptions.get(i).getText());
        }
    }


    @When("user clicks on {string} button")
    public void userClicksOnButton(String buttonName) {
        switch (buttonName) {
            case "Check All":
                smartBearWebOrderPage.checkAllButton.click();
                break;
            case "Uncheck All":
                smartBearWebOrderPage.uncheckAllButton.click();
                break;
            case "Delete Selected":
                smartBearWebOrderPage.deleteAllButton.click();
                break;
            default:
                throw new IllegalStateException("NO SUCH OPTION AVAILABLE!!!");
        }
    }

    @Then("all rows should be checked")
    public void allRowsShouldBeChecked() {
        for (int i = 0; i < smartBearWebOrderPage.checkboxes.size(); i++) {
            Assert.assertTrue(smartBearWebOrderPage.checkboxes.get(i).isSelected());
        }
    }

    @Then("all rows should be unchecked")
    public void allRowsShouldBeUnchecked() {
        for (int i = 0; i < smartBearWebOrderPage.checkboxes.size(); i++) {
            Assert.assertFalse(smartBearWebOrderPage.checkboxes.get(i).isSelected());
        }
    }

    @When("user clicks on {string} menu item")
    public void userClicksOnMenuItem(String option) {
        smartBearWebOrderPage.chooseOrderOption(option);
    }

    @And("user selects {string} as product")
    public void userSelectsAsProduct(String option) {
        smartBearWebOrderPage.selectOption(smartBearWebOrderPage.productOption, option);
    }


    @And("user enters {int} as quantity")
    public void userEntersAsQuantity(int quantity) {
        smartBearWebOrderPage.quantityOption.sendKeys(String.valueOf(quantity));
    }

    Faker faker = new Faker();
    String name = faker.name().fullName();
    String address = faker.address().streetAddress();
    String city = faker.address().city();
    String state = faker.address().state();
    String zip = faker.address().zipCode().substring(0, 5);

    @And("user enters all address information")
    public void userEntersAllAddressInformation() {
        for (int i = 0; i < smartBearWebOrderPage.addressInput.size(); i++) {
            switch (i) {
                case 0:
                    smartBearWebOrderPage.addressInput.get(i).sendKeys(name);
                    break;
                case 1:
                    smartBearWebOrderPage.addressInput.get(i).sendKeys(address);
                    break;
                case 2:
                    smartBearWebOrderPage.addressInput.get(i).sendKeys(city);
                    break;
                case 3:
                    smartBearWebOrderPage.addressInput.get(i).sendKeys(state);
                    break;
                case 4:
                    smartBearWebOrderPage.addressInput.get(i).sendKeys(zip);
                    break;
                default:
                    throw new IllegalStateException("NO SUCH INPUT AVAILABLE!!!");
            }
        }
    }

    int cardType = RandomNumberGenerator.getARandomNumber(0, 2);
    String cardTypeString;

    {
        switch (cardType) {
            case 0:
                cardTypeString = "Visa";
                break;
            case 1:
                cardTypeString = "MasterCard";
                break;
            case 2:
                cardTypeString = "American Express";
                break;
            default:
                throw new NotFoundException();
        }
    }

    String cardNumber = String.valueOf(faker.number().randomNumber(16, true));

    int month = RandomNumberGenerator.getARandomNumber(1,12);
    int year = RandomNumberGenerator.getARandomNumber(25,30);
    String cardExpDate;

    {
        if (month < 10){
            cardExpDate = "0" + month + "/" + year;
        }
        else cardExpDate = "" + month + "/" + year;
    }

//    Date date = new Date();
//    SimpleDateFormat sdf = new SimpleDateFormat("MM/yy");
//    String strDate = sdf.format(date);


    @And("user enters all payment information")
    public void userEntersAllPaymentInformation() {
        smartBearWebOrderPage.cardTypes.get(cardType).click();
        smartBearWebOrderPage.cardNumber.sendKeys(String.valueOf(cardNumber));
        smartBearWebOrderPage.expirationDate.sendKeys(cardExpDate);
        Waiter.pause(3);
    }

    @And("user clicks on “Process” button")
    public void userClicksOnProcessButton() {
        smartBearWebOrderPage.processButton.click();
        Waiter.pause(3);
    }

    @Then("user should see their order displayed in the {string} table")
    public void userShouldSeeTheirOrderDisplayedInTheTable(String tableHeader) {
        Assert.assertEquals(smartBearWebOrderPage.header.getText(), tableHeader);
        for (int i = 0; i < smartBearWebOrderPage.addedOrder.size() - 1; i++) {
            Assert.assertTrue(smartBearWebOrderPage.addedOrder.get(i).isDisplayed());
        }
    }

    String date1 = new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
    String[] order = {"", name, "FamilyAlbum", "2", date1, address, city, state, zip, cardTypeString, cardNumber, cardExpDate, ""};

    @And("validate all information entered displayed correct with the order")
    public void validateAllInformationEnteredDisplayedCorrectWithTheOrder() {
        for (int i = 1; i < smartBearWebOrderPage.addedOrder.size() - 1; i++) {
            Assert.assertEquals(order[i], smartBearWebOrderPage.addedOrder.get(i).getText());
        }
        Waiter.pause(3);
    }

    @Then("validate all orders are deleted from the {string}")
    public void validateAllOrdersAreDeletedFromThe(String header) {
        for (int i = 0; i < smartBearWebOrderPage.orderGrid.size(); i++) {
            Assert.assertFalse(smartBearWebOrderPage.orderGrid.get(i).isDisplayed());
        }
    }

    @And("validate user sees {string} message")
    public void validateUserSeesMessage(String message) {
        Assert.assertEquals(smartBearWebOrderPage.deleteMessage.getText(), message);
    }
}