package org.school.backend.domain.gateaway;

public interface LoggerGateway {
    void info(String message);
    void info(String message, String data);
    void warn(String message);
    void warn(String message, String data);
    void error(String message, Throwable throwable);
}
