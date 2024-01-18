package com.thefreak.botsmod.init;

import com.mojang.blaze3d.platform.ScreenManager;
import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.objects.containers.CoinSpecContainer;
import com.thefreak.botsmod.objects.containers.SpecialisedCraftingMenu;
import com.thefreak.botsmod.objects.containers.screens.SpecialisedCraftingScreen;
import com.thefreak.botsmod.objects.items.magic.spells.HeatSpellCardItem;
import com.thefreak.botsmod.objects.screens.menu.ArmFactoryMenu;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModContainerTypes {
		public static final DeferredRegister<MenuType<?>> CONTAINER_TYPES = DeferredRegister.create(
				ForgeRegistries.CONTAINERS, BotsMod.MOD_ID);

		public static final RegistryObject<MenuType<SpecialisedCraftingMenu>> SPECIALISED_CRAFTING_MENU = CONTAINER_TYPES.register("specialised_crafting_menu", () -> new MenuType<>(SpecialisedCraftingMenu::new));

		public static final RegistryObject<MenuType<ArmFactoryMenu>> ARM_FACTORY_MENU = CONTAINER_TYPES.register("arm_factory_menu", () -> new MenuType<>(ArmFactoryMenu::new));


}
