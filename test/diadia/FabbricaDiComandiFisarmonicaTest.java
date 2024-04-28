package diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.ComandoVai;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;

public class FabbricaDiComandiFisarmonicaTest {
	private FabbricaDiComandiFisarmonica fabbrica;
	private Comando atteso;
	
	@Before
	public void setUp() throws Exception {
		fabbrica = new FabbricaDiComandiFisarmonica();
	}

	
	/* test costruisciComando */
	@Test
	public void testCostruisciComando_VaiNord() {
		atteso = new ComandoVai();
		assertEquals(atteso.getNome(), this.fabbrica.costruisciComando("vai nord").getNome());
	}

}
