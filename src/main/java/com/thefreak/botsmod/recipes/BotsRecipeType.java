package com.thefreak.botsmod.recipes;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.recipes.specialised.shaped.ArloShapedRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BotsRecipeType {

    public static final DeferredRegister<RecipeSerializer<?>> RECIPES = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, BotsMod.MOD_ID);

    public static final RegistryObject<RecipeSerializer<ArloShapedRecipe>> ARLO_SHAPED = RECIPES.register("arlo_crafting_shaped", () -> ArloShapedRecipe.Serializer.INSTANCE);
}
