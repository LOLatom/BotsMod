package com.thefreak.botsmod.util.misc;

import com.google.common.base.Splitter;
import com.ibm.icu.impl.locale.XCldrStub;
import com.mojang.datafixers.types.templates.CompoundList;
import com.thefreak.botsmod.ClassReferences;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;
import oshi.util.tuples.Pair;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;

@OnlyIn(Dist.CLIENT)
public class SecretOptions {
    private static Minecraft minecraft = ClassReferences.getClientMC();
    private File botsfile = new File(minecraft.gameDirectory.getAbsoluteFile().getPath() + File.separator + "BOTS");
    private File savefolder = new File(minecraft.gameDirectory.getAbsoluteFile().getPath() + File.separator + "BOTS" + File.separator + "saves");
    private File savefile = new File(minecraft.gameDirectory.getAbsoluteFile().getPath() + File.separator + "BOTS" + File.separator + "saves" + File.separator +"bots.save");
    private File optionfile = new File(minecraft.gameDirectory.getAbsoluteFile().getPath() + File.separator + "BOTS" + File.separator + "options.dlt");
    private boolean optionexist;

    //values:

    private boolean hasKey = false;
    private boolean hasAccessToDoor1 = false;
    private boolean hasComputerUnlocked = false;
    private boolean hasGlobalAchievement = false;
    private String savedLastCode = "Code";
    private String keyPosX = "30";
    private String keyPosY = "30";
    private boolean isKeyVisible = false;

    //options:

    private boolean menuPencil = false;


    private ArrayList<Pair<String,Object>> optionlist = new ArrayList<Pair<String,Object>>();
    private ArrayList<Pair<String,Object>> savelist = new ArrayList<Pair<String,Object>>();

    public HashMap<String,String> info = new HashMap<String,String>();
    public HashMap<String,String> info2 = new HashMap<String,String>();


    public SecretOptions() {
        set();
        this.load();
    }
    public void set() {
        this.savelist.clear();
        this.savelist.add(new Pair<>("hasKey",this.hasKey));
        this.savelist.add(new Pair<>("doorone",this.hasAccessToDoor1));
        this.savelist.add(new Pair<>("lastcode",this.savedLastCode));
        this.savelist.add(new Pair<>("computerUnlocked",this.hasComputerUnlocked));
        this.savelist.add(new Pair<>("hasGlobalAchievement",this.hasGlobalAchievement));
        this.savelist.add(new Pair<>("keyPosX", this.keyPosX));
        this.savelist.add(new Pair<>("keyPosY",this.keyPosY));
        this.savelist.add(new Pair<>("keyVisible",this.isKeyVisible));

        this.optionlist.clear();
        this.optionlist.add(new Pair<>("menuPencil",this.menuPencil));
    }

    public void load() {

            info2.clear();
            info.clear();

        try {
            FileInputStream fis = new FileInputStream(getSavefile());
            String content = new String(fis.readAllBytes());
            fis.close();
            String[] lines = content.split("\n");
            for (int i = 0; i < lines.length; i++) {
                String[] splitted = lines[i].split(":");
                info.put(splitted[0],splitted[1]);
            }
        } catch (Exception e) {

        }
        try {
            FileInputStream fis = new FileInputStream(getOptionfile());
            String content = new String(fis.readAllBytes());
            fis.close();
            String[] lines = content.split("\n");
            for (int i = 0; i < lines.length; i++) {
                String[] splitted = lines[i].split(":");
                info2.put(splitted[0],splitted[1]);
            }
        } catch (Exception e) {

        }
        this.hasKey = info.get("hasKey").equals("true");
        this.hasAccessToDoor1 = info.get("doorone").equals("true");
        this.savedLastCode = info.get("lastcode");
        this.hasComputerUnlocked = info.get("computerUnlocked").equals("true");
        this.hasGlobalAchievement = info.get("hasGlobalAchievement").equals("true");
        this.isKeyVisible = info.get("keyVisible").equals("true");


        this.menuPencil = info2.get("menuPencil").equals("true");

        set();
    }

    public void setSavedLastCode(String savedLastCode) {
        this.savedLastCode = savedLastCode;
        save();
        load();
    }

    public void setKeyXY(int x, int y) {
        this.keyPosX = toString();
        this.keyPosY = toString();
        save();
        load();
    }

    public String getKeyPosX() {
        return keyPosX;
    }

    public String getKeyPosY() {
        return keyPosY;
    }

    public String getSavedLastCode() {
        return this.savedLastCode;
    }

    public ArrayList<Pair<String, Object>> getSavelist() {
        return savelist;
    }

    public ArrayList<Pair<String, Object>> getOptionlist() {
        return optionlist;
    }

    public void createOptions() {

        this.botsfile.mkdir();

        this.savefolder.mkdir();

        try {

            this.optionfile.createNewFile();
            this.optionfile.setWritable(true);
        } catch (Exception e) {
            e.getStackTrace();
        }
        try {

            this.savefile.createNewFile();
            this.savefile.setWritable(true);
        } catch (Exception e) {
            e.getStackTrace();
        }

        writeFile(this.optionlist, this.optionfile);
        writeFile(this.savelist, this.savefile);
    }

    public boolean isHasKey() {
        return hasKey;
    }

    @Nullable
    public Path getBotsPath() {
        return this.botsfile.toPath();
    }

    @Nullable
    public File getOptionfile() {
        return this.optionfile.exists() ? this.optionfile : null;
    }

    @Nullable
    public File getSavefile() {
        return this.savefile.exists() ? this.savefile : null;
    }

    public boolean toBool(String str) {
        if (str == "true") {
            return true;
        } else return false;
    }



    public void save() {
        set();
        writeFile(this.optionlist,this.optionfile);
        writeFile(this.savelist,this.savefile);
    }

    public void writeFile(ArrayList<Pair<String,Object>> file, File in) {
        try {
            final PrintWriter printwriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(in), StandardCharsets.UTF_8));

            for (int i = 0; i < file.size() ; i++) {
                printwriter.write(file.get(i).getA() + ":" + file.get(i).getB() + "\n");
            }
            printwriter.close();
        } catch (Exception e) {}
    }


}
