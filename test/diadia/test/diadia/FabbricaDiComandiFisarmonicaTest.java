package diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;

public class FabbricaDiComandiFisarmonicaTest {
	private FabbricaDiComandiFisarmonica fabbrica;
	private IO io;
	
	@Before
	public void setUp() throws Exception {
		io = new IOConsole();
		fabbrica = new FabbricaDiComandiFisarmonica(this.io);
	}

	
	/* test costruisciComando */
	@Test
	public void testCostruisciComando_VaiNord() {
		assertEquals("vai", this.fabbrica.costruisciComando("vai nord").getNome());
	}
	@Test
	public void testCostruisciComando_Aiuto() {
		assertEquals("aiuto", this.fabbrica.costruisciComando("aiuto").getNome());
	}
	@Test
	public void testCostruisciComando_Borsa() {
		assertEquals("borsa", this.fabbrica.costruisciComando("borsa").getNome());
	}

}
