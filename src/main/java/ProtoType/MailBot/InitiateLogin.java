package ProtoType.MailBot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.zip.ZipOutputStream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InitiateLogin {
	Map<String, Object> connectionobj = new HashMap<String, Object>();
	boolean loginstatus=false;
	//WebDriver fdriver;
	static Map loginmap;
	DriverManager dManager;
	commandManager cmd;
	
	public InitiateLogin(Logger logger) {
		// TODO Auto-generated constructor stub
	}

	private static org.slf4j.Logger logger=LoggerFactory.getLogger(InitiateLogin.class);
	

	public static void main(String args[]) throws Exception
	{
		
		FileOutputStream fos=new FileOutputStream(new File(FrameworkConstants.ScreenShots+"\\G1\\"+"G1.zip"));
		ZipOutputStream zos=new ZipOutputStream(fos); 
		InitiateLogin lg=new InitiateLogin(logger);
		loginmap=lg.HlogintoTP("IE",zos);
		
	}
	
	
	public Map<String, Object> HlogintoTP(String browser,ZipOutputStream zos) throws Exception
	{
		cmd = commandManager.getInstance();
        try{
        	switch(browser)
        	{
				case "IE":
					
						System.out.println("launching IE browser");
						
						
						
						System.out.println("Trying to Open MYCA Page");
								cmd.open("https://www.americanexpress.com/");
						/*fdriver.findElement(By.id("textboxuid_AD")).clear();
						fdriver.findElement(By.id("textboxuid_AD")).sendKeys(username);
						fdriver.findElement(By.id("textboxpwd_AD")).clear();
						fdriver.findElement(By.id("textboxpwd_AD")).sendKeys(password);*/
	           			//fdriver.findElement(By.id(login_button)).click();
						//connectionobj.put("sessionid", dManager.getSessionId());
						connectionobj.put("loginstatus", loginstatus);
						connectionobj.put("fdriver", cmd.fdriver);
						
						Thread.sleep(1000);
						System.out.println("Account Page Opened successfully");
						
						 
					break;                        
        		 
				case "Firefox":
					System.out.println("launching Firefox browser");
					Thread.sleep(1000);
					break;
				case "GoogleChrome":
					break;
				}
        	
			}catch(NullPointerException e1)
			{   e1.printStackTrace();
				logger.info("unable to proceed:\t"+e1);
                loginstatus=false;
				//connectionobj.put("sessionid", dManager.getSessionId());
				connectionobj.put("loginstatus", loginstatus);
				connectionobj.put("fdriver",cmd.fdriver);
			}
		catch(NoSuchElementException e2)
		{   
			e2.printStackTrace();
			logger.info("unable to proceed:\t"+e2);
            loginstatus=false;
			//connectionobj.put("sessionid", dManager.getSessionId());
			connectionobj.put("loginstatus", loginstatus);
			connectionobj.put("fdriver",cmd.fdriver);
        } catch (Throwable e3) {
            e3.printStackTrace();
			logger.info("unable to proceed:\t"+e3);
            loginstatus=false;
			//connectionobj.put("sessionid", dManager.getSessionId());
			connectionobj.put("loginstatus", loginstatus);
			connectionobj.put("fdriver",cmd.fdriver);
        }
		return connectionobj;
		
	}
	

}
