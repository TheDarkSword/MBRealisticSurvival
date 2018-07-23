package it.mineblock.realisticsurvival.tasks;

import it.mineblock.mbcore.spigot.Chat;
import it.mineblock.realisticsurvival.Main;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.inventivetalent.bossbar.BossBar;
import org.inventivetalent.bossbar.BossBarAPI;

import java.text.DecimalFormat;

public class BossBarTask extends BukkitRunnable {

    private int index;
    private int timeout;
    private BossBar bossBar;
    private DecimalFormat decimalFormat = new DecimalFormat("#.##");


    public BossBarTask(int timeout){
        this.timeout = timeout;
        index = 0;
    }

    @Override
    public void run() {
        Chat.getLogger("BossBar sendend");
        index++;
        for(Player player : Bukkit.getOnlinePlayers()){
            switch (index){
                case 1:
                    bossBar = BossBarAPI.addBar(player, // The receiver of the BossBar
                            new TextComponent("Stamina: " + Main.playerStamina.getValue(player.getUniqueId())), // Displayed message
                            BossBarAPI.Color.GREEN, // Color of the bar
                            BossBarAPI.Style.NOTCHED_20, // Bar style
                            1.0f, // Progress (0.0 - 1.0)
                            timeout, // Timeout
                            2); // Timeout-interval
                    break;
                case 2:
                    bossBar = BossBarAPI.addBar(player, // The receiver of the BossBar
                            new TextComponent("Fatica: " + Main.playerStats.getValue(player.getUniqueId()).getKey(0)), // Displayed message
                            BossBarAPI.Color.RED, // Color of the bar
                            BossBarAPI.Style.NOTCHED_20, // Bar style
                            1.0f, // Progress (0.0 - 1.0)
                            timeout, // Timeout
                            2); // Timeout-interval
                    break;
                case 3:
                    bossBar = BossBarAPI.addBar(player, // The receiver of the BossBar
                            new TextComponent("Water: " + decimalFormat.format(Main.playerStats.getValue(player.getUniqueId()).getValue(0))), // Displayed message
                            BossBarAPI.Color.BLUE, // Color of the bar
                            BossBarAPI.Style.NOTCHED_20, // Bar style
                            1.0f, // Progress (0.0 - 1.0)
                            timeout, // Timeout
                            2); // Timeout-interval
                    break;
                /*case 3:
                    break;
                case 4:
                    break;*/
                default:
                    index = 0;
                    break;
            }
        }
    }

    public int getIndex() {
        return index;
    }

    public BossBar getBossBar() {
        return bossBar;
    }

    public static void spawnBossBar(Player player, int index, String textComponent, BossBarAPI.Color color){
        if(Main.bossBarTask.getIndex() == index && Main.bossBarTask.getBossBar() != null) {
            if (Main.bossBarTask.getBossBar().getProgress() >= 0.0F) {
                float newProgress = Main.bossBarTask.getBossBar().getProgress();
                Chat.getLogger(String.valueOf(newProgress));
                int newTimeout = (int) (newProgress * 100);
                BossBarAPI.removeAllBars(player);

                Main.bossBarTask.bossBar = BossBarAPI.addBar(player, // The receiver of the BossBar
                        new TextComponent(textComponent), // Displayed message
                        color, // Color of the bar
                        BossBarAPI.Style.NOTCHED_20, // Bar style
                        newProgress, // Progress (0.0 - 1.0)
                        newTimeout, // Timeout
                        2); // Timeout-interval
            }
        }
    }
}
