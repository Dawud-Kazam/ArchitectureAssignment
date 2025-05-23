package architecture.API.application.Exceptions;

public class CreditCardFormatException extends RuntimeException {
    public CreditCardFormatException() {
        super("Credit Card in the incorrect format");
    }
}
