package org.microservices.service1.services;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.microservices.service1.config.ServiceConfig;
import org.microservices.service1.model.License;
import org.microservices.service1.repository.LicenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@DefaultProperties(
        // Common timeout for this service
        commandProperties =
                {
                        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
                }
)
public class LicenseService {

    @Autowired
    private LicenseRepository licenseRepository;

    @Autowired
    ServiceConfig config;

    public License getLicense(String organizationId, String licenseId) {
        License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);
        return license.withComment(config.getExampleProperty());
    }

    @HystrixCommand(
            //Fallback
            fallbackMethod = "getLicensesByOrgFallback",
            // Bulkhead
            threadPoolKey = "licensesByOrgThreadPool",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "30"), // Not able to read config here?
                    @HystrixProperty(name = "maxQueueSize", value = "10")
            })
    public List<License> getLicensesByOrg(String organizationId){
        return licenseRepository.findByOrganizationId( organizationId );
    }

    // Fallback for hystrix
    private List<License> getLicensesByOrgFallback(String organizationId){
        return Collections.emptyList();
    }

    public void saveLicense(License license){
        license.withId( UUID.randomUUID().toString());
        licenseRepository.save(license);
    }

    public void updateLicense(License license){
      licenseRepository.save(license);
    }

    public void deleteLicense(License license){
        licenseRepository.delete( license.getLicenseId());
    }

}
