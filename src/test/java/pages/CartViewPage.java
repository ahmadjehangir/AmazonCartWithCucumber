package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import utilities.Utility;

/**
 * @author ahmad.razaa
 * This class contains all the selectors and functions related to the Cart page
 */
public class CartViewPage {

    By cart_sTotal = By.cssSelector("span[class='a-size-medium a-color-base sc-price sc-white-space-nowrap']");
    By cart_whole_price = By.xpath("//span[@id='sc-subtotal-amount-activecart']//span[@class='a-price-whole']");
    By cart_fraction_price = By.xpath("//span[@id='sc-subtotal-amount-activecart']//span[@class='a-price-fraction']");
    By quantityInCart = By.xpath("//span [@class = 'a-size-medium sc-number-of-items']");
    By cartPage = By.xpath("//div[@class = \"a-row sc-cart-header sc-compact-bottom\"]");
    By qtyOfMenHat = By.xpath("//*[@data-old-value='2']//select[@id = 'quantity']");


    WebDriver driver = null;

    /**
     * Constructor
     * @param driver
     */
    
    public CartViewPage(WebDriver driver) {

        this.driver = driver;
    }

    /**
     * Observes the sub-total of the cart
     * Sometimes, the sub-total is displayed collectively and sometimes in chunks of whole & integer part
     * @return {number} subTotal
     */
    
    public float getSubTotalOfCart() {

        if(!driver.findElements(cart_sTotal).isEmpty()) {

            driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
            String cart_total = driver.findElement(cart_sTotal).getText();
            float subTotal = Utility.convertStringToFloat(cart_total);

            return subTotal;
        }

        else{

            String cart_whole = driver.findElement(cart_whole_price).getText();

            String cart_fraction = driver.findElement(cart_fraction_price).getText();

            String cart_subTotal = "$" + cart_whole + "." + cart_fraction;
            float subTotal = Utility.convertStringToFloat(cart_subTotal);

            return subTotal;
        }
    }
    
    /**
     * Observes the total quantity present in the cart
     * @return {number} quantity
     */

    public int getQuantityInCart() {
        String qty = driver.findElement(quantityInCart).getText();
        String onlyNumbers = qty.replaceAll("[^0-9]", "");
        int actualquantityInCart = Integer.parseInt(onlyNumbers);

        return actualquantityInCart;
    }
    
    /**
     * Confirms if the cart is opened or not
     */

    public void checkIfCartPageIsVisible() {

        driver.findElement(cartPage).isDisplayed();
    }

    /**
     * Changes the quantity in the cart
     * @param {number} qty
     */
    
    public void changeQuantityInCartforMenHat(int qty) {

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        Select select = new Select(driver. findElement(qtyOfMenHat));
        select.selectByIndex(qty-1);

    }
}


