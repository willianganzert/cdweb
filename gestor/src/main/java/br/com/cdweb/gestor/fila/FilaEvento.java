package br.com.cdweb.gestor.fila;

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

	public static FilaEvento getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new FilaEvento();
		}
		return INSTANCE;
	}

	@Override
	protected List<FilaEventoExecutar> carregarLista() {
		ResultFilterVo<FilaEventoExecutar> resultFilterVo = JpaAllEntities.doFilter(FilaEventoExecutar.class,
				new FieldValuesVo("tipo", TIPO));
		return resultFilterVo.getResultQuery();
	}

	@Override
	protected boolean encaminhar(FilaEventoExecutar item) {
		JpaAllEntities.merge(item);
		item.setStatus(StatusMensagem.processando());
		JpaAllEntities.update(item);
		recebeEventoEncaminhar.recebeEvento(item);
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

	public void configurarEncaminhar(RecebeEvento<FilaEventoExecutar> recebeEvento) {
		recebeEventoEncaminhar = recebeEvento;
		configurado = true;
	}

}
