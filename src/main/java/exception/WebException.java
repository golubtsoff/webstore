package exception;

/**
 * Created by Evgeniy Golubtsov on 18.02.2018.
 */
public class WebException extends RuntimeException {

    public WebException(String message, Exception e) {
        super(message, e);
    }

    public WebException(String message) {
        this(message, null);
    }

    public WebException(Exception e) {
        this(e.getMessage(), e);
    }
}
