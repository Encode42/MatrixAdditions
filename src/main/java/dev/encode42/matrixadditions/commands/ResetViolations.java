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

public class ResetViolations {
	@CommandMethod("matrixadditions resetvl <player> [hack]")
	@CommandDescription("Reset the violation level for all or specific checks on a player.")
	@CommandPermission("matrix.command")
	private void resetViolations(
		CommandSender sender,
		@Argument("player") MultiplePlayerSelector player,
		@Argument("hack") HackType hackType
	) {
		// Get the API
		MatrixAPI matrixAPI = MatrixAdditions.matrixAPI();

		for (Player p : player.getPlayers()) {
			// Specified hack type
			if (hackType != null) {
				// Reset violations
				matrixAPI.setViolations(p.getPlayer(), hackType, 0);

				Message.send(sender, "Cleared all " + hackType.name() + " violations for " + p.getPlayer().getName() + "!");
				return;
			}

			// Clear all hack type violations
			for (HackType h : HackType.values()) {
				// Reset violations
				matrixAPI.setViolations(p.getPlayer(), h, 0);
			}

			Message.send(sender, "Cleared " + p.getPlayer().getName() + "'s violations!");
		}
	}
}
