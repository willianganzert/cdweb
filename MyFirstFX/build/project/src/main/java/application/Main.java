package application;
	
import eu.hansolo.fx.SlideCheckBox;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage stage) {
		stage.setTitle("ComboBoxSample");
	    
	    Button button = new Button("Send");
	    Label notification = new Label();
	    TextField subject = new TextField("");
	    TextArea text = new TextArea("");
	    
	    Scene scene = new Scene(new Group(), 450, 250);

	    ComboBox emailComboBox = new ComboBox();
	    ComboBox priorityComboBox = new ComboBox();

	    GridPane grid = new GridPane();
	    grid.setVgap(4);
	    grid.setHgap(10);
	    grid.setPadding(new Insets(5, 5, 5, 5));
//	    grid.add(new Label("To: "), 0, 0);
//	    grid.add(emailComboBox, 1, 0);
//	    grid.add(new Label("Priority: "), 2, 0);
//	    grid.add(priorityComboBox, 3, 0);
//	    
//	    grid.add(new Label("Label: "), 4, 0);
//	    grid.add(new Label("Value"), 5, 0);
//	    
//	    
//	    grid.add(new Label("Subject: "), 0, 1);
//	    grid.add(subject, 1, 1, 3, 1);
//	    grid.add(text, 0, 2, 4, 1);
//	    grid.add(button, 0, 3);
//	    grid.add(notification, 1, 3, 3, 1);
	    
	    grid.add(new SlideCheckBox("Gestor: "), 0, 5,2,0);
	    
	    Group root = (Group) scene.getRoot();
	    root.getChildren().add(grid);
	    stage.setScene(scene);
	    stage.setHeight(600);
	    stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
