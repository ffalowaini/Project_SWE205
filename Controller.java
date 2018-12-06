package sample;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Controller {

    public void logInButton( ActionEvent event)
    {
        try {


            Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
            Scene scene = new Scene(root,600,600);

            Stage window =(Stage) ( (Node)event.getSource() ).getScene().getWindow() ;

            window.setScene(scene);
            window.show();

        }
        catch (Exception e){
            System.out.println(e.getMessage()+"");
        }

    }



    public void     goToRegistrationButton( ActionEvent e )
    {
        try {


            Parent root = FXMLLoader.load(getClass().getResource("Registration.fxml"));
            Scene scene = new Scene(root,600,600);

            Stage window =(Stage) ( (Node)e.getSource() ).getScene().getWindow() ;

            window.setScene(scene);
            window.show();

        }
        catch (Exception ex){
            System.out.println(ex.getMessage()+"");
        }


    }

    public void     goToSearchCourseButton( ActionEvent e )
    {
        try {


            Parent root = FXMLLoader.load(getClass().getResource("SearchCourse.fxml"));
            Scene scene = new Scene(root,600,600);

            Stage window =(Stage) ( (Node)e.getSource() ).getScene().getWindow() ;

            window.setScene(scene);
            window.show();

        }
        catch (Exception ex){
            System.out.println(ex.getMessage()+"");
        }


    }


    public void     goToApprovalButton( ActionEvent e )
    {
        try {


            Parent root = FXMLLoader.load(getClass().getResource("SearchCourse.fxml"));
            Scene scene = new Scene(root,600,600);

            Stage window =(Stage) ( (Node)e.getSource() ).getScene().getWindow() ;

            window.setScene(scene);
            window.show();

        }
        catch (Exception ex){
            System.out.println(ex.getMessage()+"");
        }


    }

    public void     goBackButton( ActionEvent e )
    {
        try {


            Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
            Scene scene = new Scene(root,600,600);

            Stage window =(Stage) ( (Node)e.getSource() ).getScene().getWindow() ;

            window.setScene(scene);
            window.show();

        }
        catch (Exception ex){
            System.out.println(ex.getMessage()+"");
        }


    }
    public void     logOutButton( ActionEvent e )
    {
        try {


            Parent root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
            Scene scene = new Scene(root,600,600);

            Stage window =(Stage) ( (Node)e.getSource() ).getScene().getWindow() ;

            window.setScene(scene);
            window.show();

        }
        catch (Exception ex){
            System.out.println(ex.getMessage()+"");
        }


    }



}


