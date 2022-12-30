package com.thefreak.botsmod.fluids;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.fluids.objects.CellFluid;
import com.thefreak.botsmod.init.iteminit.ItemInitNew;
import com.thefreak.botsmod.objects.items.organs.FleshBucket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BOTSFluids {
    public static final ResourceLocation FLUID_RL = new ResourceLocation(BotsMod.MOD_ID,"blocks/fluids/cell_fluid");

    public static final DeferredRegister<Fluid> BOTS_FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, BotsMod.MOD_ID);


    public static final RegistryObject<FlowingFluid> FLOWING_CELL = BOTS_FLUIDS.register("flowing_cell", () -> new CellFluid.Flowing(BOTSFluids.CELL_FLUIDPROP));
    public static final RegistryObject<FlowingFluid> CELL = BOTS_FLUIDS.register("cell", () -> new CellFluid.Source(BOTSFluids.CELL_FLUIDPROP));

    public static final CellFluid.Properties CELL_FLUIDPROP = new CellFluid.Properties(() -> CELL.get(), () -> FLOWING_CELL.get(),
            FluidAttributes.builder(FLUID_RL, FLUID_RL).density(10).luminosity(3).viscosity(20)
                    .sound(SoundEvents.HONEY_DRINK).overlay(FLUID_RL).color(0xffB08B8B)).slopeFindDistance(3).levelDecreasePerBlock(3).block(
            () -> FluidInit.CELL_FLUID.get()).bucket(()-> BOTSFluids.CELL_FLUID_BUCKET.get());



    public static final RegistryObject<Item> CELL_FLUID_BUCKET = ItemInitNew.ITEMS.register("cell_fluid_bucket", () -> new FleshBucket(Fluids.WATER, new Item.Properties().tab(BotsMod.BotsItemGroup.instance).stacksTo(1)));

}
