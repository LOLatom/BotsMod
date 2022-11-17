package com.thefreak.botsmod.init.iteminit;

import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.init.EffectInitNew;
import com.thefreak.botsmod.objects.items.Candy;
import com.thefreak.botsmod.objects.items.DreamBerryItem;
import com.thefreak.botsmod.objects.items.PiloveFruitItem;
import com.thefreak.botsmod.objects.items.PinkPurifiedSalt;
import com.thefreak.botsmod.objects.items.organs.HeartItem;
import com.thefreak.botsmod.objects.items.organs.PerishedHeartItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class FoodItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BotsMod.MOD_ID);

    public static final RegistryObject<Item> COOKED_PILOVE_FRUIT = ITEMS.register("cooked_pilove_fruit", () -> new Item(new Item.Properties().tab(BotsMod.BotsfoodItemGroup.food).food(new FoodProperties.Builder().nutrition(7).saturationMod(5F).build())));

    public static final RegistryObject<Item> PILOVE_FRUIT = ITEMS.register("pilove_fruit", () -> new PiloveFruitItem(new Item.Properties().tab(BotsMod.BotsfoodItemGroup.food).food(new FoodProperties.Builder().nutrition(5).saturationMod(3F).effect( () -> new MobEffectInstance(EffectInitNew.EXPLOSIVE_EFFECT.get(), 400, 1),1.0F).fast().build())));

    public static final RegistryObject<Item> DREAM_BERRY = ITEMS.register("dream_berry", () -> new DreamBerryItem(new Item.Properties().tab(BotsMod.BotsfoodItemGroup.food).food(new FoodProperties.Builder().nutrition(4).saturationMod(5F).alwaysEat().build())));


    public static final RegistryObject<Item> TRUFFLE = ITEMS.register("truffle", () -> new Item(new Item.Properties()
            .tab(BotsMod.BotsfoodItemGroup.food)
            .food(new FoodProperties.Builder().nutrition(2).saturationMod(0.5F)
                    .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 200, 2 ), 3).build())));

    public static final RegistryObject<Item> TUBER_BERRIES = ITEMS.register("tuber_berries", () -> new Item(new Item.Properties()
            .tab(BotsMod.BotsfoodItemGroup.food)
            .food(new FoodProperties.Builder().nutrition(5).saturationMod(0.5F)
                    .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 600, 3 ), 3).build())));


    public static final RegistryObject<Item> BLUE_CANDY = ITEMS.register("blue_candy", () -> new Candy(new Item.Properties()
            .tab(BotsMod.BotsfoodItemGroup.food)
            .food(new FoodProperties.Builder().nutrition(0).saturationMod(1.5F)
                    .build())));

    public static final RegistryObject<Item> YELLOW_CANDY = ITEMS.register("yellow_candy", () -> new Candy(new Item.Properties()
            .tab(BotsMod.BotsfoodItemGroup.food)
            .food(new FoodProperties.Builder().nutrition(0).saturationMod(1.5F)
                    .build())));

    public static final RegistryObject<Item> RED_CANDY = ITEMS.register("red_candy", () -> new Candy(new Item.Properties()
            .tab(BotsMod.BotsfoodItemGroup.food)
            .food(new FoodProperties.Builder().nutrition(0).saturationMod(1.5F)
                    .build())));

    public static final RegistryObject<Item> GREEN_CANDY = ITEMS.register("green_candy", () -> new Candy(new Item.Properties()
            .tab(BotsMod.BotsfoodItemGroup.food)
            .food(new FoodProperties.Builder().nutrition(0).saturationMod(1.5F)
                    .build())));

    public static final RegistryObject<Item> PINK_CANDY = ITEMS.register("pink_candy", () -> new Candy(new Item.Properties()
            .tab(BotsMod.BotsfoodItemGroup.food)
            .food(new FoodProperties.Builder().nutrition(0).saturationMod(1.5F)
                    .build())));

    public static final RegistryObject<Item> PINK_PURIFIED_SALT = ITEMS.register("pink_purified_salt", () -> new PinkPurifiedSalt(new Item.Properties()
            .tab(BotsMod.BotsfoodItemGroup.food)
            .food(new FoodProperties.Builder().nutrition(-1).saturationMod(-0.5F)
                    .build())));

    public static final RegistryObject<Item> DECAY_BERRY = ITEMS.register("decay_berry", () -> new Item(new Item.Properties()
            .tab(BotsMod.BotsfoodItemGroup.food)
            .food(new FoodProperties.Builder().nutrition(5).saturationMod(0.5F)
                    .effect(() -> new MobEffectInstance(EffectInitNew.DECAYING.get(), 800, 0 ), 3).build())));

    public static final RegistryObject<Item> GINGER_ROOT = ITEMS.register("ginger_root", () -> new Item(new Item.Properties()
            .tab(BotsMod.BotsfoodItemGroup.food)
            .food(new FoodProperties.Builder().nutrition(-1).saturationMod(-0.5F)
                    .build())));

    public static final RegistryObject<Item> CARAMEL_APPLE = ITEMS.register("caramel_apple", () -> new Item(new Item.Properties()
            .tab(BotsMod.BotsfoodItemGroup.food)
            .food(new FoodProperties.Builder().nutrition(5).saturationMod(5)
                    .build())));

    public static final RegistryObject<Item> CONDENSED_MILK_APPLE = ITEMS.register("condensed_milk_apple", () -> new Item(new Item.Properties()
            .tab(BotsMod.BotsfoodItemGroup.food)
            .food(new FoodProperties.Builder().nutrition(7).saturationMod(3)
                    .build())));

    public static final RegistryObject<Item> LUXURE_MELON = ITEMS.register("luxure_melon", () -> new Item(new Item.Properties()
            .tab(BotsMod.BotsfoodItemGroup.food)
            .food(new FoodProperties.Builder().nutrition(5).saturationMod(4)
                    .build())));

    public static final RegistryObject<Item> RED_BIRD_BERRIES = ITEMS.register("red_bird_berries", () -> new Item(new Item.Properties()
            .tab(BotsMod.BotsfoodItemGroup.food)
            .food(new FoodProperties.Builder().nutrition(5).saturationMod(4).fast()
                    .build())));

    public static final RegistryObject<Item> HEART = ITEMS.register("heart", () -> new HeartItem(new Item.Properties()
            .tab(BotsMod.BotsfoodItemGroup.food)
            .food(new FoodProperties.Builder().nutrition(5).saturationMod(4)
                    .build()).stacksTo(1)));

    public static final RegistryObject<Item> PERISHED_HEART = ITEMS.register("perished_heart", () -> new PerishedHeartItem(new Item.Properties()
            .tab(BotsMod.BotsfoodItemGroup.food)
            .food(new FoodProperties.Builder().nutrition(-1).saturationMod(-4)
                    .build()).stacksTo(1)));

}
