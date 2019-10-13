package hu.ben.unicodestringconverter.exception;

public class UnicodeReplacingException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UnicodeReplacingException(Exception exception) {
        super(exception);
    }

    public UnicodeReplacingException(String message) {
        super(message);
    }

}
