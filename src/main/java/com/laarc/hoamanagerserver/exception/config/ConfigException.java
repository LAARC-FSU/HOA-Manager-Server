package com.laarc.hoamanagerserver.exception.config;

public class ConfigException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "An error occurred in the application's configuration.";

    public ConfigException() {
        super(DEFAULT_MESSAGE);
    }

    public ConfigException(Exception e) {
        super(String.format("%s [Exception] %s", DEFAULT_MESSAGE, e.getMessage()));
    }

}
