package com.thefreak.botsmod.API;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import javax.swing.text.html.parser.Entity;


public interface IDuelMode {

    boolean isInDuel();
    void setInDuel(boolean inDuel, int ID);

    void createDuel(LivingEntity entity, float dmg, Level level);
}
