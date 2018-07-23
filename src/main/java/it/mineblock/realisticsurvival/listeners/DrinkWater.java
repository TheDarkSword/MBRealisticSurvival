package it.mineblock.realisticsurvival.listeners;

import it.mineblock.realisticsurvival.Main;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import java.util.UUID;

public class DrinkWater implements Listener {

    @EventHandler
    public void onDrink(PlayerItemConsumeEvent event){
        if(!event.getItem().getType().equals(Material.POTION)) return;
        UUID uuid = event.getPlayer().getUniqueId();
        if(Main.playerStats.getValue(uuid).getValue(0) >= 43.5){
            event.setCancelled(true);
        } else {
            Main.playerStats.getValue(uuid).replaceValue(
                    Main.playerStats.getValue(uuid).getKey(0), Main.playerStats.getValue(uuid).getValue(0) + 0.5F);
        }

    }
}
