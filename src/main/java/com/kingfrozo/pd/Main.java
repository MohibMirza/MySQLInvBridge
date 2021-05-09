package com.kingfrozo.pd;

import com.kingfrozo.pd.sql.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class Main extends JavaPlugin {

    public com.kingfrozo.pd.sql.MySQL SQL;

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.SQL = new MySQL();
        try {
            this.SQL.connect();
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
}