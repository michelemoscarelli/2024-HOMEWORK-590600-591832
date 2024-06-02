package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public abstract class AbstractComando implements Comando {
	private IO io;

	@Override
	public abstract void esegui(Partita partita);

	@Override
	public abstract void setParametro(String parametro);

	@Override
	public abstract String getNome();
	
	@Override
	public abstract String getParametro();

	@Override
	public void setIO(IO io) {
		this.io = io;
	}

	public IO getIo() {
		return io;
	}

}
