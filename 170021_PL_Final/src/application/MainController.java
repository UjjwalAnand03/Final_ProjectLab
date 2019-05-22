package application;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

/***
 *The MainController is the FXML-Controller Class.
 */

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
 * 
 */

public class MainController extends Application implements Initializable {


    @FXML
    private Button login;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private RadioButton reader;
    @FXML
    private RadioButton author;
    @FXML
    private RadioButton admin;
    @FXML
    private Label message;
    @FXML
    private ComboBox<String> cb_roles;
    @FXML
    private TextField Rollnumber;
    @FXML
    private TextField decimalvalue;
    @FXML
    private TextField Result;
    @FXML
    private Button Multiply;
    @FXML
    private TextField txt_userName;
    @FXML
    private PasswordField txt_password;
    @FXML
    private TextField txt_email;
    @FXML
    private PasswordField txt_confirmPassword;
    private ArrayList<String> userList = new ArrayList<String>();
    private ArrayList<String> passList = new ArrayList<String>();
    private ArrayList<String> emailList = new ArrayList<String>();
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        cb_roles.setPromptText("Select User");
        cb_roles.getItems().addAll("Reader", "Author", "Admin");
    }
    public enum EBMSHubs {
        AUTHOR_HUB,
        ADMIN_HUB,
        READER_HUB
    }
    private EBMSHubs currentlyCalledHub;

    /***
     * @author Jaskirat Singh Grewal (Team 9)
     * @throws IOException
     * Signup Window Backend Implementation
     */
    private void addNewAccount() throws IOException {

        String newUser = txt_userName.getText();
        String pass = txt_password.getText();
        String confirmPass = txt_confirmPassword.getText();
        String email = txt_email.getText();

        if (!pass.equals(confirmPass)) return;
        TextArea pseudoTextArea = new TextArea();
        Scanner scanner = new Scanner(new File("AccountDetails"));
        while (scanner.hasNext()) pseudoTextArea.setText(scanner.next());
        FileWriter fileWriter = new FileWriter(new File("AccountDetails"));
        fileWriter.write(pseudoTextArea.getText() + "\n");
        fileWriter.append(newUser + "\n" + pass + "\n" + email + "\n");
        fileWriter.close();
    }
    private void loadTheAccountData() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("AccountDetails"));
        while (scanner.hasNext()) {
            userList.add(scanner.next());passList.add(scanner.next());emailList.add(scanner.next());
        }
        scanner.close();
    }
   
    
    /***
     * @author Jaskirat Singh Grewal (Team-9)
     */
       public void launchRelevantWindow () throws FileNotFoundException {
           loadTheAccountData();
        String checkUserType = cb_roles.getSelectionModel().getSelectedItem();
        Hashtable<Integer,String> titleTable = new Hashtable<>();
        titleTable.put(1,"Reader's Hub");
        titleTable.put(2,"Author's Hub");
        titleTable.put(3,"Admin's Hub");
        String currentStageTitle = "";
        boolean userIsValidated = false;
        if (userList.contains(username.getText())&&passList.contains(password.getText())) {
            if (userList.indexOf(username.getText())==passList.indexOf(password.getText()))  {
                System.out.println("Re");
                userIsValidated= true;
            }
        }
      if (userIsValidated) {
          try {
              // Read file fxml and draw interface.
              FXMLLoader fxmlLoader;
              if (checkUserType.equals("Reader")) {
                  currentlyCalledHub = EBMSHubs.READER_HUB;
                  fxmlLoader = new FXMLLoader(getClass()
                          .getResource("/application/HomeWindow.fxml"));
                  currentStageTitle = titleTable.get(1);
              } else if (checkUserType.equals("Author")) {
                  currentlyCalledHub = EBMSHubs.AUTHOR_HUB;
                  fxmlLoader = new FXMLLoader(getClass()
                          .getResource("/application/AuthorHub.fxml"));
                  currentStageTitle = titleTable.get(2);
              } else {
                  currentlyCalledHub = EBMSHubs.ADMIN_HUB;
                  fxmlLoader = new FXMLLoader(getClass()
                          .getResource("/application/AdminHub.fxml"));
                  currentStageTitle = titleTable.get(3);
              }
              Parent root1 = (Parent) fxmlLoader.load();
              Stage mainStage = new Stage();
              mainStage.setTitle(currentStageTitle);
              mainStage.setScene(new Scene(root1));
              mainStage.show();

          } catch (Exception e) {
              e.printStackTrace();
              System.out.println("Can't Load the window");
          }
      }
      else message.setText("Invalid Credentials");
    }
    public void SignUp() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                .getResource("/application/SignUp.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage mainStage = new Stage();
        mainStage.setTitle("Signing Up Window");
        mainStage.setScene(new Scene(root1));
        mainStage.show();
    }
    
    public void launch () throws FileNotFoundException {
    	    int e;
    	    int r;
    	    int t;
    	    
    	    e=170003;
    	    
    	    r=25;
    	    
    	   t=e*r;
    	   String reddy= new Integer(t).toString();
    	   
    	   Result.setText(reddy);
    }
    

    @Override
    public void start(Stage arg0) throws Exception {
        Parent root = FXMLLoader.load(getClass()
                .getResource("/application/LoginPage.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("eBook Software");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public EBMSHubs getCurrentlyCalledHub() {
        return currentlyCalledHub;
    }
}
		
