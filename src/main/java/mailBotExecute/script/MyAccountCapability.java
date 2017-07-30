package mailBotExecute.script;

//Description - Created to automated the Add a new file flow with Catalog and encryption and signature no test case for TP

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import org.openqa.selenium.WebDriver;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import bsh.ParseException;

import ProtoType.MailBot.FrameworkConstants;
import ProtoType.MailBot.LoadProperties;
import ProtoType.MailBot.InitiateLogin;
import ProtoType.MailBot.commandManager;


@Test
public class MyAccountCapability {

	public static org.slf4j.Logger logger = LoggerFactory.getLogger(MyAccountCapability.class);

	Map<String, Object> connectionmap;
	
	WebDriver fdriver;
	File folder;
	ZipOutputStream zos = null;
	boolean testCasesucessFlag=false;
	commandManager cmd;

	@Parameters({ "TestCaseName", "Browser" })
	public void addFileFlow(String tcname, String browser) throws InterruptedException,ParseException, Throwable {

		cmd = commandManager.getInstance();

		
		try {
			InitiateLogin tp = new InitiateLogin(logger);
			connectionmap = tp.HlogintoTP(browser, zos);
			cmd.click("html/body/div[2]/div/div[1]/div[3]/div[3]/div[2]/div/ul/li[1]/a/span[2]/span[2]", "xpath");
			cmd.click("html/body/div[2]/div/div[1]/div[3]/div[3]/div[2]/div/ul/li[1]/div/div/div[1]/ul/li[1]/a", "xpath");
			
			} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());

		}

	}
	
}
