package com.thefreak.botsmod.tileentity;

import com.thefreak.botsmod.init.iteminit.FoodItemInit;
import com.thefreak.botsmod.init.iteminit.ItemInitNew;
import com.thefreak.botsmod.init.ModTileEntityTypes;
import com.thefreak.botsmod.objects.blocks.HeatBlockMechanics.CookingPotRecipes;
import com.thefreak.botsmod.objects.blocks.HeatBlockMechanics.HeatCapacitorBlock;
import com.thefreak.botsmod.objects.blocks.HeatBlockMechanics.HeatMaths;
import com.thefreak.botsmod.tileentity.TileBases.HeatBlockEntityBase;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CookingPotTileEntity extends HeatBlockEntityBase implements BlockEntityTicker, IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);

    private HeatMaths heatMaths = new HeatMaths();

    private List<ItemStack> ignoredItem = new ArrayList<>();

    private CookingPotRecipes cookingPotRecipes = new CookingPotRecipes();

    private ItemStackHandler Ingredients = new ItemStackHandler(3) {
        @Override
        protected void onContentsChanged(int slot) {

        }

        @Override
        protected int getStackLimit(int slot, @Nonnull ItemStack stack) {
            return 1;
        }
    };

    private boolean mixing;

    private int liquidAmount;

    private int liquidType;

    private int tickActionned;

    public static AnimationBuilder MIXING_ANIM = new AnimationBuilder().addAnimation("animation.cooking_pot.mixing");

    public static AnimationBuilder SHAKING_ANIM = new AnimationBuilder().addAnimation("animation.cooking_pot.shake_pot");


    public CookingPotTileEntity(BlockEntityType<?> tileEntityTypeIn, BlockPos pos, BlockState state) {
        super(tileEntityTypeIn, pos, state);
        this.liquidAmount = 0;
        this.setLiquidType(0);
        this.mixing = false;
        this.ignoredItem.add(Items.BOWL.getDefaultInstance());
    }

    public int getLiquidType() {
        return liquidType;
    }

    public void setLiquidType(int liquidType) {
        this.liquidType = liquidType;
        setChanged();
    }

    public int getLiquidAmount() {
        return liquidAmount;
    }

    public void setLiquidAmount(int liquidAmount) {
        this.liquidAmount = liquidAmount;
        setChanged();
    }

    public boolean getMixing() {
        return mixing;
    }



    public void setMixing(boolean mixing) {
        this.mixing = mixing;
        setChanged();
    }

    public CookingPotTileEntity(BlockPos pos, BlockState state) {
        this(ModTileEntityTypes.COOKING_POT_TILE_ENTITY.get(), pos, state);
    }
    private <E extends BlockEntity & IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        AnimationController controller = event.getController();
        controller.transitionLengthTicks = 0;
        if (getMixing() == true) {
            controller.setAnimation(MIXING_ANIM);
        } else {
            BlockPos pos = event.getAnimatable().getBlockPos();
            if (level.getBlockState(pos.below()).getBlock() instanceof HeatCapacitorBlock) {
                controller.setAnimation(SHAKING_ANIM);
            }
        }
        return PlayState.CONTINUE;
    }
    @Override
    public void Activated(BlockState state, Level level, Player playerEntity, BlockPos pos, InteractionHand hand) {
        if (hand == InteractionHand.MAIN_HAND) {
            if (playerEntity.getItemInHand(hand).getItem() == Items.WATER_BUCKET ) {
                if (getLiquidAmount() == 0) {
                    setLiquidType(1);
                    setLiquidAmount(3);
                    playerEntity.setItemInHand(hand, Items.BUCKET.getDefaultInstance());
                    level.playLocalSound(pos.getX(),pos.getY(),pos.getZ(), SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS,1,1,true);
                }

            } else if (playerEntity.getItemInHand(hand).getItem() == Items.GLASS_BOTTLE ) {
                if (getLiquidAmount() > 1 && getLiquidType() == 1) {
                    setLiquidType(1);
                    setLiquidAmount(getLiquidAmount() - 1);
                    playerEntity.getItemInHand(hand).setCount(playerEntity.getItemInHand(hand).getCount() - 1);
                    playerEntity.addItem(PotionUtils.setPotion(new ItemStack(Items.POTION),Potions.WATER));
                    level.playLocalSound(pos.getX(),pos.getY(),pos.getZ(), SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS,1,1.2F,true);
                } else if (getLiquidAmount() == 1 && getLiquidType() == 1) {
                    setLiquidType(0);
                    setLiquidAmount(0);
                    playerEntity.getItemInHand(hand).setCount(playerEntity.getItemInHand(hand).getCount() - 1);
                    playerEntity.addItem(PotionUtils.setPotion(new ItemStack(Items.POTION),Potions.WATER));
                    level.playLocalSound(pos.getX(),pos.getY(),pos.getZ(), SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS,1,1.2F,true);
                }

            } else if (playerEntity.getItemInHand(hand).getItem() == Items.MILK_BUCKET ) {
                if (getLiquidAmount() == 0) {
                    setLiquidType(2);
                    setLiquidAmount(3);
                    playerEntity.setItemInHand(hand, Items.BUCKET.getDefaultInstance());
                    level.playLocalSound(pos.getX(),pos.getY(),pos.getZ(), SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS,1,0.8F,true);
                }

            } else if (playerEntity.getItemInHand(hand).getItem() == Items.BUCKET ) {
                if (getLiquidAmount() == 3 && getLiquidType() == 1) {
                    setLiquidType(0);
                    setLiquidAmount(0);
                    playerEntity.setItemInHand(hand, Items.WATER_BUCKET.getDefaultInstance());
                    level.playLocalSound(pos.getX(),pos.getY(),pos.getZ(), SoundEvents.BUCKET_FILL, SoundSource.BLOCKS,1,1,true);
                } else if (getLiquidAmount() == 3 && getLiquidType() == 2) {
                    setLiquidType(0);
                    setLiquidAmount(0);
                    playerEntity.setItemInHand(hand, Items.MILK_BUCKET.getDefaultInstance());
                    level.playLocalSound(pos.getX(),pos.getY(),pos.getZ(), SoundEvents.BUCKET_FILL, SoundSource.BLOCKS,1,0.8F,true);
                }

            } else if (PotionUtils.getPotion(playerEntity.getItemInHand(hand)) == Potions.WATER) {
                if (getLiquidAmount() == 0) {
                    setLiquidType(1);
                    setLiquidAmount(1);
                    playerEntity.setItemInHand(hand, Items.GLASS_BOTTLE.getDefaultInstance());
                    level.playLocalSound(pos.getX(),pos.getY(),pos.getZ(), SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS,1,0.8F,true);

                } else if (getLiquidAmount() >= 1 && getLiquidAmount() < 3 && getLiquidType() == 1) {
                    setLiquidAmount(getLiquidAmount() + 1);
                    playerEntity.setItemInHand(hand, Items.GLASS_BOTTLE.getDefaultInstance());
                    level.playLocalSound(pos.getX(),pos.getY(),pos.getZ(), SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS,1,0.8F,true);
                }

            }else if(playerEntity.getItemInHand(hand).getItem() == Items.APPLE) {
                getApplesActions(state,level,playerEntity,pos,hand);

            }else if(playerEntity.getItemInHand(hand).getItem() == Items.BOWL) {
                getBowlActions(state,level,playerEntity,pos,hand);

            }else if(playerEntity.getItemInHand(hand).getItem() == ItemInitNew.SCRAPER.get()) {
                getScraperActions(state,level,playerEntity,pos,hand);

            } else if (playerEntity.getItemInHand(hand) != ItemStack.EMPTY && playerEntity.getItemInHand(hand).getItem() != Items.AIR && !playerEntity.isCrouching()) {
                    fillIngrediants(state, level, playerEntity, pos, hand);
            } else {
                if( playerEntity.isCrouching()) {
                    if (!level.isClientSide()) {
                        emptyIngrediants(state, level, playerEntity, pos, hand);
                    }
                }else if (getMixing() == false  && playerEntity.getItemInHand(hand) == ItemStack.EMPTY) {
                    setMixing(true);
                    System.out.println(getMixing());
                } else if (getMixing() == true && playerEntity.getItemInHand(hand) == ItemStack.EMPTY) {
                    setMixing(false);
                    System.out.println(getMixing());
                }
            }
   //         System.out.println("MixingState :" + getMixing() + " --- LiquidType :" + getLiquidType() + " --- LiquidAmount :" + getLiquidAmount() + "L");

        }
        setChanged();
        }


        public void getApplesActions(BlockState state, Level level, Player playerEntity, BlockPos pos, InteractionHand hand) {
                if (getLiquidType() == 4 && getLiquidAmount() == 1) {
                    setLiquidAmount(getLiquidAmount() - 1);
                    setLiquidType(0);
                    playerEntity.getItemInHand(hand).setCount(playerEntity.getItemInHand(hand).getCount() - 1);
                    playerEntity.addItem(FoodItemInit.CARAMEL_APPLE.get().getDefaultInstance());
                } else if (getLiquidAmount() > 1 && getLiquidType() == 4) {
                    setLiquidAmount(getLiquidAmount() - 1);
                    playerEntity.getItemInHand(hand).setCount(playerEntity.getItemInHand(hand).getCount() - 1);
                    playerEntity.addItem(FoodItemInit.CARAMEL_APPLE.get().getDefaultInstance());
                }else if (getLiquidType() == 3 && getLiquidAmount() == 1) {
                    setLiquidAmount(getLiquidAmount() - 1);
                    setLiquidType(0);
                    playerEntity.getItemInHand(hand).setCount(playerEntity.getItemInHand(hand).getCount() - 1);
                    playerEntity.addItem(FoodItemInit.CONDENSED_MILK_APPLE.get().getDefaultInstance());
                } else if (getLiquidAmount() > 1 && getLiquidType() == 3) {
                    setLiquidAmount(getLiquidAmount() - 1);
                    playerEntity.getItemInHand(hand).setCount(playerEntity.getItemInHand(hand).getCount() - 1);
                    playerEntity.addItem(FoodItemInit.CONDENSED_MILK_APPLE.get().getDefaultInstance());
                }
            setChanged();
        }

        public void getBowlActions(BlockState state, Level level, Player playerEntity, BlockPos pos, InteractionHand hand) {

        }

        public void getScraperActions(BlockState state, Level level, Player playerEntity, BlockPos pos, InteractionHand hand) {
            if (playerEntity.getItemInHand(InteractionHand.OFF_HAND).getItem() == Items.BOWL) {
                if (getLiquidType() == 5 && getLiquidAmount() == 1) {
                    setLiquidAmount(getLiquidAmount() - 1);
                    setLiquidType(0);
                    playerEntity.getItemInHand(InteractionHand.OFF_HAND).setCount(playerEntity.getItemInHand(InteractionHand.OFF_HAND).getCount() - 1);
                    playerEntity.addItem(ItemInitNew.POWDERED_MILK.get().getDefaultInstance());
                } else if (getLiquidAmount() > 1 && getLiquidType() == 5) {
                    setLiquidAmount(getLiquidAmount() - 1);
                    playerEntity.getItemInHand(InteractionHand.OFF_HAND).setCount(playerEntity.getItemInHand(InteractionHand.OFF_HAND).getCount() - 1);
                    playerEntity.addItem(ItemInitNew.POWDERED_MILK.get().getDefaultInstance());
                }
            }
            setChanged();
        }

    public ItemStack getItemStackInSlot(int slot) {
        return Ingredients.getStackInSlot(slot);
    }

    public void setItemStackInSlot(ItemStack itemStack,int slot) {
        Ingredients.setStackInSlot(slot, itemStack);
        setChanged();
    }

    public void emptyIngrediants(BlockState state, Level level, Player playerEntity, BlockPos pos, InteractionHand hand) {
        System.out.println("empty");

        if (getItemStackInSlot(2) != ItemStack.EMPTY && playerEntity.getItemInHand(InteractionHand.MAIN_HAND).isEmpty()) {
            playerEntity.setItemInHand(InteractionHand.MAIN_HAND,getItemStackInSlot(2).getItem().getDefaultInstance());
            setItemStackInSlot(ItemStack.EMPTY,2);
            System.out.println("Working empty 2");
        } else if (getItemStackInSlot(1) != ItemStack.EMPTY && playerEntity.getItemInHand(InteractionHand.MAIN_HAND).isEmpty()) {
            playerEntity.setItemInHand(InteractionHand.MAIN_HAND,getItemStackInSlot(1).getItem().getDefaultInstance());
            setItemStackInSlot(ItemStack.EMPTY,1);
            System.out.println("Working empty 1");
        } else if (getItemStackInSlot(0) != ItemStack.EMPTY && playerEntity.getItemInHand(InteractionHand.MAIN_HAND).isEmpty()) {
            playerEntity.setItemInHand(InteractionHand.MAIN_HAND,getItemStackInSlot(0).getItem().getDefaultInstance());
            setItemStackInSlot(ItemStack.EMPTY,0);
            System.out.println("Working empty 0");
        } else {

        }
        setChanged();
        return;
    }

    public void fillIngrediants(BlockState state, Level level, Player playerEntity, BlockPos pos, InteractionHand hand) {
        System.out.println("fill");

        if (getItemStackInSlot(0).isEmpty() && getItemStackInSlot(0).getItem() == Items.AIR) {
            setItemStackInSlot(playerEntity.getItemInHand(InteractionHand.MAIN_HAND).getItem().getDefaultInstance(),0);
            playerEntity.getItemInHand(InteractionHand.MAIN_HAND).setCount(playerEntity.getItemInHand(InteractionHand.MAIN_HAND).getCount() - 1);
            System.out.println("Working fill 0");
        } else if (getItemStackInSlot(1).isEmpty() && getItemStackInSlot(1).getItem() == Items.AIR) {
            setItemStackInSlot(playerEntity.getItemInHand(InteractionHand.MAIN_HAND).getItem().getDefaultInstance(),1);
            playerEntity.getItemInHand(InteractionHand.MAIN_HAND).setCount(playerEntity.getItemInHand(InteractionHand.MAIN_HAND).getCount() - 1);
            System.out.println("Working fill 1");
        } else if (getItemStackInSlot(2).isEmpty() && getItemStackInSlot(2).getItem() == Items.AIR) {
            setItemStackInSlot(playerEntity.getItemInHand(InteractionHand.MAIN_HAND).getItem().getDefaultInstance(),2);
            playerEntity.getItemInHand(InteractionHand.MAIN_HAND).setCount(playerEntity.getItemInHand(InteractionHand.MAIN_HAND).getCount() - 1);
            System.out.println("Working fill 2");
        } else {

        }
        setChanged();
        return;
    }

    @Override
    public void Ticking(BlockState state, ServerLevel serverLevel, BlockPos pos, Random random) {

    }

    public void setTickActionned(int tickActionned) {
        this.tickActionned = tickActionned;
        setChanged();
    }

    public int getTickActionned() {
        return tickActionned;
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        setMixing(pTag.getBoolean("mixing"));
        setChanged();
        setLiquidAmount(pTag.getInt("liquidAmount"));
        setChanged();
        setLiquidType(pTag.getInt("liquidType"));
        setChanged();
        setTickActionned(pTag.getInt("tickActionned"));
        setChanged();
        Ingredients.deserializeNBT(pTag.getCompound("Ingredients"));
        setChanged();
  //      System.out.println("MixingState :" + getMixing() + " --- LiquidType :" + getLiquidType() + " --- LiquidAmount :" + getLiquidAmount() + "L");
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.putBoolean("mixing", getMixing());
        pTag.putInt("liquidAmount", getLiquidAmount());
        pTag.putInt("liquidType", getLiquidType());
        pTag.putInt("tickActionned", getTickActionned());
        pTag.put("Ingredients", Ingredients.serializeNBT());
        setChanged();
        //       System.out.println("MixingState :" + getMixing() + " --- LiquidType :" + getLiquidType() + " --- LiquidAmount :" + getLiquidAmount() + "L");
        super.saveAdditional(pTag);

    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket(){
        CompoundTag nbtTag = new CompoundTag();
        saveAdditional(nbtTag);
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        load(pkt.getTag());
        super.onDataPacket(net, pkt);
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag nbtTag = new CompoundTag();
        saveAdditional(nbtTag);
        return nbtTag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        this.load(tag);
    }




    public static void tickHeat(Level pLevel, BlockPos pPos, BlockState pState, BlockEntity pBlockEntity) {
        CookingPotTileEntity cookingPotTileEntity = (CookingPotTileEntity) pLevel.getBlockEntity(pPos);
        if (pLevel.getBlockEntity(pPos.below()) instanceof HeatCapacitorTileEntity) {
            cookingPotTileEntity.setHeatV(((HeatCapacitorTileEntity) pLevel.getBlockEntity(pPos.below())).getCapacityHeatV());
        } else {
            cookingPotTileEntity.setHeatV(cookingPotTileEntity.heatMaths.getHeat(pLevel,pPos.below(),15,true));
        }

        boolean getMixing = cookingPotTileEntity.getMixing();
        if (getMixing == true) {
            cookingPotTileEntity.setTickActionned(cookingPotTileEntity.getTickActionned() + 1);
            System.out.println(cookingPotTileEntity.getTickActionned());

            ItemStack firstS = cookingPotTileEntity.getItemStackInSlot(0);
            ItemStack secondS = cookingPotTileEntity.getItemStackInSlot(1);
            ItemStack thirdS = cookingPotTileEntity.getItemStackInSlot(2);
            int ticks = cookingPotTileEntity.getTickActionned();
            int liquidIn = cookingPotTileEntity.getLiquidType();
            int liquidAmount = cookingPotTileEntity.getLiquidAmount();
            double currentHeat = cookingPotTileEntity.getHeatV();
            cookingPotTileEntity.askCookingResult(firstS,secondS,thirdS,ticks,liquidIn,liquidAmount,currentHeat,pPos,pLevel);

        } else if (getMixing == false && cookingPotTileEntity.getTickActionned() > 0) {
            cookingPotTileEntity.setTickActionned(0);
        }
        cookingPotTileEntity.setChanged();

    }

    @Override
    public void tick(Level pLevel, BlockPos pPos, BlockState pState, BlockEntity pBlockEntity) {


    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }


    public static void getCookingResult(BlockPos pos, Level pLevel,
                                        double currentHeatV, ItemStack currentFI, ItemStack currentSI, ItemStack currentTI, float currentTickPassed, int currentLiquidIn, int currentAmount,
                                        ItemStack firstIngredient, ItemStack secondIngredient, ItemStack thirdIngredient, float tickPassed, int liquidIn,
                                        int amount,
                                        ItemStack output1, ItemStack output2, ItemStack output3, int liquidOutput, double minHeatV, double maxHeatV, int resultAmount) {
        CookingPotTileEntity cookingPotTileEntity = (CookingPotTileEntity) pLevel.getBlockEntity(pos);

        if (!pLevel.isClientSide()) {
            System.out.println("Server");
        }

        if (
           (firstIngredient.equals(currentFI,false))  &&
            secondIngredient.equals(currentSI,false) &&
            thirdIngredient.equals(currentTI, false) &&
                currentLiquidIn == liquidIn &&
                        currentTickPassed >= tickPassed
                        &&
                        currentHeatV < maxHeatV && currentHeatV > minHeatV && currentAmount == amount
        ) {
            System.out.println("Process");
            cookingPotTileEntity.setItemStackInSlot(output1,0);
            cookingPotTileEntity.setChanged();
            cookingPotTileEntity.setItemStackInSlot(output2,1);
            cookingPotTileEntity.setChanged();
            cookingPotTileEntity.setItemStackInSlot(output3,2);
            cookingPotTileEntity.setChanged();
            cookingPotTileEntity.setLiquidType(liquidOutput);
            cookingPotTileEntity.setLiquidAmount(resultAmount);
            cookingPotTileEntity.setTickActionned(0);
            cookingPotTileEntity.setMixing(false);
            cookingPotTileEntity.setChanged();
            setChanged(pLevel,pos,pLevel.getBlockState(pos));
            System.out.println(cookingPotTileEntity.getItemStackInSlot(0) + "" + cookingPotTileEntity.getItemStackInSlot(1) + "" +
                    cookingPotTileEntity.getItemStackInSlot(2) + "" + cookingPotTileEntity.getLiquidType() + "" +
                    cookingPotTileEntity.getTickActionned() + "" + cookingPotTileEntity.getMixing());
        }
        cookingPotTileEntity.setChanged();

    }

    public void askCookingResult(ItemStack currentFI, ItemStack currentSIn, ItemStack currentTI, float TickPassed, int liquidIn, int amount, double heatV, BlockPos pPos, Level pLevel){
        //Condensed Milk Recipe
        getCookingResult(pPos,pLevel,heatV,currentFI,currentSIn,currentTI,TickPassed,liquidIn,amount,

                ItemInitNew.POWDERED_MILK.get().getDefaultInstance(),Items.SUGAR.getDefaultInstance(),Items.SUGAR.getDefaultInstance(),850, 2, 3,

                ItemStack.EMPTY,ItemStack.EMPTY,ItemStack.EMPTY, 3,
                40, 55, 2
        );
        //Caramel Recipe
        getCookingResult(pPos,pLevel,heatV,currentFI,currentSIn,currentTI,TickPassed,liquidIn,amount,

                Items.SUGAR.getDefaultInstance(),Items.SUGAR.getDefaultInstance(),Items.SUGAR.getDefaultInstance(),850, 1, 2,

                Items.BOWL.getDefaultInstance(),ItemStack.EMPTY,ItemStack.EMPTY, 4,
                40, 55, 1
        );
        //DriedMilk Recipe
        getCookingResult(pPos,pLevel,heatV,currentFI,currentSIn,currentTI,TickPassed,liquidIn,amount,

                ItemStack.EMPTY,ItemStack.EMPTY,ItemStack.EMPTY,1700, 2, 3,

                ItemStack.EMPTY,ItemStack.EMPTY,ItemStack.EMPTY, 5,
                40, 55, 1
        );

        setChanged();


    }



}
