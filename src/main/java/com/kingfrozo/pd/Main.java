package com.kingfrozo.pd;

import com.kingfrozo.pd.events.JoinLeaveSync;
import com.kingfrozo.pd.player.GlobalPlayer;
import com.kingfrozo.pd.player.Title;
import com.kingfrozo.pd.sql.MySQL;
import com.kingfrozo.pd.sql.SQLGetter;
import dev.lone.itemsadder.api.ItemsAdder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.mineacademy.chatcontrol.ChatControl;

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