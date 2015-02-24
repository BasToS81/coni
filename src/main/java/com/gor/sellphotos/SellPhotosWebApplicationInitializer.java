package com.gor.sellphotos;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * see https://github.com/bijukunjummen/spring-websocket-chat-sample/blob/master/src/main/java/bk/chat/SampleWebApplicationInitializer.java
 */
public class SellPhotosWebApplicationInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        application.sources(SellPhotos.class);
        return super.configure(application);
    }

}
