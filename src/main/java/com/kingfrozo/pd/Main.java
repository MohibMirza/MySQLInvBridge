package com.kingfrozo.pd;

import com.kingfrozo.pd.events.JoinLeaveSync;
import com.kingfrozo.pd.libs.LP;
import com.kingfrozo.pd.player.GlobalPlayer;
import com.kingfrozo.pd.player.Title;
import com.kingfrozo.pd.sql.MySQL;
import com.kingfrozo.pd.sql.SQLGetter;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class Main extends JavaPlugin implements Listener {

    public static Main plugin;

    public static LuckPerms lp_api;

    public MySQL SQL;
    public SQLGetter data;
    public Map<UUID, GlobalPlayer> players;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;

        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) {
            lp_api  = provider.getProvider();
        }

        this.SQL = new MySQL();
        this.data = new SQLGetter(this);

        saveDefaultConfig();

        try {
            this.SQL.connect();
            data.createTable();
            this.getServer().getPluginManager().registerEvents(this, this);
            this.getServer().getPluginManager().registerEvents(new JoinLeaveSync(), this);
        } catch (ClassNotFoundException | SQLException e) {
            // Login info is incorrect
            // they are not using a database
            Bukkit.getLogger().info("Database not connected!");
        }


        if(SQL.isConnected()) {
            Bukkit.getLogger().info("Database is connected!");
        }

        Title.readTitles();

        System.out.println("TitleCount: " + Title.titles.size());

        players = new HashMap<java.util.UUID, GlobalPlayer>();

        System.out.println("YEET: " + LP.stripPrefix("§4hello§1yeet§a") );
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        SQL.disconnect();
    }
/*
    @EventHandler
    public void testerEvent(PlayerInteractEvent event){
        JoinLeaveSync.syncInventory(event.getPlayer());

    }*/

    public static Main getPlugin() {
        return plugin;
    }


}