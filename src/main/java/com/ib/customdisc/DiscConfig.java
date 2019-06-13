package com.ib.customdisc;

import net.minecraftforge.common.config.Config;

import java.util.Arrays;
import java.util.HashSet;

@Config(modid = CustomDisc.MODID, name = "custom_disc")
public class DiscConfig {
    @Config.Name("Disc List")
    @Config.Comment("List of disc you want to add into the game.")
    @Config.RequiresMcRestart
    public static String[] discList = {};

    public static String[] getDiscList() {
        return new HashSet<>(Arrays.asList(discList)).toArray(new String[0]);
    }
}
