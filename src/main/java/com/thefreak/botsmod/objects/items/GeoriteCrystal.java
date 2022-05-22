package com.thefreak.botsmod.objects.items;

import com.thefreak.botsmod.objects.ister.GeoriteCrystalISTER;
import com.thefreak.botsmod.objects.ister.IGeoriteCrystal;
import com.thefreak.botsmod.objects.items.Capabilities.CapabilityProvideGeoriteCrystal;
import com.thefreak.botsmod.objects.items.Capabilities.GeoriteSoul;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;
import java.util.concurrent.Callable;

public class GeoriteCrystal extends Item implements IGeoriteCrystal {

    public GeoriteCrystal(Properties properties) {
        super(properties);
    }




    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        //Get stack and NBT and store as separate variables
        ItemStack stack = playerIn.getItemInHand(handIn);
        CompoundNBT nbt = stack.getOrCreateTag();

        int r = nbt.getInt("r");
        int g = nbt.getInt("g");
        int b = nbt.getInt("b");


        if (playerIn.isCrouching()) {
            r = 1;
            g = 1;
            b = 1;
        } else {
            r = 0;
            g = 0;
            b = 0;
        }


        nbt.putInt("r", r);
        nbt.putInt("g", g);
        nbt.putInt("b", b);
        stack.setTag(nbt);

        return super.use(worldIn, playerIn, handIn);
    }

    @Override
    public int glowAlpha(ItemStack stack) {
        return 0;
    }


    @Override
    public int glowR(ItemStack stack) {
        CompoundNBT nbt = stack.getOrCreateTag();
        return nbt.getInt("r");
    }

    @Override
    public int glowG(ItemStack stack) {
        CompoundNBT nbt = stack.getOrCreateTag();
        return nbt.getInt("g");
    }

    @Override
    public int glowB(ItemStack stack) {
        CompoundNBT nbt = stack.getOrCreateTag();
        return nbt.getInt("b");
    }



    @Override
    public void readShareTag(ItemStack stack,  CompoundNBT nbt) {
        super.readShareTag(stack,nbt);
        stack.setTag(nbt);
    }


}
