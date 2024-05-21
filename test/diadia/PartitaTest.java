package diadia;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;

public class PartitaTest {
	private Partita partita;
	private LabirintoBuilder labirintoBuilder;
	
	@Before
	public void setUp() {
		
		labirintoBuilder = new LabirintoBuilder();
		Labirinto bilocale = labirintoBuilder.addStanzaIniziale("salotto").addStanzaVincente("cucina")
				.addAdiacenza("salotto", "cucina", "nord").addAdiacenza("cucina", "salotto", "sud").getLabirinto();
		this.partita = new Partita(bilocale);
	}
	
	/* test isFinita */
	@Test
	public void testIsFinita_CfuMaggioriDiZero() {
		this.partita.setFinita();
		this.partita.getLabirinto().setStanzaCorrente(this.partita.getLabirinto().getStanzaVincente());
		assertTrue(this.partita.isFinita());
	}
	
	@Test
	public void testIsFinita_NonVinta() {
		this.partita.setFinita();
		this.partita.getGiocatore().setCfu(0);
		this.partita.getLabirinto().setStanzaCorrente(this.partita.getLabirinto().getStanzaCorrente());
		assertTrue(this.partita.isFinita());
	}
	
	@Test
	public void testIsFinita_NonFinita() {
		assertFalse(this.partita.isFinita());
	}

}
