package sample.database;

import sample.models.Diary;
import sample.models.Group;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBDiary {
    public static List<Diary> getAllDiary(Connection connection) {
        try {
            String query = "SELECT * FROM `get_diary`";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            List<Diary> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Diary(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5)));
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static List<Diary> getDiaryByStudent(Connection connection, int idStudent) {
        try {
            String query = "SELECT * FROM `get_diary` WHERE `student`.`ID` = " + idStudent;
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            List<Diary> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Diary(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5)));
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static boolean createDiary(Connection connection, int receptionId, String notate) {
        try {
            String query = "INSERT INTO `diary` (`student`, `date`, `result`) SELECT `student`, `date`, ? FROM `group` WHERE `ID` = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, notate);
            ps.setInt(2, receptionId);
            return !ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteDiary(Connection connection, int idDiary) {
        try {
            String query = "DELETE FROM `diary` WHERE `ID` = " + idDiary;
            Statement st = connection.createStatement();
            return !st.execute(query);
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
}
