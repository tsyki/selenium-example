package jp.gr.java_conf.tsyki.selenium.service;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

import org.junit.BeforeClass;
import org.junit.Test;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

/**
 * 
 * Selenideでの非同期処理のアサーションサンプル
 * @author TOSHIYUKI.IMAIZUMI
 *
 */
public class SelenideTestExample {
	@BeforeClass
	public static void beforeClass(){
		Configuration.browser = WebDriverRunner.CHROME;
	}
	
	@Test
	public void testAsyncChangeValue(){
		open("http://localhost:8080/selenium_example/");
		
		final SelenideElement textElement = $("#text1");
		{
			textElement.setValue("Cheese!");
			textElement.should(value("Cheese!"));
		}
		// JSでの同期処理での値変更
		{
			SelenideElement text1SyncButton = $("#text1SyncChangeButton");
			text1SyncButton.click();
			textElement.should(value("hoge"));
			textElement.clear();
			textElement.should(value(""));
		}
		// 非同期処理での値変更
		{
			SelenideElement text1AsyncButton = $("#text1AsyncChangeButton");
			text1AsyncButton.click();
			textElement.should(value("hoge"));
		}
	}

	@Test
	public void testAsyncDisabled(){
		open("http://localhost:8080/selenium_example/");
		
		final SelenideElement textElement = $("#text2");
		// JSでの同期処理での値変更
		{
			SelenideElement text2SyncDisabledButton = $("#text2SyncDisabledButton");
			text2SyncDisabledButton.click();
			textElement.should(disabled);
			
			SelenideElement text2SyncEnabledButton = $("#text2SyncEnabledButton");
			text2SyncEnabledButton.click();
			textElement.should(enabled);
		}
		// 非同期処理での値変更
		{
			SelenideElement text2AsyncDisabledButton = $("#text2AsyncDisabledButton");
			text2AsyncDisabledButton.click();
			textElement.should(disabled);
			
			SelenideElement text2AsyncEnabledButton = $("#text2AsyncEnabledButton");
			text2AsyncEnabledButton.click();
			textElement.should(enabled);
		}
	}

	@Test
	public void testAsyncVisible(){
		open("http://localhost:8080/selenium_example/");
		
		final SelenideElement textElement = $("#text3");
		// JSでの同期処理での値変更
		{
			SelenideElement syncHiddenButton = $("#text3SyncHiddenButton");
			syncHiddenButton.click();
			textElement.should(disappear);
			
			SelenideElement syncVisibleButton = $("#text3SyncVisibleButton");
			syncVisibleButton.click();
			textElement.should(appear);
		}
		// 非同期処理での値変更
		{
			SelenideElement asyncHiddenButton = $("#text3AsyncHiddenButton");
			asyncHiddenButton.click();
			textElement.should(disappear);

			SelenideElement asyncVisibleButton = $("#text3AsyncVisibleButton");
			asyncVisibleButton.click();
			textElement.should(appear);
		}
	}
}
