
// Управление тест-кейсами
package org.qa.core;

import java.util.ArrayList;
import java.util.List;

public class TestCaseManager {
    private List<String> testCases = new ArrayList<>();

    public List<String> gettestCases(){
        return testCases;
    }


    public void addTestCase(String testCaseName){
        if (!testCases.contains(testCaseName)) {
            testCases.add(testCaseName);
        }
    }


    public boolean removeTestCase(String testCaseName){
        return testCases.remove(testCaseName);
    }


    public boolean hasTestCase(String testCaseName){
        return testCases.contains(testCaseName);
    }


    public List<String> getAllTestCases() {
        return new ArrayList<>(testCases);
    }


    public int getCount(){
        return testCases.size();
    }
}
