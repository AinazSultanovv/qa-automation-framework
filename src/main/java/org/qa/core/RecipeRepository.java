package org.qa.core;
// Отвечает за поиск рецептов крафта
import org.qa.models.Recipe;

public interface RecipeRepository {
    Recipe findByName(String itemName);
}
