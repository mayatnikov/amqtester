package ru.mvn;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.DefaultShutdownStrategy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@ComponentScan
@EnableAutoConfiguration
@Configuration
public class Application {
    private static Log logger = LogFactory.getLog(Application.class);

    public static ConfigurableApplicationContext  thisApp;
    public static void main(String[] args) {

        thisApp = SpringApplication.run(Application.class, args);
    }

@PostConstruct
  public void init() {
      logger.info("Start app");
    }

}
