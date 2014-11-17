package com.gor.sellphotos;

import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * see http://info.michael-simons.eu/2014/04/15/spring-boot-as-a-backend-for-angularjs/
 */
@Configuration
@ComponentScan
@ImportResource("classpath:spring-config.xml")
@EnableAutoConfiguration()
public class SellPhotos {
    private static final Logger LOGGER = LoggerFactory.getLogger(SellPhotos.class);

    public static void main(final String[] args) throws Exception {
        SpringApplication.run(SellPhotos.class, args);
    }
    
    // @Bean
    // public Server initH2Server() {
    // try {
    // Server server = Server.createTcpServer().start();
    // System.out.println("URL: jdbc:h2:" + server.getURL() + "/mem:test");
    // return server;
    // } catch (SQLException e) {
    // e.printStackTrace();
    // }
    // return null;
    // }

    @PreDestroy
    public void exit() {
        LOGGER.info("Exiting SellPhotos");
    }

}
