package de.netzguru0815.cropprotect;


import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Set;

public class CropListener implements Listener {

    private final Set<Player> messageCooldown = new HashSet<>();


    @EventHandler
    public void onBlockBreak(final BlockBreakEvent event) {
        final Player player = event.getPlayer();
        final Block block = event.getBlock();

        // Liste der erlaubten Crop-Materialien
        final Material blockType = block.getType();
        if (!this.isAllowedCrop(blockType)) return;

        // Nur weiter verarbeiten, falls der Block Daten hat, die alter bar sind (z.B. Crop)
        if (!(block.getBlockData() instanceof final Ageable ageable)) return;

        final int currentAge = ageable.getAge();
        final int maxAge = ageable.getMaximumAge();

        // Bei unreifen Pflanzen: Falls der Spieler nicht sneak gedrückt hält, Abbau abbrechen
        if (currentAge < maxAge && !player.isSneaking()) {
            event.setCancelled(true);


            if (this.messageCooldown.add(player)) {
                player.sendMessage("§cDu musst SNEAK gedrückt halten, um unreife Pflanzen abzubauen!");
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        CropListener.this.messageCooldown.remove(player);
                    }
                }.runTaskLater(CropProtect.getPlugin(CropProtect.class), 100L); // 100 ticks = 5 seconds
            }
        }
    }

    // Überprüft, ob der gegebene Materialtyp ein erlaubter Crop ist.

    private boolean isAllowedCrop(final Material material) {
        return switch (material) {
            case WHEAT, CARROTS, POTATOES, BEETROOTS, NETHER_WART, COCOA, SWEET_BERRY_BUSH -> true;
            default -> false;
        };
    }
}
