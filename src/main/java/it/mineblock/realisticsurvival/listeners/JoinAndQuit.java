package it.mineblock.realisticsurvival.listeners;

import it.mineblock.realisticsurvival.Main;
import it.mineblock.realisticsurvival.Storage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class JoinAndQuit implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        UUID uuid = event.getPlayer().getUniqueId();
        if(!Main.playerStats.containsKey(uuid)) {
            Main.playerStats.put(uuid, new Storage<>());
            Main.playerStats.getValue(uuid).put(0F, 40.5F);
        }
        if(!Main.playerStamina.containsKey(uuid)){
            Main.playerStamina.put(uuid, 100F);
        }
        if(!Main.playerStill.containsKey(uuid)){
            Main.playerStill.put(uuid, event.getPlayer().getLocation());
        }
        if(!Main.playerTemperature.containsKey(uuid)){
            Main.playerTemperature.put(uuid, 36F);
        }
    }
}
