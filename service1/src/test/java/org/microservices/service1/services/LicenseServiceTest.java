package org.microservices.service1.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.microservices.service1.config.ServiceConfig;
import org.microservices.service1.model.License;
import org.microservices.service1.repository.LicenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;

/**
 * Created by henke on 2017-09-16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LicenseServiceTest {

    @MockBean
    private LicenseRepository licenseRepository;

    @MockBean
    ServiceConfig config;

    @Autowired
    private LicenseService sut;

    @Test
    public void getLicensesSetCommentFromConfig() {
        final String testComment = "Test";
        final License testLicense = new License();
        given(this.licenseRepository.findByOrganizationIdAndLicenseId("1","1")).willReturn(testLicense);
        given(this.config.getExampleProperty()).willReturn(testComment);

        final License license = sut.getLicense("1","1");

        assertThat(license.getComment()).isEqualTo(testComment);

    }

}