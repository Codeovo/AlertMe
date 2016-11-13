package io.codeovo.alertme.exception;

public class AlertMeException extends Exception {
    public AlertMeException() {}

    public AlertMeException(String alertMessage) { super(alertMessage); }

    public AlertMeException(Throwable cause) { super(cause); }

    public AlertMeException(String message, Throwable cause) { super(message, cause); }
}
