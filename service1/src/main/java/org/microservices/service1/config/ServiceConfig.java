package org.microservices.service1.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by henke on 2017-09-09.
 */
@Component
public class ServiceConfig {

    @Value("${example.property}")
    private String exampleProperty="";

    public String getExampleProperty(){
        return exampleProperty;
    }
}
