package diadia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.AbstractPersonaggio;

public class AbstractPersonaggioTest {
	
	public static class FakeAbstractPersonaggioTest extends AbstractPersonaggio{

		public FakeAbstractPersonaggioTest(String nome, String presentaz) {
			super(nome, presentaz);
		}

		@Override
		public String agisci(Partita partita) {
			return "test";
		}

		@Override
		public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
			return "test";
		}

	}
	
	private FakeAbstractPersonaggioTest FakeAbstractPersonaggioTest;
	private Partita partita;
	
	
	@Before
	public void setUp() throws Exception {
		this.FakeAbstractPersonaggioTest = new FakeAbstractPersonaggioTest("nome","presentaz");
		this.partita = new Partita(null);
	}
	
	@Test
	public void testsaluta_false() {
		assertFalse(this.FakeAbstractPersonaggioTest.haSalutato());
	}
	
	@Test
	public void testsaluta_true() {
		this.FakeAbstractPersonaggioTest.saluta();
		assertTrue(this.FakeAbstractPersonaggioTest.haSalutato());
	}
	@Test
	public void testsagisci() {
		assertEquals("test",this.FakeAbstractPersonaggioTest.agisci(partita));
	}
	
	@Test
	public void testriceviRegalo() {
		assertEquals("test",this.FakeAbstractPersonaggioTest.riceviRegalo(null,partita));
	}


}
