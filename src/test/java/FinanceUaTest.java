import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.FinancePageObject;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FinanceUaTest extends BaseTest {

    private static FinancePageObject financePage;

    @BeforeEach
    public void setUpForEach() {

        financePage = new FinancePageObject(webDriver);

    }


    @Test
    public void findDifferenceBetweenDollarSaleAndPurchasePrices() {

        financePage.goToMainPage()
                .verifyDollarPurchasePrice()
                .verifyDollarSalePrice()
                .findDifferenceBetweenDollarSaleAndPurchasePrices()
                .verifyDollarSalePriceIsHigherThanPurchasePrice();


    }

    @Test
    public void convertDollarAmountIntoHryvniaByAverageRateAndVerifyHryvniaAmount() {

        financePage.goToMainPage()
                .enterDollarAmount()
                .verifyAverageDollarPurchasePrice()
                .calculateExpectedAmount()
                .getFinalHryvniaAmount();
                assertEquals(financePage.calculateExpectedAmount(), financePage.getFinalHryvniaAmount());
    }
}
