package com.thefreak.botsmod.objects.items;

import com.thefreak.botsmod.objects.ister.IGeoriteCrystal;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class GeoriteCrystal extends Item implements IGeoriteCrystal {

    public GeoriteCrystal(Properties properties) {
        super(properties);
    }




    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        //Get stack and NBT and store as separate variables
        ItemStack stack = playerIn.getItemInHand(handIn);
        CompoundTag nbt = stack.getOrCreateTag();

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
        CompoundTag nbt = stack.getOrCreateTag();
        return nbt.getInt("r");
    }

    @Override
    public int glowG(ItemStack stack) {
        CompoundTag nbt = stack.getOrCreateTag();
        return nbt.getInt("g");
    }

    @Override
    public int glowB(ItemStack stack) {
        CompoundTag nbt = stack.getOrCreateTag();
        return nbt.getInt("b");
    }



    @Override
    public void readShareTag(ItemStack stack,  CompoundTag nbt) {
        super.readShareTag(stack,nbt);
        stack.setTag(nbt);
    }


}
