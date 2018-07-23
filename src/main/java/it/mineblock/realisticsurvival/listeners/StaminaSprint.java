package it.mineblock.realisticsurvival.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class StaminaSprint implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event){
        Player player = event.getPlayer();

        /*PacketContainer packetContainer = new PacketContainer(PacketType.Play.Client.BLOCK_DIG);

        packetContainer.getBlockPositionModifier().write(0, new BlockPosition(player.getLocation().getBlock().getLocation().toVector()));
        packetContainer.getEnumModifier(EnumDirection.class, 1);
        packetContainer.getEnumModifier(PacketPlayInBlockDig.EnumPlayerDigType.class, 2);*/
        /*if(event.isSprinting()) {
            if (Main.playerStamina.getValue(player.getUniqueId()) <= 0) {
                if(!Main.playerFoodLevel.containsKey(player.getUniqueId())){
                    Main.playerFoodLevel.put(player.getUniqueId(), player.getFoodLevel());
                }
                player.setFoodLevel(6);
            }
        } else {
            if(Main.playerFoodLevel.containsKey(player.getUniqueId())){
                player.setFoodLevel(Main.playerFoodLevel.getValue(player.getUniqueId()));
                Main.playerFoodLevel.remove(player.getUniqueId());
            }
        }*/
    }
}
