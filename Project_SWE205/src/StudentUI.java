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

//This class For the user Interface
public class StudentUI extends Application implements EventHandler {
    private TextField[] crnTextArray;
    private Stage window;
    private Button logOut = new Button("Log Out");
    private Button BackButton = new Button("Back");
    private TextField userNam, crnText;
    private PasswordField pass;
    private Label messageLabel, headerTableLabel;
    private Label[] crnLabel = new Label[4];
    private TextArea messageText;
    private Student students = new Student();
    private Student currentStudent;
    private Course[] listCourse;
    private Student[] listStudent;
    private int indexCurrentStudent;
    private TextField searchFiled;

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

    //To set up the scene for the Log in
    private Scene logIn() {


        window.setTitle("Log In");
        BackButton.setOnAction(this);
        logOut.setOnAction(this);


        Label userName = new Label(String.format("%9s", "ID:    \t"));
        Label password = new Label(String.format("%9s", "Password:"));


        messageLabel = new Label();
        userNam = new TextField();
        pass = new PasswordField();

        HBox userBox = new HBox();//to be a container for the user label and user Text
        HBox passBox = new HBox();//to be a container for the password label and password Text

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

        VBox centerBox = new VBox();//to be a container for the HBox


        centerBox.setSpacing(10);
        centerBox.getChildren().addAll(userBox, passBox, messageLabel, logInButton);
        centerBox.setAlignment(Pos.CENTER);

        return new Scene(centerBox, 400, 400);

    }

    //To set up the Home scene
    private Scene homeScene() throws FileNotFoundException {
        logOut.setOnAction(this);
        logOut.setPrefWidth(80);


        Scanner readFile = new Scanner(new FileInputStream("Menu Buttons.txt"));//to read a file containing the name of the menu buttons

        BorderPane outerLay = new BorderPane();
        VBox menuContainer = new VBox();// container for the menu button

        Button[] sideButton = new Button[4];

        for (int i = 0; i < sideButton.length; i++) {
            sideButton[i] = new Button();
            sideButton[i].setText(readFile.nextLine());//from reading the file
            sideButton[i].setOnAction(this);
            sideButton[i].setPrefWidth(120);
            mouseDecoration(sideButton[i]);//adding an effect when a mouse entered or existed or
            menuContainer.getChildren().add(sideButton[i]);
        }


        menuContainer.setSpacing(15);
        menuContainer.setPrefWidth(95);
        menuContainer.setAlignment(Pos.CENTER);
        Label label = new Label("ID: " + currentStudent.getID());//to display the student id on the screen
        label.setPrefWidth(90);
        logOut.setPrefWidth(90);
        outerLay.setCenter(menuContainer);
        outerLay.setRight(logOut);
        outerLay.setLeft(label);
        return new Scene(outerLay, 400, 400);
    }

