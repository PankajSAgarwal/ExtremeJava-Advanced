package JavaSpecialistsLogger;

import java.util.logging.Logger;

public class BetterApplication {
    private final static Logger logger = LoggerFactory.make();

    public static void main(String[] args) {
        BetterApplication m = new BetterApplication();
        m.run();
    }

    private void run() {
        logger.info("Hello");
    }
}
