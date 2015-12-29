package ua.alex.source.webtester.exceptions;

public class TestResultSessionInvalid extends Exception {
    public TestResultSessionInvalid(String message) {
        super(message);
    }

    public TestResultSessionInvalid(String message, Throwable cause) {
        super(message, cause);
    }
}
