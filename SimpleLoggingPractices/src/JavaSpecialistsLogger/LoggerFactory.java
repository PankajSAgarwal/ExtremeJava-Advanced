package JavaSpecialistsLogger;

import java.util.logging.Logger;

public class LoggerFactory {

    public static Logger make() {
        Throwable t = new Throwable();
        StackTraceElement directCaller = t.getStackTrace()[1];
        return Logger.getLogger(directCaller.getClassName());
    }
}
