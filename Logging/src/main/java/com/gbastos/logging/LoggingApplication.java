package com.gbastos.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ch.qos.logback.classic.Level;

@SpringBootApplication
public class LoggingApplication {
	
	private static final Logger LOG = LoggerFactory.getLogger(LoggingApplication.class);

    public void sayHello(final String name) {
    	LOG.info("Hi, {}", name);
    	LOG.info("Welcome to the HelloWorld example of SLF4J");
    }
    
    public void brakeLine() {
    	LOG.info("\n");
    }
    
    public void useContext() {
    	
    	ch.qos.logback.classic.Logger parentLogger = 
		  (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("com.gbastos.logging.logback");
		 
		parentLogger.setLevel(Level.INFO);
		 
		Logger childlogger = 
		  (ch.qos.logback.classic.Logger)LoggerFactory.getLogger("com.gbastos.logging.logback.tests");
		 
		parentLogger.warn("This message is logged because WARN > INFO.");
		parentLogger.debug("This message is not logged because DEBUG < INFO.");
		childlogger.info("INFO == INFO");
		childlogger.debug("DEBUG < INFO");
    }
    
    
    public void useRootContext() {
    	
    	ch.qos.logback.classic.Logger logger = 
		  (ch.qos.logback.classic.Logger)LoggerFactory.getLogger("com.gbastos.logging.logback");
		logger.debug("Hi there!");

		ch.qos.logback.classic.Logger rootLogger = 
		  (ch.qos.logback.classic.Logger)LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
		logger.debug("This message is logged because DEBUG == DEBUG.");

		rootLogger.setLevel(Level.ERROR);

		logger.warn("This message is not logged because WARN < ERROR.");
		logger.error("This is logged.");
    }
    
    
	public static void main(String[] args) {
		SpringApplication.run(LoggingApplication.class, args);
		LoggingApplication loggingApplication = new LoggingApplication();
		
		//Simple log
        loggingApplication.sayHello("Guilherme Borges Bastos");
        
        loggingApplication.brakeLine();
        
        // Using a context within logging hierarchies
        LOG.info("Example log from {},  using a context within logging hierarchies", LoggingApplication.class.getSimpleName());
        loggingApplication.useContext();
        
        loggingApplication.brakeLine();
        
        // Using a context within logging hierarchies
        LOG.info("Example log from {}, using the root logger context", LoggingApplication.class.getSimpleName());
        loggingApplication.useRootContext();
	}

}
