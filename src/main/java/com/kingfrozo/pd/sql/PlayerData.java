package com.kingfrozo.pd.sql;

import com.kingfrozo.pd.Main;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class PlayerData {

    public static Main plugin = Main.getPlugin();

    private Player player;
    private String db_name, title, icon;
    private int money;

    public PlayerData(Player player, String db_name, String title, String icon, int money) throws SQLException {
        this.player = player;
        this.db_name = db_name;
        this.title = title;
        this.icon = icon;
        this.money = money;

        if(player.getName() != this.db_name) {
            Main.plugin.data.setUsername(player, player.getName());
            db_name = player.getName();
            System.out.println(player.getName() + " has changed their username! Updating the db!");
        }
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getDb_name() {
        return db_name;
    }

    public void setDb_name(String db_name) {
        this.db_name = db_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String toString() {
        String output = "";
        output.concat("Player Name: " + db_name);
        output.concat("    title: " + title);
        output.concat("    icon: " + icon);
        output.concat("    money: " + money + "\n");

        return output;
    }

}
