package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import utilities.Utility;

public class ProductPage {

    By productsPageId = By.id("canvasCaption");
    By PriceOnProductViewPage = By.xpath("//span[@class=\"a-price a-text-price a-size-medium apexPriceToPay\"]");
    By wholePrice = By.xpath("//div[@class=\'a-section a-spacing-micro\']//span[@class='a-price-whole']");
    By fractionPrice = By.xpath("//div[@class=\"a-section a-spacing-micro\"]//span[@class='a-price-fraction']");
    By quantityToBeChosen = By.xpath("//*[@class=\"a-declarative\"]//select[@id = \"quantity\"]");
    By btn_addToCart = By.id("submit.add-to-cart");
    By cartIcon = By.cssSelector("span[id=\"nav-cart-count\"]");

    WebDriver driver; 

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyProductsPageIsDisplayed() {

    }

//    public float getPriceFromProductsPage(){
//
//        WebElement hatPrice = driver.findElement(PriceOnProductViewPage);
//        String priceString = hatPrice.getText();
//
//        float price = Utility.convertStringToFloat(priceString);
//        return price;
//    }
    
    public float getPriceFromProductsPage() {

        if(!driver.findElements(PriceOnProductViewPage).isEmpty()) {

            driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
            String priceOfAProduct = driver.findElement(PriceOnProductViewPage).getText();
            float price = Utility.convertStringToFloat(priceOfAProduct);

            return price;
        }

        else{

            String whole = driver.findElement(wholePrice).getText();

            String fraction = driver.findElement(fractionPrice).getText();

            String cart_subTotal = "$" + whole + "." + fraction;
            float price = Utility.convertStringToFloat(cart_subTotal);

            return price;
        }
    }

    public void selectQuantity(int qty) {

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        Select select = new Select(driver. findElement(quantityToBeChosen));
        select.selectByIndex(qty-1);
    }



    public void addToCart() {

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(btn_addToCart).click();
    }

    public void openCart() {

        driver.findElement(cartIcon).click();   
    }

}


