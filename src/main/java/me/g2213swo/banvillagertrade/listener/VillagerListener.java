package me.g2213swo.banvillagertrade.listener;

import org.bukkit.Material;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.VillagerAcquireTradeEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;

import java.util.List;

public class VillagerListener implements Listener {
    @EventHandler
    public void onVillagerTrade(VillagerAcquireTradeEvent event) {
        ItemStack result = event.getRecipe().getResult();
        if (result.getType() == Material.ENCHANTED_BOOK) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerClickVillager(PlayerInteractEntityEvent event) {
        if (event.getRightClicked() instanceof Villager villager) {
            List<MerchantRecipe> trades = villager.getRecipes();

            List<MerchantRecipe> filteredTrades = trades.stream()
                    .filter(recipe -> recipe.getResult().getType() != Material.ENCHANTED_BOOK)
                    .toList();

            villager.setRecipes(filteredTrades);
        }
    }

}
