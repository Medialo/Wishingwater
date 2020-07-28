package fr.medialo.plugins.wishingwater.listeners;


import fr.medialo.plugins.wishingwater.Wishingwater;
import fr.medialo.plugins.wishingwater.core.WishRegion;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;


public class DropEvent implements Listener {

    public DropEvent(Wishingwater plugin) {
        this.plugin = plugin;
    }

    private Wishingwater plugin;


    @EventHandler
    public void event(PlayerDropItemEvent event){
        Item item = event.getItemDrop();
        Player player = event.getPlayer();
        ItemStack is = item.getItemStack();

//        BoundingBox boundingBox = item.getBoundingBox();




        BukkitRunnable bukkitRunnable = new BukkitRunnable() {
            @Override
            public void run() {
                Location loc = item.getLocation();
                if(loc.getBlock().getType() == Material.WATER){
                    player.sendMessage("Dans l'eau !!!");



                }
                player.sendMessage(loc.getBlock().toString());
            }
        };

        if(!plugin.getWishRegionList().isEmpty()){
            for (WishRegion rg : plugin.getWishRegionList()) {
                if(rg.getL1().getWorld().getUID() == player.getWorld().getUID()){
                    if (rg.playerIsInRegion(player)){

                        bukkitRunnable.runTaskLater(plugin,50L);

                    }
                }

                player.sendMessage(String.valueOf(rg.getL1().distance(player.getLocation())));
                player.sendMessage(String.valueOf(rg.getL1().distanceSquared(player.getLocation())));


            }
        }


    }


}
