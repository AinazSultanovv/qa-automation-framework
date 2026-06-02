
// Конфигурация тестирования
package org.qa.models;

import java.util.HashMap;
import java.util.Map;

public class TestConfig {
    private Map<String, String> config = new HashMap<>();

    public Map<String, String> getConfigc(){
        return config;
    }

    public void set(String key, String value){
        config.put(key, value);
    }

    public String get(String key) {
        return config.getOrDefault(key, "NOT_FOUND");
    }

    public boolean has(String key) {
        return config.containsKey(key);
    }

    public String remove(String key) {
        return config.remove(key);
    }

    public Map<String, String> getAll() {
        return new HashMap<>(config);
    }


    public int size(){
        return config.size();
    }

}
