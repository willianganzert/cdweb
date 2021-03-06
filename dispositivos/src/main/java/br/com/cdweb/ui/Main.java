package br.com.cdweb.ui;
	
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;

import br.com.cdweb.Maestro;
import br.com.cdweb.dispositivos.configuracoes.Configuracoes;
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
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage stage) throws URISyntaxException, FileNotFoundException {
		iniciarProcessos();
		stage.setTitle("CDWEB Dispositivos");
		Scene scene = new Scene(new Group(), 450, 400);

		GridPane grid = new GridPane();
	    grid.setVgap(4);
	    grid.setHgap(10);
	    grid.setPadding(new Insets(5, 5, 5, 5));
	    
	    final Image imgWifiOff = new Image(new FileInputStream(new File(getClass().getClassLoader().getResource(".").toURI().getPath()+"wifi-off.png")),50,50,true,true);
	    final Image imgWEBOff = new Image(new FileInputStream(new File(getClass().getClassLoader().getResource(".").toURI().getPath()+"internet-off-3.png")),45,45,true,true);
	    
	    final Image imgWifiOn = new Image(new FileInputStream(new File(getClass().getClassLoader().getResource(".").toURI().getPath()+"wifi-on.png")),50,50,true,true);
	    final Image imgWEBOn = new Image(new FileInputStream(new File(getClass().getClassLoader().getResource(".").toURI().getPath()+"internet-on-3.png")),45,45,true,true);
	    
	    final ImageView imgViewWifi = new ImageView(imgWifiOff);
	    final ImageView imgViewWEB = new ImageView(imgWEBOff);
	    
	   	    
	    grid.add(imgViewWifi,2,1,1,1);
	    grid.add(imgViewWEB,3,1,1,1);
//	    Scene scene = new Scene(sp);
//	    primaryStage.setScene(scene);
//	    primaryStage.show();

	    
	    
	    SlideCheckBox slideCheckBoxGestorEventos = new SlideCheckBox("Gestor Eventos");
	    slideCheckBoxGestorEventos.setId("gestorEventos");
	    
	    SlideCheckBox slideCheckBoxGestorExecucao = new SlideCheckBox("Gestor Execução");
	    slideCheckBoxGestorExecucao.setId("gestorExecucao");
	    

	    final SlideCheckBox slideCheckBoxGestorBuscaEventos = new SlideCheckBox("Gestor Busca Eventos");
	    slideCheckBoxGestorBuscaEventos.setId("gestorBuscaEventos");
	    slideCheckBoxGestorBuscaEventos.setDisable(true);
	    
	    final SlideCheckBox slideCheckBoxGestorBuscaEventosExterno = new SlideCheckBox("Gestor Busca Eventos Externo");
	    slideCheckBoxGestorBuscaEventosExterno.setId("gestorBuscaEventosExterno");
	    slideCheckBoxGestorBuscaEventosExterno.setDisable(true);
	    
	    
	    
	    new Thread(){
	    	private int imgWIFI = 0;
	    	private int imgWEB = 0;
	    	public void run() {
	    		while (true) {				
		    		try {
						sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    		switch (Configuracoes.INSTANCE.getConexaoServerWIFI()) {
					case CONECTADO:
						if(imgWIFI != 1){
							imgViewWifi.setImage(imgWifiOn);
							imgWIFI = 1;
							slideCheckBoxGestorBuscaEventos.setDisable(false);
						}
						break;
					case CONECTANDO:
						//invert
						imgWIFI = imgWIFI == 0?++imgWIFI:--imgWIFI;
						imgViewWifi.setImage(imgWIFI==0?imgWifiOff:imgWifiOn);
						
						break;
					default:
						if(imgWIFI != 0){
							imgViewWifi.setImage(imgWifiOff);
							imgWIFI = 0;
							slideCheckBoxGestorBuscaEventos.setDisable(true);
						}
						break;
					}
		    		
		    		switch (Configuracoes.INSTANCE.getConexaoServerWEB()) {
					case CONECTADO:
						if(imgWEB != 1){
							imgViewWEB.setImage(imgWEBOn);
							imgWEB = 1;
							slideCheckBoxGestorBuscaEventosExterno.setDisable(false);
						}
						break;
					case CONECTANDO:
						//invert
						imgWEB = imgWEB == 0?++imgWEB:--imgWEB;
						imgViewWEB.setImage(imgWEB==0?imgWEBOff:imgWEBOn);
						
						break;
					default:
						if(imgWEB != 0){
							imgViewWEB.setImage(imgWEBOff);
							imgWEB = 0;
							slideCheckBoxGestorBuscaEventosExterno.setDisable(true);
						}
						break;
					}
	    		}
	    	};
	    }.start();
	    
	    
	    
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
	                		Maestro.INSTANCE.iniciarGestorBuscaEventos();;
	                	}
	                	else{
	                		Maestro.INSTANCE.pararGestorBuscaEventos();
	                	}
	                	
	                }
	                else if ("gestorBuscaEventosExterno".equals(chk.getId())) {
	                	if(chk.isSelected()){
	                		Maestro.INSTANCE.iniciarGestorBuscaEventosExterno();
	                	}
	                	else{
	                		Maestro.INSTANCE.pararGestorBuscaEventosExterno();
	                	}
	                	
	                }
	                
	                
	            }
	        }
	    };
	    slideCheckBoxGestorBuscaEventosExterno.setOnAction(eventHandler);
	    slideCheckBoxGestorBuscaEventos.setOnAction(eventHandler);
	    slideCheckBoxGestorEventos.setOnAction(eventHandler);
	    slideCheckBoxGestorExecucao.setOnAction(eventHandler);
	    
	    
	    
	    
	    grid.add(slideCheckBoxGestorBuscaEventosExterno,1,2,1,1);
	    
	    grid.add(slideCheckBoxGestorBuscaEventos,1,3,1,1);
	    
	    grid.add(slideCheckBoxGestorEventos,1,4,1,1);
	    
	    grid.add(slideCheckBoxGestorExecucao,1,5,1,1);
	    
	    Group root = (Group) scene.getRoot();
	    root.getChildren().add(grid);
	    stage.setScene(scene);
	    stage.setHeight(600);
	    stage.setHeight(400);
	    stage.show();
	}
	
	private void iniciarProcessos() {
		Maestro.INSTANCE.iniciarControleStatusConexao();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
