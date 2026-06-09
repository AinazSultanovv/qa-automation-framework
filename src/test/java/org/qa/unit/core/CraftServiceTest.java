package org.qa.unit.core;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.qa.core.*;
import org.qa.models.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CraftServiceTest {
    @Mock
    RecipeRepository recipeRepository;
    @Mock
    CraftLogger craftLogger;
    @Mock
    LegendaryCraftRoller legendaryCraftRoller;
    int legendaryChance = 10;


    CraftService craftService;
    @BeforeEach
    void setUp() {
        craftService = new CraftService(recipeRepository, craftLogger, legendaryCraftRoller, legendaryChance);
    }

    @Test
    void validCraft() {
        // Arrange
        Recipe recipe = new Recipe(
                "Меч дракона",
                Arrays.asList(new Resource("iron", 5), new Resource("wood", 3)),
                10,  5,  7
        );

        List<Resource> inventory = Arrays.asList(
                new Resource("iron", 10),
                new Resource("wood", 5)
        );
        Craftsman craftsman = new Craftsman("Aiinaz", 15, inventory);

        when(recipeRepository.findByName("Меч дракона")).thenReturn(recipe);
        when(legendaryCraftRoller.roll()).thenReturn(50);

        // Act
        Item result = craftService.craft(craftsman, "Меч дракона");

        // Assert
        assertEquals("Меч дракона", result.getName());
        assertEquals(5, result.getDamage());
        assertEquals(7, result.getDefense());
        assertFalse(result.isLegendary());

        assertEquals(5, inventory.get(0).getQuantity());
        assertEquals(2, inventory.get(1).getQuantity());

        verify(craftLogger).logCraftSuccess("Aiinaz", "Меч дракона", false);
        verify(craftLogger, never()).logCraftFailure(anyString(), anyString(), anyString());
    }
    @Test
    void successCraftLegendaryItem(){
        //Arrange
        Recipe recipe = new Recipe(
                "Меч дракона",
                Arrays.asList(new Resource("iron", 5), new Resource("wood", 3)),
                10,  5,  7
        );

        List<Resource> inventory = Arrays.asList(
                new Resource("iron", 10),
                new Resource("wood", 5)
        );
        Craftsman craftsman = new Craftsman("Aiinaz", 15, inventory);

        when(recipeRepository.findByName("Меч дракона")).thenReturn(recipe);
        when(legendaryCraftRoller.roll()).thenReturn(2);

        //Act
        Item result = craftService.craft(craftsman, "Меч дракона");

        //Assert
        assertEquals("Меч дракона", result.getName());
        assertEquals(10, result.getDamage());
        assertEquals(14, result.getDefense());
        assertTrue(result.isLegendary());

        assertEquals(5, inventory.get(0).getQuantity());
        assertEquals(2, inventory.get(1).getQuantity());

        verify(craftLogger).logCraftSuccess("Aiinaz", "Меч дракона", true);
        verify(craftLogger, never()).logCraftFailure(anyString(), anyString(), anyString());
    }
    @Test
    void receptNot(){
        //Arrange
        List<Resource> inventory = Arrays.asList(
                new Resource("iron", 10),
                new Resource("wood", 5)
        );
        //Act
        Craftsman craftsman = new Craftsman("Aiinaz", 15, inventory);
        assertThrows(RecipeNotFoundException.class, () -> {
            Item result = craftService.craft(craftsman, "Меч дракона");
        }, "Recipe not found");
        //Arrange
        assertEquals(10, inventory.get(0).getQuantity());
        assertEquals(5, inventory.get(1).getQuantity());
        verify(craftLogger).logCraftFailure("Aiinaz", "Меч дракона", "Recipe not found");
    }
    @Test
    void levelDown(){
        // Arrange
        Recipe recipe = new Recipe(
                "Меч дракона",
                Arrays.asList(new Resource("iron", 5), new Resource("wood", 3)),
                10,  5,  7
        );

        List<Resource> inventory = Arrays.asList(
                new Resource("iron", 10),
                new Resource("wood", 5)
        );
        Craftsman craftsman = new Craftsman("Aiinaz", 9, inventory);

        when(recipeRepository.findByName("Меч дракона")).thenReturn(recipe);

        // Act
        assertThrows(LevelTooLowException.class, () -> {
            Item result = craftService.craft(craftsman, "Меч дракона");
        }, "Level too low");

        verify(craftLogger).logCraftFailure("Aiinaz", "Меч дракона", "Level too low");

        // Assert

        assertEquals(10, inventory.get(0).getQuantity());
        assertEquals(5, inventory.get(1).getQuantity());

    }


}
