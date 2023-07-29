package com.thefreak.botsmod.recipes;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.recipes.specialised.NormalCraftingRecipe;
import com.thefreak.botsmod.recipes.specialised.NormalCraftingRecipeRef;
import com.thefreak.botsmod.recipes.specialised.shaped.SpecialisedShapedRecipe;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;



@Mod.EventBusSubscriber(modid = BotsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegisterRecipesTypesEvent {

    @SubscribeEvent
    public static void registerRecipeT(final RegistryEvent.Register<RecipeSerializer<?>> event) {
        Registry.register(Registry.RECIPE_TYPE, SpecialisedShapedRecipe.Type.ID, SpecialisedShapedRecipe.Type.INSTANCE);

        Registry.register(Registry.RECIPE_TYPE, NormalCraftingRecipeRef.ID, NormalCraftingRecipeRef.INSTANCE);

    }
}
