package com.thefreak.botsmod.fluids;

import com.thefreak.botsmod.BotsMod;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class FluidInit {

    public static final DeferredRegister<Block> FLUIDS = DeferredRegister.create(ForgeRegistries.BLOCKS, BotsMod.MOD_ID);

    public static final RegistryObject<LiquidBlock> CELL_FLUID = FLUIDS.register("cell", () -> new LiquidBlock(BOTSFluids.CELL, BlockBehaviour.Properties.of(Material.WATER).noCollission().strength(100.0F).noDrops()));
}
