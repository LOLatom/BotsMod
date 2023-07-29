package com.thefreak.botsmod.init.iteminit;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.objects.items.Eggs.PuffWormEggs;
import com.thefreak.botsmod.objects.items.GeoriteCrystal;
import com.thefreak.botsmod.objects.items.ItemType.AmuletItem;
import com.thefreak.botsmod.objects.items.ItemType.CleaverSwordItem;
import com.thefreak.botsmod.objects.items.ItemType.HammerItem;
import com.thefreak.botsmod.objects.items.ItemType.Scraper;
import com.thefreak.botsmod.objects.items.LadybugItem;
import com.thefreak.botsmod.objects.items.LiquidHolder;
import com.thefreak.botsmod.objects.items.loreandclueitems.*;
import com.thefreak.botsmod.objects.items.loreandclueitems.coins.*;
import com.thefreak.botsmod.objects.items.magic.SpellCardItem;
import com.thefreak.botsmod.objects.items.magic.spells.HeatSpellCardItem;
import com.thefreak.botsmod.objects.items.organs.FleshBucket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BowlFoodItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;



public class ItemInitNew {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BotsMod.MOD_ID);

    public static final RegistryObject<Item> BELL_METAL_GREATSWORD = ITEMS.register("bell_metal_greatsword", () -> new CleaverSwordItem(Tiers.GOLD, 1, 2F, new Item.Properties().tab(BotsMod.BotsItemGroup.instance)));

    public static final RegistryObject<Item> BELL_METAL_HAMMER = ITEMS.register("bell_metal_hammer", () -> new HammerItem(Tiers.GOLD, new Item.Properties()));

    public static final RegistryObject<Item> LADYBUG = ITEMS.register("ladybug", () -> new LadybugItem(new Item.Properties().tab(BotsMod.BotsItemGroup.instance)));

    public static final RegistryObject<Item> RAW_TIPPY_LIZARD = ITEMS.register("raw_tippy_lizard", () -> new Item(new Item.Properties().tab(BotsMod.BotsfoodItemGroup.food).food(new FoodProperties.Builder().nutrition(2).saturationMod(0.5F).effect( () -> new MobEffectInstance(MobEffects.HUNGER, 400, 2),1.0F).build())));

    public static final RegistryObject<Item> COOKED_TIPPY_LIZARD = ITEMS.register("cooked_tippy_lizard", () -> new Item(new Item.Properties().tab(BotsMod.BotsfoodItemGroup.food).food(new FoodProperties.Builder().nutrition(4).saturationMod(3F).build())));

    public static final RegistryObject<Item> RAW_TIPPY_LIZARD_BITS = ITEMS.register("raw_tippy_lizard_bits", () -> new Item(new Item.Properties().tab(BotsMod.BotsfoodItemGroup.food).food(new FoodProperties.Builder().nutrition(1).saturationMod(0F).effect( () -> new MobEffectInstance(MobEffects.HUNGER, 400, 2),1.0F).fast().build())));

    public static final RegistryObject<Item> COOKED_TIPPY_LIZARD_BITS = ITEMS.register("cooked_tippy_lizard_bits", () -> new Item(new Item.Properties().tab(BotsMod.BotsfoodItemGroup.food).food(new FoodProperties.Builder().nutrition(3).saturationMod(2.5F).fast().build())));

    public static final RegistryObject<Item> GLAZED_TIPPY_LIZARD_BITS = ITEMS.register("glazed_tippy_lizard_bits", () -> new Item(new Item.Properties().tab(BotsMod.BotsfoodItemGroup.food).food(new FoodProperties.Builder().nutrition(4).saturationMod(3.5F).fast().build())));

    public static final RegistryObject<Item> PUFF_WORM_EGGS = ITEMS.register("puff_worm_eggs", () -> new PuffWormEggs(new Item.Properties().tab(BotsMod.BotsfoodItemGroup.food).food(new FoodProperties.Builder().nutrition(1).saturationMod(1.5F).effect( () -> new MobEffectInstance(MobEffects.CONFUSION, 200, 1),1.0F).fast().build())));

    public static final RegistryObject<Item> GRAY_NOTE = ITEMS.register("gray_note", () -> new Item(new Item.Properties().tab(BotsMod.BotsItemGroup.instance)));

    public static final RegistryObject<Item> BIG_RUBY = ITEMS.register("big_ruby", () -> new Item(new Item.Properties().tab(BotsMod.BotsItemGroup.instance)));

    public static final RegistryObject<Item> GEORITE_CRYSTAL = ITEMS.register("georite_crystal", () -> new GeoriteCrystal(new Item.Properties().tab(BotsMod.BotsItemGroup.instance)));

    public static final RegistryObject<Item> GOLD_BRONZE_LADLE = ITEMS.register("gold_bronze_ladle", () -> new LiquidHolder(new Item.Properties().tab(BotsMod.BotsItemGroup.instance).stacksTo(1)));

    public static final RegistryObject<Item> SCRAPER = ITEMS.register("scraper", () -> new Scraper(new Item.Properties().tab(BotsMod.BotsItemGroup.instance).stacksTo(1)));

    public static final RegistryObject<Item> POWDERED_MILK = ITEMS.register("powdered_milk", () -> new BowlFoodItem(new Item.Properties().tab(BotsMod.BotsfoodItemGroup.food).stacksTo(1).food(new FoodProperties.Builder().nutrition(2).saturationMod(-5)
            .build())));

    public static final RegistryObject<Item> CAVE_ANGELIA_GUPPIE_SCALE = ITEMS.register("cave_angelia_guppie_scale", () -> new Item(new Item.Properties().tab(BotsMod.BotsItemGroup.instance)));



    //Important items :

    public static final RegistryObject<Item> TABLET = ITEMS.register("tablet", () -> new TabletItem(new Item.Properties().tab(BotsMod.BotsItemGroup.instance).stacksTo(1), new ResourceLocation("botsmod:" +"textures/gui/tablet/tablet_screen.png")));

    public static final RegistryObject<Item> HELP_ME_TABLET = ITEMS.register("help_me_tablet", () -> new TabletItem(new Item.Properties().tab(BotsMod.BotsItemGroup.instance).stacksTo(1), new ResourceLocation("botsmod:" +"textures/gui/tablet/helpme_tablet_screen.png")));

    public static final RegistryObject<Item> LOVE_ESCAPED_TABLET = ITEMS.register("love_escaped_tablet", () -> new TabletItem(new Item.Properties().tab(BotsMod.BotsItemGroup.instance).stacksTo(1), new ResourceLocation("botsmod:" +"textures/gui/tablet/lover_escaped_tablet_screen.png")));

    public static final RegistryObject<Item> DD_TABLET = ITEMS.register("dd_tablet", () -> new TabletItem(new Item.Properties().tab(BotsMod.BotsItemGroup.instance).stacksTo(1), new ResourceLocation("botsmod:" +"textures/gui/tablet/dd_tablet_screen.png")));

    public static final RegistryObject<Item> BANHIR_HEAD = ITEMS.register("banhir_head", () -> new BanhirHead(new Item.Properties().tab(BotsMod.BotsItemGroup.instance).stacksTo(1)));

    public static final RegistryObject<Item> FLESH_BUCKET = ITEMS.register("flesh_bucket", () -> new FleshBucket(Fluids.EMPTY,new Item.Properties().tab(BotsMod.BotsItemGroup.instance).stacksTo(4)));

    public static final RegistryObject<Item> GOD_KILLER_HAND = ITEMS.register("god_killer_hand", () -> new GodKillerHand(new Item.Properties().tab(BotsMod.BotsItemGroup.instance).stacksTo(1)));

    public static final RegistryObject<Item> TREMORING_TABLET = ITEMS.register("tremoring_tablet", () -> new TremoringTabletItem(new Item.Properties().tab(BotsMod.BotsItemGroup.instance).stacksTo(1)));

    public static final RegistryObject<Item> ENDER_BESTIAL_AMULET = ITEMS.register("ender_bestial_amulet", () -> new AmuletItem(new Item.Properties().tab(BotsMod.BotsItemGroup.instance).stacksTo(1)));

    public static final RegistryObject<Item> CAVE_BESTIAL_AMULET = ITEMS.register("cave_bestial_amulet", () -> new AmuletItem(new Item.Properties().tab(BotsMod.BotsItemGroup.instance).stacksTo(1)));

    public static final RegistryObject<Item> GROUND_BESTIAL_AMULET = ITEMS.register("ground_bestial_amulet", () -> new AmuletItem(new Item.Properties().tab(BotsMod.BotsItemGroup.instance).stacksTo(1)));


    public static final RegistryObject<Item> ASH = ITEMS.register("ash", () -> new Item(new Item.Properties().tab(BotsMod.BotsItemGroup.instance)));
    public static final RegistryObject<Item> REDSTONE_QUART = ITEMS.register("redstone_quart", () -> new Item(new Item.Properties().tab(BotsMod.BotsItemGroup.instance)));

    public static final RegistryObject<Item> POENICAN_CRYSTAL = ITEMS.register("poenican_crystal", () -> new Item(new Item.Properties().tab(BotsMod.BotsItemGroup.instance)));

    public static final RegistryObject<Item> POENICAN_CRYSTALITES = ITEMS.register("poenican_crystalites", () -> new Item(new Item.Properties().tab(BotsMod.BotsItemGroup.instance)));

    public static final RegistryObject<Item> BANNED_LAND_KEY = ITEMS.register("banned_land_key", () -> new Item(new Item.Properties().tab(BotsMod.BotsItemGroup.instance).stacksTo(1)));



    //                             -----SPELL----

    public static final RegistryObject<Item> SPELL_CARD = ITEMS.register("spell_card", () -> new SpellCardItem(new Item.Properties().tab(BotsMod.BotsMagicItemGroup.magic).stacksTo(1)));

    public static final RegistryObject<Item> HEAT_SPELL_CARD = ITEMS.register("heat_spell_card", () -> new HeatSpellCardItem(new Item.Properties().tab(BotsMod.BotsMagicItemGroup.magic).stacksTo(1)));


    //                              -----COIN-----

    public static final RegistryObject<Item> ARLO_COIN = ITEMS.register("arlo_coin", () -> new ArloCoinItem(new Item.Properties().tab(BotsMod.BotsItemGroup.instance).stacksTo(1)));

    public static final RegistryObject<Item> MANIAC_CITY_COIN = ITEMS.register("maniac_city_coin", () -> new ManiacCityCoin(new Item.Properties().tab(BotsMod.BotsItemGroup.instance).stacksTo(1)));

    public static final RegistryObject<Item> PROFOUND_CITY_COIN = ITEMS.register("profound_city_coin", () -> new ProfoundCityCoin(new Item.Properties().tab(BotsMod.BotsItemGroup.instance).stacksTo(1)));

    public static final RegistryObject<Item> STARLING_CITY_COIN = ITEMS.register("starling_city_coin", () -> new StarlingCityCoin(new Item.Properties().tab(BotsMod.BotsItemGroup.instance).stacksTo(1)));

    //public static final RegistryObject<Item> CELL_FLUID_BUCKET = ITEMS.register("cell_fluid_bucket", () -> new FleshBucket(BOTSFluids.CELL.get(), new Item.Properties().tab(BotsMod.BotsItemGroup.instance).stacksTo(1)));
}

