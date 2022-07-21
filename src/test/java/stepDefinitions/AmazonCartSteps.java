package stepDefinitions;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.j2objc.annotations.ReflectionSupport.Level;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import pages.AmazonHomePage;
import pages.CartViewPage;
import pages.ProductPage;
import pages.SearchResultsPage;



public class AmazonCartSteps {

    static WebDriver driver; 
    AmazonHomePage home;
    SearchResultsPage results;
    ProductPage product;
    CartViewPage cart;
    static float priceOfOneMenHat;
    static float priceOfOneWomenHat;
    float carSubTotal1;

    @SuppressWarnings("deprecation")
    @Given("browser is open")
    public void browser_is_open() {

         ChromeOptions options = new ChromeOptions();
               options.setBinary("C:/Program Files/Google/Chrome Beta/Application/chrome.exe");
          
        //        LoggingPreferences logPrefs = new LoggingPreferences();
        //        logPrefs.enable(LogType.BROWSER, java.util.logging.Level.SEVERE);
        //        options.setCapability("goog:loggingPrefs", logPrefs);
        //      
        //        String projectPath =  System.getProperty("user.dir");
        //        System.setProperty("webdriver.chrome.driver", projectPath+ "/src/test/resources/Drivers/chromedriver.exe");

        // System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe");
        // options.addArguments("--headless", "--window-size=1368,768"); 
        //options.addArguments("--incognito");
        

        String projectPath =  System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", projectPath+ "/src/test/resources/Drivers/chromedriver.exe");


        //System.setProperty("webdriver.gecko.driver", projectPath+ "/src/test/resources/Drivers/geckodriver.exe");
        //  DesiredCapabilities capabilities = new DesiredCapabilities();

        //capabilities.setCapability("marionette",true);  
        //  WebDriver driver= new  FirefoxDriver();

      //  driver = new ChromeDriver();
        driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        System.out.println("Browser is opened");
    }

    @Given("user is on amazon home page")
    public void user_is_on_amazon_home_page(){

        System.out.println("Hitting the URL: https://www.amazon.com ...");

        driver.get("https://amazon.com");
        //driver.navigate().to("https://amazon.com");

    }

    @When("user enters Hats for men in the search box")
    public void user_enters_Hats_for_men_in_the_search_box() throws InterruptedException {
        home = new AmazonHomePage(driver);
        home.searchProducts("Hats for men");
    }

    @When("hits the enter button")
    public void hits_the_enter_button() {
        home = new AmazonHomePage(driver);
        home.hitEnter();
    }

    @Then("search results are displayed")
    public void search_results_are_displayed() {

        results = new SearchResultsPage(driver);
        results.verifyResultsPageIsDisplayed();

        System.out.println("search results have been displayed");
    }


    //Scenario 2

    @Given("user has searched for a product")
    public void user_has_searched_for_a_product() {

        results = new SearchResultsPage(driver);
        results.verifyTheSearchedString("\"Hats for men\"");
    }

    @When("clicks on a product")
    public void clicks_on_a_product() {

        System.out.println("Selecting the first product ...");

        results = new SearchResultsPage(driver);
        results.clickOnProduct();

    }

    @Then("the product details should be displayed")
    public void the_product_details_should_be_displayed() {

        product = new ProductPage(driver);
        product.verifyProductsPageIsDisplayed();

        System.out.println("Product Details have been displayed");
    }

    @And("observes the price of of one men hat")
    public void observes_the_price_of_one_men_hat() {

        product = new ProductPage(driver);
        priceOfOneMenHat = product.getPriceFromProductsPage();

        System.out.println("1. price of one men hat " + priceOfOneMenHat);
    }


    //Scenario 4

    @Given("user is on products page")
    public void user_is_on_products_page() {

        product = new ProductPage(driver);
        product.verifyProductsPageIsDisplayed();
    }

    @When("user selects a quantity")
    public void user_selects_a_quantity() {

        System.out.println("Selecting quantity (2) ... ");

        product = new ProductPage(driver);
        product.selectQuantity(2);
    }

    @And("clicks on the add to cart button")
    public void clicks_on_the_add_to_cart_button() {

        System.out.println("Adding the product to cart");

        product = new ProductPage(driver);
        product.addToCart();
    }

    @Then("the product is added in the cart")
    public void the_product_is_added_in_the_cart() {

        product = new ProductPage(driver);
        product.openCart();

        System.out.println("Cart is opened");
    }

    @And("assert the subtotal in cart with two men hats is correct")
    public void assert_the_subtotal_in_cart_with_two_men_hats_is_correct() {

        System.out.println("--------------------------------");
        //product = new ProductPage(driver);
        cart = new CartViewPage(driver);

        // float priceOfOneMenHat = product.getPriceFromProductsPage();
        float cartSubTotal = cart.getSubTotalOfCart();

        System.out.println("Expected SubTotal when cart has 2 men hats: " + priceOfOneMenHat*2);
        System.out.println("Actual SubTotal when cart has 2 men hats: " + cartSubTotal);

        Assert.assertEquals(priceOfOneMenHat*2 , cartSubTotal);

        System.out.println("--------------------------------");
    }

