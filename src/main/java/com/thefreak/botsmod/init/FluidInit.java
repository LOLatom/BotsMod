package com.thefreak.botsmod.init;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.init.iteminit.ItemInitNew;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class FluidInit {
 /*   public static final ResourceLocation FLUID_RL = new ResourceLocation(BotsMod.MOD_ID,"blocks/fluids/cell_fluid");

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, BotsMod.MOD_ID);

    public static final RegistryObject<FlowingFluid> CELL_FLUID = FLUIDS.register("cell_fluid", () -> new ForgeFlowingFluid.Source(FluidInit.CELL_FLUIDPROP));

    public static final RegistryObject<FlowingFluid> CELL_FLUID_FLOWING = FLUIDS.register("cell_flowing", () -> new ForgeFlowingFluid.Flowing(FluidInit.CELL_FLUIDPROP));

    public static final ForgeFlowingFluid.Properties CELL_FLUIDPROP = new ForgeFlowingFluid.Properties(() -> CELL_FLUID.get(), () -> CELL_FLUID_FLOWING.get(),
            FluidAttributes.builder(FLUID_RL, FLUID_RL).density(30).luminosity(2).viscosity(10)
                    .sound(SoundEvents.HONEY_DRINK).overlay(FLUID_RL).color(0xbf70434a)).slopeFindDistance(2).levelDecreasePerBlock(3).block(
                    () -> FluidInit.CELL_FLUID_BLOCK.get()).bucket(()-> ItemInitNew.CELL_FLUID_BUCKET.get());

    public static final RegistryObject<LiquidBlock> CELL_FLUID_BLOCK = BlockInitNew.BLOCKS.register("cell",() -> new LiquidBlock(
            () -> CELL_FLUID.get(), BlockBehaviour.Properties.of(Material.WATER).noCollission().strength(100F).noDrops()));

  */

}
