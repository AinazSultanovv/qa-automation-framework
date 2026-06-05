
// Реестр зарегистрированных тестов
package org.qa.core;

import org.qa.models.TestCase;

import java.util.ArrayList;
import java.util.List;

public class TestRegistry {

    private final List<TestCase> tests = new ArrayList<>();

    public void addTestCase(TestCase test){

        for (TestCase existing : tests){
            if (existing.getId().equals(test.getId())) {
                throw new IllegalArgumentException("Тест с ID " + test.getId() + " уже существует");
            }
        }
    }


}
