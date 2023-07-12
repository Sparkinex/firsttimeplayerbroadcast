package com.sparkinex.firsttimeplayerbroadcast;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod(FirstTimePlayerBroadcast.MODID)
public class FirstTimePlayerBroadcast {
    public static final String MODID = "firsttimeplayerbroadcast";

    public FirstTimePlayerBroadcast() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
}
