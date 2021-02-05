package dev.encode42.matrixadditions;

import dev.encode42.encodedapi.Command;
import dev.encode42.encodedapi.Config;
import dev.encode42.encodedapi.Message;
import dev.encode42.encodedapi.Util;
import dev.encode42.matrixadditions.arguments.HackTypeArgument;
import dev.encode42.matrixadditions.commands.ResetViolations;
import dev.encode42.matrixadditions.commands.SetViolations;
import me.rerere.matrix.api.MatrixAPI;
import me.rerere.matrix.api.MatrixAPIProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class MatrixAdditions extends JavaPlugin {
	private static MatrixAdditions instance;
	private static MatrixAPI matrixAPI;

	public MatrixAdditions() {
		instance = this;
	}

	public static MatrixAdditions getInstance() {
		return instance;
	}

	public static MatrixAPI matrixAPI() {
		return matrixAPI;
	}

	private static Config messages;
	public static Config getMessages() {
		return messages;
	}

	@Override
	public void onEnable() {
		// Get the Matrix API
		matrixAPI = MatrixAPIProvider.getAPI();

		// Initialize utilities
		Util.setPlugin(this);

		// Create configuration files
		messages = new Config("messages", true);
		Message.setPrefix(messages.getString("prefix"));

		// Register commands
		Command.register(this,
			new HackTypeArgument(),
			new ResetViolations(),
			new SetViolations());
	}
}
