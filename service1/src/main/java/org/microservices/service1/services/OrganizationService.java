package org.microservices.service1.services;

import org.microservices.service1.clients.Service2FeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService {

    @Autowired
    private Service2FeignClient service2Client;

    public String getService2Message() {
        return service2Client.getSimpleMessage();
    }
}
