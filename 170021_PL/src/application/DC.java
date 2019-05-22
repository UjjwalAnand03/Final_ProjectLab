package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
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

public class DC implements Initializable {
	
	@FXML
	WebView webView;
	
    @FXML 
   private WebEngine webengine;
    @FXML
	ComboBox<String> feedback_recievers;


    ObservableList<String> List = FXCollections.<String>observableArrayList();
	ListView<String> listview = new ListView<String>(List);
	VBox vbox = new VBox(listview);
	javafx.scene.control.Label listOfBooks = new javafx.scene.control.Label("Available Books in Repository");
	Button browse = new Button("Browse Books");
	TextArea contentArea = new TextArea();
	TextField filePath = new TextField();
	File thedirectory = new File("RepositoryFolder");
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		 this.webengine = this.webView.getEngine();
		    this.webengine.load("http://google.com");
		feedback_recievers = new ComboBox<>();
		feedback_recievers.getItems().addAll("Book Author","Application Administrator");
		
	}
	public  void launchFeedbackWindow() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass()
				.getResource("/application/Feedback_Window.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();
		Stage mainStage = new Stage();
		mainStage.setTitle("Give Us Your Valuable Feedback");
		mainStage.setScene(new Scene(root1));
		mainStage.show();
	}
	
	public  void editing() throws IOException {
		finalmethod();
	}
	public  void zoomIn() throws IOException {
		webView.setZoom(webView.getZoom()*1.2);
	}
	public  void zoomOut() throws IOException {
		webView.setZoom(webView.getZoom()/1.2);
	}
	
	
	public void finalmethod()	
	{
		Stage stage = new Stage();
		Group group = new Group();
		Scene sc = new Scene(group,1500,700);
		sc.setFill(Color.WHITE);
		Button editContent = new Button("Edit Book");
		editContent.setLayoutX(1400); editContent.setLayoutY(180);
		editContent.setOnAction((event) -> {
			EditFileContent();
		});
		
		browse.setLayoutX(1000); browse.setLayoutY(50);
		browse.setOnAction((event) -> 
		{
			
			openDialo();
			
			 	});
		
		File directoryPath = new File("RepositoryFolder");
		 File[] files=directoryPath.listFiles(new FilenameFilter() {
				
				public boolean accept(File dir, String name) {
					return name.endsWith(".txt");
				}
			});
			
			for (File file : files) {
				List.add(file.getName());
				
			}
		listview.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue)->{
			contentArea.clear();

			String v= directoryPath+"\\"+listview.getSelectionModel().getSelectedItem();
			File t = new File(v);
			try {
				Scanner scan = new Scanner(t);
				while(scan.hasNextLine()) {
					
					String i = scan.nextLine();
					contentArea.appendText(i+"\n");
					}
				
			} catch (FileNotFoundException e1) {
				
				e1.printStackTrace();
			}
			
		});
		
		filePath.setLayoutX(450); filePath.setLayoutY(50);
		filePath.setMinWidth(530);
		
		contentArea.setLayoutX(350);contentArea.setLayoutY(100);
		contentArea.setText("Click on Book Please..!");
		contentArea.setMinHeight(700); contentArea.setMinWidth(1000);
		
		listOfBooks.setLayoutX(10);listOfBooks.setLayoutY(70);
		listOfBooks.setDisable(false);
		final double MAX_FONT_SIZE = 24; 
		listOfBooks.setFont(new Font(MAX_FONT_SIZE));
		listOfBooks.setTextFill(Color.web("Black"));
		vbox.setLayoutX(10);
		vbox.setLayoutY(100);
		
		listview.setPrefHeight(700);
		listview.setMinWidth(320);
		
		((Group) sc.getRoot()).getChildren().addAll(contentArea,vbox, editContent, listOfBooks, browse, filePath);
		
		stage.setTitle("Book Editor...!");
		
		stage.setScene(sc);
		stage.show();
	}

	private void EditFileContent() {
		
			Stage stage = new Stage(); // setting up the stage
			stage.setWidth(1000);
			stage.setHeight(1000);
			Scene scene = new Scene(new Group());
	
			VBox root = new VBox();
			root.setPadding(new Insets(8, 8, 8, 8));
			root.setSpacing(200);
			root.setAlignment(Pos.BOTTOM_LEFT);
			final HTMLEditor htmlEditor = new HTMLEditor();
		
			
			htmlEditor.setHtmlText(contentArea.getText());
			htmlEditor.setMinHeight(500);
//			
			Button showHTMLButton = new Button("Publish Book");
			showHTMLButton.setLayoutX(100);
			root.setAlignment(Pos.CENTER);
//			
			showHTMLButton.setOnAction((event) ->
					{
						// setting up the path as a repository folder
						File file3 = new File("PublishedBooks\\"+listview.getSelectionModel().getSelectedItem()+".html");
						try {
							// write the content of the file using buffer writer
							BufferedWriter outWriter = new BufferedWriter(new FileWriter(file3));
							System.out.println("Processing Start...!");
							//using for each loop and iterate all the content into the file
							outWriter.write(htmlEditor.getHtmlText());
							outWriter.close();
							System.out.println("Book is Published....!");
						}
						catch(Exception e) {
							System.out.println("Unable to Proceeed....!");
						}
					
					});

			root.getChildren().addAll(htmlEditor, showHTMLButton);
			scene.setRoot(root);
			stage.setScene(scene);
		    stage.show();
		
	}
	
	public void openDialo()
	{
		FileChooser fileChooser = new FileChooser(); // definaing an object for Filechooser
		 fileChooser.setInitialDirectory(thedirectory);  // set the initial directory
		    fileChooser.setTitle("Delete File...!"); // set the title of the window
		    File selectedFile = fileChooser.showOpenDialog(null);
		    filePath.setText(selectedFile.getAbsolutePath());
		   
		    if(selectedFile != null)
		    {
		    	contentArea.clear();
		    	try (BufferedReader reader = new BufferedReader(new FileReader(new File(selectedFile.getAbsolutePath())))) {

		            String line;
		            while ((line = reader.readLine()) != null)
		              contentArea.appendText(line +"\n");

		        } catch (IOException e) {
		            System.out.println("File is Empty..!");
		        }
		    }
	}
	
}
