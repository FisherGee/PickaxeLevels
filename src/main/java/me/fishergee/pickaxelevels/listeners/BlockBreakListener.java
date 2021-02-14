package me.fishergee.pickaxelevels.listeners;

import de.tr7zw.nbtapi.NBTItem;
import me.fishergee.pickaxelevels.utils.LevelUtil;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {

    @EventHandler
    public void onBreakBreakEvent(BlockBreakEvent event){
        NBTItem nbtItem = new NBTItem(event.getPlayer().getItemInHand());
        Player player = event.getPlayer();
        if(nbtItem.getString("id").equalsIgnoreCase("pickaxelevels")){
            int blocksBroken = nbtItem.getInteger("broken");
            blocksBroken++;
            nbtItem.setInteger("broken", blocksBroken);
            LevelUtil.updateLore(nbtItem.getItem(), blocksBroken);

            if(LevelUtil.levelSuffice(nbtItem.getItem()) && LevelUtil.levelMax(nbtItem.getItem()) == false){
                int level = nbtItem.getInteger("level");
                level++;
                nbtItem.setInteger("level", level);

                player.spawnParticle(Particle.FIREWORKS_SPARK, player.getLocation(), 3);
                player.sendMessage(ChatColor.BLUE + "You have leveled up to level " + level + "!");
            }
        }
    }
}
