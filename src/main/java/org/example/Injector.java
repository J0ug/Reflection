package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

public class Injector {

    private final Properties properties;

    public Injector(String propertiesFilePath) throws IOException {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream(propertiesFilePath)) {
            properties.load(fis);
        }
    }

    public <T> T inject(T object) {
        Class<?> clazz = object.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(AutoInjectable.class)) {
                String interfaceName = field.getType().getName();
                String implClassName = properties.getProperty(interfaceName);

                if (implClassName != null) {
                    try {
                        Class<?> implClass = Class.forName(implClassName);
                        Object implInstance = implClass.getDeclaredConstructor().newInstance();

                        field.setAccessible(true);
                        field.set(object, implInstance);
                    } catch (Exception e) {
                        throw new RuntimeException("Error injecting field: " + field.getName(), e);
                    }
                }
            }
        }

        return object;
    }
}
