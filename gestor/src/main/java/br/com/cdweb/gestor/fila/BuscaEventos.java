package br.com.cdweb.gestor.fila;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import br.com.cdweb.persistence.domain.FilaEventoExecutar;
import br.com.cdweb.processos.RecebeEvento;

public class BuscaEventos implements Runnable{
	private RecebeEvento<FilaEventoExecutar> encaminhar;
	private Gson gson;
	private String URL_SERVICOS = "http://localhost:8080/server/rest";
	private String PATH = "evento";
	public BuscaEventos(RecebeEvento<FilaEventoExecutar> encaminhar,String urlServicos) {
		this.encaminhar = encaminhar;
		gson = new Gson();
		this.URL_SERVICOS = urlServicos;
	}
	public BuscaEventos(RecebeEvento<FilaEventoExecutar> encaminhar) {
		this.encaminhar = encaminhar;
		gson = new Gson();
	}
	@Override
	public void run() {
		List<FilaEventoExecutar> a = buscaDadosRestfull();
		encaminhar.recebeEvento(a);		
	}
	
	private List<FilaEventoExecutar> buscaDadosRestfull(){
		List<FilaEventoExecutar> eventoExecutars =  new ArrayList<>();
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(URL_SERVICOS).path(PATH);
		 
//		Form form = new Form();
//		form.param("x", "foo");
//		form.param("y", "bar");
//		 
//		MyJAXBBean bean =
//		target.request(MediaType.APPLICATION_JSON_TYPE)
//		    .post(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED_TYPE),
//		        MyJAXBBean.class);
		Response response = target.request(MediaType.APPLICATION_JSON).get();
		String valueResp = response.readEntity(String.class);
		
		FilaEventoExecutar[] get = null;
		try {
			if(response.getStatus() == 200){
				if(!valueResp.equals("[]") ){
					get = gson.fromJson(valueResp, FilaEventoExecutar[].class);
				}				
			}
			else{
				System.out.println(String.format("[SERVICO] Serviço retornou código diferente de sucesso.(%s - %d)", (URL_SERVICOS+"/"+PATH),response.getStatus()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(get != null){
			for (FilaEventoExecutar filaEventoExecutar : get) {
				System.out.println(filaEventoExecutar.getModeloAcao().getNome());
			}
			eventoExecutars.addAll(Arrays.asList(get));
		}
		
		return eventoExecutars;
	}
}
