package org.microservices.service2.services;

import org.microservices.service2.config.ServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by henke on 2017-09-16.
 */
@Service
public class SimpleService {

    @Autowired
    private ServiceConfig config;

    public String getSimpleMessage() {
        return config.getSimpleText();
    }
}
