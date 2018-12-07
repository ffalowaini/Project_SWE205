package StudentSystem;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sun.awt.windows.ThemeReader;

public class StudentUI extends Application implements EventHandler {
    private Stage window;
    private Button logOut = new Button("Log Out");
    private Button BackButton = new Button("Back");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("Home");
        try {
            window.setScene(homeScene());
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        window.show();

    }


    private Scene homeScene() throws FileNotFoundException {
        Scanner readFile = new Scanner(new FileInputStream("Menu Buttons.txt"));
        BorderPane pane = new BorderPane();
        VBox box = new VBox();
        logOut.setOnAction(this);
        logOut.setPrefWidth(80);
        Button[] sideButton = new Button[4];
        for (int i = 0; i < sideButton.length; i++) {
            sideButton[i] = new Button();
            sideButton[i].setText(readFile.nextLine());
            sideButton[i].setOnAction(this);
            sideButton[i].setPrefWidth(120);
            mouseAffect(sideButton[i]);
            box.getChildren().add(sideButton[i]);
        }


        box.setSpacing(15);
        box.setPrefWidth(95);
        box.setAlignment(Pos.CENTER);
        Label label = new Label("Heelo");
        label.setPrefWidth(90);
        logOut.setPrefWidth(90);
        pane.setCenter(box);
        pane.setRight(logOut);
        pane.setLeft(label);
        return new Scene(pane, 400, 400);
    }

    private void mouseAffect(Button b) {
        b.setStyle("-fx-background-color: #008000;-fx-text-fill:white");
        b.setOnMouseEntered(e -> b.setStyle("-fx-background-color: #19662d;-fx-text-fill:white"));
        b.setOnMouseExited(e -> b.setStyle("-fx-background-color: #008000;-fx-text-fill:white"));
    }

    @Override
    public void handle(Event event) {
        Button button = (Button) event.getSource();
        if (button.getText().equals("Registration"))
            try {
                window.setScene(RegistrationScene());
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }


        //To change scene to Home scene from the Registration scene
        if (button.getText().equals("Back") && window.getTitle().equals("Registration")) {
            try {
                window.setScene(homeScene());
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Scene RegistrationScene() throws FileNotFoundException {
        BackButton.setOnAction(this);
        logOut.setOnAction(this);
        window.setTitle("Registration");
        BorderPane outerLay = new BorderPane();
        HBox hBox = new HBox();
        hBox.getChildren().addAll(BackButton, logOut);
        outerLay.setTop(hBox);
        hBox.setAlignment(Pos.TOP_RIGHT);
        HBox bottomBox = new HBox();

        Scanner readFile = new Scanner(new FileInputStream("RegistrationButton.txt"));


        Button[] button = new Button[4];
        for (int i = 0; i < button.length; i++) {
            button[i] = new Button();
            button[i].setText(readFile.nextLine());
            button[i].setOnAction(this);
            button[i].setPrefWidth(120);
            mouseAffect(button[i]);
            bottomBox.getChildren().add(button[i]);
        }
        outerLay.setBottom(bottomBox);
        bottomBox.setAlignment(Pos.BASELINE_LEFT);
        //   TableColumn[] tableColumns = new TableColumn[6];
        // TableView tableView = new TableView();


        HBox crnBox = new HBox();
        TextField[] crnText = new TextField[4];
        for (int i = 0; i < crnText.length; i++) {
            crnText[i] = new TextField();
            crnBox.getChildren().add(crnText[i]);

        }
        Label crnLabel = new Label("CRN: ");
        VBox centerBox = new VBox();
        centerBox.getChildren().addAll(crnLabel, crnBox);
        outerLay.setCenter(centerBox);
        centerBox.setAlignment(Pos.CENTER);

        return new Scene(outerLay, 400, 400);
    }


     private Scene searchScene() {
        BackButton.setOnAction(this);
        logOut.setOnAction(this);
        window.setTitle("Search Course");
        BorderPane outerLay = new BorderPane();
        HBox hBox = new HBox();
        hBox.getChildren().addAll(BackButton, logOut);
        outerLay.setTop(hBox);
        hBox.setAlignment(Pos.TOP_RIGHT);
        return new Scene(outerLay, 400, 400);
    }
}

