package com.ib.customdisc;

import net.minecraft.item.ItemRecord;
import net.minecraft.util.SoundEvent;

public class DiscItem extends ItemRecord {
    protected DiscItem(String name, SoundEvent sound) {
        super(name, sound);
        setRegistryName(name);
        setUnlocalizedName(name);
    }
}
