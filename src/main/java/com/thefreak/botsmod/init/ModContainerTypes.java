package com.thefreak.botsmod.init;

import com.thefreak.botsmod.BotsMod;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainerTypes {
		public static final DeferredRegister<MenuType<?>> CONTAINER_TYPES = DeferredRegister.create(
				ForgeRegistries.CONTAINERS, BotsMod.MOD_ID);
		

}
