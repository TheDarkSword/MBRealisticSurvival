package it.mineblock.realisticsurvival.listeners;

import it.mineblock.realisticsurvival.Main;
import it.mineblock.realisticsurvival.Storage;
import it.mineblock.realisticsurvival.tasks.BossBarTask;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffectType;
import org.inventivetalent.bossbar.BossBarAPI;

import java.util.UUID;

public class Fatigue implements Listener {

    @EventHandler
    public void onPlace(BlockPlaceEvent event){
        UUID uuid = event.getPlayer().getUniqueId();
        Storage<Float, Float> values = new Storage<>();
        values.put(Main.playerStats.getValue(uuid).getKey(0) + 0.25F, Main.playerStats.getValue(uuid).getValue(0));
        Main.playerStats.replaceValue(uuid, values);
        if(Main.playerStats.getValue(uuid).getKey(0) >= 25){
            if(event.getPlayer().hasPotionEffect(PotionEffectType.SLOW)) event.getPlayer().removePotionEffect(PotionEffectType.SLOW);
            event.getPlayer().addPotionEffect(PotionEffectType.SLOW.createEffect(800, 0));
        }
        BossBarTask.spawnBossBar(event.getPlayer(), 2,
                "Fatica: " + Main.playerStats.getValue(event.getPlayer().getUniqueId()).getKey(0), BossBarAPI.Color.RED);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event){
        UUID uuid = event.getPlayer().getUniqueId();
        Storage<Float, Float> values = new Storage<>();
        values.put(Main.playerStats.getValue(uuid).getKey(0) + 0.25F, Main.playerStats.getValue(uuid).getValue(0));
        Main.playerStats.replaceValue(uuid, values);
        if(Main.playerStats.getValue(uuid).getKey(0) >= 25){
            if(event.getPlayer().hasPotionEffect(PotionEffectType.SLOW_DIGGING)) event.getPlayer().removePotionEffect(PotionEffectType.SLOW_DIGGING);
            event.getPlayer().addPotionEffect(PotionEffectType.SLOW_DIGGING.createEffect(800, 2));
        }
        BossBarTask.spawnBossBar(event.getPlayer(), 2,
                "Fatica: " + Main.playerStats.getValue(event.getPlayer().getUniqueId()).getKey(0), BossBarAPI.Color.RED);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        if(event.getClickedBlock() == null) return;
        Material blockType = event.getClickedBlock().getType();
        if(Main.fatigueBlock.contains(blockType)){
            UUID uuid = event.getPlayer().getUniqueId();
            Storage<Float, Float> values = new Storage<>();
            values.put(Main.playerStats.getValue(uuid).getKey(0) + 0.5F, Main.playerStats.getValue(uuid).getValue(0));
            Main.playerStats.replaceValue(uuid, values);
            if(Main.playerStats.getValue(uuid).getKey(0) >= 25){
                if(event.getPlayer().hasPotionEffect(PotionEffectType.SLOW)) event.getPlayer().removePotionEffect(PotionEffectType.SLOW);
                event.getPlayer().addPotionEffect(PotionEffectType.SLOW.createEffect(800, 0));
            }
            BossBarTask.spawnBossBar(event.getPlayer(), 2,
                    "Fatica: " + Main.playerStats.getValue(event.getPlayer().getUniqueId()).getKey(0), BossBarAPI.Color.RED);
        }

    }
}
