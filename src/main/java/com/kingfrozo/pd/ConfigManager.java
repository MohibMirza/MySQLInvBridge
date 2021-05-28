package com.kingfrozo.pd;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManager {

    private Main plugin = Main.getPlugin();

    public FileConfiguration config;
    public File file;

    public void setup() {
        if(!plugin.getDataFolder().exists()){
            plugin.getDataFolder().mkdir();
        }

        file = new File(plugin.getDataFolder(),
                "src/main/titles.yml");
        if(!file.exists()) {
            try {
                file.createNewFile();
            }catch(IOException ioe) {
                System.out.println(ChatColor.RED +
                        "Could not find yml file!");
            }

            config = YamlConfiguration.loadConfiguration(file);
            System.out.println(ChatColor.GREEN + "The yml file has been created!");

        }
    }
}
