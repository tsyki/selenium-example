package jp.gr.java_conf.tsyki.selenium.service;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;
import org.openqa.selenium.By;

/**
 * 
 * Seleniumでの非同期処理のアサーションサンプル
 * @author TOSHIYUKI.IMAIZUMI
 *
 */
public class SeleniumTestExample {
	@Test
	public void testAsyncChangeValue(){
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8080/selenium_example/");

		final WebElement text1Element = driver.findElement(By.id("text1"));
		// Seleniumでの値設定
		{
			text1Element.sendKeys("Cheese!");
			assertThat(text1Element.getAttribute("value"), is("Cheese!"));
		}
		// JSでの同期処理での値変更
		{
			WebElement text1SyncButton = driver.findElement(By.id("text1SyncChangeButton"));
			text1SyncButton.click();
			assertThat(text1Element.getAttribute("value"), is("hoge"));
			text1Element.clear();
		}
		// 非同期処理での値変更
		{
			WebElement text1Button = driver.findElement(By.id("text1AsyncChangeButton"));
			text1Button.click();

			// ここで明示的な待機を入れないとこける
			(new WebDriverWait(driver, 5)).until(ExpectedConditions.attributeContains(text1Element, "value", "hoge"));
			
			assertThat(text1Element.getAttribute("value"), is("hoge"));
		}
		driver.quit();
	}

	@Test
	public void testAsyncDisabled(){
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8080/selenium_example/");

		final WebElement text2Element = driver.findElement(By.id("text2"));
		// JSでの同期処理での値変更
		{
			WebElement text2SyncDisabledButton = driver.findElement(By.id("text2SyncDisabledButton"));
			text2SyncDisabledButton.click();
			assertThat(text2Element.isEnabled(), is(false));
			WebElement text2SyncEnabledButton = driver.findElement(By.id("text2SyncEnabledButton"));
			text2SyncEnabledButton.click();
			assertThat(text2Element.isEnabled(), is(true));
		}
		// 非同期処理での値変更
		{
			WebElement text2AsyncDisabledButton = driver.findElement(By.id("text2AsyncDisabledButton"));
			text2AsyncDisabledButton.click();

			// ここで明示的な待機を入れないとこける
			(new WebDriverWait(driver, 5)).until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(text2Element)));
			assertThat(text2Element.isEnabled(), is(false));

			WebElement text2AsyncEnabledButton = driver.findElement(By.id("text2AsyncEnabledButton"));
			text2AsyncEnabledButton.click();
			// ここで明示的な待機を入れないとこける
			(new WebDriverWait(driver, 5)).until(ExpectedConditions.elementToBeClickable(text2Element));
			assertThat(text2Element.isEnabled(), is(true));
		}
		driver.quit();
	}

	@Test
	public void testAsyncVisible(){
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8080/selenium_example/");

		// これ入れても効いてない感じがする
		//driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);

		final WebElement textElement = driver.findElement(By.id("text3"));
		// JSでの同期処理での値変更
		{
			WebElement syncHiddenButton = driver.findElement(By.id("text3SyncHiddenButton"));
			syncHiddenButton.click();
			assertThat(textElement.isDisplayed(), is(false));
			WebElement syncVisibleButton = driver.findElement(By.id("text3SyncVisibleButton"));
			syncVisibleButton.click();
			assertThat(textElement.isDisplayed(), is(true));
		}
		// 非同期処理での値変更
		{
			WebElement asyncHiddenButton = driver.findElement(By.id("text3AsyncHiddenButton"));
			asyncHiddenButton.click();
			// ここで明示的な待機を入れないとこける
			(new WebDriverWait(driver, 5)).until(ExpectedConditions.invisibilityOf(textElement));
			assertThat(textElement.isDisplayed(), is(false));

			WebElement asyncVisibleButton = driver.findElement(By.id("text3AsyncVisibleButton"));
			asyncVisibleButton.click();
			// ここで明示的な待機を入れないとこける
			(new WebDriverWait(driver, 5)).until(ExpectedConditions.visibilityOf(textElement));

			assertThat(textElement.isDisplayed(), is(true));
		}
		driver.quit();
	}
}
