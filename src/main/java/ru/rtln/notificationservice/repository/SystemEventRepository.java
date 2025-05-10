package ru.rtln.notificationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.rtln.notificationservice.entity.SystemEvent;

public interface SystemEventRepository extends JpaRepository<SystemEvent, Long>, JpaSpecificationExecutor<SystemEvent> {

}