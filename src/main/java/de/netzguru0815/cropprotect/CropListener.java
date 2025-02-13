package de.netzguru0815.cropprotect;


import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class CropListener implements Listener {


    @EventHandler
    public void onBlockBreak(final BlockBreakEvent event) {
        final Player player = event.getPlayer();
        final Block block = event.getBlock();

        // Nur weiter verarbeiten, falls der Block Daten hat, die alterbar sind (z.B. Crop)
        if (!(block.getBlockData() instanceof final Ageable ageable)) return;

        final int currentAge = ageable.getAge();
        final int maxAge = ageable.getMaximumAge();

        // Bei unreifen Pflanzen: Falls der Spieler nicht sneak gedrückt hält, Abbau abbrechen
        if (currentAge < maxAge && !player.isSneaking()) {
            event.setCancelled(true);
            player.sendMessage("§cDu musst sneak gedrückt halten, um unreife Pflanzen abzubauen!");
            return;
        }

        // Für reife Pflanzen: Automatisches Wiederanpflanzen
        if (currentAge >= maxAge) {
            // Für non-OPs (und alle anderen) den Abbau durch das Plugin regeln
            // Abbruch des normalen Abbauverhaltens


            // Setze den Block auf den neu angepflanzten Zustand (Alter 0)
            final Ageable newAgeable = ageable;
            newAgeable.setAge(0);
            block.setType(block.getType());
            block.setBlockData(newAgeable);

            // Optional: Manuelle Treffer oder Drops, falls gewünscht
            // Zum Beispiel könnte man hier dem Spieler ein Crop-Item geben.
            player.sendMessage("§aPflanze abgebaut und wieder angepflanzt!");
        }
    }
}
