package com.ib.customdisc;

import net.minecraftforge.common.config.Config;

@Config(modid = CustomDisc.MODID, name = "custom_disc")
public class DiscConfig {
    @Config.Name("Disc List")
    @Config.Comment("List of disc you want to add into the game.")
    @Config.RequiresMcRestart
    public static String[] discList = {};

    public static String[] getDiscList() {
        return discList;
    }
}
