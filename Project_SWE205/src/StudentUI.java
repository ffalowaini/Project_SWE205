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
import java.util.Arrays;
import java.util.Scanner;


public class StudentUI extends Application implements EventHandler {
    private TextField[] crnTextArray;
    private Stage window;
    private Button logOut = new Button("Log Out");
    private Button BackButton = new Button("Back");
    private TextField userNam, crnText;
    private PasswordField pass;
    private Label messageLabel,headerTableLabel;
    private Label[] crnLabel = new Label[4];
    private TextArea messageText;
    private Student students = new Student();
    private Student currentStudent;
    private Course[] listCourse;
    private Student[] listStudent;
    private int indexCurrentStudent;
    TextField searchFiled;
    Button searchButton;

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

    private Scene registrationScene() throws FileNotFoundException, ClassNotFoundException {
        listStudent = students.getStudent();
        listCourse = students.getCourse();
        currentStudent = listStudent[indexCurrentStudent];
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


        HBox crnTextBox = new HBox();
        crnTextArray = new TextField[4];
        for (int i = 0; i < crnTextArray.length; i++) {
            crnTextArray[i] = new TextField();
            crnTextBox.getChildren().add(crnTextArray[i]);

        }
        VBox crnLabelBox = new VBox();
        for (int i = 0; i < crnLabel.length; i++) {
            crnLabel[i] = new Label();
            crnLabelBox.getChildren().add(crnLabel[i]);
        }
        VBox tableBox = new VBox();
        headerTableLabel = new Label();
        headerTableLabel.setText(String.format("  %-5s\t%-12s\t%s\t%-30s\t%-3s\t%-11s", "CRN", "Course", "Sec", "Instructor", "Day", "Time"));
        tableBox.getChildren().add(headerTableLabel);
        Label[] crnTable = new Label[7];
        for (int i = 0; i < crnTable.length; i++) {
            crnTable[i] = new Label();
            tableBox.getChildren().addAll(crnTable[i]);
        }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (currentStudent.getCurrentCourses().size() >= 1) {
            int crn = currentStudent.getCurrentCourses().get(0);
            for (int i = 0; i < listCourse.length; i++)
                if (crn == listCourse[i].getCRN())
                    crnTable[0].setText(listCourse[i].getInfo());
        }
        if (currentStudent.getCurrentCourses().size() >= 2) {
            int crn = currentStudent.getCurrentCourses().get(1);
            for (int i = 0; i < listCourse.length; i++)
                if (crn == listCourse[i].getCRN())
                    crnTable[1].setText(listCourse[i].getInfo());
        }
        if (currentStudent.getCurrentCourses().size() >= 3) {
            int crn = currentStudent.getCurrentCourses().get(2);
            for (int i = 0; i < listCourse.length; i++)
                if (crn == listCourse[i].getCRN())
                    crnTable[2].setText(listCourse[i].getInfo());
        }
        if (currentStudent.getCurrentCourses().size() >= 4) {
            int crn = currentStudent.getCurrentCourses().get(3);
            for (int i = 0; i < listCourse.length; i++)
                if (crn == listCourse[i].getCRN())
                    crnTable[3].setText(listCourse[i].getInfo());
        }
        if (currentStudent.getCurrentCourses().size() >= 5) {
            int crn = currentStudent.getCurrentCourses().get(4);
            for (int i = 0; i < listCourse.length; i++)
                if (crn == listCourse[i].getCRN())
                    crnTable[4].setText(listCourse[i].getInfo());
        }
        if (currentStudent.getCurrentCourses().size() >= 6) {
            int crn = currentStudent.getCurrentCourses().get(5);
            for (int i = 0; i < listCourse.length; i++)
                if (crn == listCourse[i].getCRN())
                    crnTable[5].setText(listCourse[i].getInfo());
        }
        if (currentStudent.getCurrentCourses().size() >= 7) {
            int crn = currentStudent.getCurrentCourses().get(6);
            for (int i = 0; i < listCourse.length; i++)
                if (crn == listCourse[i].getCRN())
                    crnTable[6].setText(listCourse[i].getInfo());
        }
///////////////////////////////////////////////////////////////////////////////////////////////////////////
        Label crnLabel = new Label("CRN: ");
        HBox crnBox = new HBox();
        crnBox.getChildren().add(crnLabel);
        VBox centerBox = new VBox();
        centerBox.getChildren().addAll(tableBox, crnBox, crnTextBox, crnLabelBox);
        crnBox.setAlignment(Pos.BOTTOM_LEFT);
        outerLay.setCenter(centerBox);
        centerBox.setAlignment(Pos.BOTTOM_CENTER);

        return new Scene(outerLay, 400, 400);
    }


