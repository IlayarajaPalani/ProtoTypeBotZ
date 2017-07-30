package ProtoType.MailBot;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class commandManager {
	
	private static commandManager CommandManager;
	DriverManager dManager;
	WebDriver fdriver;
	String parentwindow ="";
	int hits = 0;
	int threshold = 5;
	JavascriptExecutor js;
	
	
	public commandManager(WebDriver wbd){
		fdriver = wbd;
		fdriver.manage().window().maximize();
		parentwindow= fdriver.getWindowHandle();
		js = (JavascriptExecutor)fdriver;
	}
	public static commandManager getInstance() throws Exception{
		if(CommandManager==null) {
			CommandManager = new commandManager(DriverManager.getWebDriver());
			return CommandManager;
			
		}
		else{
			return CommandManager;
		}
	}
	

	
			public void open(String url) throws Exception {
				fdriver.get(url);
			}
			
			public void type(String target, String type, String value) throws Exception	{
				isPageLoaded();
				WebElement wel = this.getWebElement(target,type);
				wel.clear();
				wel.sendKeys(value);
			}
				
			public void isPageLoaded() throws Exception{
				hits=0;
				try{
					while (! js.executeScript("return document.readyState").toString().equals("complete")){
						Thread.currentThread().sleep(100);
						//System.out.println("Waiting to page load");
					}
				}
				catch(WebDriverException e){
					if(hits < threshold){
						Thread.currentThread().sleep(500);
					}
					else{
						throw new Exception(e.fillInStackTrace());
					}
				}
				
			}
			
			
			
			public void click(String target, String type) throws Exception {
				isPageLoaded();
				WebElement wel = this.getWebElement(target,type);
				try{
					wel.click();
				}
				catch(Exception e){
					wel.sendKeys(Keys.RETURN);
				}
				
			}
			
			public void sendKeyToElement(String target, int index) throws Exception {
				isPageLoaded();
				List<WebElement> wbEs = getWebElements(target);
				//wbEs.get(index).click();
				wbEs.get(index).sendKeys(Keys.RETURN);
			}
			public void selectByText(String target, String type, String value) throws Exception {
				isPageLoaded();
				WebElement wel = this.getWebElement(target,type);
				int hits = 0;
				
				while(hits < threshold){
					hits++;
					try{
						Select sel = new Select(wel);
						sel.selectByVisibleText(value);
						break;
					}
					catch(NoSuchElementException e){
						sleep(200);
						continue;
					}
					catch(WebDriverException e){
						sleep(200);
						continue;
					}
				}
			}
			
			public void selectByIndex(String target, String type, int value) throws Exception {
				isPageLoaded();
				WebElement wel = this.getWebElement(target,type);
				int hits = 0;
				
				while(hits < threshold){
					hits++;
					try{
						Select sel = new Select(wel);
						sel.selectByIndex(value);
						break;
					}
					catch(NoSuchElementException e){
						sleep(200);
						continue;
					}
					catch(WebDriverException e){
						sleep(200);
						continue;
					}
				}
			}
			
			public void selectByValue(String target, String type, String value) throws Exception {
				isPageLoaded();
				WebElement wel = this.getWebElement(target,type);
				int hits = 0;
				
				while(hits < threshold){
					hits++;
					try{
						Select sel = new Select(wel);
						sel.selectByValue(value);
						break;
					}
					catch(NoSuchElementException e){
						sleep(200);
						continue;
					}
					catch(WebDriverException e){
						sleep(200);
						continue;
					}
				}
			}
			
			public void sleep(int value) throws Exception {
				Thread.currentThread().sleep(value);
			}
			
			public String getContent(String target, String type) throws Exception {
				isPageLoaded();
				WebElement wel = this.getWebElement(target,type);
				return wel.getText();
			}
			
			public String getAttribute(String target, String type, String attribute) throws Exception {
				isPageLoaded();
				WebElement wel = this.getWebElement(target,type);
				return wel.getAttribute(attribute);
			}
			
			public boolean ifExists(String target, String type, int localThreshold, long sleepDuration) throws Exception {
				isPageLoaded();
				try{
				this.getWebElement(target,type,localThreshold,sleepDuration);
				return true;
				}
				catch(Exception e){
					return false;
				}
				
			}
			public boolean ifDisplayed(String target) throws Exception {
				isPageLoaded();
				try{
					List<WebElement> wbEs = getWebElements(target);
					for(WebElement wEl : wbEs){
						if(wEl.isDisplayed())
						return true;
					}
					return false;
				}
				catch(Exception e){
					return false;
				}
			}
			public boolean ifEnabled(String target) throws Exception {
				isPageLoaded();
				try{
					List<WebElement> wbEs = getWebElements(target);
					for(WebElement wEl : wbEs){
						if(wEl.isEnabled())
						return true;
					}
					return false;
				}
				catch(Exception e){
					return false;
				}
			}
			
			public List<String> getContents(String target) throws Exception {
				hits=0;
				List elements = null;
				List<String> elementText = new ArrayList<String>();
				isPageLoaded();
				while(hits < threshold){
						hits++;
						try{
							elements = fdriver.findElements(By.xpath(target));
							break;
						}
						catch(Exception e){
							if(hits < threshold){
								Thread.currentThread().sleep(2000);
							}
							else{
								throw new Exception(e.fillInStackTrace());
							}
						}
					}
					for( Object element : elements){
						elementText.add( ((WebElement)element).getText() );
					}

				return elementText;
			}
			public WebElement getWebElement(String target, String type) throws Exception {
				hits=0;
				isPageLoaded();
				while(hits < threshold){
					hits++;
					try{

						switch(type){

						case "id":
							return fdriver.findElement(By.id(target));
						case "name":
							return fdriver.findElement(By.name(target));
						case "linkText":
							return fdriver.findElement(By.linkText(target));
						case "xpath":
							return fdriver.findElement(By.xpath(target));
						case "className":
							return fdriver.findElement(By.className(target));
						case "cssSelector":
							return fdriver.findElement(By.cssSelector(target));
						case "partialLinkText":
							return fdriver.findElement(By.partialLinkText(target));
						case "tagName":
							return fdriver.findElement(By.tagName(target));
							
						default:
							return null;
						}
					}
					catch(Exception e){
						if(hits < threshold){
							Thread.currentThread().sleep(2000);
						}
						else{
							throw new Exception(e.fillInStackTrace());
						}
					}

				}
				return null;
			}
			
			public WebElement getWebElement(String target, String type, int localThreshold, long sleepDuration) throws Exception {
				hits=0;
				isPageLoaded();
				while(hits < localThreshold){
					hits++;
					try{

						switch(type){

						case "id":
							return fdriver.findElement(By.id(target));
						case "name":
							return fdriver.findElement(By.name(target));
						case "linkText":
							return fdriver.findElement(By.linkText(target));
						case "xpath":
							return fdriver.findElement(By.xpath(target));
						case "className":
							return fdriver.findElement(By.className(target));
						case "cssSelector":
							return fdriver.findElement(By.cssSelector(target));
						case "partialLinkText":
							return fdriver.findElement(By.partialLinkText(target));
						case "tagName":
							return fdriver.findElement(By.tagName(target));
						default:
							return null;
						}
					}
					catch(Exception e){
						if(hits < localThreshold){
							Thread.currentThread().sleep(sleepDuration);
						}
						else{
							throw new Exception(e.fillInStackTrace());
						}
					}

				}
				return null;
			}
			
			public List<WebElement> getWebElements(String target) throws Exception {
				hits=0;
				//target="//img[@id='okBtn' and @src='images/btn_ok.gif' and @onclick='javascript:ajaxFunctionForConfirmRetrievalDP1()']";
				List<WebElement> elements = null;
				isPageLoaded();
				while(hits < threshold){
						hits++;
						try{
							elements = fdriver.findElements(By.xpath(target));
							break;
						}
						catch(Exception e){
							if(hits < threshold){
								//wd.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
								Thread.currentThread().sleep(2000);
							}
							else{
								throw new Exception(e.fillInStackTrace());
							}
						}
					}

				return elements;
			}


}
