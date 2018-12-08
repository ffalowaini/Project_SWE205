package StudentSystem;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class StudentUI extends Application implements EventHandler {
    private TextField[] crnTextArray;
    private Stage window;
    private Button logOut = new Button("Log Out");
    private Button BackButton = new Button("Back");
    private TextField userNam, crnText;
    private PasswordField pass;
    private Label messageLabel;
    private TextArea messageText;
    private Student students = new Student();
    private Course course = new Course();
    private Student currentStudent;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("Log In");
        window.setScene(logIn());

        window.show();

    }


    private Scene logIn() {
        window.setTitle("Log In");
        BackButton.setOnAction(this);
        logOut.setOnAction(this);
        Stage dialog = new Stage();


        Label userName = new Label(String.format("%9s", "ID:    \t"));
        Label password = new Label(String.format("%9s", "Password:"));
        messageLabel = new Label();
        userNam = new TextField();
        pass = new PasswordField();

        HBox userBox = new HBox();
        HBox passBox = new HBox();
        userBox.setPrefWidth(100);
        passBox.setPrefWidth(100);
        userBox.setAlignment(Pos.CENTER);
        userBox.setSpacing(10);
        passBox.setAlignment(Pos.CENTER);
        passBox.setSpacing(10);
        userBox.getChildren().addAll(userName, userNam);
        passBox.getChildren().addAll(password, pass);
        Button logInButton = new Button("Log In");
        logInButton.setOnAction(this);
        VBox centerBox = new VBox();
        centerBox.setSpacing(10);
        centerBox.getChildren().addAll(userBox, passBox, messageLabel, logInButton);


        centerBox.setAlignment(Pos.CENTER);

        return new Scene(centerBox, 400, 400);

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
        Label label = new Label("ID: " + currentStudent.getID());
        label.setPrefWidth(90);
        logOut.setPrefWidth(90);
        pane.setCenter(box);
        pane.setRight(logOut);
        pane.setLeft(label);
        return new Scene(pane, 400, 400);
    }

    private Scene registrationScene() throws FileNotFoundException {
        messageLabel.setText("");
        BackButton.setOnAction(this);
        logOut.setOnAction(this);
        BackButton.setPrefWidth(80);
        logOut.setPrefWidth(80);
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
        crnTextArray = new TextField[4];
        for (int i = 0; i < crnTextArray.length; i++) {
            crnTextArray[i] = new TextField();
            crnBox.getChildren().add(crnTextArray[i]);

        }
        Label crnLabel = new Label("CRN: ");
        VBox centerBox = new VBox();
        centerBox.getChildren().addAll(messageLabel, crnLabel, crnBox);
        outerLay.setCenter(centerBox);
        centerBox.setAlignment(Pos.CENTER);

        return new Scene(outerLay, 400, 400);
    }


    private Scene searchScene() {
        BackButton.setOnAction(this);
        logOut.setOnAction(this);
        BackButton.setPrefWidth(80);
        logOut.setPrefWidth(80);
        window.setTitle("Search Course");
        BorderPane outerLay = new BorderPane();
        HBox hBox = new HBox();
        hBox.getChildren().addAll(BackButton, logOut);
        outerLay.setTop(hBox);
        hBox.setAlignment(Pos.TOP_RIGHT);
        TextField searchFiled = new TextField();
        Button searchButton = new Button("Search");
        searchButton.setOnAction(this);
        HBox searchBox = new HBox();
        searchBox.getChildren().addAll(searchButton, searchFiled);
        outerLay.setCenter(searchBox);
        Button addButton = new Button("Add");
        addButton.setOnAction(this);
        outerLay.setBottom(addButton);
        addButton.setAlignment(Pos.TOP_LEFT);
        addButton.setPrefWidth(50);
        searchBox.setAlignment(Pos.TOP_LEFT);
        return new Scene(outerLay, 400, 400);
    }

    private Scene approvalScene() {

        window.setTitle("Approval");
        BorderPane outerLay = new BorderPane();


        BackButton.setOnAction(this);
        logOut.setOnAction(this);
        BackButton.setPrefWidth(80);
        logOut.setPrefWidth(80);
        HBox hBox = new HBox();
        hBox.getChildren().addAll(BackButton, logOut);
        outerLay.setTop(hBox);
        hBox.setAlignment(Pos.TOP_RIGHT);
        Label crnLabel = new Label("CRN:");
        crnLabel.setPrefWidth(60);
        crnText = new TextField();
        crnText.setPrefWidth(200);
        Label messageLabelLoc = new Label("Message:");
        messageLabelLoc.setPrefWidth(60);
        messageText = new TextArea();
        messageText.setPrefWidth(300);
        HBox crnBox = new HBox();
        HBox messBox = new HBox();
        crnBox.getChildren().addAll(crnLabel, crnText);
        messBox.getChildren().addAll(messageLabelLoc, messageText);

        VBox centerBox = new VBox();
        centerBox.setSpacing(20);
        centerBox.getChildren().addAll(crnBox, messBox, messageLabel);
        centerBox.setAlignment(Pos.CENTER_LEFT);
        Button sendButton = new Button("Send");
        sendButton.setOnAction(this);
        outerLay.setCenter(centerBox);
        outerLay.setBottom(sendButton);
        sendButton.setAlignment(Pos.CENTER_LEFT);
        return new Scene(outerLay, 400, 400);
    }

    @Override
    public void handle(Event event) {


        Button button = (Button) event.getSource();

        //to Change the scene from The home scene to the registration scene
        if (button.getText().equals("Registration"))
            try {
                window.setScene(registrationScene());
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        //to Change the scene from The registration scene to the search course scene
        if (button.getText().equals("Search Course"))
            window.setScene(searchScene());
        //to Change the scene from The registration scene to the Approval scene
        if (button.getText().equals("Approval"))
            window.setScene(approvalScene());
        //to Change the scene to the log in scene
        if (button.getText().equals("Log Out")) {
            window.setScene(logIn());

        }

        int result = 0;
        if (button.getText().equals("Add Course")) {


            try {
                result = students.addCourse(
                        currentStudent.getID(),
                        Integer.parseInt(crnTextArray[0].getText()));
                System.out.println(result);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                if (crnTextArray[0].getText().trim().isEmpty())
                    messageLabel.setText("You need to write a CRN");


            }


            if (result == 101) {
            } else if (result == 102) {
                System.out.println("102");
            } else if (result == 103) {
            } else if (result == 104) {
            } else if (result == 105) {
            } else {

            }
        }


        if (button.getText().equals("Log In")) {
            Student[] listStudent = null;
            try {

                listStudent = students.getStudent();
                Course[] listCourse = students.getCourse();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            String passwordEntered = pass.getText();
            String idEnterd = userNam.getText();
            try {
                for (Student student : listStudent) {
                    if (student.getID() == Integer.parseInt(idEnterd))
                        if (student.getPassword().equals(passwordEntered)) {
                            currentStudent = student;
                            window.setScene(homeScene());
                            break;
                        }
                }
                //it will print anyway
                messageLabel.setText("The username/password is incorrect");
                messageLabel.setStyle("-fx-text-fill: Red");

            } catch (FileNotFoundException e) {
                e.printStackTrace();


            } catch (NumberFormatException ex) {
                messageLabel.setText("The username must be an integer");
                messageLabel.setStyle("-fx-text-fill: Red");
            }


        }

        //To change scene to Home scene from the Registration scene
        if (button.getText().equals("Back") && window.getTitle().equals("Registration")) {
            try {
                window.setScene(homeScene());
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
        //To change scene to Registration scene from the Search Course scene
        if (button.getText().equals("Back") && window.getTitle().equals("Search Course") || button.getText().equals("Back") && window.getTitle().equals("Approval")) {
            try {
                window.setScene(registrationScene());
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }


        if (button.getText().equals("Send")) {
            if (messageText.getText().trim().isEmpty() || crnText.getText().trim().isEmpty())
                messageLabel.setText("Your CRN/Message is empty");
            else{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Succeed");
                alert.setContentText("Your message ");
                alert.showAndWait();
                try {
                    window.setScene(registrationScene());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void mouseAffect(Button b) {
        b.setStyle("-fx-background-color: #008000;-fx-text-fill:white");
        b.setOnMouseEntered(e -> b.setStyle("-fx-background-color: #19662d;-fx-text-fill:white"));
        b.setOnMouseExited(e -> b.setStyle("-fx-background-color: #008000;-fx-text-fill:white"));
    }
}

