package it.mineblock.realisticsurvival.tasks;

import it.mineblock.mbcore.spigot.Chat;
import it.mineblock.realisticsurvival.Main;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.inventivetalent.bossbar.BossBarAPI;

public class StaminaTask extends BukkitRunnable {

    public StaminaTask(){

    }

    @Override
    public void run() {
        for(Player player : Bukkit.getOnlinePlayers()){
            if(Main.playerStill.getValue(player.getUniqueId()).getX() == player.getLocation().getX() &&
                    Main.playerStill.getValue(player.getUniqueId()).getY() == player.getLocation().getY() &&
                    Main.playerStill.getValue(player.getUniqueId()).getZ() == player.getLocation().getZ()){
                if(Main.playerStamina.getValue(player.getUniqueId()) < 100){
                    Main.playerStamina.replaceValue(player.getUniqueId(), Main.playerStamina.getValue(player.getUniqueId()) + 1.5F);
                }
            } else {
                Main.playerStill.replaceValue(player.getUniqueId(), player.getLocation());
                if(player.isSprinting()){
                    Block a = player.getLocation().subtract(0, 1, 0).getBlock();
                    Block b = player.getLocation().subtract(0, 2, 0).getBlock();

                    if (a.getType().equals(Material.AIR) && !b.getType().equals(Material.AIR)) {
                        Main.playerStamina.replaceValue(player.getUniqueId(), Main.playerStamina.getValue(player.getUniqueId()) - 3F);
                    } else {
                        Main.playerStamina.replaceValue(player.getUniqueId(), Main.playerStamina.getValue(player.getUniqueId()) - 1.5F);
                    }
                }
            }
            BossBarTask.spawnBossBar(player, 1,
                    "Stamina: " + Main.playerStamina.getValue(player.getUniqueId()), BossBarAPI.Color.GREEN);
        }
    }
}
