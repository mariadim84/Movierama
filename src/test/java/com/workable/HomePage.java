
package com.workable;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HomePage {

    @FindBy(css = "#movie-add")
    private WebElement movieAdd;

    @FindBy(css = "#movie-title")
    private WebElement movieTitle;

    @FindBy(css = "#movie-description")
    private WebElement movieDesc;

    @FindBy(css = "#movie-submit")
    private WebElement movieSubmit;
    @FindBy(css = "#click_here")
    private WebElement clickHere;

    public HomePage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void addMovie (String movieTitle, String movieDesc,WebDriver webDriver){
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        JavascriptExecutor executor = (JavascriptExecutor) webDriver;

        WebElement navMoviesTab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-movies")));

        executor.executeScript("arguments[0].click()", navMoviesTab);

        WebElement addMovieButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("movie-add")));

        this.movieAdd.click();
        this.movieTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("movie-title")));
        this.movieTitle.sendKeys(movieTitle);
        this.movieDesc= wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("movie-description")));
        this.movieDesc.sendKeys(movieDesc);
        this.movieSubmit.click();
        WebElement divSuccess = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("div-success")));
        assertThat(divSuccess.getText().contains("Your changes were successfully saved"));
        WebElement clickHere =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("click_here")));
        executor.executeScript("arguments[0].click()", clickHere);

    }

    public void editMovie (String movieTitle, String movieDesc,WebDriver webDriver){
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        JavascriptExecutor executor = (JavascriptExecutor) webDriver;

        WebElement navMoviesTab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-movies")));

        List<WebElement>  editMovieButton = (List<WebElement>) wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("edit-movie-button")));

        assertThat(navMoviesTab.getText().contains("test"));
        assertThat(navMoviesTab.getText().contains("test1"));
        if (editMovieButton.size() > 0) {
            editMovieButton.get(0).click();

            this.movieTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("movie-title")));
            this.movieTitle.clear();
            this.movieTitle.sendKeys(movieTitle);
            this.movieDesc = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("movie-description")));
            this.movieDesc.clear();
            this.movieDesc.sendKeys(movieDesc);
            this.movieSubmit.click();
            WebElement divSuccess = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("div-success")));
            assertThat(divSuccess.getText().contains("Your changes were successfully saved"));
            WebElement clickHere =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("click_here")));
            executor.executeScript("arguments[0].click()", clickHere);

        }
    }

    public void deleteMovie (WebDriver webDriver){
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        JavascriptExecutor executor = (JavascriptExecutor) webDriver;

        WebElement navMoviesTab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-movies")));

        List<WebElement>  deleteMovieButton = (List<WebElement>) wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("delete-movie-button")));

        assertThat(navMoviesTab.getText().contains("test"));
        assertThat(navMoviesTab.getText().contains("test1"));
        if (deleteMovieButton.size() > 0) {
            deleteMovieButton.get(0).click();
            WebElement divSuccess = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("div-success")));
            assertThat(divSuccess.getText().contains("Your changes were successfully saved"));
            WebElement clickHere =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("click_here")));
            executor.executeScript("arguments[0].click()", clickHere);

        }
    }
    public void likeMovie (WebDriver webDriver){
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        JavascriptExecutor executor = (JavascriptExecutor) webDriver;

        WebElement navMoviesTab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-movies")));

        List<WebElement>  likeMovieButton = (List<WebElement>) wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("like-movie-btn")));

        assertThat(navMoviesTab.getText().contains("test"));
        assertThat(navMoviesTab.getText().contains("test1"));
        if (likeMovieButton.size() > 0) {
            likeMovieButton.get(0).click();
            WebElement divSuccess = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("div-success")));
            assertThat(divSuccess.getText().contains("Your changes were successfully saved"));
            WebElement clickHere =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("click_here")));
            executor.executeScript("arguments[0].click()", clickHere);

        }
    }

    public void unlikeMovie (WebDriver webDriver){
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        JavascriptExecutor executor = (JavascriptExecutor) webDriver;

        WebElement navMoviesTab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-movies")));

        List<WebElement>  unlikeMovieButton = (List<WebElement>) wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("delete-like-movie-btn")));

        assertThat(navMoviesTab.getText().contains("test"));
        assertThat(navMoviesTab.getText().contains("test1"));
        if (unlikeMovieButton.size() > 0) {
            unlikeMovieButton.get(0).click();
            WebElement divSuccess = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("div-success")));
            assertThat(divSuccess.getText().contains("Your changes were successfully saved"));
            WebElement clickHere =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("click_here")));
            executor.executeScript("arguments[0].click()", clickHere);

        }
    }

    public void hateMovie (WebDriver webDriver){
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        JavascriptExecutor executor = (JavascriptExecutor) webDriver;

        WebElement navMoviesTab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-movies")));

        List<WebElement>  hateMovieButton = (List<WebElement>) wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("hate-movie-btn")));

        assertThat(navMoviesTab.getText().contains("test"));
        assertThat(navMoviesTab.getText().contains("test1"));
        if (hateMovieButton.size() > 0) {
            hateMovieButton.get(0).click();
            WebElement divSuccess = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("div-success")));
            assertThat(divSuccess.getText().contains("Your changes were successfully saved"));
            WebElement clickHere =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("click_here")));
            executor.executeScript("arguments[0].click()", clickHere);

        }
    }

    public void unhateMovie (WebDriver webDriver){
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        JavascriptExecutor executor = (JavascriptExecutor) webDriver;

        WebElement navMoviesTab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-movies")));

        List<WebElement>  unhateMovieButton = (List<WebElement>) wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("delete-hate-movie-btn")));

        assertThat(navMoviesTab.getText().contains("test"));
        assertThat(navMoviesTab.getText().contains("test1"));
        if (unhateMovieButton.size() > 0) {
            unhateMovieButton.get(0).click();
            WebElement divSuccess = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("div-success")));
            assertThat(divSuccess.getText().contains("Your changes were successfully saved"));
            WebElement clickHere =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("click_here")));
            executor.executeScript("arguments[0].click()", clickHere);

        }
    }
}

