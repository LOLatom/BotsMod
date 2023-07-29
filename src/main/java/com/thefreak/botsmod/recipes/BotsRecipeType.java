package com.thefreak.botsmod.recipes;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.recipes.specialised.shaped.ShapedNormalCraftRecipe;
import com.thefreak.botsmod.recipes.specialised.shaped.SpecialisedShapedRecipe;
import com.thefreak.botsmod.recipes.specialised.shapeless.ShapelessNormalCraftRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BotsRecipeType {

    public static final DeferredRegister<RecipeSerializer<?>> RECIPES = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, BotsMod.MOD_ID);

    public static final RegistryObject<RecipeSerializer<SpecialisedShapedRecipe>> SPECIALISED_SHAPED = RECIPES.register("specialised_crafting_shaped", () -> SpecialisedShapedRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<ShapedNormalCraftRecipe>> NORMAL_SHAPED = RECIPES.register("normal_shaped", () -> ShapedNormalCraftRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<ShapelessNormalCraftRecipe>> NORMAL_SHAPELESS = RECIPES.register("normal_shapeless", () -> ShapelessNormalCraftRecipe.Serializer.INSTANCE);


}
