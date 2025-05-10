package ru.rtln.notificationservice.util.enumeration;

import lombok.Getter;

import java.util.Comparator;

import static java.util.Comparator.comparing;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@Getter
public enum SystemEventCategoryType {

    DEFAULT(0, EMPTY, EMPTY),
    REPORT(1, "Отчеты по работе фермы", "/user/profile/profile/user-card"),
    ;

    public static final Comparator<SystemEventCategoryType> orderComparator = comparing(SystemEventCategoryType::getOrder);

    private final Integer order;
    private final String name;
    private final String link;

    SystemEventCategoryType(Integer order, String name, String link) {
        this.order = order;
        this.name = name;
        this.link = link;
    }
}
