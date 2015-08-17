package io.mbedder.controlcenter;

public class MalformedPreferencesException extends Exception {

    public MalformedPreferencesException() {
        super("shared preferences appear to be malformed");
    }

    public MalformedPreferencesException(String message) {
        super(message);
    }

}
