package dev.encode42.matrixadditions;

import dev.encode42.encodedapi.Command;
import dev.encode42.encodedapi.Config;
import dev.encode42.encodedapi.Message;
import dev.encode42.encodedapi.Util;
import dev.encode42.matrixadditions.command.CommandRegistration;
import me.rerere.matrix.api.MatrixAPI;
import me.rerere.matrix.api.MatrixAPIProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class MatrixAdditions extends JavaPlugin {
	private static MatrixAdditions instance;
	private static MatrixAPI matrixAPI;
	private static Config messages;
	public static String version;

	public MatrixAdditions() {
		instance = this;
	}
	public static MatrixAdditions getInstance() {
		return instance;
	}
	public static MatrixAPI matrixAPI() {
		return matrixAPI;
	}
	public static Config getMessages() {
		return messages;
	}

	@Override
	public void onEnable() {
		version = getDescription().getVersion();

		// Get the Matrix API
		matrixAPI = MatrixAPIProvider.getAPI();

		// Initialize utilities
		Util.setPlugin(this);

		// Create configuration files
		messages = new Config("messages", true);
		reload();

		// Command and argument registration
		Command.create(this);
		Command.recursiveRegister("dev.encode42.matrixadditions.command", CommandRegistration.class);
	}

	// Reload the plugin
	public static void reload() {
		// Messages
		messages.reload();
		Message.setPrefix(messages.getString("prefix"));
	}
}
