package com.ib.customdisc;

import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber
public class SoundRegister {

    @SubscribeEvent
    public static void onRegSound(RegistryEvent.Register<SoundEvent> event) {
        IForgeRegistry<SoundEvent> registry = event.getRegistry();
        String[] list = DiscConfig.getDiscList();

        for (String disc : list) {
            registry.register(ItemRegister.sounds.get(disc));
        }
    }
}
