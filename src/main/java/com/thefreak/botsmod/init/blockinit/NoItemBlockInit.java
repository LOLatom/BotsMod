package com.thefreak.botsmod.init.blockinit;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.objects.blocks.plants.LogMushroom;
import com.thefreak.botsmod.objects.blocks.plants.LuxureMelon;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class NoItemBlockInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, BotsMod.MOD_ID);

    public static final RegistryObject<Block> LUXURE_MELON_PLANT = BLOCKS.register("luxure_melon_plant", () -> new LuxureMelon(BlockBehaviour.Properties.of(Material.GRASS).sound(SoundType.BIG_DRIPLEAF).requiresCorrectToolForDrops().emissiveRendering((p_61036_, p_61037_, p_61038_) -> {return true;}).randomTicks(), 4,0,4,12,14,12));

    public static final RegistryObject<Block> MUD_PUDDLE_FULL_BLOCK = BLOCKS.register("mud_puddle_block", () -> new Block(BlockBehaviour.Properties.of(Material.DIRT).sound(SoundType.ROOTED_DIRT).requiresCorrectToolForDrops()));

}
