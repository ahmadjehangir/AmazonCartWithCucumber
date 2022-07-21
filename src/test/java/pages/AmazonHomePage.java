package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

/**
 * @author ahmad.razaa
 * @ desc This class contains all the selectors and functions related to Amazon Search page
 */

public class AmazonHomePage {

    WebDriver driver;

    private By txt_searchbox = By.xpath("//input[@id = 'twotabsearchtextbox' or @id = 'nav-bb-search']");

    public AmazonHomePage(WebDriver driver) {
        this.driver= driver;
    }


    /**
     * Search any string on the amazon search bar
     * @param {String} The text to be searched
     */

    public void searchProducts(String searchText) throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(txt_searchbox).sendKeys(searchText);

        System.out.println("Searching the text: "+ searchText);
    }

    /**
     * Presses the enter button
     */
    
    public void hitEnter() {
        driver.findElement(txt_searchbox).sendKeys(Keys.ENTER);
    }
}
