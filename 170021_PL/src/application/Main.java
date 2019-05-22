package application;
 
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
 
/**
 * 
 * 
 * @author
 * Team 4 ZoomIn and ZoomOut functionality
 * Team 7 Editing EBook functionality
 * Team 8 GUI for EBook
 * Team 9 Sending Feedback Functionality
 * 
 * Managed By Jaskirat Singh Grewal
 * Integrated By Shivam Singhal, Sanchit, Sumit Singh
 */

public class Main  extends Application {
    
  
    @Override
    public void start(Stage primaryStage) {
        try {
            // Read file fxml and draw interface.
            Parent root = FXMLLoader.load(getClass() 
                    .getResource("/application/LoginPage.fxml"));
 
            primaryStage.setTitle("eBook Software");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
            
            
         
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
    
//fx:Controller="application.Controller"