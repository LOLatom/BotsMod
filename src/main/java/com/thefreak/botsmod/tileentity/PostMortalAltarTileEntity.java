package com.thefreak.botsmod.tileentity;

import com.thefreak.botsmod.init.ModTileEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class PostMortalAltarTileEntity extends BlockEntity implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);

    public static AnimationBuilder SPIN_ANIM = new AnimationBuilder().addAnimation("animation.postmortal_altar.spin");

    public static AnimationBuilder ROLL_ANIM = new AnimationBuilder().addAnimation("animation.postmortal_altar.roll");

    public PostMortalAltarTileEntity(BlockEntityType<?> tileEntityTypeIn, BlockPos pos, BlockState state) {
        super(tileEntityTypeIn, pos, state);
    }

    public PostMortalAltarTileEntity(BlockPos pos, BlockState state) {
        this(ModTileEntityTypes.POST_MORTAL_ALTAR_TILE_ENTITY.get(), pos, state);
    }

    private <E extends PostMortalAltarTileEntity & IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        AnimationController controller = event.getController();
        AnimationBuilder anim = SPIN_ANIM;
        controller.setAnimation(anim);
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController<PostMortalAltarTileEntity>(this, "controller", 1, this::predicate));
    }



    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}
