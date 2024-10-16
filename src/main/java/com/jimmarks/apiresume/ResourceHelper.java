package com.jimmarks.apiresume;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class ResourceHelper {
	
	private static final String CATALINA_BASE = System.getProperty("catalina.base");
	private static final String DEPLOYMENT_PROPERTIES = "apiresume.properties";
	public static final String APPLICATION_PROPERTIES = "application.properties";
	public static final String USE_DEBUG_LOGGER = "off";


	public static Logger getAppLogger(Class<?> classObject, String filename, boolean forDebugOnly) {
		
		String conversionPattern = "[%d] %5p %m (%C::%M:%L)%n";
		Level consoleLevel = Level.OFF;
		Level errorLevel = Level.OFF;
		Level fileLevel = Level.OFF;
		if(forDebugOnly)
		{
			boolean useDebuglogger = USE_DEBUG_LOGGER.equals("on") ? true : false;
			conversionPattern = "{%d} %5p %m%n";
			
			consoleLevel = useDebuglogger ? Level.ALL : Level.OFF;
			errorLevel = useDebuglogger ? Level.ERROR : Level.OFF;
			fileLevel = useDebuglogger ? Level.ALL : Level.OFF;
		}
		else	
		{
			consoleLevel = Level.ALL;
			errorLevel = Level.OFF;
			fileLevel = Level.ALL;
		}
		
		String loggerName = forDebugOnly ? String.format("%s-debug", classObject.getName()) : classObject.getName();
		//			String appenderName = forDebugOnly ? String.format("%s-debug-appender", classObject.getName()) : String.format("%s-appender", classObject.getName());
		Logger logger = Logger.getLogger(loggerName);
		
		PatternLayout layout = new PatternLayout();
		layout.setConversionPattern(conversionPattern);
		
		logger.setLevel(Level.ALL);
		
		ConsoleAppender consoleAppender_stdio = new ConsoleAppender();
		consoleAppender_stdio.setThreshold(consoleLevel);
		consoleAppender_stdio.setLayout(layout);
		consoleAppender_stdio.activateOptions();
		logger.addAppender(consoleAppender_stdio);
		
		ConsoleAppender consoleAppender_stderr = new ConsoleAppender();
		consoleAppender_stderr.setThreshold(errorLevel);
		consoleAppender_stderr.setLayout(layout);
		consoleAppender_stderr.activateOptions();
		logger.addAppender(consoleAppender_stderr);
		
		//			RollingFileAppender fileAppender = (RollingFileAppender) logger.getAppender(appenderName);
		//			TimeBasedRollingPolicy timeBasedRollingPolicy = (TimeBasedRollingPolicy) fileAppender.getRollingPolicy();
		//			timeBasedRollingPolicy.setFileNamePattern(String.format("%s/%d{yyyy-MM}", filename));
		//			timeBasedRollingPolicy.activateOptions();
		DailyRollingFileAppender fileAppender = new DailyRollingFileAppender();
		fileAppender.setThreshold(fileLevel);
		fileAppender.setFile(String.format("%s/logs/%s.log", CATALINA_BASE, filename));
		fileAppender.setDatePattern("'.'yyy-MM-dd");
		fileAppender.setLayout(layout);
		fileAppender.activateOptions();
		logger.addAppender(fileAppender);
		       
		logger.info(String.format("Starting logger: %s", logger.getName()));
		        return logger;
	}
	public static String getAppConstant(String propertyName)
	{
		PropertiesConfiguration config = getAppConfig();
		String propertyValue = config.getString(propertyName);
		if(propertyValue == null)
		{
			String warning = String.format("missing property: %s", propertyName);
			App.APPLOGGER.warn(warning);
			propertyValue = warning;
		}
		return propertyValue;
	}
	public static String getConstantFromConfig(String propertyName, PropertiesConfiguration config)
	{
		String propertyValue = config.getString(propertyName);
		if(propertyValue == null)
		{
			String warning = String.format("missing property: %s", propertyName);
			App.APPLOGGER.warn(warning);
			propertyValue = warning;
		}
		return propertyValue;
	}
	public static String getDeploymentConstant(String propertyName)
	{
		PropertiesConfiguration config = getDeploymentConfig();
		String propertyValue = config.getString(propertyName);
		if(propertyValue == null)
		{
			String warning = String.format("missing property: %s", propertyName);
			App.APPLOGGER.warn(warning);
			propertyValue = warning;
		}
		return propertyValue;
	}
	public static PropertiesConfiguration getAppConfig() {
		
		PropertiesConfiguration config = new PropertiesConfiguration();
		try
		{
			config.load(APPLICATION_PROPERTIES);
		}
		catch(Exception e)
		{
			App.APPLOGGER.error("application properties file failure");
		}
		return config;
	}	
	public static PropertiesConfiguration getDeploymentConfig() {
		
		PropertiesConfiguration config = new PropertiesConfiguration();
		try
		{
			config.load(String.format("%s/conf/%s", CATALINA_BASE, DEPLOYMENT_PROPERTIES));
		}
		catch(Exception e)
		{
			App.APPLOGGER.error("deployment properties file failure");
		}
		return config;
	}
}
