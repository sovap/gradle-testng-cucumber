package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class GooglePage extends BasePage{

    public GooglePage(WebDriver driver) {
        super(driver);
    }

// ******************** LOCATORS **********************************************************************************************************

    @FindBy(how = How.NAME, using = "q")
    private WebElement inputSearch;

    @FindBy(how = How.NAME, using = "btnK")
    private WebElement btnSearch;

// ******************** BASIC METHODS ****************************************************************************************************

    public void setInputSearch(String searchString){
        waitThenSendKeys(inputSearch, searchString);
    }

    public void clickBtnSearch(){
        waitThenClick(btnSearch);
    }
}
