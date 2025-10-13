public class ExceedCreditLimitException extends Exception {
    public ExceedCreditLimitException(String exceedCreditLimitMessage) {
        super(exceedCreditLimitMessage);
    }
    @Override
    public String toString() {
        return "Credit Limit Error Message: " + getMessage();
    }
}
