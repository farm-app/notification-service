package ru.rtln.notificationservice.util.exception;

import lombok.Getter;

import java.text.MessageFormat;

@Getter
public class InternalException extends RuntimeException {

    protected final Code code;

    protected InternalException(Code code, String msg) {
        this(code, null, msg);
    }

    protected InternalException(Code code, Throwable e, String msg) {
        super(msg, e);
        this.code = code;
    }

    public enum Code {

        SERVICE_UNAVAILABLE("Service unavailable"),
        MATTERMOST_ERROR("Error when sending a message to Mattermost"),
        CAST_ERROR("Cast error"),
        INCORRECT_PAYLOAD("Incorrect payload"),
        ;

        /**
         * Pattern {0} - code description, {1} - message
         */
        private static final String EXCEPTION_MESSAGE_PATTERN = "{0}: {1}";
        private final String description;

        Code(String description) {
            this.description = "%s (%s)".formatted(name(), description);
        }

        public InternalException get() {
            return new InternalException(this, description);
        }

        public InternalException get(Throwable e) {
            String errorMessage = MessageFormat.format(EXCEPTION_MESSAGE_PATTERN, this.description, e.getMessage());
            return new InternalException(this, e, errorMessage);
        }

        public InternalException get(Throwable e, String msg) {
            String errorMessage = MessageFormat.format(EXCEPTION_MESSAGE_PATTERN, this.description, msg);
            return new InternalException(this, e, errorMessage);
        }
    }
}
