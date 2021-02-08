package dev.encode42.matrixadditions.command.commands;

import cloud.commandframework.annotations.Argument;
import cloud.commandframework.annotations.CommandDescription;
import cloud.commandframework.annotations.CommandMethod;
import cloud.commandframework.annotations.CommandPermission;
import cloud.commandframework.bukkit.arguments.selector.MultiplePlayerSelector;
import dev.encode42.matrixadditions.command.CommandRegistration;
import dev.encode42.encodedapi.Config;
import dev.encode42.encodedapi.Message;
import dev.encode42.matrixadditions.MatrixAdditions;
import me.rerere.matrix.api.HackType;
import me.rerere.matrix.api.MatrixAPI;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;

@CommandRegistration
public class TempBypassAll {
	@CommandMethod("matrixadditions tempbypassall <player> <time>")
	@CommandDescription("Temporarily bypasses all checks for a player.")
	@CommandPermission("matrixadditions.tempbypassall")
	private void tempBypassAll(
			CommandSender sender,
			@Argument("player") MultiplePlayerSelector player,
			@Argument("time") int time
	) {
		Config messages = MatrixAdditions.getMessages();

		// Get the API
		MatrixAPI matrixAPI = MatrixAdditions.matrixAPI();

		for (Player p : player.getPlayers()) {
			// Temporary bypass for all hack types
			for (HackType h : HackType.values()) matrixAPI.tempBypass(p, h, (long) time);

			// Command response
			Message.send(sender, messages.getString("commands.tempbypassall"), Map.of(
				"player", p.getName(),
				"time", String.valueOf(time)
			));
		}
	}
}
