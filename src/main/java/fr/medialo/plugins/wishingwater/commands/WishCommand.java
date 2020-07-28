package fr.medialo.plugins.wishingwater.commands;

import fr.medialo.plugins.wishingwater.Wishingwater;
import fr.medialo.plugins.wishingwater.core.WishRegion;
import fr.medialo.plugins.wishingwater.exception.LocationNotSameWorldException;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

public class WishCommand implements CommandExecutor, TabCompleter {

    private Wishingwater plugin;

    public WishCommand(Wishingwater plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player;
        if (sender instanceof Player){
            player = (Player) sender;


            if (args.length != 0){
                switch (args[0]){
                    case "region":
                        if(args.length >= 2 ){
                            if(args[1].equalsIgnoreCase("create")){

                                try {
                                    plugin.getWishRegionList().add(new WishRegion(plugin.loc1,plugin.loc2,args[2]));
                                } catch (LocationNotSameWorldException e) {
                                    e.printStackTrace();
                                }

                            }
                            if(args[1].equalsIgnoreCase("pos1")){
                                plugin.loc1 = player.getLocation();

                            }
                            if(args[1].equalsIgnoreCase("pos2")){
                                plugin.loc2 = player.getLocation();


                            }
                        }






                        break;
                }
            }
        } else {
            sender.sendMessage("non");
        }




        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return null;
    }
}
