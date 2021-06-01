package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.controllers.*;
import sample.models.Reception;

import java.io.IOException;

public class View {
    private static MainReceptionController mrc;
    private static Stage mainStage;

    public static void showMainForm(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("designs/MainReception.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("АИС \"Психолог\"");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        mrc = loader.getController();
        mainStage = primaryStage;
        primaryStage.show();
    }

    public static void showReceptionForm(Reception reception) throws IOException {
        Stage stageReception = new Stage();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("designs/ReceptionDetails.fxml"));
        Parent parentReception = loader.load();
        stageReception.setTitle("Запись на прием");
        stageReception.setScene(new Scene(parentReception));
        ReceptionDetailsController rdc = loader.getController();
        if (reception != null) {
            rdc.setReception(reception);
        }
        //rdc.setDBController(DBController.getDBController());
        rdc.setStage(stageReception);
        stageReception.setResizable(false);
        stageReception.showAndWait();
    }

    public static void showGroupsAndStudents() throws IOException {
        Stage stageGroupsAndStudents = new Stage();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("designs/GroupsAndStudents.fxml"));
        Parent parentGroupsAndStudents = loader.load();
        stageGroupsAndStudents.setTitle("Группы и студенты");
        stageGroupsAndStudents.setScene(new Scene(parentGroupsAndStudents));
        stageGroupsAndStudents.setResizable(false);
        stageGroupsAndStudents.showAndWait();
    }

    public static void showAddGroupForm(GroupsAndStudentsController controller) throws IOException {
        Stage stageAddGroupForm = new Stage();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("designs/NewGroup.fxml"));
        Parent parentAddGroupForm = loader.load();
        stageAddGroupForm.setTitle("Новая группа");
        stageAddGroupForm.setScene(new Scene(parentAddGroupForm));
        stageAddGroupForm.setResizable(false);
        NewGroupController ngc = loader.getController();
        ngc.setParentController(controller);
        stageAddGroupForm.showAndWait();
    }

    public static void notifyEditReception() {
        mrc.notifyEditReception();
    }

    public static void showAddStudentForm(GroupsAndStudentsController controller) {
        try {
            Stage stageAddStudentForm = new Stage();
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("designs/NewStudent.fxml"));
            Parent parentAddGroupForm = loader.load();
            stageAddStudentForm.setTitle("Добавление студента");
            stageAddStudentForm.setScene(new Scene(parentAddGroupForm));
            stageAddStudentForm.setResizable(false);
            stageAddStudentForm.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showDiary() {
        try {
            Stage stageDiary = new Stage();
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("designs/Diary.fxml"));
            Parent parentDiary = loader.load();
            stageDiary.setTitle("Дневник сеансов");
            stageDiary.setScene(new Scene(parentDiary));
            stageDiary.setResizable(false);
            stageDiary.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showCompleteDiary(int receptionId) {
        try {
            Stage stageCompleteDiary = new Stage();
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("designs/Diary.fxml"));
            Parent parentCompleteDiary = loader.load();
            stageCompleteDiary.setTitle("Дневник сеансов");
            stageCompleteDiary.setScene(new Scene(parentCompleteDiary));
            stageCompleteDiary.setResizable(false);
            CompleteDiaryController controller = loader.getController();
            controller.setReception(receptionId);
            stageCompleteDiary.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
