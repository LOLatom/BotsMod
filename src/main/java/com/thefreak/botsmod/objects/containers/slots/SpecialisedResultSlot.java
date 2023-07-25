package com.thefreak.botsmod.objects.containers.slots;

import com.thefreak.botsmod.init.ItemInit;
import com.thefreak.botsmod.init.iteminit.ItemInitNew;
import com.thefreak.botsmod.objects.containers.CoinSpecContainer;
import com.thefreak.botsmod.recipes.BotsRecipeType;
import com.thefreak.botsmod.recipes.specialised.shaped.ArloShapedRecipe;
import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.RecipeHolder;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;

public class SpecialisedResultSlot extends Slot {
    private final CoinSpecContainer specContainer;
    private final CraftingContainer craftSlots;
    private final Player player;
    private int removeCount;

    public SpecialisedResultSlot(Player player, CraftingContainer craftingContainer, CoinSpecContainer specContainer, Container p_40168_, int index, int x, int y) {
        super(p_40168_, index, x, y);
        this.player = player;
        this.craftSlots = craftingContainer;
        this.specContainer = specContainer;
    }
    public boolean mayPlace(ItemStack pStack) {
        return false;
    }

    /**
     * Decrease the size of the stack in slot (first int arg) by the amount of the second int arg. Returns the new stack.
     */
    public ItemStack remove(int pAmount) {
        if (this.hasItem()) {
            this.removeCount += Math.min(pAmount, this.getItem().getCount());
        }

        return super.remove(pAmount);
    }

    /**
     * the itemStack passed in is the output - ie, iron ingots, and pickaxes, not ore and wood. Typically increases an
     * internal count then calls onCrafting(item).
     */
    protected void onQuickCraft(ItemStack pStack, int pAmount) {
        this.removeCount += pAmount;
        this.checkTakeAchievements(pStack);
    }

    protected void onSwapCraft(int pNumItemsCrafted) {
        this.removeCount += pNumItemsCrafted;
    }

    /**
     * the itemStack passed in is the output - ie, iron ingots, and pickaxes, not ore and wood.
     */
    protected void checkTakeAchievements(ItemStack pStack) {
        if (this.removeCount > 0) {
            pStack.onCraftedBy(this.player.level, this.player, this.removeCount);
            net.minecraftforge.event.ForgeEventFactory.firePlayerCraftingEvent(this.player, pStack, this.craftSlots);
        }

        if (this.container instanceof RecipeHolder) {
            ((RecipeHolder)this.container).awardUsedRecipes(this.player);
        }

        this.removeCount = 0;
    }

    public void onTake(Player pPlayer, ItemStack pStack) {
        NonNullList<ItemStack> nonnulllist;
        this.checkTakeAchievements(pStack);
        net.minecraftforge.common.ForgeHooks.setCraftingPlayer(pPlayer);
        if ((specContainer.getItem(0)).getItem() == ItemInitNew.ARLO_COIN.get()) {
            nonnulllist = pPlayer.level.getRecipeManager().getRemainingItemsFor(ArloShapedRecipe.Type.INSTANCE, this.craftSlots, pPlayer.level);
        } else {
            nonnulllist = pPlayer.level.getRecipeManager().getRemainingItemsFor(RecipeType.CRAFTING, this.craftSlots, pPlayer.level);
        }

        net.minecraftforge.common.ForgeHooks.setCraftingPlayer(null);
        for(int i = 0; i < nonnulllist.size(); ++i) {
            ItemStack itemstack = this.craftSlots.getItem(i);
            ItemStack itemstack1 = nonnulllist.get(i);
            if (!itemstack.isEmpty()) {
                this.craftSlots.removeItem(i, 1);
                itemstack = this.craftSlots.getItem(i);
            }

            if (!itemstack1.isEmpty()) {
                if (itemstack.isEmpty()) {
                    this.craftSlots.setItem(i, itemstack1);
                } else if (ItemStack.isSame(itemstack, itemstack1) && ItemStack.tagMatches(itemstack, itemstack1)) {
                    itemstack1.grow(itemstack.getCount());
                    this.craftSlots.setItem(i, itemstack1);
                } else if (!this.player.getInventory().add(itemstack1)) {
                    this.player.drop(itemstack1, false);
                }
            }
        }

    }

}
