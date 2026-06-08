package org.qa.core;
import org.qa.models.Character;
// отвечает за логирование событий боя
public interface BattleLogger {
    void logAttack(String attackerName, String defenderName, int damage); // Логирует факт атаки
    void logDeath(String characterName); // Логирует факт смерти персонажа
}
