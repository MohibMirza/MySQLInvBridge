package com.kingfrozo.pd.sql;

import com.kingfrozo.pd.Main;
import org.bukkit.entity.Player;

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

    public void createPlayer(Player player) {
        try {
            UUID uuid = player.getUniqueId();
            if(!exists(uuid)){
                PreparedStatement ps2 = plugin.SQL.getConnection().prepareStatement("INSERT INTO playerdata" +
                        " (NAME,UUID) VALUES (?,?)");
                System.out.println(ps2.toString());
                ps2.setString(1, player.getName());
                ps2.setString(2, uuid.toString());
                ps2.executeUpdate();

                return;
            }
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

    public void addPoints(UUID uuid, int points) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE playerdata SET" +
                    " POINTS=? WHERE UUID=?");
            ps.setInt(1, getPoints(uuid) + 1);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        }catch(Exception e) {
            e.printStackTrace();
            System.out.println(4);
        }
    }

    public int getPoints(UUID uuid) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT POINTS FROM playerdata" +
                    " WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            int points = 0;
            if(rs.next()){
                    points = rs.getInt("POINTS");
                    return points;
            }
        }catch(SQLException e) {
            e.printStackTrace();
            System.out.println(5);
        }
        return 0;
    }


}
