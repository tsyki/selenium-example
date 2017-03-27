package jp.gr.java_conf.tsyki.selenium.service;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
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

		// Seleniumでの値設定
		final WebElement text1Element = driver.findElement(By.id("text1"));
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
			(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver d) {
					return text1Element.getAttribute("value").equals("hoge");
				}
			});
			assertThat(text1Element.getAttribute("value"), is("hoge"));
		}
		driver.quit();
	}
}