    //To set up the registration scene
    private Scene registrationScene() throws FileNotFoundException, ClassNotFoundException {
        messageLabel.setText("");
        BackButton.setOnAction(this);
        logOut.setOnAction(this);
        BackButton.setPrefWidth(80);
        logOut.setPrefWidth(80);
        HBox topBox = new HBox();// to contain the back and log out buttons
        topBox.getChildren().addAll(BackButton, logOut);
        topBox.setAlignment(Pos.TOP_RIGHT);


        HBox bottomBox = new HBox();// To contains the button of the registration scene
        listStudent = students.getStudent();//To assign the students to the listStudent from the database
        listCourse = students.getCourse();//To assign the  courses to the listCourse from the database
        currentStudent = listStudent[indexCurrentStudent];
        window.setTitle("Registration");
        BorderPane outerLay = new BorderPane();
        outerLay.setTop(topBox);
        Scanner readFile = new Scanner(new FileInputStream("RegistrationButton.txt"));//to read a file that contains the buttons for the registration scene


        Button[] button = new Button[4];
        for (int i = 0; i < button.length; i++) {
            button[i] = new Button();
            button[i].setText(readFile.nextLine());
            button[i].setOnAction(this);
            button[i].setPrefWidth(120);
            mouseDecoration(button[i]);//to add effect on the mouse
            bottomBox.getChildren().add(button[i]);
        }
        outerLay.setBottom(bottomBox);
        bottomBox.setAlignment(Pos.BASELINE_LEFT);


        HBox crnTextBox = new HBox();//To contain the text fields for the CRN
        crnTextArray = new TextField[4];
        for (int i = 0; i < crnTextArray.length; i++) {
            crnTextArray[i] = new TextField();
            crnTextBox.getChildren().add(crnTextArray[i]);

        }
        VBox crnLabelBox = new VBox();//To contain the CRN label to show the message taken under specific condition for the CRN
        for (int i = 0; i < crnLabel.length; i++) {
            crnLabel[i] = new Label();
            crnLabelBox.getChildren().add(crnLabel[i]);
        }
        VBox tableBox = new VBox(); // For the schedule of the student
        headerTableLabel = new Label();
        headerTableLabel.setText(String.format("  %-5s\t%-12s\t%s\t%-30s\t%-3s\t%-11s", "CRN", "Course", "Sec", "Instructor", "Day", "Time"));
        tableBox.getChildren().add(headerTableLabel);
        Label[] crnTable = new Label[7];//to show the information for the courses,[7] stands for the maximum possible courses student can have
        for (int i = 0; i < crnTable.length; i++) {
            crnTable[i] = new Label();
            tableBox.getChildren().addAll(crnTable[i]);
        }
//////////////////////////////////////////////////////////////to get the info of the student's courses
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
/////////////////////////////////////////////////////////////////////////////////////////////////////////// end of getting the info
        Label crnLabel = new Label("CRN: ");
        HBox crnBox = new HBox();
        crnBox.getChildren().add(crnLabel);
        VBox centerBox = new VBox();// To contain all the nodes for the registration scene
        centerBox.getChildren().addAll(tableBox, crnBox, crnTextBox, crnLabelBox);
        crnBox.setAlignment(Pos.BOTTOM_LEFT);
        outerLay.setCenter(centerBox);
        centerBox.setAlignment(Pos.BOTTOM_CENTER);

        return new Scene(outerLay, 400, 400);
    }

