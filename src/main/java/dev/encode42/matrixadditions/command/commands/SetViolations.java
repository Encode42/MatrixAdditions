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
public class SetViolations {
	@CommandMethod("matrixadditions setvl <player> <hack> <level>")
	@CommandDescription("Set the violation level for specific checks on a player.")
	@CommandPermission("matrixadditions.setvl")
	private void setViolations(
		CommandSender sender,
		@Argument("player") MultiplePlayerSelector player,
		@Argument("hack") HackType hackType,
		@Argument("level") int level
	) {
		Config messages = MatrixAdditions.getMessages();

		// Get the API
		MatrixAPI matrixAPI = MatrixAdditions.matrixAPI();

		for (Player p : player.getPlayers()) {
			// Set violations
			matrixAPI.setViolations(p.getPlayer(), hackType, level);

			// Command response
			Message.send(sender, messages.getString("commands.setvl"), Map.of(
				"player", p.getName(),
				"hack", hackType.name(),
				"number", String.valueOf(level)
			));
		}
	}
}
