package fr.medialo.plugins.wishingwater;

import de.leonhard.storage.Yaml;
import de.leonhard.storage.internal.FlatFile;
import de.leonhard.storage.internal.settings.ConfigSettings;
import fr.medialo.plugins.wishingwater.commands.WishCommand;
import fr.medialo.plugins.wishingwater.core.WishRegion;
import fr.medialo.plugins.wishingwater.data.PbmYaml;
import fr.medialo.plugins.wishingwater.listeners.DropEvent;
import org.bukkit.Location;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public final class Wishingwater extends JavaPlugin {

    private List<WishRegion> wishRegionList;
    public Location loc1;
    public Location loc2;
    static Logger logggerst = Logger.getLogger("Minecraft");

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.getConfig().getInt("");

        this.saveConfig();
        this.reloadConfig();

        File filetest = new File(this.getDataFolder(),"test.yml");
        if (!filetest.exists()) {
            filetest.getParentFile().mkdirs();
            saveResource("test.yml", false);
        }


        PbmYaml fileConfiguration = new PbmYaml();
        fileConfiguration.load(filetest);
        fileConfiguration.set("aside",false);
        fileConfiguration.save();

        System.out.println(new File(this.getDataFolder(),"data/azerty").getParent());
        System.out.println(new File(this.getDataFolder(),"test.yml").getAbsolutePath());
        System.out.println(new File(this.getDataFolder(),"tetst/test.yml").getName());


//        Yaml yamltest = new Yaml(filetest);
//
//        yamltest.setConfigSettings(ConfigSettings.PRESERVE_COMMENTS);
//
//        yamltest.write();
//        Map<String,List<String>> maptest = yamltest.getParser().assignCommentsToKey();
//        for (String stttr : maptest.keySet()) {
//            Wishingwater.log(" Map -> key : " + stttr);
//        }




        this.getCommand("wishingwater").setExecutor(new WishCommand(this));
        this.getServer().getPluginManager().registerEvents(new DropEvent(this),this);
        wishRegionList = new ArrayList<>();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public List<WishRegion> getWishRegionList() {
        return wishRegionList;
    }

    public static void log(String str){
        logggerst.info(str);
    }
}
