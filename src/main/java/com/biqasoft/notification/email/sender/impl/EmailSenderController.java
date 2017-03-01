package com.biqasoft.notification.email.sender.impl;

import com.biqasoft.notification.email.sender.api.Email;
import com.biqasoft.notification.email.sender.api.EmailSenderProvider;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Nikita on 9/13/2016.
 */
@RestController
@RequestMapping(value = "/v1/notification/email")
public class EmailSenderController {

    private final EmailSenderProvider emailSenderProvider;

    @Autowired
    public EmailSenderController(EmailSenderProvider emailSenderProvider) {
        this.emailSenderProvider = emailSenderProvider;
    }

    @ApiOperation("Send prepared email with body, from ,to, subject...")
    @RequestMapping(value = "prepared", method = RequestMethod.POST)
    public void sendEmail(@RequestBody Email email, @RequestParam(value = "sync", required = false) boolean sync){
        emailSenderProvider.sendEmail(email, sync);
    }

}
