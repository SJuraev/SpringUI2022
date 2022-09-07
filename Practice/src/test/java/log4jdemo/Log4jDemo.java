package log4jdemo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4jDemo {

    private static final Logger LOGGER = LogManager.getLogger(Log4jDemo.class);

    public static void main(String[] args) {
        for(int i = 0; i < 5; i++) {
            LOGGER.debug("Running loop " + i);
        }

        calculateTax(123456, true);
        calculateTax(-123, true);
    }

    static double calculateTax(double salary, boolean isMarried) {
        LOGGER.info("Received request for calculating tax");
        LOGGER.debug("Persons name, salary " + salary + " marriage status " + isMarried);
        if (salary > 0) {
            if (salary < 35000 && isMarried) {
                LOGGER.debug("tax = " + (salary * 0.1));
                return salary * 0.1;
            }
            //more conditions here
        }
        else {
            LOGGER.error("Salary cannot be negative");
            return  0;
        }
        return 0;
    }

    //Logs in all programming languages follow the same conventions for level.

    //DEBUG -> we put very detailed information in DEBUG level
    //INFO -> we put general info in INFO level
    //WARN -> We put possible issues in warn level
    //ERROR -> when errors occur we put in ERROR level
    //FATAL -> when something went horribly wrong we put in fatal level
}
