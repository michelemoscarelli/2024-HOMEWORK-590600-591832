package diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;

public class FabbricaDiComandiRiflessivaTest {
	private FabbricaDiComandiRiflessiva fabbrica;
	private IO io;
	
	@Before
	public void setUp() throws Exception {
		io = new IOConsole();
		fabbrica = new FabbricaDiComandiRiflessiva(this.io);
	}

	
	/* test costruisciComando */
	@Test
	public void testCostruisciComando_VaiNord() throws Exception {
		assertEquals("vai", this.fabbrica.costruisciComando("vai nord").getNome());
	}
	@Test
	public void testCostruisciComando_Aiuto() throws Exception{
		assertEquals("aiuto", this.fabbrica.costruisciComando("aiuto").getNome());
	}
	@Test
	public void testCostruisciComando_Borsa() throws Exception{
		assertEquals("borsa", this.fabbrica.costruisciComando("borsa").getNome());
	}

}
