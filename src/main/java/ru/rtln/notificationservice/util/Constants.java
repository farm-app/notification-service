package ru.rtln.notificationservice.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {

    public static final String ACCESS_TOKEN = "access_token";
    public static final String REFRESH_TOKEN = "refresh_token";

    public static final String[] INTERNAL_ENDPOINTS = {
            "/internal/**",
            "/actuator/**"
    };
    public static final String[] EXTERNAL_ENDPOINTS = {
            "/favorite-authors/**",
            "/notification-settings/**",
            "/system-events/**",
            "/ws",
    };

    public static final String WS_PREFIX = "/topic";
    public static final String NOTIFICATIONS_TOPIC = "/notifications/users";

    public static final String NOTIFICATIONS_TEMPLATE = "notification.html";
}