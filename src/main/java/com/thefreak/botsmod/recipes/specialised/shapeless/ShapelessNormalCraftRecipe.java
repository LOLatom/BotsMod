package com.thefreak.botsmod.recipes.specialised.shapeless;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.thefreak.botsmod.BotsMod;
import com.thefreak.botsmod.objects.containers.CoinSpecContainer;
import com.thefreak.botsmod.recipes.BotsRecipeType;
import com.thefreak.botsmod.recipes.specialised.NormalCraftingRecipe;
import com.thefreak.botsmod.recipes.specialised.shaped.ShapedNormalCraftRecipe;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class ShapelessNormalCraftRecipe implements NormalCraftingRecipe {
    private final ResourceLocation id;
    final String group;
    final ItemStack result;
    final NonNullList<Ingredient> ingredients;
    private final boolean isSimple;

    public ShapelessNormalCraftRecipe(ResourceLocation pId, String pGroup, ItemStack pResult, NonNullList<Ingredient> pIngredients) {
        this.id = pId;
        this.group = pGroup;
        this.result = pResult;
        this.ingredients = pIngredients;
        this.isSimple = pIngredients.stream().allMatch(Ingredient::isSimple);
    }

    public ResourceLocation getId() {
        return this.id;
    }

    public RecipeSerializer<?> getSerializer() {
        return BotsRecipeType.NORMAL_SHAPELESS.get();
    }

    /**
     * Recipes with equal group are combined into one button in the recipe book
     */
    public String getGroup() {
        return this.group;
    }

    /**
     * Get the result of this recipe, usually for display purposes (e.g. recipe book). If your recipe has more than one
     * possible result (e.g. it's dynamic and depends on its inputs), then return an empty stack.
     */
    public ItemStack getResultItem() {
        return this.result;
    }

    public NonNullList<Ingredient> getIngredients() {
        return this.ingredients;
    }

    /**
     * Used to check if a recipe matches current crafting inventory
     */
    public boolean matches(CoinSpecContainer pInv, Level pLevel) {
        StackedContents stackedcontents = new StackedContents();
        java.util.List<ItemStack> inputs = new java.util.ArrayList<>();
        int i = 0;

        for(int j = 0; j < pInv.getContainerSize(); ++j) {
            ItemStack itemstack = pInv.getItem(j);
            if (!itemstack.isEmpty()) {
                ++i;
                if (isSimple)
                    stackedcontents.accountStack(itemstack, 1);
                else inputs.add(itemstack);
            }
        }

        return i == this.ingredients.size() && (isSimple ? stackedcontents.canCraft(this, (IntList)null) : net.minecraftforge.common.util.RecipeMatcher.findMatches(inputs,  this.ingredients) != null);
    }

    /**
     * Returns an Item that is the result of this recipe
     */
    public ItemStack assemble(CoinSpecContainer pInv) {
        return this.result.copy();
    }

    /**
     * Used to determine if this recipe can fit in a grid of the given width/height
     */
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return pWidth * pHeight >= this.ingredients.size();
    }

    public static class Serializer implements RecipeSerializer<ShapelessNormalCraftRecipe> {
        public static final ShapelessNormalCraftRecipe.Serializer INSTANCE = new ShapelessNormalCraftRecipe.Serializer();
        private static final ResourceLocation ID = new ResourceLocation("botsmod", "crafting_shapeless");
        public ShapelessNormalCraftRecipe fromJson(ResourceLocation pRecipeId, JsonObject pJson) {
            String s = GsonHelper.getAsString(pJson, "group", "");
            NonNullList<Ingredient> nonnulllist = itemsFromJson(GsonHelper.getAsJsonArray(pJson, "ingredients"));
            if (nonnulllist.isEmpty()) {
                throw new JsonParseException("No ingredients for shapeless recipe");
            } else if (nonnulllist.size() > 3 * 3) {
                throw new JsonParseException("Too many ingredients for shapeless recipe. The maximum is " + (3 * 3));
            } else {
                ItemStack itemstack = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pJson, "result"));
                return new ShapelessNormalCraftRecipe(pRecipeId, s, itemstack, nonnulllist);
            }
        }

        private static NonNullList<Ingredient> itemsFromJson(JsonArray pIngredientArray) {
            NonNullList<Ingredient> nonnulllist = NonNullList.create();

            for(int i = 0; i < pIngredientArray.size(); ++i) {
                Ingredient ingredient = Ingredient.fromJson(pIngredientArray.get(i));
                if (net.minecraftforge.common.ForgeConfig.SERVER.skipEmptyShapelessCheck.get() || !ingredient.isEmpty()) {
                    nonnulllist.add(ingredient);
                }
            }

            return nonnulllist;
        }

        public ShapelessNormalCraftRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            String s = pBuffer.readUtf();
            int i = pBuffer.readVarInt();
            NonNullList<Ingredient> nonnulllist = NonNullList.withSize(i, Ingredient.EMPTY);

            for(int j = 0; j < nonnulllist.size(); ++j) {
                nonnulllist.set(j, Ingredient.fromNetwork(pBuffer));
            }

            ItemStack itemstack = pBuffer.readItem();
            return new ShapelessNormalCraftRecipe(pRecipeId, s, itemstack, nonnulllist);
        }

        public void toNetwork(FriendlyByteBuf pBuffer, ShapelessNormalCraftRecipe pRecipe) {
            pBuffer.writeUtf(pRecipe.group);
            pBuffer.writeVarInt(pRecipe.ingredients.size());

            for(Ingredient ingredient : pRecipe.ingredients) {
                ingredient.toNetwork(pBuffer);
            }

            pBuffer.writeItem(pRecipe.result);
        }

        @Override
        public RecipeSerializer<?> setRegistryName(ResourceLocation name) {
            return INSTANCE;
        }

        @Nullable
        @Override
        public ResourceLocation getRegistryName() {
            return ID;
        }

        @Override
        public Class<RecipeSerializer<?>> getRegistryType() {
            return ShapelessNormalCraftRecipe.Serializer.castClass(RecipeSerializer.class);
        }

        private static <G> Class<G> castClass(Class<?> cls) {
            return (Class<G>)cls;
        }
    }
}
