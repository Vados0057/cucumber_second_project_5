package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utils.Driver;

import java.util.List;

public class SmartBearWebOrderPage {
    public SmartBearWebOrderPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(css = "ul[id='ctl00_menu']>li")
    public List<WebElement> orderOptions;

    @FindBy(id = "ctl00_MainContent_btnCheckAll")
    public WebElement checkAllButton;

    @FindBy(id = "ctl00_MainContent_btnUncheckAll")
    public WebElement uncheckAllButton;

    @FindBy(css = "input[type='checkbox']")
    public List<WebElement> checkboxes;

    public void chooseOrderOption(String option) {
        for (WebElement element : orderOptions) {
            if (element.getText().equals(option)) {
                element.click();
                break;
            }
        }
    }

    @FindBy(id = "ctl00_MainContent_fmwOrder_ddlProduct")
    public WebElement productOption;

    public void selectOption(WebElement dropdown, String text) {
        new Select(dropdown).selectByVisibleText(text);
    }

    @FindBy(id = "ctl00_MainContent_fmwOrder_txtQuantity")
    public WebElement quantityOption;

    @FindBy(xpath = "//ol[2]/li/input")
    public List<WebElement> addressInput;

    @FindBy(xpath = "//ol[2]/li/label")
    public List<WebElement> addressLabel;

    @FindBy(css = "input[id*='cardList']")
    public List<WebElement> cardTypes;

    @FindBy(css = "input[id*='TextBox6']")
    public WebElement cardNumber;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox1")
    public WebElement expirationDate;

    public String createExpirationDate(int month, int year){
        String expirationDate;
        if (month < 10){
            expirationDate = "0" + month + "/" + year;
        }
        else expirationDate = month + "/" + year;
        return expirationDate;
    }

    @FindBy(id = "ctl00_MainContent_fmwOrder_InsertButton")
    public WebElement processButton;

    @FindBy(tagName = "h2")
    public WebElement header;

    @FindBy(xpath = "//table[@class='SampleTable']/tbody/tr[2]/td")
    public List<WebElement> addedOrder;

    @FindBy(id = "ctl00_MainContent_btnDelete")
    public WebElement deleteAllButton;

    @FindBy(id = "ctl00_MainContent_orderGrid")
    public List<WebElement> orderGrid;

    @FindBy(id = "ctl00_MainContent_orderMessage")
    public WebElement deleteMessage;

}
