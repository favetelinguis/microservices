package org.microservices.service2.controllers;

import org.microservices.service2.services.SimpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by henke on 2017-09-16.
 */
@RestController
@RequestMapping(value="v1")
public class ServiceController {

    @Autowired
    SimpleService service;

    @RequestMapping(value="/",method = RequestMethod.GET)
    public String getSimpleMessage() {

        return service.getSimpleMessage();
    }
}
