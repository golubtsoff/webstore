package exception;

/**
 * Created by Evgeniy Golubtsov on 18.02.2018.
 */
public class ServiceException extends RuntimeException {

    public ServiceException(String message, Exception e) {
        super(message, e);
    }

    public ServiceException(String message) {
        this(message, null);
    }

    public ServiceException(Exception e) {
        this(e.getMessage(), e);
    }
}
