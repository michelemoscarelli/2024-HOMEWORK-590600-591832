package diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoPrendi;
import it.uniroma3.diadia.comandi.ComandoVai;

public class ComandoVaiTest {
	private ComandoVai comandovai;
	private Partita partita;

	@Before
	public void setUp() throws Exception {
		this.comandovai = new ComandoVai();
		this.partita = new Partita();
	}

	/* test esegui */
	@Test
	public void testEsegui_ParametroValido() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testEsegui_ParametroNonValido() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testEsegui_ParametroNullo() {
		fail("Not yet implemented");
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
