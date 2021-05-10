package com.kingfrozo.pd.sql;

import com.kingfrozo.pd.Main;
import org.bukkit.entity.Player;

import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class SQLGetter { // !!! CLOSE ALL PS & RS & ULTIMATELY THE CONNECTION !!!

    private Main plugin;

    public SQLGetter(Main plugin) {
        this.plugin = plugin;
        plugin.SQL.getConnection();
    }

    public void createTable() {
        PreparedStatement ps;
        try {
            ps = plugin.SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS playerdata"
                + "(NAME VARCHAR(16) NOT NULL,UUID VARCHAR(36) NOT NULL,MONEY INT(15) DEFAULT 0 NOT NULL," +
                    "TITLE VARCHAR(25) DEFAULT 'player' NOT NULL,ICON VARCHAR(25) DEFAULT 'player' NOT NULL,PRIMARY KEY (UUID))");
            ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println(1);
        }

    }

    public PlayerData getPlayer(Player player) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM playerdata" +
                    " WHERE UUID=?");
            ps.setString(1, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String db_name = rs.getString("NAME");
                int money = rs.getInt("MONEY");
                String title = rs.getString("TITLE");
                String icon = rs.getString("ICON");
                return (new PlayerData(player, db_name, title, icon, money));
            }else{
                createPlayer(player);
                player.sendMessage("new player being created!");
                return new PlayerData(player, player.getName(), "player", "player", 0); // NOTICE: HARD-CODED VALUES MAY BE CHANGED IN FUTURE

            }
        }catch(SQLException e) {
            e.printStackTrace();
            System.out.println("DB ERROR 123");
        }
        return null;

    }

    public void createPlayer(Player player) {
        try {
            UUID uuid = player.getUniqueId();
            PreparedStatement ps2 = plugin.SQL.getConnection().prepareStatement("INSERT INTO playerdata" +
                    " (NAME,UUID) VALUES (?,?)");
            ps2.setString(1, player.getName());
            ps2.setString(2, uuid.toString());
            ps2.executeUpdate();

            return;
        }catch(SQLException e) {
            e.printStackTrace();
            System.out.println(2);
        }
    }

    public boolean exists(UUID uuid) {
        try{
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM playerdata" +
                    " WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet results = ps.executeQuery();
            return (results.next()) ? true : false; // player is found
        }catch(SQLException e) {
            e.printStackTrace();
            System.out.println(3);
        }
        return false;
    }

    public void addMoney(UUID uuid, int points) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE playerdata SET" +
                    " MONEY=? WHERE UUID=?");
            ps.setInt(1, getMoney(uuid) + 1);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        }catch(Exception e) {
            e.printStackTrace();
            System.out.println(4);
        }
    }

    public int getMoney(UUID uuid) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT MONEY FROM playerdata" +
                    " WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            int points = 0;
            if(rs.next()){
                    points = rs.getInt("MONEY");
                    return points;
            }
        }catch(SQLException e) {
            e.printStackTrace();
            System.out.println(5);
        }
        return 0;
    }

    public void setTitle(Player player, String title) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE playerdata SET" +
                    " TITLE=? WHERE UUID=?");
            ps.setString(1, title);
            ps.setString(2, player.getUniqueId().toString());

            ps.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public String getTitle(Player player) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT TITLE FROM playerdata" +
                    " WHERE UUID=?");
            ps.setString(1, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            String title = "";
            if (rs.next()) {
                title = rs.getString("TITLE");
                return title;
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }

        return "error";

    }

    public void setIcon(Player player, String icon) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE playerdata SET" +
                    " ICON=? WHERE UUID=?");
            ps.setString(1, icon);
            ps.setString(2, player.getUniqueId().toString());

            ps.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public String getIcon(Player player) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT ICON FROM playerdata" +
                    " WHERE UUID=?");
            ps.setString(1, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            String icon = "";
            if (rs.next()) {
                icon = rs.getString("ICON");
                return icon;
            } else {
                System.out.println("Finding icon for unknown player: " + player.getName());
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }

        return "error";

    }

    public void setUsername(Player player, String username) { // checked
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE playerdata SET" +
                    "  NAME=? WHERE UUID=?");

            ps.setString(1, username);
            ps.setString(2, player.getUniqueId().toString());
            ps.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public String getUsername(Player player) {

        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT NAME FROM playerdata" +
                    " WHERE UUID=?");

            ps.setString(1, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("NAME");
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return "error";
    }



}
