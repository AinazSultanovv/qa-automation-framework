package org.qa.models;

import java.util.List;

public class Recipe {
    private String itemName;
    private List<Resource> requiredResources;
    private int requiredLevel; // мин уровень мастера
    private int baseDamage;
    private int baseDefense;

    public Recipe(String itemName, List<Resource> requiredResources,int requiredLevel, int baseDamage, int baseDefense ){
        this.itemName = itemName;
        this.requiredResources = requiredResources;
        this.requiredLevel = requiredLevel;
        this.baseDamage = baseDamage;
        this.baseDefense = baseDefense;
    }
    public String getItemName(){return itemName;}
    public List<Resource> getRequiredResources(){return requiredResources;}
    public int getRequiredLevel(){return requiredLevel;}
    public int getBaseDamage(){return baseDamage;}
    public int getBaseDefense(){return baseDefense;}
}
