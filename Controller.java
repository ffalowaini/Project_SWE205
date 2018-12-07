package sample;


import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import javax.xml.soap.Text;

import javafx.scene.control.TextField;

public class Controller {

    Student currentStudent;
    int ourIndex;

    public void logInButton(ActionEvent event) {
        try {
            System.out.println(currentStudent.getID());

            Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
            Scene scene = new Scene(root, 600, 600);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(scene);

            window.show();
            System.out.println(ourIndex + "A");


        } catch (Exception e) {
            System.out.println(e.getMessage() + "");
        }

    }

    @FXML
    Label row1, row2, row3, row4, row5, row6;

    public void goToRegistrationButton(ActionEvent e) {
        try {

            FXMLLoader Loader = new FXMLLoader();
            Loader.setLocation(getClass().getResource("Registration.fxml"));

                Loader.load();





           /* Parent root = FXMLLoader.load(getClass().getResource("Registration.fxml"));
            Scene scene = new Scene(root, 600, 600);

            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();

            window.setScene(scene);
            window.show();*/
            Controller controller = Loader.getController();
            
controller
                    Parent p = Loader.getRoot();
            Stage stage = new Stage();
           stage.setScene(new Scene(p));
           stage.showAndWait();
           

        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "");
        }

        Course x = new Course();
        if (currentStudent.getCurrentCourses().size() >= 1)
            row1.setText(x.getInfo(currentStudent.getCurrentCourses().get(0)));
        if (currentStudent.getCurrentCourses().size() >= 2)
            row1.setText(x.getInfo(currentStudent.getCurrentCourses().get(1)));
        if (currentStudent.getCurrentCourses().size() >= 3)
            row1.setText(x.getInfo(currentStudent.getCurrentCourses().get(2)));
        if (currentStudent.getCurrentCourses().size() >= 4)
            row1.setText(x.getInfo(currentStudent.getCurrentCourses().get(3)));
        if (currentStudent.getCurrentCourses().size() >= 5)
            row1.setText(x.getInfo(currentStudent.getCurrentCourses().get(4)));
        if (currentStudent.getCurrentCourses().size() >= 6)
            row1.setText(x.getInfo(currentStudent.getCurrentCourses().get(5)));

    }

    public void goToSearchCourseButton(ActionEvent e) {
        try {


            Parent root = FXMLLoader.load(getClass().getResource("SearchCourse.fxml"));
            Scene scene = new Scene(root, 600, 600);

            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();

            window.setScene(scene);
            window.show();

        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "");
        }


    }


    public void goToApprovalButton(ActionEvent e) {
        try {


            Parent root = FXMLLoader.load(getClass().getResource("Approval.fxml"));
            Scene scene = new Scene(root, 600, 600);

            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();

            window.setScene(scene);
            window.show();

        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "");
        }


    }

    public void goBackButton(ActionEvent e) {
        try {


            Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
            Scene scene = new Scene(root, 600, 600);

            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();

            window.setScene(scene);
            window.show();

        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "");
        }


    }

    public void logOutButton(ActionEvent e) {
        try {


            Parent root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
            Scene scene = new Scene(root, 600, 600);

            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();

            window.setScene(scene);
            window.show();

        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "");
        }


    }

    @FXML
    TextField txt;

    @FXML
    PasswordField passwordField;

    @FXML
    Label errorsLabel;


    public void logInAction(ActionEvent ev) throws ClassNotFoundException {

        Student students = new Student();
        Student[] listStudent = students.getStudent();
        Course[] listCourse = students.getCourse();


        String idEnterd = txt.getText();


        try {


            String passwordEntered = passwordField.getText();


            for (int i = 0; i < listStudent.length; i++) {
                if (listStudent[i].getID() == Integer.parseInt(idEnterd))
                    if (listStudent[i].getPassword().equals(passwordEntered)) {
                        currentStudent = listStudent[i];
                        ourIndex = i;

                        logInButton(ev);
                        break;
                    }


            }

            errorsLabel.setText("The id or Password is not correct");


        } catch (NumberFormatException ex) {
            errorsLabel.setText("The id can only be numbers");

        }
    }


}


