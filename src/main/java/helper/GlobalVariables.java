package helper;

import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;

public class GlobalVariables {

	static EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();
	
	public static String applicationURL = variables.getProperty("applicationURL");
	
	public static String userName = variables.getProperty("UserName");
	
	public static String password = variables.getProperty("Password");
	
	public static String email = variables.getProperty("email");
	
	public static String seleniumAutomationURL = variables.getProperty("seleniumAutomationURL");
	
	public static String remoteURL = variables.getProperty("remoteURL");


		
}