package it.mineblock.realisticsurvival.tasks;

import it.mineblock.realisticsurvival.Main;
import it.mineblock.realisticsurvival.Storage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class WaterTask extends BukkitRunnable {

    public WaterTask(){

    }

    @Override
    public void run() {
        for(Player player : Bukkit.getOnlinePlayers()){
            //if(Main.playerTemperature.getValue(player.getUniqueId()) >= )
            if(Main.getNearbyMaterials(player.getLocation(), 5).contains(Material.LAVA) ||
                    player.getWorld().getEnvironment().equals(World.Environment.NETHER) ||
                    player.getLocation().getBlock().getBiome().equals(Biome.DESERT) ||
                    player.getLocation().getBlock().getBiome().equals(Biome.DESERT_HILLS)){
                Storage<Float, Float> values = new Storage<>();
                values.put(Main.playerStats.getValue(player.getUniqueId()).getKey(0),
                        Main.playerStats.getValue(player.getUniqueId()).getValue(0) - 0.1F);
                Main.playerStats.replaceValue(player.getUniqueId(), values);
            } else {
                Storage<Float, Float> values = new Storage<>();
                values.put(Main.playerStats.getValue(player.getUniqueId()).getKey(0),
                        Main.playerStats.getValue(player.getUniqueId()).getValue(0) - 0.05F);
                Main.playerStats.replaceValue(player.getUniqueId(), values);
            }
            if(Main.playerStats.getValue(player.getUniqueId()).getValue(0) <= 38){
                
            }
        }
    }
}
