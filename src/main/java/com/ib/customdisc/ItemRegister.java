package com.ib.customdisc;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.HashMap;

@Mod.EventBusSubscriber
public class ItemRegister {
    private static HashMap<String, DiscItem> discs = new HashMap<>();
    public static HashMap<String, SoundEvent> sounds = new HashMap<>();

    private static SoundEvent makeSound(String name){
        ResourceLocation location = new ResourceLocation(CustomDisc.MODID, name);
        return new SoundEvent(location).setRegistryName(location);
    }

    @SubscribeEvent
    public static void onRegItem(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        String[] list = DiscConfig.getDiscList();
        for (String disc : list) {
            SoundEvent newSound = makeSound(disc);
            DiscItem newDisc = new DiscItem(disc, newSound);

            sounds.put(disc, newSound);
            discs.put(disc, newDisc);
            registry.register(newDisc);

        }
    }

    @SubscribeEvent
    public static void onRegModel(ModelRegistryEvent event) {
        for (DiscItem disc : discs.values()) {
            regModel(disc, 0);
        }
    }

    private static void regModel(Item item, int meta) {
        ModelResourceLocation modelResourceLocation = new ModelResourceLocation(item.getRegistryName(), "inventory");
        ModelLoader.setCustomModelResourceLocation(item, meta, modelResourceLocation);
    }
}
