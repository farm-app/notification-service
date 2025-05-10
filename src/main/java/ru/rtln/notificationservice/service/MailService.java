package ru.rtln.notificationservice.service;

import ru.rtln.notificationservice.entity.SystemEventParticipant;

public interface MailService {

    void sendMail(SystemEventParticipant systemEventParticipant);
}
