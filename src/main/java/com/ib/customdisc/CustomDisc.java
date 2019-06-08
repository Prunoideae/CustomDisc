package com.ib.customdisc;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.FolderResourcePack;
import net.minecraft.client.resources.IResourcePack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.List;

@Mod(modid = CustomDisc.MODID, name = CustomDisc.NAME, version = CustomDisc.VERSION)
public class CustomDisc {
    public static final String MODID = "customdisc";
    public static final String NAME = "Custom Disc";
    public static final String VERSION = "1.0";

    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();

        File directory = new File("customdisc/assets/customdisc/");
        if (!directory.exists()){
            directory.mkdirs();
        }

        FolderResourcePack forDisc = new FolderResourcePack(new File("customdisc/"));
        List<IResourcePack> defaultResourcePacks = ReflectionHelper.getPrivateValue(
                Minecraft.class, Minecraft.getMinecraft(), "defaultResourcePacks");
        defaultResourcePacks.add(forDisc);
        ReflectionHelper.setPrivateValue(
                Minecraft.class, Minecraft.getMinecraft(), defaultResourcePacks, "defaultResourcePacks");

        //I know it's deprecated but...
        Minecraft.getMinecraft().refreshResources();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {

    }
}
