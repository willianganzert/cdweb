package br.com.cdweb.ui;
	
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;

import br.com.cdweb.Maestro;
import br.com.cdweb.ui.custom.slidebox.SlideCheckBox;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage stage) throws URISyntaxException, FileNotFoundException {
		stage.setTitle("CDWEB Dispositivos");
		Scene scene = new Scene(new Group(), 380, 400);

		GridPane grid = new GridPane();
	    grid.setVgap(4);
	    grid.setHgap(10);
	    grid.setPadding(new Insets(5, 5, 5, 5));
	    
	    final Image imgWifiOff = new Image(new FileInputStream(new File(getClass().getClassLoader().getResource(".").toURI().getPath()+"wifi-off.png")),50,50,true,true);
	    final Image imgInternetOff = new Image(new FileInputStream(new File(getClass().getClassLoader().getResource(".").toURI().getPath()+"internet-off-3.png")),45,45,true,true);
	    
	    final Image imgWifiOn = new Image(new FileInputStream(new File(getClass().getClassLoader().getResource(".").toURI().getPath()+"wifi-on.png")),50,50,true,true);
	    final Image imgInternetOn = new Image(new FileInputStream(new File(getClass().getClassLoader().getResource(".").toURI().getPath()+"internet-on-3.png")),45,45,true,true);
	    
	    final ImageView imgViewWifi = new ImageView(imgWifiOff);
	    final ImageView imgViewInternet = new ImageView(imgInternetOff);
	    
	   	    
	    grid.add(imgViewWifi,2,1,1,1);
	    grid.add(imgViewInternet,3,1,1,1);
//	    Scene scene = new Scene(sp);
//	    primaryStage.setScene(scene);
//	    primaryStage.show();

	    
	    
	    SlideCheckBox slideCheckBoxGestorEventos = new SlideCheckBox("Gestor Eventos");
	    slideCheckBoxGestorEventos.setId("gestorEventos");
	    
	    SlideCheckBox slideCheckBoxGestorExecucao = new SlideCheckBox("Gestor Execução");
	    slideCheckBoxGestorExecucao.setId("gestorExecucao");
	    

	    SlideCheckBox slideCheckBoxGestorBuscaEventos = new SlideCheckBox("Gestor Busca Eventos");
	    slideCheckBoxGestorExecucao.setId("gestorBuscaEventos");
	    
	    
	    EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
	        @Override
	        public void handle(ActionEvent event) {
	            if (event.getSource() instanceof CheckBox) {
	                CheckBox chk = (CheckBox) event.getSource();
	                System.out.println("Action performed on checkbox " + chk.getText());
	                if ("gestorEventos".equals(chk.getId())) {
	                	if(chk.isSelected()){
	                		Maestro.INSTANCE.iniciarGestorEventos();
	                	}
	                	else{
	                		Maestro.INSTANCE.pararGestorEventos();
	                	}
	                } else if ("gestorExecucao".equals(chk.getId())) {
	                	if(chk.isSelected()){
	                		Maestro.INSTANCE.iniciarGestorExecucao();
	                	}
	                	else{
	                		Maestro.INSTANCE.pararGestorExecucao();
	                	}
	                	
	                }else if ("gestorBuscaEventos".equals(chk.getId())) {
	                	if(chk.isSelected()){
	                		Maestro.INSTANCE.iniciarGestorExecucao();
	                	}
	                	else{
	                		Maestro.INSTANCE.pararGestorExecucao();
	                	}
	                	
	                }
	            }
	        }
	    };
	    
	    slideCheckBoxGestorEventos.setOnAction(eventHandler);
	    slideCheckBoxGestorExecucao.setOnAction(eventHandler);
	    
	    grid.add(slideCheckBoxGestorEventos,1,2,1,1);
	    
	    grid.add(slideCheckBoxGestorExecucao,1,3,1,1);
	    
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
