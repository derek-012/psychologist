package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import sample.database.DBConnection;
import sample.database.DBDiary;

import java.net.URL;
import java.util.ResourceBundle;

public class CompleteDiaryController implements Initializable {
    @FXML TextArea TNotate;
    @FXML Button BSave;

    int reception;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        BSave.setOnAction(e -> {
            if (saveDiary()) {
                Stage stage = (Stage) BSave.getScene().getWindow();
                stage.close();
            }
        });
    }

    public void setReception(int receptionId) {
        reception = receptionId;
    }

    private boolean saveDiary() {
        return DBDiary.createDiary(DBConnection.getConnection(), reception, TNotate.getText());
    }
}
