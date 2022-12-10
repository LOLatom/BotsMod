package com.thefreak.botsmod.spells;

import com.thefreak.botsmod.spells.spellclass.AbstractSpell;
import com.thefreak.botsmod.spells.spellclass.spelltype.BlankSpell;
import com.thefreak.botsmod.spells.spellclass.spelltype.FreezeSpell;
import com.thefreak.botsmod.spells.spellclass.spelltype.UnfreezeSpell;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;

import java.util.ArrayList;
import java.util.logging.Level;

public class SpellIdentifier {
        public ArrayList<AbstractSpell> spellList = new ArrayList<>();
        public ArrayList<AbstractSpell> spellListUseOn = new ArrayList<>();
        public ArrayList<AbstractSpell> spellListLeft = new ArrayList<>();
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

        public SpellIdentifier(boolean f1, boolean f2, boolean f3, boolean f4, boolean blade, int ID,ItemStack stack) {
            this.f01 = f1;
            this.f2 = f2;
            this.f3 = f3;
            this.f4 = f4;
            this.blade = blade;
            this.ID = ID;
            this.itemStack = stack;
            prepareList(f1,f2,f3,f4,blade,ID,stack);
            prepareListUseOn(f1,f2,f3,f4,blade,ID,stack);
            prepareListLeft(f1,f2,f3,f4,blade,ID,stack);
        }

        public void prepareList(boolean f1, boolean f2, boolean f3, boolean f4, boolean blade, int ID,ItemStack stack) {
            //this.spellList.add(new BlankSpell(stack,f1,f2,f3,f4,blade)); //ID: 0
            this.spellList.add(new FreezeSpell(stack,f1,f2,f3,f4,blade)); //ID: 1
        }
        public void prepareListUseOn(boolean f1, boolean f2, boolean f3, boolean f4, boolean blade, int ID,ItemStack stack) {
            this.spellListUseOn.add(new BlankSpell(stack,f1,f2,f3,f4,blade)); //ID: 0
            //this.spellListUseOn.add(new BlankSpell(stack,f1,f2,f3,f4,blade)); //ID: 1

        }
        public void prepareListLeft(boolean f1, boolean f2, boolean f3, boolean f4, boolean blade, int ID,ItemStack stack) {
            //this.spellListLeft.add(new BlankSpell(stack,f1,f2,f3,f4,blade)); //ID: 0
            this.spellListLeft.add(new UnfreezeSpell(stack,f1,f2,f3,f4,blade)); //ID: 1
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



}
