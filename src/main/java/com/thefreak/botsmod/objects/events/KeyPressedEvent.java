package com.thefreak.botsmod.objects.events;

import com.thefreak.botsmod.API.IAmDivine;
import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.ClassReferences;
import com.thefreak.botsmod.client.gui.gkhguis.spellguis.SavedSpellSelectionGUI;
import com.thefreak.botsmod.client.gui.gkhguis.spellguis.SpellSelectionGUI;
import com.thefreak.botsmod.init.iteminit.ItemInitNew;
import com.thefreak.botsmod.objects.items.loreandclueitems.GodKillerHand;
import com.thefreak.botsmod.objects.items.loreandclueitems.TremoringTabletItem;
import com.thefreak.botsmod.objects.keys.KeyInitiation;
import com.thefreak.botsmod.spells.SpellIdentifier;
import com.thefreak.botsmod.spells.spellclass.AbstractSpell;
import com.thefreak.botsmod.util.packets.BotsPacketHandler;
import com.thefreak.botsmod.util.packets.interractionpackets.DivineKeyClick;
import com.thefreak.botsmod.util.packets.interractionpackets.OpenCloseGodKHPacket;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;

@Mod.EventBusSubscriber(modid = BotsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class KeyPressedEvent {

    @SubscribeEvent
    public static void pressKey(InputEvent.KeyInputEvent event) {
        if (KeyInitiation.botskeyMapping.consumeClick()) {
            boolean isme = ClassReferences.getPlayer().getUUID().toString().equals("6fe814fa9a6a436d80996c390116a67e") || ClassReferences.getPlayer().getUUID().toString().equals("6fe814fa-9a6a-436d-8099-6c390116a67e");
            System.out.println("KEYPRESSED");
            if(isme && ClassReferences.getPlayer() instanceof IAmDivine divine) {
                ClassReferences.getClientLevel().addParticle(ParticleTypes.CLOUD,ClassReferences.getPlayer().getX(),ClassReferences.getPlayer().getY() + 0.8F,ClassReferences.getPlayer().getZ()
                        ,0,-0.1D,0);
                ClassReferences.getClientLevel().addParticle(ParticleTypes.CLOUD,ClassReferences.getPlayer().getX(),ClassReferences.getPlayer().getY() + 0.8F,ClassReferences.getPlayer().getZ()
                        ,0,-0.1D,0);
                ClassReferences.getClientLevel().addParticle(ParticleTypes.CLOUD,ClassReferences.getPlayer().getX(),ClassReferences.getPlayer().getY() + 0.8F,ClassReferences.getPlayer().getZ()
                        ,0,-0.1D,0);
                divine.setDivine(!divine.isDivine());
            }

            BotsPacketHandler.INSTANCE.sendToServer(new DivineKeyClick());
            return;
        } else if (KeyInitiation.F1.consumeClick()){
            BotsPacketHandler.INSTANCE.sendToServer(new OpenCloseGodKHPacket(1));
        } else if (KeyInitiation.F2.consumeClick()){
            BotsPacketHandler.INSTANCE.sendToServer(new OpenCloseGodKHPacket(2));
        } else if (KeyInitiation.F3.consumeClick()){
            BotsPacketHandler.INSTANCE.sendToServer(new OpenCloseGodKHPacket(3));
        } else if (KeyInitiation.F4.consumeClick()){
            BotsPacketHandler.INSTANCE.sendToServer(new OpenCloseGodKHPacket(4));
        } else if (KeyInitiation.S1.consumeClick()){
            BotsPacketHandler.INSTANCE.sendToServer(new OpenCloseGodKHPacket(5));
        }
        if (KeyInitiation.FASTS.consumeClick()){
            SpellIdentifier SI = new SpellIdentifier(false,false,false,false,false,0,null,null);
            ItemStack stacka = ClassReferences.getPlayer().getItemInHand(InteractionHand.MAIN_HAND);
            CompoundTag nbta = stacka.getOrCreateTag();
            if (stacka.getItem() instanceof GodKillerHand) {
                ArrayList<Integer> savedSpells = new ArrayList<Integer>();
                for (int i = 0; i < ClassReferences.getPlayer().getInventory().getContainerSize(); i++) {
                    if (ClassReferences.getPlayer().getInventory().getItem(i).getItem() instanceof TremoringTabletItem) {
                        ItemStack stack = ClassReferences.getPlayer().getInventory().getItem(i);
                        CompoundTag nbt = stack.getOrCreateTag();
                        if (SI.getSpellFromID(nbt.getInt("spellid")).modeActive() == nbt.getInt("mode")) {
                            savedSpells.add(nbt.getInt("spellid"));
                            System.out.println(nbt.getInt("spellid"));
                        }
                    }
                    System.out.println("ITEM:" + ClassReferences.getPlayer().getInventory().getItem(i));
                }
                savedSpells.add(0);
                savedSpells.add(0);
                savedSpells.add(0);
                savedSpells.add(0);

                ClassReferences.getClientMC().setScreen(new SavedSpellSelectionGUI(savedSpells.get(0),savedSpells.get(1),savedSpells.get(2),savedSpells.get(3)));
            }
        }
    }

    @SubscribeEvent
    public static void tickPlayer(TickEvent.PlayerTickEvent event) {
        boolean isme = event.player.getUUID().toString().equals("6fe814fa9a6a436d80996c390116a67e") || event.player.getUUID().toString().equals("6fe814fa-9a6a-436d-8099-6c390116a67e");
        if (event.player.pick(16,0,false).getType().equals(HitResult.Type.ENTITY)) {
            EntityHitResult entityHitResult = (EntityHitResult) ClassReferences.getPlayer().pick(16,0,false);
            if (entityHitResult.getEntity() instanceof Player player) {
                if (player instanceof IAmDivine divine) {
                    if (divine.isDivine() && isme) player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 40, 1));

                }
            }
        }

    }
}
