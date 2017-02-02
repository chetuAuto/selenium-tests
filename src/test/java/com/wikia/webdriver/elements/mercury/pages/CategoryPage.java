package com.wikia.webdriver.elements.mercury.pages;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.mercury.components.Header;
import com.wikia.webdriver.elements.mercury.components.Loading;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CategoryPage extends WikiBasePageObject {

  @Getter(lazy = true)
  private final Header header = new Header();

  private By articleContent = By.cssSelector(".article-content");
  private By categoryMembersContainer = By.cssSelector(".category-members-grouped");
  private By categoryMembers = By.cssSelector(".category-members-group li a");
  private By nextButton = By.cssSelector(".category-navigation__next");
  private By previousButton = By.cssSelector(".category-navigation__prev");

  private Navigate navigate;

  public CategoryPage() {
    super();

    navigate = new Navigate();
  }

  public CategoryPage open(String categoryName) {
    this.navigate.toPage(String.format("%s%s", URLsContent.WIKI_DIR, categoryName));

    return this;
  }


  public ArticlePage navigateToCategoryMemberPage() {
    WebElement member = driver.findElement(categoryMembers);
    String memberName = member.getText();

    wait.forElementClickable(member);
    member.click();

    new Loading(driver).handleAsyncPageReload();

    PageObjectLogging.logInfo(String.format("You were redirected to page: \"%s\".", memberName));

    return new ArticlePage();
  }

  public String getArticleContent() {
    return driver.findElement(articleContent).getText();
  }

  public boolean categoryMembersContainerIsVisible() {
    wait.forElementVisible(categoryMembersContainer);
    PageObjectLogging.logInfo("Category sections container is visible.");

    return true;
  }

  public boolean hasCategoryMembers() {
    wait.forElementVisible(categoryMembers);

    return driver.findElements(categoryMembers).size() > 0;
  }

  public boolean nextButtonIsVisible() {
    wait.forElementVisible(nextButton);

    return driver.findElement(nextButton).isDisplayed();
  }

  public boolean previousButtonIsVisible() {
    wait.forElementVisible(previousButton);

    return driver.findElement(previousButton).isDisplayed();
  }

  public CategoryPage clickNextButton() {
    wait.forElementClickable(nextButton);
    this.scrollAndClick(driver.findElement(nextButton));

    return this;
  }

  public CategoryPage clickPreviousButton() {
    wait.forElementClickable(previousButton);
    this.scrollAndClick(driver.findElement(previousButton));

    return this;
  }
}