    @And("assert the quantity in cart with two men hats is correct")
    public void assert_the_quantity_in_cart_with_two_men_hats_is_correct() {

        //product = new ProductPage(driver);
        cart = new CartViewPage(driver);

        // float priceOfOneMenHat = product.getPriceFromProductsPage();
        int actualQuantity = cart.getQuantityInCart();

        System.out.println("Expected quantity when cart has 2 men hats: " + "2");
        System.out.println("Actual quantity when cart has 2 men hats: " + actualQuantity);

        Assert.assertEquals(actualQuantity,2);

        System.out.println("--------------------------------");

    }

    //Scenario 5

    @Given("user is on cart page")
    public void user_is_on_cart_page() {

        cart = new CartViewPage(driver);
        cart.checkIfCartPageIsVisible();
    }

    @When("user enters a Hats for Women in the search box")
    public void user_enters_a_hats_for_women_in_the_search_box() throws InterruptedException {

        home = new AmazonHomePage(driver);
        home.searchProducts("Hats for women");

    }



    @And("observes the price of of one women hat")
    public void observes_the_price_of_one_women_hat() {

        product = new ProductPage(driver);
        priceOfOneWomenHat = product.getPriceFromProductsPage();

        System.out.println("2. price of one women hat " + priceOfOneWomenHat);
    }

    @And("user selects a quantity for women hat")
    public void user_selects_a_quantity_for_women_hat() {

        System.out.println("Selecting quantity (1) ... ");

        product = new ProductPage(driver);
        product.selectQuantity(1);
    }

    @And("assert the subtotal in cart with two men and one women hat is correct")

    public void assert_the_subtotal_in_cart_with_two_men_and_one_women_hat_is_correct() {

        System.out.println("--------------------------------");
        cart = new CartViewPage(driver);

        float cartSubTotal = cart.getSubTotalOfCart();

        float expectedCartTotal = (float) (Math.round(cartSubTotal*100.0)/100.0);
        float actualCartTotal = (float) (Math.round((priceOfOneMenHat*2 + priceOfOneWomenHat)*100.0)/100.0);

        Assert.assertEquals(expectedCartTotal, actualCartTotal);
        //Assert.assertEquals((priceOfOneMenHat*2 + priceOfOneWomenHat), expectedCartTotal);

        System.out.println("Expected SubTotal when cart has 2 men hats and 1 women hat : " + (priceOfOneMenHat*2 + priceOfOneWomenHat));
        System.out.println("Actual SubTotal when cart has 2 men hats and 1 women hat : " + cartSubTotal);

        System.out.println("--------------------------------");

    }

    @And("assert the quantity in cart with two men and one women hat is correct")
    public void assert_the_quantity_in_cart_with_two_men_and_one_women_hat_is_correct() {

        //product = new ProductPage(driver);
        cart = new CartViewPage(driver);

        // float priceOfOneMenHat = product.getPriceFromProductsPage();
        int actualQuantity = cart.getQuantityInCart();

        System.out.println("Expected quantity in cart with two men and one women hat: " + "3");
        System.out.println("Actual quantity in cart with two men and one women hat: " + actualQuantity);

        Assert.assertEquals(actualQuantity,3);

        System.out.println("--------------------------------");

    }


    // Scenario 6

    @When("user removes one men hat")
    public void user_removes_one_men_hat() throws InterruptedException {

        System.out.println("Removing one men hat");

        cart = new CartViewPage(driver);
        cart.changeQuantityInCartforMenHat(2);


        driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);

        Thread.sleep(10000);



    }

    @Then("assert the subtotal in cart with one men and one women hat is correct")
    public void assert_the_subtotal_in_cart_with_one_men_and_one_women_hat_is_correct() {
        // System.out.println("+assert_the_subtotal_in_cart_with_one_men_and_one_women_hat_is_correct");

        System.out.println("--------------------------------");
        cart = new CartViewPage(driver);

        System.out.println("Calculating SubTotal");

        float cartSubTotal2 = cart.getSubTotalOfCart();

        //        System.out.println(cartSubTotal2);
        //        System.out.println(priceOfOneMenHat +"    "+ priceOfOneWomenHat);

        float expectedCartTotal = priceOfOneMenHat + priceOfOneWomenHat;
        float expectedCartTotal1 = (float) (Math.round(expectedCartTotal*100.0)/100.0);

        Assert.assertEquals(expectedCartTotal1, cartSubTotal2);

        System.out.println("Expected SubTotal when cart has 1 men hats and 1 women hat : " + expectedCartTotal1);
        System.out.println("Expected SubTotal when cart has 1 men hats and 1 women hat : " + cartSubTotal2);

        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

        System.out.println("--------------------------------");
    }
    @And("assert the quantity in cart with one men and one women hat is correct")
    public void assert_the_quantity_in_cart_with_one_men_and_one_women_hat_is_correct() {

        //product = new ProductPage(driver);
        cart = new CartViewPage(driver);

        // float priceOfOneMenHat = product.getPriceFromProductsPage();
        int actualQuantity = cart.getQuantityInCart();

        System.out.println("Expected quantity in cart with one men and one women hat: " + "2");
        System.out.println("Actual quantity in cart with one men and one women hat: " + actualQuantity);

        Assert.assertEquals(actualQuantity,2);

        System.out.println("--------------------------------");

        System.out.println("Closing the Browser ... ");
        driver.close();
    }

}
