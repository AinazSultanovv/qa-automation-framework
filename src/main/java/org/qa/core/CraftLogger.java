package org.qa.core;
//Отвечает за логирование событий крафта
public interface CraftLogger {
    void logCraftSuccess(String craftsmanName, String itemName, boolean isLegendary);
    void logCraftFailure(String craftsmanName, String itemName, String reason);
}
