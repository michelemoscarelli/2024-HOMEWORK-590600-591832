package diadia;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.comandi.AbstractComando;

public class AbstractComandoTest {
	
	public class fakeAbstractComando extends AbstractComando{


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

	private IOConsole io;
	private diadia.AbstractComandoTest.fakeAbstractComando fakeAbstractComando;
	
	public void setUp() throws Exception {
		this.io = new IOConsole();
		fakeAbstractComando = new fakeAbstractComando();
		//this.fakeAbstractComando.setIO(this.io);
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
