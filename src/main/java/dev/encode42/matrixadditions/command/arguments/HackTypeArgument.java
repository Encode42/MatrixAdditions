package dev.encode42.matrixadditions.command.arguments;

import cloud.commandframework.annotations.parsers.Parser;
import cloud.commandframework.annotations.suggestions.Suggestions;
import cloud.commandframework.context.CommandContext;
import cloud.commandframework.exceptions.parsing.NoInputProvidedException;
import dev.encode42.matrixadditions.command.CommandRegistration;
import me.rerere.matrix.api.HackType;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

@CommandRegistration
public class HackTypeArgument {
	@Parser(suggestions = "HackType")
	public HackType hackTypeArgument(CommandContext<CommandSender> sender, Queue<String> inputQueue) {
		String input = inputQueue.peek();

		// No input
		if (input == null) throw new NoInputProvidedException(HackTypeArgument.class, sender);

		// Parse the input
		try {
			return HackType.valueOf(input.toUpperCase());
		} catch (IllegalArgumentException ignore) {
			// Invalid input
			throw new IllegalArgumentException("test123");
		}
	}

	@Suggestions("HackType")
	public List<String> hackTypeSuggestions(CommandContext<CommandSender> sender, String input) {
		return Arrays.stream(HackType.values()).map(HackType::name).collect(Collectors.toList());
	}
}