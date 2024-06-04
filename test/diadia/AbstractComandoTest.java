package diadia;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.comandi.AbstractComando;

public class AbstractComandoTest {
	
	public static class FakeAbstractComando extends AbstractComando{


		@Override
		public void esegui(Partita partita) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setParametro(String parametro) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public String getNome() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getParametro() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}

	private IO io;
	private FakeAbstractComando fakeAbstractComando;
	
	@Before
	public void setUp() throws Exception {
		this.io = new IOConsole();
		this.fakeAbstractComando = new FakeAbstractComando();
	}
	
	@Test
	public void testgetIo_null() {
		assertEquals(null,this.fakeAbstractComando.getIo());
	}
	@Test
	public void testgetIo_success() {
		this.fakeAbstractComando.setIO(this.io);
		assertEquals(this.io,this.fakeAbstractComando.getIo());
	}
}
