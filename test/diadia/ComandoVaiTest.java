package diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.comandi.ComandoVai;

public class ComandoVaiTest {
	private ComandoVai comandovai;
	private Partita partita;
	private LabirintoBuilder labirintoBuilder;
	private IO io;

	@Before
	public void setUp() throws Exception {
		this.io = new IOConsole();
		this.comandovai = new ComandoVai();
		Labirinto bilocale = labirintoBuilder.addStanzaIniziale("salotto").addStanzaVincente("cucina")
				.addAdiacenza("salotto", "cucina", "nord").addAdiacenza("cucina", "salotto", "sud").getLabirinto();
		this.partita = new Partita(bilocale);
		this.comandovai.setIO(this.io);
	}

	/* test esegui */
	@Test
	public void testEsegui_ParametroValido() {
		this.comandovai.setParametro("nord");
		this.comandovai.esegui(this.partita);
		assertEquals(this.partita.getLabirinto().getStanzaCorrente(),this.partita.getLabirinto().getStanzaCorrente().getStanzaAdiacente("sud"));
	}
	
	@Test
	public void testEsegui_ParametroNonValidoNullo() {
		this.comandovai.setParametro("pippo");
		this.comandovai.esegui(this.partita);
		assertEquals(this.partita.getLabirinto().getEntrata(),this.partita.getLabirinto().getStanzaCorrente());
	}
	
	
	/* test setParametro */
	@Test
	public void testSetParametro_OK() {
		this.comandovai.setParametro("ovest");		//imposta il parametro al comando
		assertEquals("ovest",this.comandovai.getParametro());
	}
	
	@Test
	public void testSetParametro_Null() {
		this.comandovai.setParametro(null);		//imposta il parametro al comando
		assertNull(this.comandovai.getParametro());
	}
}
