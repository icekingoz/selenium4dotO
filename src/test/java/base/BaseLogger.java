// BaseLogger.java
package base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseLogger {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected void logInfo(String message) {
        logger.info(message);
    }

    protected void logDebug(String message) {
        logger.debug(message);
    }

    protected void logWarn(String message) {
        logger.warn(message);
    }

    protected void logError(String message) {
        logger.error(message);
    }
}