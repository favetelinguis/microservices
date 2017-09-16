package org.microservices.service1.clients;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by henke on 2017-09-16.
 */
@FeignClient("service2")
public interface Service2FeignClient {


    @RequestMapping(
            value="/v1/",
            method = RequestMethod.GET,
            consumes = "application/json")
    String getSimpleMessage();
}
