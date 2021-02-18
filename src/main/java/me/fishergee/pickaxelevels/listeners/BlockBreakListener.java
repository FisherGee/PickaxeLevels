package me.fishergee.pickaxelevels.listeners;

import de.tr7zw.nbtapi.NBTItem;
import me.fishergee.pickaxelevels.utils.LevelUtil;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.meta.ItemMeta;

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

                ItemMeta itemMeta = nbtItem.getItem().getItemMeta();

                int digLevel = itemMeta.getEnchantLevel(Enchantment.DIG_SPEED);

                itemMeta.addEnchant(Enchantment.DIG_SPEED, digLevel + 1, true);
                itemMeta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, digLevel + 1, true);

                nbtItem.getItem().setItemMeta(itemMeta);
            
                player.setInvulnerable(true);
                player.getWorld().createExplosion(player.getLocation(), 5);
                player.setInvulnerable(false);
                
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 10);

            }
            player.getInventory().setItem(LevelUtil.findCustomPickaxeIndex(player.getInventory()), nbtItem.getItem());
        }


    }
}
