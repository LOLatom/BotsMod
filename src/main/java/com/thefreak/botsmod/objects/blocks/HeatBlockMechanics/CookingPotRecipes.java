package com.thefreak.botsmod.objects.blocks.HeatBlockMechanics;

import com.thefreak.botsmod.tileentity.CookingPotTileEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class CookingPotRecipes {


    public CookingPotRecipes() {
    }

    public void getCookingResult(CookingPotTileEntity cookingPotTileEntity,
                                 double currentHeatV, ItemStack currentFI, ItemStack currentSI, ItemStack currentTI, float currentTickPassed, int currentLiquidIn,int currentAmount,
                                 ItemStack firstIngredient, ItemStack secondIngredient, ItemStack thirdIngredient, float tickPassed, int liquidIn,
                                 int amount,
                                 ItemStack output1, ItemStack output2, ItemStack output3, int liquidOutput, double minHeatV, double maxHeatV) {
        if (cookingPotTileEntity.getLevel().isClientSide()) {
            System.out.println("Client");
        } else if (!cookingPotTileEntity.getLevel().isClientSide()) {
            System.out.println("Server");
        }
                if (
                        //currentFI == firstIngredient.getContainerItem().getItem().getDefaultInstance() &&
                    //currentSI == secondIngredient.getContainerItem().getItem().getDefaultInstance() &&
                    //currentTI == thirdIngredient.getContainerItem().getItem().getDefaultInstance() &&
                    currentLiquidIn == liquidIn &&
                        currentTickPassed >= tickPassed
                        &&
                      currentHeatV < maxHeatV && currentHeatV > minHeatV && currentAmount == amount
                  ) {
                System.out.println("Process");
                    cookingPotTileEntity.setChanged();
                    cookingPotTileEntity.setItemStackInSlot(output1,0);
                    cookingPotTileEntity.setChanged();
                    cookingPotTileEntity.setItemStackInSlot(output2,1);
                    cookingPotTileEntity.setChanged();
                    cookingPotTileEntity.setItemStackInSlot(output3,2);
                    cookingPotTileEntity.setChanged();
                    cookingPotTileEntity.setLiquidType(liquidOutput);
                    cookingPotTileEntity.setChanged();
                    cookingPotTileEntity.setTickActionned(0);
                    cookingPotTileEntity.setChanged();
                    cookingPotTileEntity.setMixing(false);
                    cookingPotTileEntity.setChanged();
            }


    }
    //Liquids :
    /*
    Nothing = 0, Water = 1, Milk = 2
     */

    public void askCookingResult(ItemStack currentFI, ItemStack currentSIn, ItemStack currentTI, float TickPassed, int liquidIn, int amount, double heatV, CookingPotTileEntity cookingPotTileEntity){

        getCookingResult(cookingPotTileEntity,heatV,currentFI,currentSIn,currentTI,TickPassed,liquidIn,amount,

                Items.SUGAR.getDefaultInstance(),ItemStack.EMPTY,ItemStack.EMPTY,200, 2, 3,

                ItemStack.EMPTY,ItemStack.EMPTY,ItemStack.EMPTY, 0,
                40, 55
        );
        System.out.println("Stop");


    }

}
