package sample.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.View;
import sample.database.DBConnection;
import sample.database.DBReception;
import sample.models.Reception;
import sample.modules.MyAlert;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainReceptionController implements Initializable {
    @FXML TableView<Reception> TableReception;
    @FXML TableColumn<Reception, String> TVStudent, TVDate, TVTime;

    @FXML Button BGroupsAndStudents, BDiary;

    private ContextMenu createContextMenu() {
        ContextMenu cm = new ContextMenu();
        MenuItem itemComplete, itemCreate, itemModify, itemDelete;

        itemComplete = new MenuItem("Завершить сеанс");
        itemComplete.setOnAction(e -> {

        });

        itemCreate = new MenuItem("Новая запись");
        itemCreate.setOnAction(this::handleItemCreate);

        itemModify = new MenuItem("Изменить");
        itemModify.setOnAction(e -> showReception(TableReception.getSelectionModel().getSelectedItem()));

        itemDelete = new MenuItem("Удалить");
        itemDelete.setOnAction(e -> {
            if (TableReception.getSelectionModel().getSelectedItem() != null)
                DBReception.deleteDeception(DBConnection.getConnection(), TableReception.getSelectionModel().getSelectedItem().getId());
        });

        cm.getItems().setAll(itemCreate, itemModify, itemDelete);

        return cm;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TableReception.setContextMenu(createContextMenu());

        TVStudent.setCellValueFactory(new PropertyValueFactory<>("name"));
        TVDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        TVTime.setCellValueFactory(new PropertyValueFactory<>("time"));

        if (DBConnection.connect()) {
            refreshReception();
        } else {
            MyAlert.showAlert(Alert.AlertType.ERROR, "Ошибка!", null, "Ошибка при подключении к базе данных!");
            Platform.exit();
        }

        BGroupsAndStudents.setOnAction(this::showGroupsAndStudents);
        BDiary.setOnAction(e -> {
            View.showDiary();
        });
    }

    private void refreshReception() {
        if (DBConnection.isActive()) {
            TableReception.getItems().setAll(DBReception.getList(DBConnection.getConnection()));
        }
    }

    private void showReception(Reception reception) {
        try {
            View.showReceptionForm(reception);
            //refreshReception();
        } catch (Exception e) {
            System.out.println(e);
            MyAlert.showAlert(Alert.AlertType.ERROR, "Ошибка", null, e.getMessage());
        }
    }

    private void showGroupsAndStudents(ActionEvent actionEvent) {
        try {
            View.showGroupsAndStudents();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleItemCreate(ActionEvent e) {
        showReception(null);
    }

    public void notifyEditReception() {
        refreshReception();
    }
}
