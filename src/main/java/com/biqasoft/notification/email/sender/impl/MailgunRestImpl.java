package com.biqasoft.notification.email.sender.impl;

import com.biqasoft.microservice.communicator.interfaceimpl.annotation.MicroHeader;
import com.biqasoft.microservice.communicator.interfaceimpl.annotation.MicroMapping;
import com.biqasoft.microservice.communicator.interfaceimpl.annotation.MicroPathVar;
import com.biqasoft.microservice.communicator.interfaceimpl.annotation.Microservice;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

/**
 * Created by Nikita on 9/13/2016.
 */
@Microservice
public interface MailgunRestImpl {

    @MicroMapping(value = "{url}", method = HttpMethod.POST)
    ResponseEntity<byte[]> sendEmail(
            MultiValueMap<String, String> formData,
            @MicroPathVar(value = "url", encode = false) String url,
            @MicroHeader("Authorization") String auth,
            @MicroHeader("Content-Type") String value);
}
