package Utils;

import java.io.*;
import java.util.Properties;

public class PropertyReader {
    Properties properties;
    public Properties load(String relativePath)  {
        File file=new File(relativePath);
        properties=new Properties();
        InputStream inputStream= null;
        try {
            inputStream = new FileInputStream(file);
            properties.load(inputStream);
            inputStream.close();
            return properties;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
