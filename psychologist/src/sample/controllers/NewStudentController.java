package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.database.DBConnection;
import sample.database.DBGroup;
import sample.database.DBStudent;

import java.net.URL;
import java.sql.Date;
import java.util.Map;
import java.util.ResourceBundle;

public class NewStudentController implements Initializable {
    @FXML ChoiceBox<String> CBGroup;
    @FXML TextField TName;
    @FXML DatePicker DPDate;
    @FXML CheckBox CBSpecial, CBChummery;
    @FXML Button BSave;

    GroupsAndStudentsController gsc;
    Map<String, Integer> groups;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        groups = DBGroup.getMapGroups(DBConnection.getConnection());
        if (groups != null) {
            CBGroup.getItems().setAll(groups.keySet());
        }

        BSave.setOnAction(e -> {
            if (createStudent()) {
                Stage stage = (Stage) BSave.getScene().getWindow();
                stage.close();
            }
        });
    }

    private boolean createStudent() {
        return DBStudent.createStudent(DBConnection.getConnection(), TName.getText(), groups.get(CBGroup.getValue()), Date.valueOf(DPDate.getValue()), CBSpecial.isSelected(), CBChummery.isSelected());
    }
}
