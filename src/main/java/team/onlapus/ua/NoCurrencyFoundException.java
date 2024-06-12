package team.onlapus.ua;

public class NoCurrencyFoundException extends Exception{

    private final String message;

    public NoCurrencyFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
