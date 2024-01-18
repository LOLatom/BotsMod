package com.thefreak.botsmod.objects.screens.menu;

import com.thefreak.botsmod.init.ModContainerTypes;
import com.thefreak.botsmod.objects.items.organs.tools.arms.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

public class ArmFactoryMenu extends AbstractContainerMenu {


    public ArmFactoryMenu(int id, Inventory inventory) {
        this(id, inventory, new SimpleContainer(7));
    }
    public ArmFactoryMenu(int pContainerId, Inventory inv, SimpleContainer container) {
        super(ModContainerTypes.ARM_FACTORY_MENU.get(), pContainerId);

        this.addSlot(new Slot(container,0,80,9){
            @Override
            public boolean mayPlace(ItemStack pStack) {
                if (pStack.getItem() instanceof MechanicalArm) {
                    return true;
                } else {
                    return false;
                }
            }

            @Override
            public void set(ItemStack pStack) {
                if (pStack.getItem() instanceof MechanicalArm) {
                    CompoundTag nbt = pStack.getOrCreateTag();
                    ItemStackHandler parts = new ItemStackHandler(6);
                    parts.deserializeNBT(nbt.getCompound("parts"));
                    for (int i = 0; i < parts.getSlots(); i++) {
                        container.setItem(i + 1,parts.getStackInSlot(i));

                    }
                }
                super.set(pStack);
            }



            @Override
            public void onTake(Player pPlayer, ItemStack pStack) {
                if (pStack.getItem() instanceof MechanicalArm) {
                    CompoundTag nbt = pStack.getOrCreateTag();
                    ItemStackHandler parts = new ItemStackHandler(6);
                    for (int i = 0; i < parts.getSlots(); i++) {
                        parts.setStackInSlot(i,container.getItem(i+1));
                    }
                    nbt.put("parts", parts.serializeNBT());
                    for (int i = 0; i < parts.getSlots(); i++) {
                        container.setItem(i + 1,ItemStack.EMPTY);
                    }


                }
                super.onTake(pPlayer, pStack);
            }
        });

        this.addSlot(new Slot(container,1,44,-10) {

            @Override
            public boolean mayPlace(ItemStack pStack) {
                if (!container.getItem(0).isEmpty() && pStack.getItem() instanceof UpperOuterPlateComponent) {
                    return true;
                } else {
                    return false;
                }
            }

            @Override
            public void set(ItemStack pStack) {
                if (container.getItem(0).getItem() instanceof MechanicalArm) {
                    CompoundTag nbt = container.getItem(0).getOrCreateTag();
                    ItemStackHandler parts = new ItemStackHandler(6);
                    parts.deserializeNBT(nbt.getCompound("parts"));
                    parts.setStackInSlot(0, pStack);
                    nbt.put("parts",parts.serializeNBT());

                }
                super.set(pStack);
            }



            @Override
            public void onTake(Player pPlayer, ItemStack pStack) {
                if (container.getItem(0).getItem() instanceof MechanicalArm) {
                    CompoundTag nbt = container.getItem(0).getOrCreateTag();
                    ItemStackHandler parts = new ItemStackHandler(6);
                    parts.deserializeNBT(nbt.getCompound("parts"));
                    parts.setStackInSlot(0, ItemStack.EMPTY);
                    nbt.put("parts", parts.serializeNBT());
                }
                super.onTake(pPlayer, pStack);
            }
        });

        this.addSlot(new Slot(container,2,44,16){

            @Override
            public boolean mayPlace(ItemStack pStack) {
                if (!container.getItem(0).isEmpty() && pStack.getItem() instanceof LowerOuterPlateComponent) {
                    return true;
                } else {
                    return false;
                }
            }
            @Override
            public void set(ItemStack pStack) {
                if (container.getItem(0).getItem() instanceof MechanicalArm) {
                    CompoundTag nbt = container.getItem(0).getOrCreateTag();
                    ItemStackHandler parts = new ItemStackHandler(6);
                    parts.deserializeNBT(nbt.getCompound("parts"));
                    parts.setStackInSlot(1, pStack);
                    nbt.put("parts",parts.serializeNBT());

                }
                super.set(pStack);
            }



            @Override
            public void onTake(Player pPlayer, ItemStack pStack) {
                if (container.getItem(0).getItem() instanceof MechanicalArm) {
                    CompoundTag nbt = container.getItem(0).getOrCreateTag();
                    ItemStackHandler parts = new ItemStackHandler(6);
                    parts.deserializeNBT(nbt.getCompound("parts"));
                    parts.setStackInSlot(1, ItemStack.EMPTY);
                    nbt.put("parts", parts.serializeNBT());
                }
                super.onTake(pPlayer, pStack);
            }
        });

        this.addSlot(new Slot(container,3,47,52){

            @Override
            public boolean mayPlace(ItemStack pStack) {
                if (!container.getItem(0).isEmpty() && pStack.getItem() instanceof ModuleComponent moduleComponent) {
                    if (container.getItem(5).getItem() instanceof AdditionComponent additionComponent) {
                        if (additionComponent.hasModuleInput(container.getItem(5)) && (moduleComponent.getRequiredStackToBePlaced().equals(container.getItem(5).getItem()))) {
                            return true;
                        } else return false;
                    } else return false;

                } else return false;
            }
            @Override
            public void set(ItemStack pStack) {
                if (container.getItem(0).getItem() instanceof MechanicalArm) {
                    CompoundTag nbt = container.getItem(0).getOrCreateTag();
                    ItemStackHandler parts = new ItemStackHandler(6);
                    parts.deserializeNBT(nbt.getCompound("parts"));
                    parts.setStackInSlot(2, pStack);
                    nbt.put("parts",parts.serializeNBT());

                }
                super.set(pStack);
            }



            @Override
            public void onTake(Player pPlayer, ItemStack pStack) {
                if (container.getItem(0).getItem() instanceof MechanicalArm) {
                    CompoundTag nbt = container.getItem(0).getOrCreateTag();
                    ItemStackHandler parts = new ItemStackHandler(6);
                    parts.deserializeNBT(nbt.getCompound("parts"));
                    parts.setStackInSlot(2, ItemStack.EMPTY);
                    nbt.put("parts", parts.serializeNBT());
                }
                super.onTake(pPlayer, pStack);
            }
        });

        this.addSlot(new Slot(container,4,69,52){
            @Override
            public boolean mayPlace(ItemStack pStack) {
                if (!container.getItem(0).isEmpty() && pStack.getItem() instanceof InnerPlateComponent) {
                    return true;
                } else {
                    return false;
                }
            }
            @Override
            public void set(ItemStack pStack) {
                if (container.getItem(0).getItem() instanceof MechanicalArm) {
                    CompoundTag nbt = container.getItem(0).getOrCreateTag();
                    ItemStackHandler parts = new ItemStackHandler(6);
                    parts.deserializeNBT(nbt.getCompound("parts"));
                    parts.setStackInSlot(3, pStack);
                    nbt.put("parts",parts.serializeNBT());

                }
                super.set(pStack);
            }



            @Override
            public void onTake(Player pPlayer, ItemStack pStack) {
                if (container.getItem(0).getItem() instanceof MechanicalArm) {
                    CompoundTag nbt = container.getItem(0).getOrCreateTag();
                    ItemStackHandler parts = new ItemStackHandler(6);
                    parts.deserializeNBT(nbt.getCompound("parts"));
                    parts.setStackInSlot(3, ItemStack.EMPTY);
                    nbt.put("parts", parts.serializeNBT());
                }
                super.onTake(pPlayer, pStack);
            }
        });

        this.addSlot(new Slot(container,5,91,52){
            @Override
            public boolean mayPlace(ItemStack pStack) {
                if (!container.getItem(0).isEmpty() && pStack.getItem() instanceof AdditionComponent) {
                    return true;
                } else {
                    return false;
                }
            }
            @Override
            public void set(ItemStack pStack) {
                if (container.getItem(0).getItem() instanceof MechanicalArm) {
                    CompoundTag nbt = container.getItem(0).getOrCreateTag();
                    ItemStackHandler parts = new ItemStackHandler(6);
                    parts.deserializeNBT(nbt.getCompound("parts"));
                    parts.setStackInSlot(4, pStack);
                    nbt.put("parts",parts.serializeNBT());

                }
                super.set(pStack);
            }



            @Override
            public void onTake(Player pPlayer, ItemStack pStack) {
                if (container.getItem(0).getItem() instanceof MechanicalArm) {
                    CompoundTag nbt = container.getItem(0).getOrCreateTag();
                    ItemStackHandler parts = new ItemStackHandler(6);
                    parts.deserializeNBT(nbt.getCompound("parts"));
                    parts.setStackInSlot(4, ItemStack.EMPTY);
                    nbt.put("parts", parts.serializeNBT());
                }
                super.onTake(pPlayer, pStack);
            }
        });

        this.addSlot(new Slot(container,6,113,52){
            @Override
            public boolean mayPlace(ItemStack pStack) {
                if (!container.getItem(0).isEmpty() && pStack.getItem() instanceof PistonComponent) {
                    return true;
                } else {
                    return false;
                }
            }
            @Override
            public void set(ItemStack pStack) {
                if (container.getItem(0).getItem() instanceof MechanicalArm) {
                    CompoundTag nbt = container.getItem(0).getOrCreateTag();
                    ItemStackHandler parts = new ItemStackHandler(6);
                    parts.deserializeNBT(nbt.getCompound("parts"));
                    parts.setStackInSlot(5, pStack);
                    nbt.put("parts",parts.serializeNBT());

                }
                super.set(pStack);
            }



            @Override
            public void onTake(Player pPlayer, ItemStack pStack) {
                if (container.getItem(0).getItem() instanceof MechanicalArm) {
                    CompoundTag nbt = container.getItem(0).getOrCreateTag();
                    ItemStackHandler parts = new ItemStackHandler(6);
                    parts.deserializeNBT(nbt.getCompound("parts"));
                    parts.setStackInSlot(5, ItemStack.EMPTY);
                    nbt.put("parts", parts.serializeNBT());
                }
                super.onTake(pPlayer, pStack);
            }
        });

        for(int k = 0; k < 3; ++k) {
            for(int i1 = 0; i1 < 9; ++i1) {
                this.addSlot(new Slot(inv, i1 + k * 9 + 9, 8 + i1 * 18, 84 + k * 18));
            }
        }

        for(int l = 0; l < 9; ++l) {
            this.addSlot(new Slot(inv, l, 8 + l * 18, 142));
        }

    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(pIndex);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            slot.onTake(pPlayer, itemstack1);
            if (pIndex <= 6 && pIndex >= 0) {
                if (!this.moveItemStackTo(itemstack1, 9, 37, true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 0, 37, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                slot.onTake(pPlayer, itemstack1);
                return ItemStack.EMPTY;
            }

            //slot.onTake(pPlayer, itemstack1);
        }

        return itemstack;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return true;
    }
}
