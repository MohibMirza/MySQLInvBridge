package com.kingfrozo.pd;

import com.kingfrozo.pd.sql.MySQL;
import com.kingfrozo.pd.sql.SQLGetter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class Main extends JavaPlugin implements Listener {

    public MySQL SQL;
    public SQLGetter data;

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.SQL = new MySQL();
        this.data = new SQLGetter(this);

        try {
            this.SQL.connect();
            data.createTable();
            this.getServer().getPluginManager().registerEvents(this, this);
        } catch (ClassNotFoundException | SQLException e) {
            // Login info is incorrect
            // they are not using a database
            Bukkit.getLogger().info("Database not connected!");
        }


        if(SQL.isConnected()) {
            Bukkit.getLogger().info("Database is connected!");
        }
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
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        data.createPlayer(player);

    }

    @EventHandler
    public void onMobKill(PlayerInteractEvent event){
        Player player = event.getPlayer();
        data.addMoney(player.getUniqueId(), 1);
        player.sendMessage("db updated!");

    }


}