package sample.database;

import sample.models.Reception;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBReception {
    public static List<Reception> getList(Connection connection) {
        Statement st;
        try {
            st = connection.createStatement();
            String query = "SELECT * FROM get_reception";
            ResultSet rs = st.executeQuery(query);

            List<Reception> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Reception(rs.getInt("ID"), rs.getString("group_name"), rs.getString("student_name"), rs.getDate("date"), rs.getTime("time")));
            }
            return list;
        } catch (SQLException e) {
            return null;
        }
    }

    public static boolean createReception(Connection connection, int studentId, Date date, Time time) {
        try {
            String query = "INSERT INTO `reception` (`student`, `date`, `time`) VALUES (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, studentId);
            ps.setDate(2, date);
            ps.setTime(3, time);
            return !ps.execute();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean editReception(Connection connection, int receptionId, Date date, Time time) {
        try {
            String query = "UPDATE `reception` SET `date` = ?, `time` = ? WHERE `ID` = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setDate(1, date);
            ps.setTime(2, time);
            ps.setInt(3, receptionId);
            return !ps.execute();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean deleteDeception(Connection connection, int idReception) {
        try {
            String query = "DELETE FROM `reception` WHERE `ID` = " + idReception;
            Statement st = connection.createStatement();
            return !st.execute(query);
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
}
