package de.netzguru0815.cropprotect;

import org.bukkit.plugin.java.JavaPlugin;

public class CropProtect extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new CropListener(), this);
        this.getLogger().info("CropProtect loaded!");
    }

    @Override
    public void onDisable() {
        this.getLogger().info("CropProtect disabled!");
    }
}