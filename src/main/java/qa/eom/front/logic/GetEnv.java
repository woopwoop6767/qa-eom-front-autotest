package qa.eom.front.logic;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

public interface GetEnv {


    default String getPropFromFile(String property) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/env.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties.getProperty(property);
    }

    default String getEnv(String envName) {
        return Optional.ofNullable(System.getenv(envName)).orElse("default");
    }
}
