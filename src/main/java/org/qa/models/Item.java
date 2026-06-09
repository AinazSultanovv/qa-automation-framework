package org.qa.models;

public class Item {
    private String name;
    private int damage;
    private int defense;
    private boolean isLegendary;

    public Item(String name, int damage, int defense, boolean isLegendary){
        this.name = name;
        this.damage = damage;
        this.defense = defense;
        this.isLegendary = isLegendary;
    }
    public String getName(){return name;}
    public int getDamage(){return damage;}
    public int getDefense(){return defense;}
    public boolean isLegendary (){return isLegendary;}
}
