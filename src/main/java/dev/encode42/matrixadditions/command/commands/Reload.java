package dev.encode42.matrixadditions.command.commands;

import cloud.commandframework.annotations.CommandDescription;
import cloud.commandframework.annotations.CommandMethod;
import cloud.commandframework.annotations.CommandPermission;
import dev.encode42.matrixadditions.command.CommandRegistration;
import dev.encode42.encodedapi.Message;
import dev.encode42.matrixadditions.MatrixAdditions;
import org.bukkit.command.CommandSender;

import java.util.Map;

@CommandRegistration
public class Reload {
	@CommandMethod("matrixadditions reload")
	@CommandDescription("Reload the configuration files for MatrixAdditions")
	@CommandPermission("matrixadditions.reload")
	private void reload(CommandSender sender) {
		// Reload
		MatrixAdditions.reload();

		// Command response
		Message.send(sender, MatrixAdditions.getMessages().getString("commands.reload"), Map.of(
			"version", MatrixAdditions.version
		));
	}
}
