package onthe.io.Utils;

import org.openqa.selenium.By;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Props {
    private static Properties props;

    private Props() {
        super();
    }

    public static Properties init() {
        props = new Properties();

        String conf = System.getProperty("configuration") != null ? System.getProperty("configuration") : "resources";

        props.setProperty("configuration", conf);
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream(conf + ".properties");
        try {
            props.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return props;
    }

    public static String getProp(String property) {
        return (props == null) ? init().getProperty(property) : props.getProperty(property);
    }

    public static String getPageRelativeURI(POEnum t) {
        String propName = t.getClass().getSimpleName() + "." + "URL";
        String propValue = getProp(propName);
        if (propValue == null || propValue.equals("null")) {
            System.out.println("No configuration found for " + propName);
            return "";
        }
        return propValue;
    }

    public static By getProp(POEnum t) {
        String propName = t.getClass().getSimpleName() + "." + t;
        String propValue = getProp(propName);
        if (propValue == null || propValue.equals("null")) {
            System.out.println("No configuration found for " + propName);
            return null;
        }
        By by = propValue.startsWith("::xpath::") ? By.xpath(propValue.substring(9)) : By.cssSelector(propValue);
        return by;
    }

    public static Integer getIntProp(String property) {
        return Integer.parseInt(getProp(property));
    }

}
