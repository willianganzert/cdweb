package application;
	
import eu.hansolo.fx.SlideCheckBox;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage stage) {
//		stage.initStyle(StageStyle.TRANSPARENT);

		stage.setTitle("CDWEB Dispositivos");
	    
//	    Button button = new Button("Send");
//	    Label notification = new Label();
//	    TextField subject = new TextField("");
//	    TextArea text = new TextArea("");
//	    
	    Scene scene = new Scene(new Group(), 350, 400);

//	    ComboBox emailComboBox = new ComboBox();
//	    ComboBox priorityComboBox = new ComboBox();

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
	    SlideCheckBox slideCheckBoxGestorEventos = new SlideCheckBox("Gestor Eventos");
	    slideCheckBoxGestorEventos.setId("gestorEventos");
	    
	    SlideCheckBox slideCheckBoxGestorExecucao = new SlideCheckBox("Gestor Execução");
	    slideCheckBoxGestorExecucao.setId("gestorExecucao");
	    
	    
	    EventHandler eventHandler = new EventHandler<ActionEvent>() {
	        @Override
	        public void handle(ActionEvent event) {
	            if (event.getSource() instanceof CheckBox) {
	                CheckBox chk = (CheckBox) event.getSource();
	                System.out.println("Action performed on checkbox " + chk.getText());
	                if ("gestorEventos".equals(chk.getId())) {
	                	System.out.println("EVT1");
	                } else if ("gestorExecucao".equals(chk.getId())) {
	                	System.out.println("EVT2");
	                }
	            }
	        }
	    };
	    
	    slideCheckBoxGestorEventos.setOnAction(eventHandler);
	    slideCheckBoxGestorExecucao.setOnAction(eventHandler);
	    
	    grid.add(slideCheckBoxGestorEventos,1,1,1,1);
	    
	    grid.add(slideCheckBoxGestorExecucao,1,2,1,1);
	    
	    Group root = (Group) scene.getRoot();
	    root.getChildren().add(grid);
	    stage.setScene(scene);
	    stage.setHeight(600);
	    stage.setHeight(400);
	    stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
