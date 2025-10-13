
// Thrown when creating object with invalid arguments
public class Invalid extends RuntimeException {

    public Invalid(String msg, Throwable error) {
        super(msg, error);
    }
}
