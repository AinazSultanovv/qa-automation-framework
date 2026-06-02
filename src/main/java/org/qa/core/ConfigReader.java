
// Чтение и парсинг конфигурационных файлов
package org.qa.core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigReader {


    public static String readConfig(String filePath, String key) {


        if (filePath == null || key == null) {
            throw new IllegalArgumentException("File path and key must not be null");
        }



        String content;

        try {

            content = Files.readString(Path.of(filePath));

        } catch (IOException e) {

            System.err.println("Error reading file: " + e.getMessage());


            return "CONFIG_ERROR: " + e.getMessage();
        }


        String[] lines = content.split("\n");


        for (String line : lines) {


            if (line.isEmpty() || line.trim().startsWith("#")) {
                continue;
            }

            String[] parts = line.split("=", 2);

            if (parts.length != 2) {
                continue;
            }

            String currentKey = parts[0].trim();
            String currentValue = parts[1].trim();


            if (currentKey.equals(key)) {
                return currentValue;
            }
        }

        return "KEY_NOT_FOUND";
    }
}