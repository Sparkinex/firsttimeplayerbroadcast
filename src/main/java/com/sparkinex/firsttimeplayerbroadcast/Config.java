package com.sparkinex.firsttimeplayerbroadcast;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = FirstTimePlayerBroadcast.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.ConfigValue<String> WELCOME_MESSAGE = BUILDER
            .comment("The welcome message to broadcast to all players when a first time user joins. Use '[username]' to insert the username of the player.'")
            .define("welcomeMessage", "&dWelcome &e[username] &dto the server!");

    protected static final ForgeConfigSpec SPEC = BUILDER.build();

    public static String welcomeMessage;

    @SubscribeEvent
    public static void onLoad(final ModConfigEvent event) {
        welcomeMessage = WELCOME_MESSAGE.get();
    }
}
