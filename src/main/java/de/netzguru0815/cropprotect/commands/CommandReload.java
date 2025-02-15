package de.netzguru0815.cropprotect.commands;

import de.netzguru0815.cropprotect.CropProtect;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandReload implements CommandExecutor {

    private final CropProtect plugin;

    public CommandReload(final CropProtect plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (sender instanceof final Player player && !player.hasPermission("cropprotect.command.reload")) {
            player.sendMessage("§cYou do not have permission to execute this command!");
            return true;
        }

        // Reload plugin
        this.plugin.reloadConfig();
        sender.sendMessage("§aCropProtect configuration reloaded!");
        this.plugin.getLogger().info("Configuration reloaded by " + sender.getName());
        return true;
    }
}
