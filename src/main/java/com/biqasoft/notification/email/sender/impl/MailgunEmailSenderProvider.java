/*
 * Copyright (c) 2016. com.biqasoft
 */

package com.biqasoft.notification.email.sender.impl;

import com.biqasoft.notification.email.sender.api.Email;
import com.biqasoft.notification.email.sender.api.EmailSenderProvider;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Send emails with https://www.mailgun.com REST API
 * <p>
 * Created by Nikita Bakaev, ya@nbakaev.ru on 5/24/2016.
 * All Rights Reserved
 */
@Service
public class MailgunEmailSenderProvider implements EmailSenderProvider {

    private final ThreadPoolExecutor threadPoolExecutor = new ScheduledThreadPoolExecutor(15);
    private final Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());
    private final String mailgunApiUrl;
    private final MailgunRestImpl mailgunRest;
    private final String mailgunAuthHeader;

    @Autowired
    public MailgunEmailSenderProvider(@Value("${mailgun.api.key}") String mailgunApiKey, @Value("${mailgun.api.url}") String mailgunApiUrl, MailgunRestImpl mailgunRest) {
        this.mailgunRest = mailgunRest;

        byte[] credentials = Base64.encodeBase64(("api" + ":" + mailgunApiKey).getBytes(StandardCharsets.UTF_8));
        this.mailgunAuthHeader = "Basic " + new String(credentials, StandardCharsets.UTF_8);
        this.mailgunApiUrl = mailgunApiUrl;
    }


    @Override
    public void sendEmail(Email email, boolean synchronously) {
        if (synchronously){
            processSendEmail(email);
        }else{
            threadPoolExecutor.submit(() -> processSendEmail(email));
        }
    }

    private void processSendEmail(Email email) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();

        formData.add("from", email.getFrom());
        formData.add("to", email.getTo());
        formData.add("subject", email.getSubject());
        formData.add("text", email.getBody());
        formData.add("html", email.getBody());

        try {
            ResponseEntity response = mailgunRest.sendEmail(formData, mailgunApiUrl, mailgunAuthHeader, "multipart/form-data");
            if (!response.getStatusCode().is2xxSuccessful()) {
                logger.warn("Error in send email to {} subject {} {}", email.getTo(), email.getSubject(), response);
            }
        } catch (Exception e) {
            logger.warn("Error in send email to {} subject {} {}", email.getTo(), email.getSubject(), e);
        }
    }
}
