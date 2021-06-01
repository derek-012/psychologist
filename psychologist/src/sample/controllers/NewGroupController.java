package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.database.DBConnection;
import sample.database.DBGroup;

import java.net.URL;
import java.util.ResourceBundle;

public class NewGroupController implements Initializable{
    GroupsAndStudentsController gsc;

    @FXML TextField TFName, TFCurator;
    @FXML Spinner<Integer> SYear;
    @FXML Button BSave;

    public void setParentController(GroupsAndStudentsController controller) {
        gsc = controller;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        BSave.setOnAction(e -> {
            if (saveGroup()) {
                gsc.handleEditGroups();
                Stage stage = (Stage) BSave.getScene().getWindow();
                stage.close();
            }
        });
    }

    private boolean saveGroup() {
        return DBGroup.addGroup(DBConnection.getConnection(), TFName.getText(), SYear.getValue(), TFCurator.getText());
    }
}
