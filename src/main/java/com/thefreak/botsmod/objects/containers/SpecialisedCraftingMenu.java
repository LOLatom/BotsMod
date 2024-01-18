package com.thefreak.botsmod.objects.containers;

import com.thefreak.botsmod.init.ModContainerTypes;
import com.thefreak.botsmod.objects.containers.slots.SpecialisedResultSlot;
import com.thefreak.botsmod.objects.items.loreandclueitems.coins.CoinItem;
import com.thefreak.botsmod.recipes.specialised.NormalCraftingRecipe;
import com.thefreak.botsmod.recipes.specialised.NormalCraftingRecipeRef;
import com.thefreak.botsmod.recipes.specialised.shaped.SpecialisedShapedRecipe;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;

import java.util.Optional;

public class SpecialisedCraftingMenu extends AbstractContainerMenu {

    private static final int INV_SLOT_START = 10;
    private static final int INV_SLOT_END = 37;
    private static final int USE_ROW_SLOT_START = 37;
    private static final int USE_ROW_SLOT_END = 46;

    private final ContainerLevelAccess access;
    private final ResultContainer resultContainer = new ResultContainer();

    private final CoinSpecContainer coinSpecContainer = new CoinSpecContainer(this, 3,3);
    private final Player player;
    public SpecialisedCraftingMenu(int id, Inventory inventory) {
        this(id, ContainerLevelAccess.NULL, inventory);
    }

    public SpecialisedCraftingMenu(int pContainerId, ContainerLevelAccess access, Inventory inv) {
        super(ModContainerTypes.SPECIALISED_CRAFTING_MENU.get(), pContainerId);
        this.access = access;
        this.player = inv.player;
        this.addSlot(new SpecialisedResultSlot(inv.player, this.coinSpecContainer, this.resultContainer, 0, 124, 35));

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) {
                this.addSlot(new Slot(this.coinSpecContainer, j + i * 3, 30 + j * 18, 17 + i * 18));
            }
        }

        for(int k = 0; k < 3; ++k) {
            for(int i1 = 0; i1 < 9; ++i1) {
                this.addSlot(new Slot(inv, i1 + k * 9 + 9, 8 + i1 * 18, 84 + k * 18));
            }
        }

        for(int l = 0; l < 9; ++l) {
            this.addSlot(new Slot(inv, l, 8 + l * 18, 142));
        }

        this.addSlot(new Slot(this.coinSpecContainer, 9, 124, -12) {
            @Override
            public boolean mayPlace(ItemStack pStack) {
                if(pStack.getItem() instanceof CoinItem) {
                    return true;
                }else {
                    return false;
                }
            }
        });
    }

    @Override
    public void slotsChanged(Container pInventory) {
        this.access.execute((p_39386_, p_39387_) -> {
            slotChangedCraftingGrid(this, p_39386_, this.player, this.coinSpecContainer, this.resultContainer);
        });
    }

    protected static void slotChangedCraftingGrid(AbstractContainerMenu p_150547_, Level p_150548_, Player p_150549_, CoinSpecContainer craftingContainer, ResultContainer p_150551_) {
        if (!p_150548_.isClientSide) {
            ServerPlayer serverplayer = (ServerPlayer)p_150549_;
            ItemStack itemstack = ItemStack.EMPTY;
            Optional<SpecialisedShapedRecipe> optional = p_150548_.getServer().getRecipeManager().getRecipeFor(SpecialisedShapedRecipe.Type.INSTANCE, craftingContainer, p_150548_);
            Optional<NormalCraftingRecipe> optional2 = p_150548_.getServer().getRecipeManager().getRecipeFor(NormalCraftingRecipeRef.INSTANCE, craftingContainer, p_150548_);

            if (optional.isPresent()) {
                SpecialisedShapedRecipe craftingrecipe = optional.get();
                if (p_150551_.setRecipeUsed(p_150548_, serverplayer, craftingrecipe)) {
                    itemstack = craftingrecipe.assemble(craftingContainer);
                }
            } else if (optional2.isPresent()) {
                NormalCraftingRecipe craftingrecipe = optional2.get();
                if (p_150551_.setRecipeUsed(p_150548_, serverplayer, craftingrecipe)) {
                    itemstack = craftingrecipe.assemble(craftingContainer);
                }
            }

            p_150551_.setItem(0, itemstack);
            p_150547_.setRemoteSlot(0, itemstack);
            serverplayer.connection.send(new ClientboundContainerSetSlotPacket(p_150547_.containerId, p_150547_.incrementStateId(), 0, itemstack));
        }
    }

    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(pIndex);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (pIndex == 0) {
                this.access.execute((p_39378_, p_39379_) -> {
                    itemstack1.getItem().onCraftedBy(itemstack1, p_39378_, pPlayer);
                });
                if (!this.moveItemStackTo(itemstack1, 10, 46, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemstack1, itemstack);
            } else if (pIndex >= 10 && pIndex < 46) {
                if (!this.moveItemStackTo(itemstack1, 1, 10, false)) {
                    if (pIndex < 37) {
                        if (!this.moveItemStackTo(itemstack1, 37, 46, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if (!this.moveItemStackTo(itemstack1, 10, 37, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            } else if (!this.moveItemStackTo(itemstack1, 10, 46, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(pPlayer, itemstack1);
            if (pIndex == 0) {
                pPlayer.drop(itemstack1, false);
            }
        }

        return itemstack;
    }
    public void fillCraftSlotsStackedContents(StackedContents pItemHelper) {
        this.coinSpecContainer.fillStackedContents(pItemHelper);
    }

    public void clearCraftingContent() {
        this.coinSpecContainer.clearContent();
        this.resultContainer.clearContent();
    }

    public boolean recipeMatches(Recipe<? super CoinSpecContainer> pRecipe) {
        return pRecipe.matches(this.coinSpecContainer, this.player.level);
    }

    public boolean canTakeItemForPickAll(ItemStack pStack, Slot pSlot) {
        return pSlot.container != this.resultContainer && super.canTakeItemForPickAll(pStack, pSlot);
    }

    public void removed(Player pPlayer) {
        super.removed(pPlayer);
        this.access.execute((p_39371_, p_39372_) -> {
            this.clearContainer(pPlayer, this.coinSpecContainer);
        });
    }

    public int getResultSlotIndex() {
        return 0;
    }

    public int getGridWidth() {
        return this.coinSpecContainer.getWidth();
    }

    public int getGridHeight() {
        return this.coinSpecContainer.getHeight();
    }

    public int getSize() {
        return 10;
    }

    public boolean shouldMoveToInventory(int p_150553_) {
        return p_150553_ != this.getResultSlotIndex();
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return true;
    }
}
