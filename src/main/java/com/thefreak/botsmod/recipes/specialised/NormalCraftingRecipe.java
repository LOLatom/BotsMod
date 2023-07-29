package com.thefreak.botsmod.recipes.specialised;

import com.thefreak.botsmod.objects.containers.CoinSpecContainer;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

public interface NormalCraftingRecipe extends Recipe<CoinSpecContainer> {
    default RecipeType<?> getType() {
        return NormalCraftingRecipeRef.INSTANCE;
    }
}
