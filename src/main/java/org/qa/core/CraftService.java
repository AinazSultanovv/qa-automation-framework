package org.qa.core;

import org.qa.models.*;

public class CraftService {
    private final RecipeRepository recipeRepository;
    private final CraftLogger craftLogger;
    private final LegendaryCraftRoller legendaryRoller;
    private final int legendaryChance;

    public CraftService(RecipeRepository recipeRepository, CraftLogger craftLogger,
                        LegendaryCraftRoller legendaryRoller,int legendaryChance){
        this.recipeRepository = recipeRepository;
        this.craftLogger = craftLogger;
        this.legendaryRoller = legendaryRoller;
        this.legendaryChance = legendaryChance;
    }
    public Item craft(Craftsman craftsman, String itemName){
        Recipe recipe = recipeRepository.findByName(itemName);
        if (recipe == null){
            craftLogger.logCraftFailure(craftsman.getName(), itemName, "Recipe not found");
            throw new RecipeNotFoundException("Recipe not found");
        }
        if (craftsman.getLevel() < recipe.getRequiredLevel()){
            craftLogger.logCraftFailure(craftsman.getName(), itemName, "Level too low");
            throw new LevelTooLowException("Level too low");
        }
        if (!craftsman.hasResources(recipe.getRequiredResources())){  // ← отрицание!
            craftLogger.logCraftFailure(craftsman.getName(), itemName, "Insufficient resources");
            throw new InsufficientResourcesException("Insufficient resources");
        }

        craftsman.removeResources(recipe.getRequiredResources());

        int chance = legendaryRoller.roll();
        boolean isLegendary = chance < legendaryChance;

        Item item;
        if (isLegendary){
            item = new Item(recipe.getItemName(), recipe.getBaseDamage() * 2,
                    recipe.getBaseDefense() * 2, true);
        }
        else{
            item = new Item(recipe.getItemName(), recipe.getBaseDamage(),
                    recipe.getBaseDefense(), false);
        }

        craftLogger.logCraftSuccess(craftsman.getName(), recipe.getItemName(), isLegendary);
        return item;
    }
}
