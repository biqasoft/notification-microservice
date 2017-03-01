/*
 * Copyright (c) 2016. com.biqasoft
 */

package com.biqasoft.notification.email.sender.api;

import io.swagger.annotations.ApiModel;

/**
 * This is Data Object for Email message
 *
 * Created by Nikita Bakaev, ya@nbakaev.ru on 5/24/2016.
 * All Rights Reserved
 */
@ApiModel("Data Object for Email message")
public class Email {

    private String from;
    private String to;
    private String body;
    private String subject;

    public Email() {
    }

    public Email(String from, String to, String body, String subject) {
        this.from = from;
        this.to = to;
        this.body = body;
        this.subject = subject;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
