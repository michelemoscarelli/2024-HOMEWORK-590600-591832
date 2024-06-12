package diadia;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
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
		Scanner scanner = new Scanner(System.in);
		this.io = new IOConsole(scanner);
		this.comandovai = new ComandoVai();
		labirintoBuilder = new LabirintoBuilder();
		Labirinto bilocale = labirintoBuilder.addStanzaIniziale("salotto").addStanzaVincente("cucina")
				.addAdiacenza("salotto", "cucina", Direzione.nord).addAdiacenza("cucina", "salotto", Direzione.sud).getLabirinto();
		this.partita = new Partita(bilocale);
		this.comandovai.setIO(this.io);
	}

	/* test esegui */
	@Test
	public void testEsegui_ParametroValido() {
		this.comandovai.setParametro("nord");
		this.comandovai.esegui(this.partita);
		assertEquals(this.partita.getLabirinto().getStanzaIniziale(),this.partita.getLabirinto().getStanzaCorrente().getStanzaAdiacente(Direzione.sud));
	}
	
	/* test setParametro */
	@Test
	public void testSetParametro_OK() {
		this.comandovai.setParametro("ovest");		//imposta il parametro al comando
		assertEquals("ovest",this.comandovai.getParametro());
	}

}
