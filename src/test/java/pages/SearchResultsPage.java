package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchResultsPage {


    private By searchText = By.xpath("//span[@class='a-color-state a-text-bold']");
    private By resultsPage = By.xpath("//*[contains(text(), 'RESULTS')]");
    private By searchButton = By.id("nav-search-submit-button");
    private int productNumber = 1;

    WebDriver driver;

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyResultsPageIsDisplayed() {

        driver.findElement(resultsPage).isDisplayed();
    }
    public void verifyTheSearchedString(String searchedString) {
        String actualText = driver.findElement(searchText).getText();

        if (!searchedString.equals(actualText)) {
            throw new IllegalStateException("The searched Text is not matching the text entered by user. The actual text searched by system is:  " 
                    + actualText);
        }
    }



    public void clickOnProduct() {


        driver.findElement(By.xpath("//div[@data-cel-widget= 'search_result_"+productNumber+"']")).click();


        while(!driver.findElements(By.xpath("//SPAN[@class='a-color-price a-text-bold'][text()='Currently unavailable.' or ' Temporarily out of stock']")).isEmpty()){
          //  System.out.println(!driver.findElements(By.xpath("//*[text()='Currently unavailable.' or text()='Temporarily out of stock.']")).isEmpty());

            driver.findElement(searchButton).click();
            productNumber = productNumber + 1;
            driver.findElement(By.xpath("//div[@data-cel-widget='search_result_"+productNumber+"']")).click();
        }
    }









    //        if(!driver.findElements(By.xpath("//*[text()='Currently unavailable.' or text()='Temporarily out of stock']")).isEmpty())
    //        {
    //            System.out.println("The cap is not currently availble. That's system is selecting the first available product");
    //            
    //            driver.findElement(searchButton).click();
    //            
    //            driver.findElement(secondProduct).click();
    //            
    //        }
    //        else if(!driver.findElements(By.xpath("//*[text()='Currently unavailable.' or text()='Temporarily out of stock']")).isEmpty()) {
    //            
    //            driver.findElement(searchButton).click();
    //            driver.findElement(thirdProduct).click();
    //        }
    //        
    //       else {
    //        
    //           System.out.println("This product is also not available");
    //        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    //        }
}


