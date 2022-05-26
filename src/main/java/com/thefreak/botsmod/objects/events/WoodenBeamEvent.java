package com.thefreak.botsmod.objects.events;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.objects.blockpackage.ConnectedWoodBeam;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.stats.Stat;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.living.LivingDestroyBlockEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = BotsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public class WoodenBeamEvent {


    @SubscribeEvent
    public static void WoodenBeamAddedRessistance(PlayerEvent.BreakSpeed event) {
        BlockPos pos = event.getPos();
        Level world = event.getEntity().level;

        if (world.getBlockState(pos.below()).getBlock() instanceof ConnectedWoodBeam || world.getBlockState(pos.above()).getBlock() instanceof ConnectedWoodBeam ||
                world.getBlockState(pos.east()).getBlock() instanceof ConnectedWoodBeam || world.getBlockState(pos.west()).getBlock() instanceof ConnectedWoodBeam ||
                world.getBlockState(pos.south()).getBlock() instanceof ConnectedWoodBeam || world.getBlockState(pos.north()).getBlock() instanceof ConnectedWoodBeam ||

                world.getBlockState(pos.below(2)).getBlock() instanceof ConnectedWoodBeam || world.getBlockState(pos.above(2)).getBlock() instanceof ConnectedWoodBeam ||
                world.getBlockState(pos.east(2)).getBlock() instanceof ConnectedWoodBeam || world.getBlockState(pos.west(2)).getBlock() instanceof ConnectedWoodBeam ||
                world.getBlockState(pos.south(2)).getBlock() instanceof ConnectedWoodBeam || world.getBlockState(pos.north(2)).getBlock() instanceof ConnectedWoodBeam||

                world.getBlockState(pos.below().south()).getBlock() instanceof ConnectedWoodBeam || world.getBlockState(pos.above().south()).getBlock() instanceof ConnectedWoodBeam ||
                world.getBlockState(pos.east().above()).getBlock() instanceof ConnectedWoodBeam || world.getBlockState(pos.west().below()).getBlock() instanceof ConnectedWoodBeam ||
                world.getBlockState(pos.south().east()).getBlock() instanceof ConnectedWoodBeam || world.getBlockState(pos.north().west()).getBlock() instanceof ConnectedWoodBeam||

                world.getBlockState(pos.below().north()).getBlock() instanceof ConnectedWoodBeam || world.getBlockState(pos.above().north()).getBlock() instanceof ConnectedWoodBeam ||
                world.getBlockState(pos.east().below()).getBlock() instanceof ConnectedWoodBeam || world.getBlockState(pos.west().above()).getBlock() instanceof ConnectedWoodBeam ||
                world.getBlockState(pos.south().west()).getBlock() instanceof ConnectedWoodBeam || world.getBlockState(pos.north().east()).getBlock() instanceof ConnectedWoodBeam ) {

            BlockState block = world.getBlockState(pos);
            event.setNewSpeed(event.getNewSpeed() * 0.25F);
        }


    }




    }