    //To set up the Search scene
    private Scene searchScene() {
        window.setTitle("Search Course");
        BackButton.setOnAction(this);
        logOut.setOnAction(this);
        BackButton.setPrefWidth(80);
        logOut.setPrefWidth(80);
        HBox ContainerBox = new HBox();//To contain the Back and Home buttons
        ContainerBox.getChildren().addAll(BackButton, logOut);
        ContainerBox.setAlignment(Pos.TOP_RIGHT);
        try {
            listStudent = students.getStudent();//To assign the students to the listStudent from the database
            listCourse = students.getCourse();//To assign the courses to the listCourse from the database

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        currentStudent = listStudent[indexCurrentStudent];
        BorderPane outerLay = new BorderPane();
        outerLay.setTop(ContainerBox);
        searchFiled = new TextField();
        Button searchButton = new Button("Search");
        searchButton.setOnAction(this);
        HBox searchBox = new HBox();//To contain the search label and box
        VBox searchContainer = new VBox();// To contain the whole nodes for the search scene

        searchBox.getChildren().addAll(searchButton, searchFiled);
        searchContainer.getChildren().addAll(searchBox, headerTableLabel, messageLabel);
        outerLay.setCenter(searchContainer);
        searchBox.setAlignment(Pos.TOP_LEFT);
        return new Scene(outerLay, 400, 400);
    }
//To set up the approval scene
    private Scene approvalScene() {

        window.setTitle("Approval");


        BackButton.setOnAction(this);
        logOut.setOnAction(this);
        BackButton.setPrefWidth(80);
        logOut.setPrefWidth(80);
        HBox ContainerBox = new HBox();//To contain the Back and Home buttons
        ContainerBox.getChildren().addAll(BackButton, logOut);
        ContainerBox.setAlignment(Pos.TOP_RIGHT);


        BorderPane outerLay = new BorderPane();
        outerLay.setTop(ContainerBox);


        Label crnLabel = new Label("CRN:");
        crnLabel.setPrefWidth(60);
        crnText = new TextField();
        crnText.setPrefWidth(200);


        Label messageLabelLoc = new Label("Message:");
        messageLabelLoc.setPrefWidth(60);
        messageText = new TextArea();
        messageText.setPrefWidth(300);

        HBox crnBox = new HBox();//To contain the CRN label and Text
        HBox messBox = new HBox();//To contain the message label and Text

        crnBox.getChildren().addAll(crnLabel, crnText);
        messBox.getChildren().addAll(messageLabelLoc, messageText);

        VBox centerBox = new VBox();//to contain the whole nodes for the approval scene
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



//////////////////////////////////////////////////////////



        /*in this if-statements we will pass all the CRN-slots to the "addcourse" method, and it will return a specific
        number.Each number represents a specific message
        */

        int[] crnResult = new int[4];//[4] stands for the possible number of courses to add at the same time
        if (button.getText().equals("Add Course")) {
            if (!(crnTextArray[0].getText().trim().isEmpty())) {
                try {
                    crnResult[0] = students.addCourse(currentStudent.getID(), Integer.parseInt(crnTextArray[0].getText()));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            if (!(crnTextArray[1].getText().trim().isEmpty())) {
                try {
                    crnResult[1] = students.addCourse(currentStudent.getID(), Integer.parseInt(crnTextArray[1].getText()));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            if (!(crnTextArray[2].getText().trim().isEmpty())) {
                try {
                    crnResult[2] = students.addCourse(currentStudent.getID(), Integer.parseInt(crnTextArray[2].getText()));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            if (!(crnTextArray[3].getText().trim().isEmpty())) {
                try {
                    crnResult[3] = students.addCourse(currentStudent.getID(), Integer.parseInt(crnTextArray[3].getText()));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            //Depends on the number from the previous if-statements, a certain message will be printed
            for (int i = 0; i < crnResult.length; i++) {
                if (crnResult[i] == 101) {
                    crnLabel[i].setText(crnTextArray[i].getText() + " :You reached the maximum credit hours");
                } else if (crnResult[i] == 102) {
                    crnLabel[i].setText(crnTextArray[i].getText() + " :The section is closed");
                } else if (crnResult[i] == 103) {
                    crnLabel[i].setText(crnTextArray[i].getText() + " :Missing pre-request(s)");

                } else if (crnResult[i] == 104) {
                    crnLabel[i].setText(crnTextArray[i].getText() + " :You have finished this course");
                } else if (crnResult[i] == 105) {
                    crnLabel[i].setText(crnTextArray[i].getText() + " :Conflict in time");

                } else if (crnResult[i] == 106) {
                    crnLabel[i].setText(crnTextArray[i].getText() + " :Course has been added");
                    try {
                        window.setScene(registrationScene());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                } else if (crnResult[i] == 107) {
                    crnLabel[i].setText(crnTextArray[i].getText() + " :You already have this course");
                } else if (crnResult[i] == 108) {
                    crnLabel[i].setText(crnTextArray[i].getText() + " :You entered invalid CRN");
                }
            }
        }



//////////////////////////////////////////////////////////////////////////////


        //To check the validation of the username and password
        if (button.getText().equals("Log In")) {
            try {
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
        //this if-statements to check if a student has this course(s) or not to delete it
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
            //related to the previous if-statements
            for (int i = 0; i < crnResult.length; i++) {
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

        //to search to a certain CRN
        if (button.getText().equals("Search")) {
            if (!(searchFiled.getText().trim().isEmpty())) {
                headerTableLabel.setText(String.format("  %-5s\t%-12s\t%s\t%-30s\t%-3s\t%-11s", "CRN", "Course", "Sec", "Instructor", "Day", "Time"));
                int crn = Integer.parseInt(searchFiled.getText());
                for (int i = 0; i < listCourse.length; i++)
                    if (crn == listCourse[i].getCRN())
                        messageLabel.setText(listCourse[i].getInfo());
            } else {
                messageLabel.setText("You have to enter a valid CRN");
            }

        }

        //to request an approval for a specific course
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
    private void mouseDecoration(Button b) {
        b.setStyle("-fx-background-color: #008000;-fx-text-fill:white");
        b.setOnMouseEntered(e -> b.setStyle("-fx-background-color: #19662d;-fx-text-fill:white"));
        b.setOnMouseExited(e -> b.setStyle("-fx-background-color: #008000;-fx-text-fill:white"));
    }
}

