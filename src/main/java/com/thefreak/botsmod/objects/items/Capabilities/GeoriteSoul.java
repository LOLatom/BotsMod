package com.thefreak.botsmod.objects.items.Capabilities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;

import javax.annotation.Nullable;

public class GeoriteSoul {

    public GeoriteSoul() {
        this(null);
    }

    public GeoriteSoul(@Nullable String soulname) {
        soultype = soulname;
    }

    @Nullable
    private String soultype;
    private int red;
    private int green;
    private int blue;

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public int getBlue() {
        return blue;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getGreen() {
        return green;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getRed() {
        return red;
    }

    public void setSoultype(@Nullable String soultype, int r, int g, int b) {
        this.soultype = soultype;
        this.red = r;
        this.green = g;
        this.blue = b;
    }

    @Nullable
    public String getSoultype() {
        return soultype;
    }

    public static class GeoriteSoulNBTStore implements Capability.IStorage<GeoriteSoul> {


        @Override
        public INBT writeNBT(Capability<GeoriteSoul> capability, GeoriteSoul instance, Direction side) {


            CompoundNBT nbt = new CompoundNBT();
            nbt.putString("soultype", instance.soultype);
            nbt.putInt("r", instance.red);
            nbt.putInt("g", instance.green);
            nbt.putInt("b", instance.blue);
            return nbt;
        }

        @Override
        public void readNBT(Capability<GeoriteSoul> capability, GeoriteSoul instance, Direction side, INBT nbt) {

            @Nullable
            String soultype = null;
            int r = 0;
            int g = 0;
            int b = 0;
            CompoundNBT nnbt = (CompoundNBT) nbt;
            soultype = nnbt.getString("soultype");
            r = nnbt.getInt("r");
            g = nnbt.getInt("g");
            b = nnbt.getInt("b");
            instance.setSoultype(soultype, r, g, b);
        }

    }

    public static GeoriteSoul defaultInstance() {
        return new GeoriteSoul();
    }
}
