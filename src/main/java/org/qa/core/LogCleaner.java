
// Утилита для фильтрации и очистки логов
package org.qa.core;

import java.util.ArrayList;
import java.util.List;

public class LogCleaner {
    public static List<String> cleanLogs(String[] rawLogs) {
        List<String> noDebug = new ArrayList<>();

        if (rawLogs == null) {
            return noDebug;
        }

        for (String log : rawLogs) {
            if (!log.startsWith("DEBUG ")) {
                noDebug.add(log);
            }
        }
        return noDebug;
    }
}
