package br.com.cdweb.gestor.fila;

import java.util.List;

import br.com.cdweb.mensagens.StatusMensagem;
import br.com.cdweb.persistence.domain.FilaEventoExecutar;
import br.com.cdweb.persistence.domain.FilaEventoExecutar.TIPO;
import br.com.cdweb.persistence.jpa.JpaAllEntities;
import br.com.cdweb.persistence.vo.FieldValuesVo;
import br.com.cdweb.persistence.vo.ResultFilterVo;
import br.com.cdweb.processos.RecebeEvento;

public class FilaExecucao extends Fila<FilaEventoExecutar> implements RecebeEvento<FilaEventoExecutar>{
	private static final TIPO TIPO = FilaEventoExecutar.TIPO.EXECUCAO;
	private static FilaExecucao INSTANCE;
	private RecebeEvento<FilaEventoExecutar> recebeEventoEncaminhar;
	
	private FilaExecucao(RecebeEvento<FilaEventoExecutar> recebeEvento) {
		super(FilaEvento.class.getName());
		recebeEventoEncaminhar = recebeEvento;
	}
	
	public static FilaExecucao getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new FilaExecucao(FilaExecucao.getInstance());
		}
		return INSTANCE;
	}
	
	@Override
	protected List<FilaEventoExecutar> carregarLista() {
		ResultFilterVo<FilaEventoExecutar> resultFilterVo = JpaAllEntities.doFilter(FilaEventoExecutar.class, new FieldValuesVo("tipo", TIPO));
		return resultFilterVo.getResultQuery();
	}
	@Override
	protected boolean encaminhar(FilaEventoExecutar item) {
		recebeEventoEncaminhar.recebeEvento(item);
		return true;
	}
	@Override
	protected void gravarErro(FilaEventoExecutar item2) {
		JpaAllEntities.merge(item2);
		item2.setStatus(StatusMensagem.E);
		JpaAllEntities.update(item2);		
	}
	@Override
	protected void gravarSucesso(FilaEventoExecutar item2) {
		JpaAllEntities.merge(item2);
		item2.setStatus(StatusMensagem.K);
		JpaAllEntities.update(item2);	
	}
	@Override
	protected void adicionarImp(FilaEventoExecutar item2) {
		item2.setTipo(TIPO);
		JpaAllEntities.update(item2);	
	}
	@Override
	protected void removerImp(FilaEventoExecutar item2) {
		JpaAllEntities.delete(item2);
	}
	@Override
	public void recebeEvento(FilaEventoExecutar evento) {
		adicionar(evento);		
	}
}
