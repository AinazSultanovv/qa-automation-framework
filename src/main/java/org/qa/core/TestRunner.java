
// Запуск тестовых сценариев
package org.qa.core;

import java.util.List;

public class TestRunner {
    public static void performActions(List<WebElementBase> elements){
        for (WebElementBase element : elements) {
            element.highlight();
            element.click();
        }
    }
}
