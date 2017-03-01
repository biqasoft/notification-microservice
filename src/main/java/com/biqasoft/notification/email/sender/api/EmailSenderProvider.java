/*
 * Copyright (c) 2016. com.biqasoft
 */

package com.biqasoft.notification.email.sender.api;

/**
 * Created by Nikita Bakaev, ya@nbakaev.ru on 5/24/2016.
 * All Rights Reserved
 */
public interface EmailSenderProvider {

    default void sendEmail(Email email){
        sendEmail(email, false);
    }

    /**
     *
     * @param email email to send
     * @param synchronously execute synchronise(wait confirmation)
     */
    void sendEmail(Email email, boolean synchronously);
}
