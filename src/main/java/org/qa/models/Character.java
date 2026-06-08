package org.qa.models;

public class Character {
    private String name;
    private int health;
    private double mana;
    private boolean isAlive;

    public Character(String name, int health, double mana){
        this.name = name;
        this.health = health;
        this.mana = mana;

        this.isAlive = true;
    }
    public String getName(){return name;}
    public int getHealth(){return health;}
    public double getMana(){return mana;}
    public boolean isAlive(){return this.isAlive;}

    public void takeDamage(int damage){
        this.health = this.health - damage;

        if (health <= 0 ){
            this.isAlive = false;
            health = 0;
        }
    }
    public void spendMana(int amount){
        this.mana = this.mana - amount;
    }
}


