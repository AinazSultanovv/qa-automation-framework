
// Парсинг лог-файлов
package org.qa.core;

public class LogParser {
    public static String parseLog(String rawLog) {

        if (rawLog == null || rawLog.isBlank()) {
            return "Invalid log";
        }

        int bracketOpen = rawLog.indexOf('[');
        int bracketClose = rawLog.indexOf(']');

        int messageStart = rawLog.indexOf(": ", bracketClose);

        if (bracketOpen == -1 || bracketClose == -1 || messageStart == -1) {
            return "Invalid log";
        }


        String timestamp = rawLog.substring(0, 19);
        String level = rawLog.substring(bracketOpen + 1, bracketClose);
        String message = rawLog.substring(messageStart + 2);

        StringBuilder sb = new StringBuilder();
        sb.append("[")
                .append(level)
                .append("] at ")
                .append(timestamp)
                .append(": ")
                .append(message);

        return sb.toString();
    }
}