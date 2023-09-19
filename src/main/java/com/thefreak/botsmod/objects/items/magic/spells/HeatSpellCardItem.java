package com.thefreak.botsmod.objects.items.magic.spells;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.client.access.IBotsModAnimatable;
import com.thefreak.botsmod.init.iteminit.ItemInitNew;
import com.thefreak.botsmod.mixins.client.AbstractClientPlayerMixin;
import com.thefreak.botsmod.objects.items.magic.SpellCardItem;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.apache.logging.log4j.util.TriConsumer;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class HeatSpellCardItem extends SpellCardItem {
    public HeatSpellCardItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public ResourceLocation iconTex(ItemStack pStack) {
        return new ResourceLocation("botsmod:textures/icons/attack_icon.png");
    }


    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        pTooltipComponents.add(Component.nullToEmpty("0").copy().withStyle().setStyle(Style.EMPTY.withFont(new ResourceLocation(BotsMod.MOD_ID, "rarityfont"))));
        pTooltipComponents.add(Component.nullToEmpty("Spell: Heat Wave"));
    }

    @Override
    public Component spellName() {
        return new TextComponent("Heat Wave");
    }

    @Override
    public TriConsumer<Level, Player, InteractionHand> rightClickAction() {
        return (level,player,hand) -> {

        };
    }

    @Override
    public ResourceLocation spellCardTexture() {
        return new ResourceLocation("botsmod:textures/gui/spell/heat_spell_card.png");
    }


    @Override
    public void tickingOnUse(Level pLevel, LivingEntity pLivingEntity, ItemStack pStack, int pRemainingUseDuration) {
        List<LivingEntity> entities = getEntitiesInFrontOfPlayer((Player) pLivingEntity);
        if (pLivingEntity instanceof IBotsModAnimatable animatable && pLivingEntity instanceof AbstractClientPlayer) {
            animatable.getSet().startAnimation("botsmod.foward_spell_casting", animatable.getObject().animator());
        }

        Vec3 pushVector = ((Player) pLivingEntity).getLookAngle().scale(0.15);

        for (LivingEntity entity : entities) {
            entity.push(pushVector.x, pushVector.y, pushVector.z);
            entity.hurt(DamageSource.LAVA,0.01F);
            entity.setSecondsOnFire(1);
        }
        super.tickingOnUse(pLevel, pLivingEntity, pStack, pRemainingUseDuration);
    }

    @Override
    public void onRelease(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity, int pTimeCharged) {
        super.onRelease(pStack, pLevel, pLivingEntity, pTimeCharged);
    }

    public List<LivingEntity> getEntitiesInFrontOfPlayer(Player player) {
        List<LivingEntity> entities = new ArrayList<>();
        List<ItemEntity> items = new ArrayList<>();
        List<Projectile> proj = new ArrayList<>();

        // Get player's position and look vector

        // Calculate the end position based on the player's view direction and range


        // Get the bounding box between the player's position and end position
        AABB boundingBox = player.getBoundingBox().inflate(4);

        // Get all entities within the bounding box
        for (LivingEntity entity : player.level.getEntitiesOfClass(LivingEntity.class,boundingBox)) {
            // Exclude the player from the list
            if (entity != player) {
                entities.add(entity);
            }
        }
        for (ItemEntity itemEntity : player.level.getEntitiesOfClass(ItemEntity.class,boundingBox)) {
            if (!itemEntity.getItem().getItem().isEdible()) {
                itemEntity.setItem(ItemInitNew.ASH.get().getDefaultInstance());
            }
            //if (itemEntity.getItem().get)
        }
        for (Projectile projectile : player.level.getEntitiesOfClass(Projectile.class,boundingBox)) {

            projectile.setDeltaMovement(0,0,0);
        }

        return entities;
    }

    @Override
    public int animationForActivation() {
        return 0;
    }

    @Override
    public boolean isTickAllowed() {
        return true;
    }
}
