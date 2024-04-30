package diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoPrendi;
import it.uniroma3.diadia.comandi.ComandoVai;

public class ComandoVaiTest {
	private ComandoVai comandovai;
	private Partita partita;
	private Stanza primastanza;
	private IO io;

	@Before
	public void setUp() throws Exception {
		this.io = new IOConsole();
		this.comandovai = new ComandoVai();
		this.partita = new Partita();
		this.comandovai.setIO(this.io);
		this.primastanza = this.partita.getLabirinto().getStanzaCorrente();
	}

	/* test esegui */
	@Test
	public void testEsegui_ParametroValido() {
		this.comandovai.setParametro("sud");
		this.comandovai.esegui(this.partita);
		assertEquals(this.primastanza,this.partita.getLabirinto().getStanzaCorrente().getStanzaAdiacente("nord"));
	}
	
	@Test
	public void testEsegui_ParametroNonValidoNullo() {
		this.comandovai.setParametro("pippo");
		this.comandovai.esegui(this.partita);
		assertEquals(this.primastanza,this.partita.getLabirinto().getStanzaCorrente());
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
