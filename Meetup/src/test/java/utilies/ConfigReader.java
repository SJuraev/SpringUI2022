package utilies;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    //implement the class & and test that everything works in one of your steps classes

    //this class will read from config.properties file
    //then it will pass those values to out tests

    private static Properties properties;
    //we need to load our configuration.properties file into properties variable

    static {

        try {
            //first, we need to provide a path to out properties file
            String path = "src/test/resources/config.properties";

            //to read from the file
            FileInputStream inputStream = new FileInputStream(path);

            //now we need to load into the properties variable

            properties = new Properties();
            properties.load(inputStream);

            inputStream.close();

        }catch (IOException e){
            //in case this exception happens we want to know - where it's coming from,
            //and the reason
            e.printStackTrace();
        }

    }
    //properties.getProperty(key)
    public  static String getProperty(String key){
        return properties.getProperty(key).trim();
    }
}
