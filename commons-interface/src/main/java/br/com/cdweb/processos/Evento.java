package br.com.cdweb.processos;

public class Evento implements Comparable<Evento>{
	private long idModeloAcao;
	private long idFilaEventoExecutar;

	
	public Evento(long idModeloAcao, long idFilaEventoExecutar) {
		super();
		this.idModeloAcao = idModeloAcao;
		this.idFilaEventoExecutar = idFilaEventoExecutar;
	}	
	
	public long getIdModeloAcao() {
		return idModeloAcao;
	}
	public void setIdModeloAcao(long idModeloAcao) {
		this.idModeloAcao = idModeloAcao;
	}
	public long getIdFilaEventoExecutar() {
		return idFilaEventoExecutar;
	}
	public void setIdFilaEventoExecutar(long idFilaEventoExecutar) {
		this.idFilaEventoExecutar = idFilaEventoExecutar;
	}

	@Override
	public int compareTo(Evento o) {
		
		if(this.idFilaEventoExecutar > o.idFilaEventoExecutar)
			return 1;
		if(this.idFilaEventoExecutar < o.idFilaEventoExecutar)
			return -1;
		return 0;
	}
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Evento))
            return false;
        if (obj == this)
            return true;

        Evento evento = (Evento) obj;
         
		if(this.idFilaEventoExecutar == evento.idFilaEventoExecutar){
			return true;
		}
		return false;
	}
	
	
}
