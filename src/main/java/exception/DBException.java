package exception;

/**
 * Created by Evgeniy Golubtsov on 18.02.2018.
 */
public class DBException extends RuntimeException {

    public DBException(String message, Exception e) {
        super(message, e);
    }

    public DBException(String message) {
        this(message, null);
    }

    public DBException(Exception e) {
        this(e.getMessage(), e);
    }
}
