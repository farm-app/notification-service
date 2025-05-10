package ru.rtln.notificationservice.redis.repository;

import org.springframework.data.repository.CrudRepository;
import ru.rtln.notificationservice.redis.entity.RedisUser;

import java.util.List;
import java.util.stream.StreamSupport;

import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;
import static ru.rtln.notificationservice.util.exception.NotFoundException.Code.USER_NOT_FOUND;


public interface RedisUserRepository extends CrudRepository<RedisUser, Long> {

    default RedisUser get(Long id) {
        return findById(id).orElseThrow(USER_NOT_FOUND::get);
    }

    default List<Long> findReportModeratorIds() {
        return StreamSupport.stream(findAll().spliterator(), false)
                .filter(user -> emptyIfNull(user.getPermissions()).contains("REPORT_MANAGEMENT"))
                .map(RedisUser::getId)
                .toList();
    }

}