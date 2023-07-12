package com.sparkinex.firsttimeplayerbroadcast;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Mod.EventBusSubscriber(modid = FirstTimePlayerBroadcast.MODID)
public class FirstTimeBroadcastListener {
    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();

        if (!player.getPersistentData().contains("hasJoinedBefore")) {
            String message = translateColorCodes(Config.welcomeMessage.replace("[username]", player.getDisplayName().getString()));
            broadcastMessage(player.level.getServer(), Component.literal(message));
            player.getPersistentData().putBoolean("hasJoinedBefore", true);
        }
    }

    private static void broadcastMessage(MinecraftServer server, Component message) {
        if (server == null) {
            return;
        }

        for (ServerPlayer p : server.getPlayerList().getPlayers()) {
            p.sendSystemMessage(message);
        }
    }

    private static String translateColorCodes(String text) {
        Pattern hexPattern = Pattern.compile("&#[a-fA-F0-9]{6}");

        Matcher hexMatcher = hexPattern.matcher(text);
        StringBuffer sb = new StringBuffer();
        while (hexMatcher.find()) {
            String hexCode = hexMatcher.group();
            int rgb = Integer.parseInt(hexCode.substring(2), 16);
            String forgeCode = TextColor.fromRgb(rgb).toString();
            hexMatcher.appendReplacement(sb, forgeCode);
        }
        hexMatcher.appendTail(sb);

        return sb.toString().replace("&", "\u00A7");
    }
}
