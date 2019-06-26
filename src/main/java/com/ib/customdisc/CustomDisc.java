package com.ib.customdisc;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.FolderResourcePack;
import net.minecraft.client.resources.IResourcePack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Mod(
        modid = CustomDisc.MODID,
        name = CustomDisc.NAME,
        version = CustomDisc.VERSION,
        dependencies = "required-after:forge@[14.23.5.2838);")
public class CustomDisc {
    public static final String MODID = "customdisc";
    public static final String NAME = "Custom Disc";
    public static final String VERSION = "1.3";

    private static Logger logger;

    private static String generateJSON() {
        JsonObject root = new JsonObject();
        for (String s : DiscConfig.getDiscList()) {
            JsonObject entry = new JsonObject();
            JsonObject sound = new JsonObject();
            JsonArray ja = new JsonArray();
            sound.addProperty("name", "customdisc:" + s);
            sound.addProperty("preload", true);
            sound.addProperty("stream", true);
            ja.add(sound);
            entry.add("sounds", ja);
            root.add(s, entry);
        }
        return root.toString();
    }

    @SideOnly(Side.CLIENT)
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();

        //Generate root directory
        File directory = new File("customdisc/assets/customdisc/");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        //Creates pack.mcmeta file
        File mcmeta = new File("customdisc/pack.mcmeta");
        if (!mcmeta.exists()) {
            try {
                FileWriter fw = new FileWriter("customdisc/pack.mcmeta");
                fw.write("{\n" +
                        "  \"pack\": {\n" +
                        "    \"pack_format\": 3,\n" +
                        "    \"description\": \"Custom disc\"\n" +
                        "  } \n" +
                        "}");
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Creates sounds.json
        try {
            FileWriter fw = new FileWriter("customdisc/assets/customdisc/sounds.json");
            fw.write(generateJSON());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Generate folders
        File lang = new File("customdisc/assets/customdisc/lang/");
        if (!lang.exists()) lang.mkdir();
        File sounds = new File("customdisc/assets/customdisc/sounds/");
        if (!sounds.exists()) sounds.mkdir();
        File textures = new File("customdisc/assets/customdisc/textures/items/");
        if (!textures.exists()) textures.mkdirs();
        File models = new File("customdisc/assets/customdisc/models/item/");
        if (!models.exists()) models.mkdirs();

        //Generate model file
        for (String s : DiscConfig.getDiscList()) {
            try {
                FileWriter fw = new FileWriter(
                        "customdisc/assets/customdisc/models/item/" + s + ".json");
                JsonObject root = new JsonObject();
                JsonObject tts = new JsonObject();
                tts.addProperty("layer0", "customdisc:items/" + s);
                root.addProperty("parent", "minecraft:item/generated");
                root.add("textures", tts);
                fw.write(root.toString());
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FolderResourcePack forDisc = new FolderResourcePack(new File("customdisc/"));
        List<IResourcePack> defaultResourcePacks = ObfuscationReflectionHelper.getPrivateValue(
                Minecraft.class, Minecraft.getMinecraft(), "field_110449_ao");
        defaultResourcePacks.add(forDisc);
        ObfuscationReflectionHelper.setPrivateValue(
                Minecraft.class, Minecraft.getMinecraft(), defaultResourcePacks, "field_110449_ao");

        //I know it's deprecated but...
        Minecraft.getMinecraft().refreshResources();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {

    }
}
