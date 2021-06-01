package sample.controllers;

import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import sample.View;
import sample.database.DBConnection;
import sample.database.DBGroup;
import sample.database.DBStudent;
import sample.models.Group;
import sample.models.Student;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class GroupsAndStudentsController implements Initializable {
    //DBController dbController;

    @FXML TableView<Group> TableGroups;
    @FXML TableColumn<Group, String> TGName;
    @FXML TableColumn<Group, Integer> TGYear;
    @FXML TableColumn<Group, String> TGCurator;

    @FXML TableView<Student> TableStudents;
    @FXML TableColumn<Student, String> TSName;
    @FXML TableColumn<Student, Date> TSBirthday;
    @FXML TableColumn<Student, Boolean> TSSpecial, TSChummery;

    @FXML Button BAddGroup, BAddStudent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //dbController = DBController.getDBController();

        initForm();
        refreshGroups();
    }

    private void initForm() {
        TGName.setCellValueFactory(g -> new SimpleObjectProperty<>(g.getValue().getName()));
        TGYear.setCellValueFactory(g -> new SimpleObjectProperty<>(g.getValue().getYear()));
        TGCurator.setCellValueFactory(g -> new SimpleObjectProperty<>(g.getValue().getCurator()));

        TableGroups.getSelectionModel().selectedItemProperty().addListener((value, oldValue, newValue) -> {
            System.out.println("Selected!");
            if (newValue != null) {
                refreshStudents(newValue.getId());
            }
        });

        ContextMenu CMGroups =  new ContextMenu();
        MenuItem itemDelete = new MenuItem("Удалить");
        itemDelete.setOnAction(e -> {
            if (TableGroups.getSelectionModel().getSelectedItem() != null)
                DBGroup.deleteGroup(DBConnection.getConnection(), TableGroups.getSelectionModel().getSelectedItem().getId());
        });
        CMGroups.getItems().setAll(itemDelete);
        TableGroups.setContextMenu(CMGroups);

        TSName.setCellValueFactory(s -> new SimpleObjectProperty<>(s.getValue().getName()));
        TSBirthday.setCellValueFactory(s -> new SimpleObjectProperty<>(s.getValue().getDate()));
        TSSpecial.setCellValueFactory(s -> new SimpleObjectProperty<>(s.getValue().isSpecial()));
        TSSpecial.setCellFactory(s -> new CheckBoxTableCell<>());
        TSChummery.setCellValueFactory(s -> new SimpleObjectProperty<>(s.getValue().isChummery()));
        TSChummery.setCellFactory(s -> new CheckBoxTableCell<>());


        BAddGroup.setOnAction(this::showAddGroupForm);
        BAddStudent.setOnAction(this::showAddStudentForm);
    }

    public void refreshGroups() {
        TableGroups.getItems().setAll(DBGroup.getListGroups(DBConnection.getConnection()));
    }

    public void refreshStudents(int groupId) {
        TableStudents.getItems().setAll(DBStudent.getStudentsByGroup(DBConnection.getConnection(), groupId));
    }

    public void handleEditGroups() {
        refreshGroups();
    }

    private void showAddGroupForm(ActionEvent e) {
        try {
            View.showAddGroupForm(this);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private void showAddStudentForm(ActionEvent e) {
        View.showAddStudentForm(this);
    }
}
