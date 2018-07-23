package it.mineblock.realisticsurvival.tasks;

import it.mineblock.realisticsurvival.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TemperatureTask extends BukkitRunnable {

    public TemperatureTask(){

    }

    @Override
    public void run() {
        for(Player player : Bukkit.getOnlinePlayers()){
            if(Main.coldBiomes.contains(player.getLocation().getBlock().getBiome())){
                Main.playerTemperature.replaceValue(player.getUniqueId(), Main.playerStamina.getValue(player.getUniqueId()) - 1);
            }
        }
    }
}
