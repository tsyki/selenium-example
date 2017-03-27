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

		WebElement text1Element = driver.findElement(By.id("text1"));
		text1Element.sendKeys("Cheese!");
		assertThat(driver.findElement(By.id("text1")).getAttribute("value"), is("Cheese!"));
		
		WebElement text1Button = driver.findElement(By.id("text1ChangeButton"));
		text1Button.click();
		
		// ここで明示的な待機を入れないとこける
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
		         public Boolean apply(WebDriver d) {
		        	 return driver.findElement(By.id("text1")).getAttribute("value").equals("hoge");
		         }
		     });
		assertThat(driver.findElement(By.id("text1")).getAttribute("value"), is("hoge"));
		
		driver.quit();
	}
}
