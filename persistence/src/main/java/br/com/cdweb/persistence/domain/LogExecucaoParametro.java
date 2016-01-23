package br.com.cdweb.persistence.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="log_execucao_parametro")
@SequenceGenerator(name="log_execucao_parametro_id_log_execucao_parametro_seq",
sequenceName="log_execucao_parametro_id_log_execucao_parametro_seq", allocationSize=1)
public class LogExecucaoParametro  extends ComunEntidades implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id    
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator="log_execucao_parametro_id_log_execucao_parametro_seq")	
    @Column(name = "id_log_execucao_parametro", updatable=false)
	private long idLogExecucaoParametro;

	@JoinColumn(name="id_fila_evento")
	private FilaEventoExecutar filaEventoExecutar;
	
	@JoinColumn(name="id_parametro")
	private Parametro parametro;

	@Column(name="valor_parametro")
	private String valorParametro;

	@Column(name="descricao_erro")
	private String descricaoErro;

	@Column(name="hora_criacao")
	@Temporal(TemporalType.TIMESTAMP)
	private Date horaCriacaoLog;
	
	
	public LogExecucaoParametro() {
	
	}
	
	
	public long getIdLogExecucaoParametro() {
		return idLogExecucaoParametro;
	}

	public void setIdLogExecucaoParametro(long idLogExecucaoParametro) {
		this.idLogExecucaoParametro = idLogExecucaoParametro;
	}

	public Parametro getParametro() {
		return parametro;
	}

	public void setParametro(Parametro parametro) {
		this.parametro = parametro;
	}

	public String getValorParametro() {
		return valorParametro;
	}

	public void setValorParametro(String valorParametro) {
		this.valorParametro = valorParametro;
	}

	public String getDescricaoErro() {
		return descricaoErro;
	}

	public void setDescricaoErro(String descricaoErro) {
		this.descricaoErro = descricaoErro;
	}

	public Date getHoraCriacaoLog() {
		return horaCriacaoLog;
	}

	public void setHoraCriacaoLog(Date horaCriacaoLog) {
		this.horaCriacaoLog = horaCriacaoLog;
	}


	public FilaEventoExecutar getFilaEventoExecutar() {
		return filaEventoExecutar;
	}


	public void setFilaEventoExecutar(FilaEventoExecutar filaEventoExecutar) {
		this.filaEventoExecutar = filaEventoExecutar;
	}	

}
