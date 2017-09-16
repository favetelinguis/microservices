package org.microservices.service2.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by henke on 2017-09-16.
 */
@Component
public class ServiceConfig {

    @Value("${example.service2.property}")
    private String simpleText;

    public String getSimpleText() {
        return simpleText;
    }

}
