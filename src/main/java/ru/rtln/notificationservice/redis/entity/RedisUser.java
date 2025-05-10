package ru.rtln.notificationservice.redis.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;
import padeg.lib.Padeg;
import ru.rtln.notificationservice.util.enumeration.Gender;
import ru.rtln.notificationservice.util.enumeration.WordCase;

import java.util.List;

import static ru.rtln.notificationservice.util.enumeration.Gender.MALE;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("redis-user")
public class RedisUser {

    @Id
    private Long id;

    private String username;

    private String email;

    private String firstName;

    private String lastName;

    private String patronymic;

    @Indexed
    private Boolean active;

    private Gender gender;

    private List<String> permissions;

    public String getShrinkName() {
        return "%s %s.".formatted(firstName, lastName.substring(0, 1));
    }

    public String getShrinkNameWithCase(WordCase wordCase) {
        var firstNameWithCase = Padeg.getIFPadeg(firstName, "", MALE.equals(gender), wordCase.getId());
        return "%s %s.".formatted(firstNameWithCase, lastName.substring(0, 1));
    }

    public String getFullName() {
        return "%s %s".formatted(firstName, lastName);
    }
}
