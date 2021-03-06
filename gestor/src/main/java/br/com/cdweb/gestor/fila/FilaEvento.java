package br.com.cdweb.gestor.fila;

import java.util.Date;
import java.util.List;

import br.com.cdweb.mensagens.StatusMensagem;
import br.com.cdweb.persistence.domain.FilaEventoExecutar;
import br.com.cdweb.persistence.domain.FilaEventoExecutar.TIPO;
import br.com.cdweb.persistence.jpa.JpaAllEntities;
import br.com.cdweb.persistence.vo.FieldValuesVo;
import br.com.cdweb.persistence.vo.ResultFilterVo;
import br.com.cdweb.processos.RecebeEvento;

public class FilaEvento extends Fila<FilaEventoExecutar> implements RecebeEvento<FilaEventoExecutar> {
	private static final TIPO TIPO = FilaEventoExecutar.TIPO.EVENTO;
	private static FilaEvento INSTANCE;
	private RecebeEvento<FilaEventoExecutar> recebeEventoEncaminhar;

	private FilaEvento() {
		super(FilaEvento.class.getName());
	}

	public static FilaEvento getInstance( RecebeEvento<FilaEventoExecutar> recebeEvento) {
		if (INSTANCE == null) {
			INSTANCE = new FilaEvento();
		}
		INSTANCE.configurarEncaminhar(recebeEvento);
		return INSTANCE;
	}

	@Override
	protected List<FilaEventoExecutar> carregarLista() {
		ResultFilterVo<FilaEventoExecutar> resultFilterVo = JpaAllEntities.doFilter(FilaEventoExecutar.class,
				new FieldValuesVo("tipo", TIPO),new FieldValuesVo("status", StatusMensagem.pendente()));
		return resultFilterVo.getResultQuery();
	}

	@Override
	protected boolean encaminhar(FilaEventoExecutar item) {
		FilaEventoExecutar itemClone = (FilaEventoExecutar) item.clone();
//		itemClone.setIdFilaEventoExecutar(0);
		itemClone.setHoraInsercaoFila(null);
		itemClone.setHoraExecucaoEvento(null);
		recebeEventoEncaminhar.recebeEvento(itemClone);
		return true;
	}

	@Override
	protected void gravarErro(FilaEventoExecutar item) {
		JpaAllEntities.merge(item);
		item.setStatus(StatusMensagem.erro());
		JpaAllEntities.update(item);
	}

	@Override
	protected void gravarSucesso(FilaEventoExecutar item) {
		JpaAllEntities.merge(item);
		item.setStatus(StatusMensagem.processada());
		JpaAllEntities.update(item);
	}

	@Override
	protected void adicionarImp(FilaEventoExecutar item2) {
		item2.setIdFilaEventoExecutarOrigem(item2.getIdFilaEventoExecutar());
		item2.setIdFilaEventoExecutar(0);
		item2.setTipo(TIPO);
		item2.setStatus(StatusMensagem.pendente());
		item2.setHoraInsercaoFila(new Date());
		item2.setNumeroTentativa(0);
		JpaAllEntities.insertOrUpdate(item2);
	}
	
	@Override
	protected void adicionarImp(List<FilaEventoExecutar> item2) {
		for (FilaEventoExecutar filaEventoExecutar : item2) {
			filaEventoExecutar.setIdFilaEventoExecutarOrigem(filaEventoExecutar.getIdFilaEventoExecutar());
			filaEventoExecutar.setIdFilaEventoExecutar(0);
			filaEventoExecutar.setTipo(TIPO);
			filaEventoExecutar.setStatus(StatusMensagem.pendente());
			filaEventoExecutar.setHoraInsercaoFila(new Date());
			filaEventoExecutar.setNumeroTentativa(0);
		}
		JpaAllEntities.insertOrUpdate(item2.toArray(new FilaEventoExecutar[0]));		
	}

	@Override
	protected void removerImp(FilaEventoExecutar item2) {
//		JpaAllEntities.delete(item2);
	}

	@Override
	public void recebeEvento(FilaEventoExecutar evento) {
		adicionar(evento);
	}

	public void configurarEncaminhar(RecebeEvento<FilaEventoExecutar> recebeEvento) {
		recebeEventoEncaminhar = recebeEvento;
		configurado = true;
	}

	@Override
	public void recebeEvento(List<FilaEventoExecutar> eventos) {
		adicionar(eventos);		
	}
	
	@Override
	protected void emExecucao(FilaEventoExecutar item2) {
		JpaAllEntities.merge(item2);
		item2.setNumeroTentativa(item2.getNumeroTentativa()+1);
		item2.setHoraExecucaoEvento(new Date());
		item2.setStatus(StatusMensagem.processando());
		JpaAllEntities.update(item2);		
	}
	

}
