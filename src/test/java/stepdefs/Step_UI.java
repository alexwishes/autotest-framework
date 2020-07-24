package stepdefs;

import helper.Utils;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import helper.BaseDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import uiTest.pageobjects.BaiduPage;


public class Step_UI {

    private WebDriver driver;
    private BaiduPage page;

    @Before("@ui_demo")
    //初始化WebDriver以及Page对象中定义的所有控件
    public void initialize() {
        driver = BaseDriver.getDriver("Chrome");
        page = PageFactory.initElements(driver, BaiduPage.class);
    }

    @Given("I am on the Google search page")
    public void I_visit_google() {
        driver.get(Utils.readProperty("config.properties", "uiTestUrl"));
    }

    @When("I search for {string}")
    //调用Page对象中定义的方法
    public void search_for(String query) {
        page.searchFor(query);
    }

    @Then("the page title should start with {string}")
    public void checkTitle(String titleStartsWith) {
        new WebDriverWait(driver, 10L).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith(titleStartsWith);
            }
        });
    }

    @AfterStep("@ui_demo")
    public void check(Scenario scenario){
        if(scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) driver). getScreenshotAs(OutputType. BYTES);
            scenario.attach(screenshot, "image/png","fail_capture.png");
        }
    }

    @After("@ui_demo")
    public void after(){
        driver.quit();
    }
}