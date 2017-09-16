package org.microservices.service1.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.microservices.service1.services.OrganizationService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


/**
 * Created by henke on 2017-09-16.
 */

@RunWith(MockitoJUnitRunner.class)
public class Service2InService1ControllerTest {

    private MockMvc mvc;

    // This object will be magically initialized by the initFields method below.
    private JacksonTester<String> jsonSuperHero;

    @Mock
    private OrganizationService organizationService;

    @InjectMocks
    private Service2InService1Controller controller;

    @Before
    public void setup() {
        // We would need this line if we would not use MockitoJUnitRunner
        // MockitoAnnotations.initMocks(this);
        // Initializes the JacksonTester
        JacksonTester.initFields(this, new ObjectMapper());
        // MockMvc standalone approach
        mvc = MockMvcBuilders.standaloneSetup(controller)
                //.setControllerAdvice(new SuperHeroExceptionHandler())
                //.addFilters(new SuperHeroFilter())
                .build();
    }

    @Test
    public void getService2Message() throws Exception {
        final String testString = "TestString";
        when(organizationService.getService2Message()).thenReturn(testString);

        MockHttpServletResponse response = mvc.perform(
                get("/v1/service2/")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        //this.mockMvc.perform(get("v1/")).andDo(print()).andExpect(status().isOk())
        //        .andExpect(content().string(containsString("Hello Mock")));
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        final String responseBody = jsonSuperHero.write(testString).getJson();
        assertThat(response.getContentAsString()).isEqualTo(responseBody.substring(1, responseBody.length()-1));
    }

}