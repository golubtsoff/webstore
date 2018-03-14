package exception;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Evgeniy Golubtsov on 18.02.2018.
 */
public class DBException extends Exception {

    private static final Logger logger = Logger.getLogger(DBException.class.getName());

    public DBException(String message, Exception e) {
        super(message, e);
        logger.log(Level.WARNING, e.getMessage(), e);
    }

    public DBException(String message) {
        this(message, null);
    }

    public DBException(Exception e) {
        this(e.getMessage(), e);
    }
}
