package org.qa.core;
import java.util.Random;

public class RandomCriticalHitRoller implements CriticalHitRoller {
    private final Random random;

    public RandomCriticalHitRoller(Random random) {
        this.random = random;
    }

    @Override
    public int roll() {
        return random.nextInt(100);
    }
}