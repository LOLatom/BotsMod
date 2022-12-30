package com.thefreak.botsmod.spells;

import com.thefreak.botsmod.spells.spellclass.AbstractSpell;
import com.thefreak.botsmod.spells.spellclass.spelltype.*;
import com.thefreak.botsmod.spells.spellclass.spelltype.hurtspell.FireHurtSpell;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.logging.Level;

public class SpellIdentifier {
        public ArrayList<AbstractSpell> spellList = new ArrayList<>();
        public ArrayList<AbstractSpell> spellListUseOn = new ArrayList<>();
        public ArrayList<AbstractSpell> spellListLeft = new ArrayList<>();
    public ArrayList<AbstractSpell> spellListOnAttack = new ArrayList<>();
        public boolean f01;
        public boolean f2;
        public boolean f3;
        public boolean f4;
        public boolean blade;
        public int ID;
        public Player player;
        public ItemStack itemStack;
        public Level level;
        public BlockPos executedAT;
        public UseOnContext context;

        public SpellIdentifier(boolean f1, boolean f2, boolean f3, boolean f4, boolean blade, int ID, ItemStack stack, @Nullable UseOnContext context) {
            this.f01 = f1;
            this.f2 = f2;
            this.f3 = f3;
            this.f4 = f4;
            this.blade = blade;
            this.ID = ID;
            this.itemStack = stack;
            this.context = context;
            prepareList(f1,f2,f3,f4,blade,ID,stack);
            prepareListUseOn(f1,f2,f3,f4,blade,ID,stack,context);
            prepareListLeft(f1,f2,f3,f4,blade,ID,stack);
            prepareListOnAttack(f1,f2,f3,f4,blade,ID,stack);
        }

        public void prepareList(boolean f1, boolean f2, boolean f3, boolean f4, boolean blade, int ID,ItemStack stack) {
            this.spellList.add(new BlankSpell(stack,f1,f2,f3,f4,blade,null)); //ID: 0
            this.spellList.add(new FreezeSpell(stack,f1,f2,f3,f4,blade,null)); //ID: 1
            this.spellList.add(new FireSpell(stack,f1,f2,f3,f4,blade,null)); //ID: 2
            this.spellList.add(new EnderBestialSpell(stack,f1,f2,f3,f4,blade,null)); //ID: 3
            this.spellList.add(new CaveBestialSpell(stack,f1,f2,f3,f4,blade,null)); //ID: 4
            this.spellList.add(new GroundBestialSpell(stack,f1,f2,f3,f4,blade,null)); //ID: 5


        }
        public void prepareListUseOn(boolean f1, boolean f2, boolean f3, boolean f4, boolean blade, int ID,ItemStack stack, UseOnContext context1) {
            this.spellListUseOn.add(new BlankSpell(stack,f1,f2,f3,f4,blade,context1)); //ID: 0
            this.spellListUseOn.add(new BlankSpell(stack,f1,f2,f3,f4,blade,context1)); //ID: 1
            this.spellListUseOn.add(new FireUseOnSpell(stack,f1,f2,f3,f4,blade,context1)); //ID: 2
            this.spellListUseOn.add(new BlankSpell(stack,f1,f2,f3,f4,blade,context1)); //ID: 3
            this.spellListUseOn.add(new BlankSpell(stack,f1,f2,f3,f4,blade,context1)); //ID: 4
            this.spellListUseOn.add(new BlankSpell(stack,f1,f2,f3,f4,blade,context1)); //ID: 5
        }
        public void prepareListLeft(boolean f1, boolean f2, boolean f3, boolean f4, boolean blade, int ID,ItemStack stack) {
            this.spellListLeft.add(new BlankSpell(stack,f1,f2,f3,f4,blade,null)); //ID: 0
            this.spellListLeft.add(new UnfreezeSpell(stack,f1,f2,f3,f4,blade,null)); //ID: 1
            this.spellListLeft.add(new LeftClickFireSpell(stack,f1,f2,f3,f4,blade,null)); //ID: 2
            this.spellListLeft.add(new BlankSpell(stack,f1,f2,f3,f4,blade,null)); //ID: 3
            this.spellListLeft.add(new BlankSpell(stack,f1,f2,f3,f4,blade,null)); //ID: 4
            this.spellListLeft.add(new BlankSpell(stack,f1,f2,f3,f4,blade,null)); //ID: 5
        }
    public void prepareListOnAttack(boolean f1, boolean f2, boolean f3, boolean f4, boolean blade, int ID,ItemStack stack) {
        this.spellListOnAttack.add(new BlankSpell(stack,f1,f2,f3,f4,blade,null)); //ID: 0
        this.spellListOnAttack.add(new BlankSpell(stack,f1,f2,f3,f4,blade,null)); //ID: 1
        this.spellListOnAttack.add(new FireHurtSpell(stack,f1,f2,f3,f4,blade,null)); //ID: 2
        this.spellListOnAttack.add(new BlankSpell(stack,f1,f2,f3,f4,blade,null)); //ID: 3
        this.spellListOnAttack.add(new BlankSpell(stack,f1,f2,f3,f4,blade,null)); //ID: 4
        this.spellListOnAttack.add(new BlankSpell(stack,f1,f2,f3,f4,blade,null)); //ID: 5
    }

        public ResourceLocation getIconFromID(int ID) {
            return this.spellList.get(ID).getIconLocation();
        }


        public AbstractSpell getSpellFromID(int ID) {
            return this.spellList.get(ID);
        }

        public AbstractSpell getUseOnSpellFromID(int ID) {
        return this.spellListUseOn.get(ID);
        }
        public AbstractSpell getLeftCliclkSpellFromID(int ID) {
        return this.spellListLeft.get(ID);
        }
    public AbstractSpell getOnAttackSpellFromID(int ID) {
        return this.spellListOnAttack.get(ID);
    }



}
