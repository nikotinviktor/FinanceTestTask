package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.math.BigDecimal;

public class FinancePageObject extends BasePageObject {

    private final String mainPage = "https://finance.i.ua/";

    BigDecimal saleDollarPrice;
    BigDecimal purchaseDollarPrice;
    BigDecimal averagePurchaseDollarPrice;
    BigDecimal saleAmount = new BigDecimal(1000);
    BigDecimal actualHryvniaAmount;
    BigDecimal expectedHryvniaAmount;

    @FindBy(xpath = "//*[@id='currency_amount']")
    private WebElement converterInputField;

    public FinancePageObject(WebDriver webDriver) {

        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public FinancePageObject goToMainPage() {

        webDriver.get(mainPage);
        return this;

    }

    public FinancePageObject getDollarPurchasePrice() {


        WebElement dollarPurchasePrice =
                webDriver.findElement(By.xpath("/html/body/div[2]/div[3]/div/div[1]/div[1]/div[1]/div/table/tbody/tr[1]/td[1]/span/span[1]"));

        purchaseDollarPrice = new BigDecimal(dollarPurchasePrice.getText());

        System.out.println();
        System.out.println("Purchase price for USD 1 is UAH " + purchaseDollarPrice);

        return this;

    }

    public FinancePageObject getDollarSalePrice() {

        WebElement dollarSalePrice =
                webDriver.findElement(By.xpath("/html/body/div[2]/div[3]/div/div[1]/div[1]/div[1]/div/table/tbody/tr[1]/td[2]/span/span[1]"));

        saleDollarPrice = new BigDecimal(dollarSalePrice.getText());

        System.out.println();
        System.out.println("Sale price for USD 1 is UAH " + saleDollarPrice);

        return this;
    }

    public FinancePageObject findDifferenceBetweenDollarSaleAndPurchasePrices() {

        BigDecimal saleAndPurchasePricesDifference = saleDollarPrice.subtract(purchaseDollarPrice);

        System.out.println();
        System.out.println("Difference between sale and purchase price for USD is UAH " + saleAndPurchasePricesDifference);

        return this;

    }

    public FinancePageObject verifyDollarSalePriceIsHigherThanPurchasePrice() {

        int comparator = saleDollarPrice.compareTo(purchaseDollarPrice);

        if (comparator == 1) {

            System.out.println();
            System.out.println("Dollar sale price is higher than dollar purchase price");

        } else if (comparator == -1) {

            System.out.println();
            System.out.println("Dollar sale price is lower than dollar purchase price");

        } else {

            System.out.println();
            System.out.println("Dollar sale price and dollar purchase price are equal");
        }

        return this;
    }

    public FinancePageObject enterDollarAmount() {

        WebElement converterInput = converterInputField;

        converterInput.sendKeys(String.valueOf(saleAmount));

        return this;
    }

    public FinancePageObject getAverageDollarPurchasePrice() {

        WebElement averagePurchasePriceField =
                webDriver.findElement(By.xpath("//*[@id=\'latest_currency_container\']/tfoot[1]/tr[1]/td[1]/span/span"));

        averagePurchaseDollarPrice = new BigDecimal(averagePurchasePriceField.getText());

        System.out.println();
        System.out.println("Average purchase price for USD 1 is " + averagePurchaseDollarPrice);

        return this;
    }

    public FinancePageObject calculateExpectedAmount() {

        expectedHryvniaAmount = averagePurchaseDollarPrice.multiply(saleAmount);

        return this;
    }

    public FinancePageObject getFinalHryvniaAmount() {

        WebElement calculatedAmountField =
                webDriver.findElement(By.xpath("//input[@id='currency_exchange']"));


        actualHryvniaAmount = new BigDecimal(calculatedAmountField.getAttribute("value").replace(" ", ""));

        int comparator = expectedHryvniaAmount.compareTo(actualHryvniaAmount);

        if (comparator == 0) {

            System.out.println();
            System.out.println("Expected amount (" + expectedHryvniaAmount + ") and actual amount (" + actualHryvniaAmount + ") are the same");

        } else {

            System.out.println();
            System.out.println("Expected amount (" + expectedHryvniaAmount + ") and actual amount (" + actualHryvniaAmount + ") are NOT the same");

        }

        return this;
    }


}

