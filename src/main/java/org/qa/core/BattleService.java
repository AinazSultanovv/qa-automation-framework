package org.qa.core;
import org.qa.models.Character;
import org.qa.core.BattleLogger;
import org.qa.core.CriticalHitRoller;
import org.qa.models.CharacterIsDeadException;
import org.qa.models.OutOfManaException;



public class BattleService {
    private final DamageCalculator damageCalculator;
    private final BattleLogger battleLogger;
    private final int criticalHitChance;
    private CriticalHitRoller criticalHitRoller;

    public BattleService(DamageCalculator damageCalculator,BattleLogger battleLogger, int criticalHitChance, CriticalHitRoller criticalHitRoller){
        this.damageCalculator = damageCalculator;
        this.battleLogger = battleLogger;
        this.criticalHitChance = criticalHitChance;
        this.criticalHitRoller = criticalHitRoller;
    }
    public void attack(Character attacker, Character defender){
        if (!attacker.isAlive()){
            throw new CharacterIsDeadException("Attacker is dead");
        }
        if (!defender.isAlive()){
            throw new CharacterIsDeadException("Defender is dead");
        }
        if (attacker.getMana() < 10){
            throw new OutOfManaException("атака стоит 10 маны");
        }
        int damage = damageCalculator.calculateDamage(attacker, defender);
        if (criticalHitRoller.roll() < criticalHitChance) {
            damage = damage * 3;
        }
        attacker.spendMana(10);
        defender.takeDamage(damage);

        battleLogger.logAttack(attacker.getName(), defender.getName(), damage);
        if (!defender.isAlive()){battleLogger.logDeath(defender.getName());}

    }

}
