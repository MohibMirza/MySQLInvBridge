package com.kingfrozo.pd.sql;

import com.kingfrozo.pd.Main;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class PlayerData {

    public static Main plugin = Main.getPlugin();

    Player player;
    String db_name, title, icon;
    int money;

    public PlayerData(Player player, String db_name, String title, String icon, int money) throws SQLException {
        this.player = player;
        this.db_name = db_name;
        this.title = title;
        this.icon = icon;
        this.money = money;

        if(player.getName() != this.db_name) {
            Main.plugin.data.setUsername(player, player.getName());
            System.out.println(player.getName() + " has changed their username! Updating the db!");
        }
    }

}
