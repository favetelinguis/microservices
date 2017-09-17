package org.microservices.service1.clients;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by henke on 2017-09-16.
 */
@FeignClient(value = "service2", fallback = Service2FeignClient.Service2FeignClientFallback.class)
public interface Service2FeignClient {


    @RequestMapping(
            value="/v1/",
            method = RequestMethod.GET,
            consumes = "application/json")
    String getSimpleMessage();

    @Component
    class Service2FeignClientFallback implements Service2FeignClient {

        @Override
        public String getSimpleMessage() {
            return "THIS IS THE HYSTRIX FALLBACK";
        }
    }
}
