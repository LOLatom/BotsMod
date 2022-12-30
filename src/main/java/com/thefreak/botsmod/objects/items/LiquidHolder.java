package com.thefreak.botsmod.objects.items;

import com.thefreak.botsmod.tileentity.CookingPotTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

public class LiquidHolder extends Item {


    public LiquidHolder(Properties pProperties) {
        super(pProperties);

    }




    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player playerIn, InteractionHand handIn) {
        ItemStack stack = playerIn.getItemInHand(handIn);
        CompoundTag nbt = stack.getOrCreateTag();
        HitResult raytraceresult = getPlayerPOVHitResult(level, playerIn, ClipContext.Fluid.SOURCE_ONLY);
        BlockHitResult blockraytraceresult = (BlockHitResult)raytraceresult;
        BlockPos blockpos = blockraytraceresult.getBlockPos();
        System.out.println(getLiquidType(stack) + "LiquidHolder PosClick: " + blockpos);

        if (level.getBlockEntity(blockpos) instanceof CookingPotTileEntity) {
            System.out.println(getLiquidType(stack));
            CookingPotTileEntity cookingPotTileEntity = (CookingPotTileEntity) level.getBlockEntity(blockpos);
            int liquidInPot = cookingPotTileEntity.getLiquidType();
            int liquidAmount = cookingPotTileEntity.getLiquidAmount();

            if (liquidAmount == 0 && getLiquidType(stack) != 0) {
                cookingPotTileEntity.setLiquidType(getLiquidType(stack));
                cookingPotTileEntity.setLiquidAmount(1);
                nbt.putInt("liquidIn", 0);
                System.out.println(getLiquidType(stack));
                return InteractionResultHolder.success(stack);
            } else if (liquidAmount >= 1 && liquidAmount < 3 && liquidInPot == getLiquidType(stack)) {
                cookingPotTileEntity.setLiquidAmount(liquidAmount + 1);
                nbt.putInt("liquidIn", 0);
                System.out.println(getLiquidType(stack));
                return InteractionResultHolder.success(stack);
            } else if (liquidAmount <= 3 && liquidAmount > 1 && getLiquidType(stack) == 0) {
                cookingPotTileEntity.setLiquidAmount(liquidAmount - 1);
                nbt.putInt("liquidIn", liquidInPot);
                System.out.println(getLiquidType(stack));
                return InteractionResultHolder.success(stack);
            } else if (liquidAmount == 1 && getLiquidType(stack) == 0) {
                nbt.putInt("liquidIn", liquidInPot);
                cookingPotTileEntity.setLiquidAmount(0);
                cookingPotTileEntity.setLiquidType(0);
                System.out.println(getLiquidType(stack));
                return InteractionResultHolder.success(stack);
            } else {
                return InteractionResultHolder.pass(stack);
            }
        } else {
            return InteractionResultHolder.fail(stack);
        }


    }


    public int getLiquidType(ItemStack stack) {
        CompoundTag nbt = stack.getOrCreateTag();
        return nbt.getInt("b");
    }

    @Override
    public void readShareTag(ItemStack stack,  CompoundTag nbt) {
        super.readShareTag(stack,nbt);
        stack.setTag(nbt);
    }


}
