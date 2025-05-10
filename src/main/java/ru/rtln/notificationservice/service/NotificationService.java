package ru.rtln.notificationservice.service;

import ru.rtln.notificationservice.entity.SystemEventParticipant;

import java.util.List;

public interface NotificationService {

    void sendNotifications();

    void sendNotifications(List<SystemEventParticipant> unsentSystemEventParticipants);
}
