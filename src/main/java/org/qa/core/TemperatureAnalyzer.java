
// Анализ температурных данных с валидацией
package org.qa.core;

import java.util.ArrayList;
import java.util.List;

public class TemperatureAnalyzer {

    public static List<Double> filterValid(Double[] temps, double min, double max){

        if (temps == null){ throw new IllegalArgumentException("Массив датчиков не может быть null");};
        if (min > max){throw new IllegalArgumentException("Некорректный диапазон: min > max");};

        List<Double> validTemps = new ArrayList<>();

        for (Double temp: temps){
            if(temp != null && (min <= temp) && (temp <= max) ){
                validTemps.add(temp);
            }
        }
        return validTemps;
    }
    public static double getAverage(List<Double> validTemps){
        if (validTemps == null || validTemps.isEmpty()){ throw new IllegalArgumentException("Массив датчиков не может быть null");};

        double sum =0;
        for (Double valid: validTemps){
            sum += valid;
        }
        return sum / validTemps.size();
    }
}
