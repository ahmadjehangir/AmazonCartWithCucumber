package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class AmazonHomePage {

    WebDriver driver;



    private By txt_searchbox = By.xpath("//input[@id = 'twotabsearchtextbox' or @id = 'nav-bb-search']");

    public AmazonHomePage(WebDriver driver) {
        this.driver= driver;
    }

    public void searchProducts(String searchText) throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(txt_searchbox).sendKeys(searchText);
        
        System.out.println("Searching the text: "+ searchText);
    }

    public void hitEnter() {
        driver.findElement(txt_searchbox).sendKeys(Keys.ENTER);
    }
}
