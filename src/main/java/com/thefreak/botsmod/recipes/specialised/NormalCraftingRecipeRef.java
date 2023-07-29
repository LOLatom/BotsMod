package com.thefreak.botsmod.recipes.specialised;

import com.thefreak.botsmod.recipes.specialised.shaped.SpecialisedShapedRecipe;
import net.minecraft.world.item.crafting.RecipeType;

public class NormalCraftingRecipeRef implements RecipeType<NormalCraftingRecipe> {
        private NormalCraftingRecipeRef() { }
        public static final NormalCraftingRecipeRef INSTANCE = new NormalCraftingRecipeRef();
        public static final String ID = "normal_crafting";
}
