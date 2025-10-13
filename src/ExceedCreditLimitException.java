public class ExceedCreditLimitException extends Exception {
    public ExceedCreditLimitException(String exceedCreditLimitMessage) {
        super(exceedCreditLimitMessage);
    }
}
