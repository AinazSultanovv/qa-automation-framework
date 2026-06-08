package org.qa.core;
import org.qa.models.Character;
// Принимает атакующего и защищающегося, возвращает рассчитанное количество урона (int).
public interface DamageCalculator {
    int calculateDamage(Character attacker, Character defender);
}
