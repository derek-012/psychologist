package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.database.DBConnection;
import sample.database.DBDiary;
import sample.database.DBStudent;
import sample.models.Diary;

import java.net.URL;
import java.sql.Date;
import java.util.Map;
import java.util.ResourceBundle;

public class DiaryController implements Initializable {

    @FXML TableView<Diary> TableDiary;
    @FXML TableColumn<Diary, String> TDStudent, TDGroup;
    @FXML TableColumn<Diary, Date> TDDate;

    @FXML TextField TStudent, TGroup, TDate;
    @FXML TextArea TNotate;

    @FXML ChoiceBox<String> CBGroup, CBStudent;

    Map<String, Map<String, Integer>> students;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TDStudent.setCellValueFactory(new PropertyValueFactory<>("student"));
        TDGroup.setCellValueFactory(new PropertyValueFactory<>("group"));
        TDDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableDiary.getSelectionModel().selectedItemProperty().addListener((item, oldValue, newValue) -> {

        });

        students = DBStudent.getStudentMap(DBConnection.getConnection());

        refreshTable();
    }

    private void refreshTable() {
        if (CBGroup.getValue() != null && CBStudent.getValue() != null)
            TableDiary.getItems().setAll(DBDiary.getDiaryByStudent(DBConnection.getConnection(), students.get(CBGroup.getValue()).get(CBStudent.getValue())));
        else
            TableDiary.getItems().setAll(DBDiary.getAllDiary(DBConnection.getConnection()));
    }
}
