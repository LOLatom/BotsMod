package com.thefreak.botsmod.recipes;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.recipes.specialised.shaped.ArloShapedRecipe;
import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;



@Mod.EventBusSubscriber(modid = BotsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegisterRecipesTypesEvent {

    @SubscribeEvent
    public static void registerRecipeT(final RegistryEvent.Register<RecipeSerializer<?>> event) {
        Registry.register(Registry.RECIPE_TYPE, ArloShapedRecipe.Type.ID, ArloShapedRecipe.Type.INSTANCE);
    }
}