    private Scene searchScene() {
        try {
            listStudent = students.getStudent();
            listCourse = students.getCourse();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        currentStudent = listStudent[indexCurrentStudent];
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
        searchFiled = new TextField();
        searchButton = new Button("Search");
        searchButton.setOnAction(this);
        HBox searchBox = new HBox();
        VBox searchContainer = new VBox();

        searchBox.getChildren().addAll(searchButton, searchFiled);
        searchContainer.getChildren().addAll(searchBox, headerTableLabel, messageLabel);
        outerLay.setCenter(searchContainer);
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
        if (button.getText().equals("Registration")) {

            try {
                window.setScene(registrationScene());

            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
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

        int[] result = new int[4];
        if (button.getText().equals("Add Course")) {
            if (!(crnTextArray[0].getText().trim().isEmpty())) {
                try {
                    result[0] = students.addCourse(currentStudent.getID(), Integer.parseInt(crnTextArray[0].getText()));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            if (!(crnTextArray[1].getText().trim().isEmpty())) {
                try {
                    result[1] = students.addCourse(currentStudent.getID(), Integer.parseInt(crnTextArray[1].getText()));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            if (!(crnTextArray[2].getText().trim().isEmpty())) {
                try {
                    result[2] = students.addCourse(currentStudent.getID(), Integer.parseInt(crnTextArray[2].getText()));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            if (!(crnTextArray[3].getText().trim().isEmpty())) {
                try {
                    result[3] = students.addCourse(currentStudent.getID(), Integer.parseInt(crnTextArray[3].getText()));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i < result.length; i++) {
                if (result[i] == 101) {
                    crnLabel[i].setText(crnTextArray[i].getText() + " :You reached the maximum credit hours");
                } else if (result[i] == 102) {
                    crnLabel[i].setText(crnTextArray[i].getText() + " :The section is closed");
                } else if (result[i] == 103) {
                    crnLabel[i].setText(crnTextArray[i].getText() + " :Missing pre-request(s)");

                } else if (result[i] == 104) {
                    crnLabel[i].setText(crnTextArray[i].getText() + " :You have finished this course");
                } else if (result[i] == 105) {
                    crnLabel[i].setText(crnTextArray[i].getText() + " :Conflict in time");

                } else if (result[i] == 106) {
                    crnLabel[i].setText(crnTextArray[i].getText() + " :Course has been added");
                    try {
                        window.setScene(registrationScene());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                } else if (result[i] == 107) {
                    crnLabel[i].setText(crnTextArray[i].getText() + " :You already have this course");
                } else if (result[i] == 108) {
                    crnLabel[i].setText(crnTextArray[i].getText() + " :You entered invalid CRN");
                }
            }
        }


        if (button.getText().equals("Log In")) {
            try {
                System.out.println(Arrays.toString(students.getStudent()));
                listStudent = students.getStudent();
                listCourse = students.getCourse();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            String passwordEntered = pass.getText();
            String idEnterd = userNam.getText();
            boolean check = false;
            try {
                for (int i = 0; i < listStudent.length; i++) {
                    if (listStudent[i].getID() == Integer.parseInt(idEnterd))
                        if (listStudent[i].getPassword().equals(passwordEntered)) {
                            currentStudent = listStudent[i];
                            indexCurrentStudent = i;
                            window.setScene(homeScene());
                            check = true;
                            break;
                        }
                }

                //it will print anyway
                if (!check) {
                    messageLabel.setText("The username/password is incorrect");
                    messageLabel.setStyle("-fx-text-fill: Red");
                }
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
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        boolean[] checkDelete = new boolean[4];
        if (button.getText().equals("Drop Course")) {
            for (int i = 0; i < crnTextArray.length; i++) {
                if (!(crnTextArray[i].getText().trim().isEmpty())) {
                    try {
                        checkDelete[i] = false;
                        checkDelete[i] = students.deleteCourse(currentStudent.getID(), Integer.parseInt(crnTextArray[i].getText()));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }

            for (int i = 0; i < result.length; i++) {
                if (!(crnTextArray[i].getText().trim().isEmpty())) {
                    if (checkDelete[i]) {
                        crnLabel[i].setText(crnTextArray[i].getText() + " : Has been deleted");
                        try {
                            window.setScene(registrationScene());
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    } else {
                        crnLabel[i].setText(crnTextArray[i].getText() + " : You don't have this course");
                    }
                }
            }


        }

        if (button.getText().equals("Search")) {
            if (!(searchFiled.getText().trim().isEmpty())){
                headerTableLabel.setText(String.format("  %-5s\t%-12s\t%s\t%-30s\t%-3s\t%-11s", "CRN", "Course", "Sec", "Instructor", "Day", "Time"));
                int crn = Integer.parseInt(searchFiled.getText());
                for (int i = 0; i < listCourse.length; i++)
                    if (crn == listCourse[i].getCRN())
                        messageLabel.setText(listCourse[i].getInfo());
             //   messageLabel.setText();
            } else {
                messageLabel.setText("You have to enter a valid CRN");
            }

        }

        if (button.getText().equals("Send")) {
            if (messageText.getText().trim().isEmpty() || crnText.getText().trim().isEmpty())
                messageLabel.setText("Your CRN/Message is empty");
            else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Succeed");
                alert.setContentText("Your message has been send and it will be respond as soon as possible");
                alert.showAndWait();
                try {
                    window.setScene(registrationScene());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //to have an interaction between the button and the mouse pointer
    private void mouseAffect(Button b) {
        b.setStyle("-fx-background-color: #008000;-fx-text-fill:white");
        b.setOnMouseEntered(e -> b.setStyle("-fx-background-color: #19662d;-fx-text-fill:white"));
        b.setOnMouseExited(e -> b.setStyle("-fx-background-color: #008000;-fx-text-fill:white"));
    }
}

