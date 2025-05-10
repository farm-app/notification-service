package ru.rtln.notificationservice.queue.model.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.ResolvableType;
import org.springframework.core.ResolvableTypeProvider;
import ru.rtln.notificationservice.queue.determinant.EventDeterminant;
import ru.rtln.notificationservice.queue.model.payload.base.PayloadModel;
import ru.rtln.notificationservice.queue.resolver.EventTypeResolver;
import ru.rtln.notificationservice.queue.resolver.PayloadResolver;

import java.time.ZonedDateTime;

@Getter
@Setter
@JsonTypeInfo(use = Id.CUSTOM, visible = true, property = "eventType")
@JsonTypeIdResolver(EventTypeResolver.class)
public abstract class AbstractEvent<PM extends PayloadModel> implements ResolvableTypeProvider {


    @JsonTypeInfo(
            use = Id.CUSTOM,
            include = JsonTypeInfo.As.EXTERNAL_PROPERTY,
            property = "targetObjectType"
    )
    @JsonTypeIdResolver(PayloadResolver.class)
    private PM attributes;
    private EventDeterminant eventType;
    private String targetObjectType;
    private String targetObjectId;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private ZonedDateTime eventTimestamp;
    private AdditionalInfo additionalInfo;

    @Override
    public ResolvableType getResolvableType() {
        if (attributes == null || eventType == null) {
            throw new IllegalArgumentException("Message can not be constructed");
        }
        return ResolvableType.forClassWithGenerics(this.getClass(), attributes.getClass());
    }

    public void setEventType(String eventType) {
        this.eventType = EventDeterminant.fromEventName(eventType);
    }
}
