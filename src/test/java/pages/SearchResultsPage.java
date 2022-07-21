package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchResultsPage {

    /**
     * @author ahmad.razaa
     * This class contains all the selectors and functions related to the Search results page
     */

    private By searchText = By.xpath("//span[@class='a-color-state a-text-bold']");
    private By resultsPage = By.xpath("//*[contains(text(), 'RESULTS')]");
    private By searchButton = By.id("nav-search-submit-button");
    private int productNumber = 1;

    WebDriver driver;

    /**
     * Constructor
     * @param driver
     */

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Confirms if the results page is displayed or not
     */

    public void verifyResultsPageIsDisplayed() {

        driver.findElement(resultsPage).isDisplayed();
    }

    /**
     * Verifies if the desired string is searched
     * @param searchedString
     */

    public void verifyTheSearchedString(String searchedString) {
        String actualText = driver.findElement(searchText).getText();

        if (!searchedString.equals(actualText)) {
            throw new IllegalStateException("The searched Text is not matching the text entered by user. The actual text searched by system is:  " 
                    + actualText);
        }
    }

    /**
     * Clicks on a product
     */

    public void clickOnProduct() {


        driver.findElement(By.xpath("//div[@data-cel-widget= 'search_result_"+productNumber+"']")).click();


        while(!driver.findElements(By.xpath("//SPAN[@class='a-color-price a-text-bold'][text()='Currently unavailable.' or ' Temporarily out of stock']")).isEmpty()){
            //  System.out.println(!driver.findElements(By.xpath("//*[text()='Currently unavailable.' or text()='Temporarily out of stock.']")).isEmpty());

            driver.findElement(searchButton).click();
            productNumber = productNumber + 1;
            driver.findElement(By.xpath("//div[@data-cel-widget='search_result_"+productNumber+"']")).click();
        }
    }

}


