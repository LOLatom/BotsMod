package com.thefreak.botsmod.tileentity;

import com.google.common.base.Preconditions;
import com.thefreak.botsmod.init.ModTileEntityTypes;
import com.thefreak.botsmod.objects.screens.menu.ArmFactoryMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class ArmFactoryTileEntity extends BaseContainerBlockEntity implements IAnimatable {

    private SimpleContainer container = new SimpleContainer(7);
    protected static final AnimationBuilder DEPLOY_ANIM = new AnimationBuilder().addAnimation("animation.arm_factory.deploy").addAnimation("animation.arm_factory.idle", ILoopType.EDefaultLoopTypes.LOOP);
    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);

    public ArmFactoryTileEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        this(ModTileEntityTypes.ARM_FACTORY_TILE_ENTITY.get(), pWorldPosition, pBlockState);
    }

    public ArmFactoryTileEntity(BlockEntityType<ArmFactoryTileEntity> armFactoryTileEntityBlockEntityType, BlockPos pWorldPosition, BlockState pBlockState) {
        super(armFactoryTileEntityBlockEntityType,pWorldPosition,pBlockState);
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::deployAnimController));
    }

    protected <E extends ArmFactoryTileEntity> PlayState deployAnimController(final AnimationEvent<E> event) {
        event.getController().setAnimation(DEPLOY_ANIM);

        return PlayState.CONTINUE;
    }
    private static NonNullList<ItemStack> copyFromInv(Container inv) {
        NonNullList<ItemStack> ret = NonNullList.withSize(inv.getContainerSize(), ItemStack.EMPTY);
        for (int i = 0; i < inv.getContainerSize(); i++) {
            ret.set(i, inv.getItem(i));
        }
        return ret;
    }

    private static void copyToInv(NonNullList<ItemStack> src, Container dest) {
        Preconditions.checkArgument(src.size() == dest.getContainerSize());
        for (int i = 0; i < src.size(); i++) {
            dest.setItem(i, src.get(i));
        }
    }

    public void sendUpdates() {
        level.setBlocksDirty(worldPosition, getBlockState(), getBlockState());
        level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        setChanged();
    }
    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        NonNullList<ItemStack> tmp = NonNullList.withSize(7, ItemStack.EMPTY);
        ContainerHelper.loadAllItems(pTag, tmp);
        copyToInv(tmp, this.container);
    }

    public SimpleContainer getContainer() {
        return container;
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        ContainerHelper.saveAllItems(pTag,copyFromInv(this.container));
    }

    @Override
    protected Component getDefaultName() {
        return TextComponent.EMPTY;
    }

    @Override
    protected AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory) {
        return new ArmFactoryMenu(pContainerId,pInventory, this.getContainer());
    }

    @Override
    public CompoundTag getUpdateTag() {
        return super.getUpdateTag();
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public int getContainerSize() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public ItemStack getItem(int pIndex) {
        return null;
    }

    @Override
    public ItemStack removeItem(int pIndex, int pCount) {
        return null;
    }

    @Override
    public ItemStack removeItemNoUpdate(int pIndex) {
        return null;
    }

    @Override
    public void setItem(int pIndex, ItemStack pStack) {

    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return false;
    }

    @Override
    public void clearContent() {

    }
}
