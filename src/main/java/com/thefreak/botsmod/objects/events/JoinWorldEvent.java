package com.thefreak.botsmod.objects.events;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.objects.items.loreandclueitems.GodKillerHand;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;
import software.bernie.geckolib3.network.GeckoLibNetwork;
import software.bernie.geckolib3.network.ISyncable;
import software.bernie.geckolib3.util.GeckoLibUtil;

@Mod.EventBusSubscriber(modid = BotsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class JoinWorldEvent{

    @SubscribeEvent
    public static void Joined(PlayerEvent.PlayerLoggedInEvent event) {
        ItemStack stack = event.getEntityLiving().getMainHandItem();
        if (stack.getItem() instanceof ISyncable) {
            Level level = event.getPlayer().level;
            CompoundTag nbt = stack.getOrCreateTag();
            final int id = GeckoLibUtil.guaranteeIDForStack(stack, (ServerLevel) level);
            final PacketDistributor.PacketTarget target = PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> event.getPlayer());
            if (nbt.getBoolean("firstfing") == true) {
                GeckoLibNetwork.syncAnimation(target, (ISyncable) stack.getItem(), id, GodKillerHand.F1);

            }
            if (nbt.getBoolean("secondfing") == true) {
                GeckoLibNetwork.syncAnimation(target, (ISyncable) stack.getItem(), id, GodKillerHand.F2);

            }
            if (nbt.getBoolean("thirdfing") == true) {
                GeckoLibNetwork.syncAnimation(target, (ISyncable) stack.getItem(), id, GodKillerHand.F3);

            }
            if (nbt.getBoolean("fourthfing") == true) {
                GeckoLibNetwork.syncAnimation(target, (ISyncable) stack.getItem(), id, GodKillerHand.F4);

            }
            if (nbt.getBoolean("blade") == true) {
                GeckoLibNetwork.syncAnimation(target, (ISyncable) stack.getItem(), id, GodKillerHand.BLADE_OUT);
                GeckoLibNetwork.syncAnimation(target, (ISyncable) stack.getItem(), id,
                        GodKillerHand.ARM);

            }
        }

    }

}
