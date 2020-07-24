package uiTest.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class BaiduPage {

    @FindBy(how = How.ID, using = "kw")
    private WebElement searchInput;

    public void searchFor(String text) {
        searchInput.sendKeys(text);
        searchInput.submit();
    }

}