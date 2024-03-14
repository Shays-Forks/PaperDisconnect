package com.cookies250;

import net.fabricmc.api.ClientModInitializer;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.network.packet.c2s.play.UpdateSelectedSlotC2SPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PaperDisconnect implements ClientModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("paperdisconnect");
	public static MinecraftClient mc = MinecraftClient.getInstance();

	@Override
	public void onInitializeClient() {
		LOGGER.info("Initializing PaperDisconnect");

	}

	public static void disconnect(ButtonWidget buttonWidget) {
		ClientPlayNetworkHandler networkHandler = mc.getNetworkHandler();
		ClientConnection clientConnection = networkHandler.getConnection();
		if (mc.player != null) networkHandler.sendPacket(PlayerInteractEntityC2SPacket.attack(mc.player, false));
		networkHandler.sendPacket(new UpdateSelectedSlotC2SPacket(10));
		clientConnection.flush();
	}
}