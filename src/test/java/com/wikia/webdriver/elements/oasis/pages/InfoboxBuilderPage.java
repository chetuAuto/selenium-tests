package com.wikia.webdriver.elements.oasis.pages;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialPageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class InfoboxBuilderPage extends SpecialPageObject {

  @FindBy(css = ".InfoboxBuilder")
  private WebElement builderIFrame;

  @FindBy(css = ".sub-head .sub-head--done")
  private WebElement saveButton;

  @FindBy(css = ".edit-header--delete")
  private WebElement deleteButton;

  @FindBy(css = "#useArticleName")
  private WebElement titleCheckbox;

  @FindBy(css = "#isCollapsible")
  private WebElement collapsibilityCheckbox;

  @FindBy(css = "#infoboxRowLabel")
  private WebElement rowLabelInputField;

  @FindBy(css = "#infoboxSectionHeader")
  private WebElement sectionHeaderInputField;

  @FindBy(css = ".back-arrow")
  private WebElement backArrowButton;

  @FindBy(css = ".infobox-builder-questionmark")
  private WebElement questionMarkButton;

  @FindBy(css = ".infobox-builder-sidebar .modal-dialog")
  private WebElement helpDialog;

  @FindBy(css = ".infobox-builder-preview")
  private WebElement previewArea;

  @FindBy(css = ".on-hover-tooltip")
  private WebElement tooltip;

  @FindBy(css = ".infobox-builder-spinner")
  private WebElement savingSpinner;

  @FindBy(css = ".success-icon")
  private WebElement successIcon;

  @FindBy(css = ".infobox-builder-preview")
  private WebElement builderBackground;

  @FindBy(css = ".portable-infobox .pi-data-label")
  private List<WebElement> rowLabels;

  @FindBy(css = ".infobox-builder-button")
  private List<WebElement> componentsButtons;

  @FindBy(css = ".portable-infobox .pi-data")
  private List<WebElement> rows;

  @FindBy(css = ".portable-infobox .pi-title")
  private List<WebElement> titles;

  @FindBy(css = ".portable-infobox .pi-image")
  private List<WebElement> images;

  @FindBy(css = ".portable-infobox .pi-header")
  private List<WebElement> headers;

  @FindBy(css = ".portable-infobox .sortable-item")
  private List<WebElement> component;

  public InfoboxBuilderPage() {
    super();
  }

  public InfoboxBuilderPage open(String templateName) {
    getUrl(urlBuilder.getUrlForWiki() + URLsContent.SPECIAL_INFOBOX_BUILDER + templateName);
    driver.switchTo().frame(builderIFrame);

    return this;
  }

  public boolean isInfoboxBuilderDisplayed() {
    return builderIFrame.isDisplayed();
  }

  public InfoboxBuilderPage clickBuilderBackground() {
    wait.forElementClickable(builderBackground);
    builderBackground.click();

    return this;
  }

  public int countRows() {
    return rows.size();
  }

  public int countTitles() {
    return titles.size();
  }

  public int countImages() {
    return images.size();
  }

  public int countHeaders() {
    return headers.size();
  }

  public String getBackgroundColor() {
    return component.get(0).getCssValue("background-color");
  }

  public String getBorderStyle() {
    JavascriptExecutor js = (JavascriptExecutor)driver;
    WebElement selectedComponent = component.get(0);
    wait.forElementClickable(selectedComponent);
    selectedComponent.click();

    String script = "window.getComputedStyle("
                    + "document.querySelector('.active'),':before').getPropertyValue('Border')";

    return js.executeScript(script).toString();
  }

  public InfoboxBuilderPage selectTitleWithIndex(int index) {
    WebElement selectedTitle = titles.get(index);
    wait.forElementVisible(selectedTitle);
    selectedTitle.click();

    return this;
  }

  public InfoboxBuilderPage selectImageWithIndex(int index) {
    WebElement selectedImage = images.get(index);
    wait.forElementVisible(selectedImage);
    selectedImage.click();

    return this;
  }

  public InfoboxBuilderPage selectRowWithIndex(int index) {
    WebElement selectedRow = rows.get(index);
    wait.forElementVisible(selectedRow);
    selectedRow.click();

    return this;
  }

  public InfoboxBuilderPage selectHeaderWithIndex(int index) {
    WebElement selectedHeader = headers.get(index);
    wait.forElementClickable(selectedHeader);
    selectedHeader.click();

    return this;
  }

  public InfoboxBuilderPage addRowComponent() {
    WebElement rowComponentButton = componentsButtons.get(0);
    wait.forElementVisible(rowComponentButton);
    rowComponentButton.click();

    return this;
  }

  public InfoboxBuilderPage addImageComponent() {
    WebElement imageComponentButton = componentsButtons.get(1);
    wait.forElementVisible(imageComponentButton);
    imageComponentButton.click();

    return this;
  }

  public InfoboxBuilderPage addTitleComponent() {
    WebElement titleComponentButton = componentsButtons.get(2);
    wait.forElementVisible(titleComponentButton);
    titleComponentButton.click();

    return this;
  }

  public InfoboxBuilderPage addHeaderComponent() {
    WebElement headerComponentButton = componentsButtons.get(3);
    wait.forElementVisible(headerComponentButton);
    headerComponentButton.click();

    return this;
  }

  public InfoboxBuilderPage deleteTitleComponentWithIndex(int index) {
    WebElement titleToDelete = titles.get(index);
    wait.forElementClickable(titleToDelete);
    titleToDelete.click();

    wait.forElementClickable(deleteButton);
    deleteButton.click();

    return this;
  }

  public InfoboxBuilderPage deleteRowComponentWithIndex(int index) {
    WebElement rowToDelete = rows.get(index);
    wait.forElementClickable(rowToDelete);
    rowToDelete.click();

    wait.forElementClickable(deleteButton);
    deleteButton.click();

    return this;
  }

  public InfoboxBuilderPage deleteImageComponentWithIndex(int index) {
    WebElement imageToDelete = images.get(index);
    wait.forElementClickable(imageToDelete);
    imageToDelete.click();

    wait.forElementClickable(deleteButton);
    deleteButton.click();

    return this;
  }

  public InfoboxBuilderPage deleteHeaderComponentWithIndex(int index) {
    WebElement headerToDelete = headers.get(index);
    wait.forElementClickable(headerToDelete);
    headerToDelete.click();

    wait.forElementClickable(deleteButton);
    deleteButton.click();

    return this;
  }

  public InfoboxBuilderPage verifyScrollbarIsVisible() {
    Assertion.assertEquals(previewArea.getCssValue("overflow"), "auto");

    return this;
  }

  public InfoboxBuilderPage verifyInfoboxPreviewBackgroundColor(String invocationBgColor) {
    String previewBackgroundColor = getBackgroundColor();
    Assertion.assertEquals(invocationBgColor, previewBackgroundColor);

    return this;
  }

  public InfoboxBuilderPage verifyBackArrowFunctionality() {
    wait.forElementVisible(backArrowButton);
    backArrowButton.click();
    Assertion.assertTrue(componentsButtons.get(0).isDisplayed());

    return this;
  }

  public InfoboxBuilderPage verifyHelpDialog() {
    wait.forElementVisible(questionMarkButton);
    questionMarkButton.click();
    Assertion.assertTrue(helpDialog.isDisplayed());

    return this;
  }

  public InfoboxBuilderPage verifyTooltipOnHover() {
    wait.forElementVisible(component.get(0));
    builder.moveToElement(component.get(0)).perform();
    Assertion.assertTrue(tooltip.isDisplayed());

    return this;
  }

  /**
   *  Verifies default rendered template structure, which should contain:
   *  1 title component, 1 image component and 2 row components
   */
  public InfoboxBuilderPage verifyDefaultTemplateStructure() {
    Assertion.assertEquals(this.titles.size(), 1);
    Assertion.assertEquals(this.images.size(), 1);
    Assertion.assertEquals(this.rows.size(), 2);

    return this;
  }

  public InfoboxBuilderPage changeHeaderCollapsibilityState(int index) {
    JavascriptExecutor js = (JavascriptExecutor)driver;
    headers.get(index).click();

    wait.forElementVisible(collapsibilityCheckbox);
    collapsibilityCheckbox.click();

    String script = "return window.getComputedStyle(document"
                    + ".querySelector('.pi-header'),':after').content";
    String chevronContent = js.executeScript(script).toString();

    if (collapsibilityCheckbox.isSelected()) {
      Assertion.assertFalse(chevronContent.isEmpty());
    } else {
      Assertion.assertTrue(chevronContent.isEmpty());
    }

    return this;
  }

  public InfoboxBuilderPage setTitleToUseArticleName(int index) {
    WebElement selectedTitle = titles.get(index);
    wait.forElementClickable(selectedTitle);
    selectedTitle.click();
    wait.forElementVisible(titleCheckbox);

    if (!titleCheckbox.isSelected()) {
      titleCheckbox.click();
    }

    return this;
  }

  public InfoboxBuilderPage setAndVerifyRowLabel(int index, String labelName) {
    WebElement selectedRow = rows.get(index);
    wait.forElementClickable(selectedRow);
    selectedRow.click();

    wait.forElementClickable(rowLabelInputField);
    rowLabelInputField.click();
    rowLabelInputField.clear();
    rowLabelInputField.sendKeys(labelName);
    Assertion.assertEquals(rowLabels.get(index).getText(), labelName);

    return this;
  }

  public InfoboxBuilderPage setLongLabelNameAndVerifyBreakLine(int index, String labelName) {
    WebElement selectedRow = rows.get(index);
    wait.forElementClickable(selectedRow);
    selectedRow.click();

    wait.forElementClickable(rowLabelInputField);
    rowLabelInputField.click();
    rowLabelInputField.clear();
    rowLabelInputField.sendKeys(labelName);
    Assertion.assertEquals(rowLabels.get(index).getCssValue("word-wrap"), "break-word");

    return this;
  }

  public InfoboxBuilderPage setAndVerifyHeaderName(int index, String labelName) {
    WebElement selectedHeader = headers.get(index);
    wait.forElementClickable(selectedHeader);
    selectedHeader.click();

    wait.forElementClickable(sectionHeaderInputField);
    sectionHeaderInputField.click();
    sectionHeaderInputField.clear();
    sectionHeaderInputField.sendKeys(labelName);
    Assertion.assertEquals(headers.get(index).getText(), labelName);

    return this;
  }

  public InfoboxBuilderPage selectLastComponent() {
    scrollAndClick(component, component.size() - 1);

    return this;
  }

  public InfoboxBuilderPage dragAndDropToTheTop(int index) {
    String componentToBeMovedText = component.get(index).getText();
    Point location = component.get(component.size() - 1).getLocation();
    Dimension size = component.get(component.size() - 1).getSize();
    Integer targetY = location.getY() + size.getHeight();

    new Actions(driver)
        .clickAndHold(component.get(index))
        .moveByOffset(0, targetY)
        .release(component.get(index))
        .perform();

    wait.forElementClickable(component.get(component.size() - 1));
    component.get(component.size() - 1).click();
    Assertion.assertEquals(componentToBeMovedText, component.get(0).getText());

    return this;
  }

  public TemplatePage save() {
    wait.forElementClickable(saveButton);
    saveButton.click();

    wait.forElementVisible(savingSpinner);
    Assertion.assertTrue(savingSpinner.isDisplayed());

    //wait until template page is loaded
    wait.forElementVisible(driver.findElement(By.className("header-title")));

    return new TemplatePage();
  }
}
