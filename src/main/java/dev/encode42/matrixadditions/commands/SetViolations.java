package dev.encode42.matrixadditions.commands;

import cloud.commandframework.annotations.Argument;
import cloud.commandframework.annotations.CommandDescription;
import cloud.commandframework.annotations.CommandMethod;
import cloud.commandframework.annotations.CommandPermission;
import cloud.commandframework.bukkit.arguments.selector.MultiplePlayerSelector;
import dev.encode42.encodedapi.Message;
import dev.encode42.matrixadditions.MatrixAdditions;
import me.rerere.matrix.api.HackType;
import me.rerere.matrix.api.MatrixAPI;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetViolations {
	@CommandMethod("matrixadditions setvl <player> <hack> <level>")
	@CommandDescription("Set the violation level for specific checks on a player.")
	@CommandPermission("matrix.command")
	private void resetViolations(
		CommandSender sender,
		@Argument("player") MultiplePlayerSelector player,
		@Argument("hack") HackType hackType,
		@Argument("level") int level
	) {
		// Get the API
		MatrixAPI matrixAPI = MatrixAdditions.matrixAPI();

		for (Player p : player.getPlayers()) {
			// Reset violations
			matrixAPI.setViolations(p.getPlayer(), hackType, level);

			Message.send(sender, "Set " + hackType.name() + " violations for " + p.getPlayer().getName() + " to " + level + "!");
		}
	}
}
