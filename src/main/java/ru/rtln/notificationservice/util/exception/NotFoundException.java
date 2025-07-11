package ru.rtln.notificationservice.util.exception;

import java.text.MessageFormat;

public class NotFoundException extends RuntimeException {

    protected final Code code;

    protected NotFoundException(Code code, String msg) {
        this(code, null, msg);
    }

    protected NotFoundException(Code code, Throwable e, String msg) {
        super(msg, e);
        this.code = code;
    }

    public Code getCode() {
        return code;
    }

    public enum Code {

        USER_NOT_FOUND("User not found"),
        SYSTEM_EVENT_NOT_FOUND("System event not found"),
        ;

        /**
         * Pattern {0} - code description, {1} - message
         */
        private static final String EXCEPTION_MESSAGE_PATTERN = "{0}: {1}";
        private final String description;

        Code(String description) {
            this.description = "%s (%s)".formatted(name(), description);
        }

        public NotFoundException get() {
            return new NotFoundException(this, description);
        }

        public NotFoundException get(Throwable e) {
            String errorMessage = MessageFormat.format(EXCEPTION_MESSAGE_PATTERN, this.description, e.getMessage());
            return new NotFoundException(this, e, errorMessage);
        }

        public NotFoundException get(Throwable e, String msg) {
            String errorMessage = MessageFormat.format(EXCEPTION_MESSAGE_PATTERN, this.description, msg);
            return new NotFoundException(this, e, errorMessage);
        }
    }
}
