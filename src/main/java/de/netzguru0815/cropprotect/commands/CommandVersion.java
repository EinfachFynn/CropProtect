package de.netzguru0815.cropprotect.commands;


import de.netzguru0815.cropprotect.CropProtect;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandVersion implements CommandExecutor {

    private CropProtect plugin;

    public void commandreload(CropProtect plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1 && args[0].equalsIgnoreCase("version")) {
            String version = this.plugin.getDescription().getVersion();
            sender.sendMessage(ChatColor.GREEN + "CropProtect Version: " + version);
            return true;
        } else {
            sender.sendMessage(ChatColor.RED + "Usage: /cropprotect version");
            return false;
        }
    }
}
