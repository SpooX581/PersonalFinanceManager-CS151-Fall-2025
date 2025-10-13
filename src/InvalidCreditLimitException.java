public class InvalidCreditLimitException extends Exception {
    public InvalidCreditLimitException(String invalidLimitMessage) {
        super(invalidLimitMessage);
    }
}
