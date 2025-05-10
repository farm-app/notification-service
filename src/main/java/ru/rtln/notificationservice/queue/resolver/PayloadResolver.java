package ru.rtln.notificationservice.queue.resolver;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DatabindContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.jsontype.impl.TypeIdResolverBase;
import ru.rtln.notificationservice.queue.determinant.PayloadDeterminant;

public class PayloadResolver extends TypeIdResolverBase {

    @Override
    public JavaType typeFromId(DatabindContext context, String entityType) {
        Class<?> payloadType = PayloadDeterminant.fromEntityType(entityType).getPayloadType();
        return context.getTypeFactory().constructType(payloadType);
    }

    @Override
    public String idFromValue(Object value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String idFromValueAndType(Object value, Class<?> suggestedType) {
        throw new UnsupportedOperationException();
    }

    @Override
    public JsonTypeInfo.Id getMechanism() {
        return JsonTypeInfo.Id.CUSTOM;
    }
}
