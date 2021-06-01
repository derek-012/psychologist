package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.View;
import sample.database.DBConnection;
import sample.database.DBReception;
import sample.database.DBStudent;
import sample.models.Reception;

import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.Map;
import java.util.ResourceBundle;

public class ReceptionDetailsController implements Initializable {
    Reception reception;
    //DBController dbController;
    Map<String, Map<String, Integer>> studentList;
    Stage stage;

    @FXML ChoiceBox<String> CBGroup, CBStudent;
    @FXML Button BGroup, BStudent, BSave;
    @FXML DatePicker DPDate;
    @FXML Spinner<String> STime;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        STime.setValueFactory(new SpinnerValueFactory<String>() {
            int hour, minutes;
            final int step = 15, minHour = 9, maxHour = 16;

            private void setTime() {
                setValue(String.format("%02d:%02d:00", hour, minutes));
            }

            public void setData(String time) {
                if (!time.isEmpty()) {
                    String[] data = time.split(":");
                    hour = Integer.parseInt(data[0]);
                    minutes = Integer.parseInt(data[1]);
                }
            }

            void initTime() {
                hour = 9;
                minutes = 0;
                setTime();
            }

            @Override
            public void decrement(int i) {
                if (hour == 0 && minutes == 0) {
                    if (getValue() == null) {
                        initTime();
                    } else if (!getValue().isEmpty()) {
                        setData(getValue());
                    }
                } else {
                    if (minutes == 0) {
                        if (hour > minHour) {
                            hour--;
                            minutes = 60 - step;
                        }
                    } else {
                        minutes -= step;
                    }
                    setTime();
                }
            }

            @Override
            public void increment(int i) {
                if (hour == 0 && minutes == 0) {
                    if (getValue() == null) {
                        initTime();
                    } else if (!getValue().isEmpty()){
                        setData(getValue());
                    }
                } else {
                    if (minutes == 60 - step) {
                        if (hour < maxHour) {
                            hour++;
                            minutes = 0;
                        }
                    } else {
                        minutes += step;
                    }
                    setTime();
                }
            }
        });

        STime.getValueFactory().setValue("09:00:00");

        CBGroup.setOnAction(e -> {
            if (reception == null)
                selectGroup();
        });

        BSave.setOnAction(e -> {
            if ((reception == null ? createReception() : saveReception())) {
                View.notifyEditReception();
                stage.close();
            } else {
                System.out.println("Don't saved!");
            }
        });

        initForm();
    }

    public void setReception(Reception data) {
        reception = data;
        CBGroup.getItems().setAll(reception.getGroup());
        CBGroup.getSelectionModel().select(0);
        CBGroup.setDisable(true);
        CBStudent.getItems().setAll(reception.getName());
        CBStudent.getSelectionModel().select(0);
        CBStudent.setDisable(true);
        DPDate.setValue(reception.getDate().toLocalDate());
        STime.getValueFactory().setValue(reception.getTime().toString());
    }

    public void initForm() {
        studentList = DBStudent.getStudentMap(DBConnection.getConnection());
        if (studentList != null) {
            CBGroup.getItems().setAll(studentList.keySet());
        }
    }

    private void selectGroup() {
        CBStudent.setDisable(false);
        CBStudent.getItems().setAll(studentList.get(CBGroup.getValue()).keySet());
    }

    private boolean checkForm() {
        return (!CBGroup.getSelectionModel().isEmpty() && !CBStudent.getSelectionModel().isEmpty() && DPDate.getValue() != null && STime.getValue() != null);
    }

    private Date getDate() {
        return Date.valueOf(DPDate.getValue());
    }

    private Time getTime() {
        return Time.valueOf(STime.getValueFactory().getValue());
    }

    private boolean createReception() {
        if (reception == null) {
            if (checkForm()) {
                return DBReception.createReception(DBConnection.getConnection(), studentList.get(CBGroup.getValue()).get(CBStudent.getValue()), getDate(), getTime());
            } else
                return false;
        }
        return false;
    }

    private boolean saveReception() {
        if (reception != null && checkForm()) {
            return DBReception.editReception(DBConnection.getConnection(), reception.getId(), getDate(), getTime());
        } else {
            return false;
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
