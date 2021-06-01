package sample.database;

import sample.models.Group;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBGroup {
//    Connection connection;
//
//    public DBGroup(Connection connection) {
//        this.connection = connection;
//    }

    public static List<Group> getListGroups(Connection connection) {
        try {
            String query = "SELECT * FROM `group`";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            List<Group> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Group(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4)));
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static Map<String, Integer> getMapGroups(Connection connection) {
        try {
            String query = "SELECT `name`, `ID` FROM `group`";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            Map<String, Integer> groups = new HashMap<>();
            while (rs.next()) {
                groups.put(rs.getString(1), rs.getInt(2));
            }
            return groups;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean addGroup(Connection connection, String groupName, int year, String curator) {
        try {
            String query = "INSERT INTO `group` (`name`, `year`, `curator`) VALUES (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, groupName);
            ps.setInt(2, year);
            ps.setString(3, curator);
            return !ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteGroup(Connection connection, int idGroup) {
        try {
            String query = "DELETE FROM `group` WHERE `ID` = " + idGroup;
            Statement st = connection.createStatement();
            return !st.execute(query);
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
}
