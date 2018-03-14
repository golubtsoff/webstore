package exception;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Evgeniy Golubtsov on 18.02.2018.
 */
public class ServiceException extends Exception {

    private static final Logger logger = Logger.getLogger(ServiceException.class.getName());

    public ServiceException(String message, Exception e) {
        super(message, e);
        logger.log(Level.WARNING, e.getMessage(), e);
    }

    public ServiceException(String message) {
        this(message, null);
    }

    public ServiceException(Exception e) {
        this(e.getMessage(), e);
    }
}
