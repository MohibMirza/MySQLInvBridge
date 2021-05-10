package com.kingfrozo.pd;

import com.kingfrozo.pd.events.JoinLeaveSync;
import com.kingfrozo.pd.player.GlobalPlayer;
import com.kingfrozo.pd.sql.MySQL;
import com.kingfrozo.pd.sql.SQLGetter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class Main extends JavaPlugin implements Listener {

    public static Main plugin;

    public MySQL SQL;
    public SQLGetter data;

    public Map<UUID, GlobalPlayer> players;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;

        this.SQL = new MySQL();
        this.data = new SQLGetter(this);

        try {
            this.SQL.connect();
            data.createTable();
            this.getServer().getPluginManager().registerEvents(new JoinLeaveSync(), this);
        } catch (ClassNotFoundException | SQLException e) {
            // Login info is incorrect
            // they are not using a database
            Bukkit.getLogger().info("Database not connected!");
        }


        if(SQL.isConnected()) {
            Bukkit.getLogger().info("Database is connected!");
        }

        players = new HashMap<java.util.UUID, GlobalPlayer>();

        System.out.println("a");
        System.out.println("a");
        System.out.println("a");
        System.out.println("a");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        SQL.disconnect();
    }

    @EventHandler
    public void testerEvent(PlayerInteractEvent event){
        Player player = event.getPlayer();
        // PlayerData playerData = data.getPlayer(player);


    }

    public static Main getPlugin() {
        return plugin;
    }


}