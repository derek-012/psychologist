package sample.database;

import sample.models.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBStudent {

    public static Map<String, Map<String, Integer>> getStudentMap(Connection connection) {
        try {
            Statement st = connection.createStatement();
            String query = "SELECT * FROM get_students_group";
            ResultSet rs = st.executeQuery(query);
            Map<String, Map<String, Integer>> students = new HashMap<>();
            while (rs.next()) {
                if (!students.containsKey(rs.getString("group_name")))
                    students.put(rs.getString("group_name"), new HashMap<>());
                students.get(rs.getString("group_name")).put(rs.getString("student_name"), rs.getInt("student_id"));
            }
            return students;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public static List<Student> getStudentsByGroup(Connection connection, int groupId) {
        try {
            String query = "SELECT * FROM student WHERE `group` = " + groupId;
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            List<Student> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Student(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getInt(4), rs.getBoolean(5), rs.getBoolean(6)));
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public static boolean createStudent(Connection connection, String studentName, int groupId, Date date, boolean special, boolean chummery) {
        try {
            String query = "INSERT INTO `student` (`name`, `birthday`, `group`, `special`, `chummery`) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, studentName);
            ps.setDate(2, date);
            ps.setInt(3, groupId);
            ps.setBoolean(4, special);
            ps.setBoolean(5, chummery);
            return !ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
