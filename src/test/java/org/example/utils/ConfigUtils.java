package org.example.utils;

import org.yaml.snakeyaml.Yaml;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

public class ConfigUtils {
    public static Map<String, Object> envConfig;
    public static void readEnvConfiguration(String filePath) {

        InputStream inputStream;

        try {
            inputStream = new FileInputStream(filePath);
            Yaml yaml = new Yaml();
            envConfig = yaml.load(inputStream);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
