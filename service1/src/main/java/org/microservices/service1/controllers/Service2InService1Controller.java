package org.microservices.service1.controllers;

import org.microservices.service1.config.ServiceConfig;
import org.microservices.service1.model.License;
import org.microservices.service1.services.LicenseService;
import org.microservices.service1.services.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="v1/service2")
public class Service2InService1Controller {

    @Autowired
    private OrganizationService organizationService;

    @RequestMapping(
            value="/",
            method = RequestMethod.GET)
    public String getService2Message() {

        return organizationService.getService2Message();
    }
}
