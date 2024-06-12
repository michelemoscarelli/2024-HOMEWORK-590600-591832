package diadia;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;


public class CaricatoreLabirintoTest {
	private Labirinto lab;
	private String nomeFile;
	private LabirintoBuilder labirintoBuilder;
	private Labirinto labAtteso;
	
	@Before
	public void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
		this.nomeFile = new String("a.txt");
		this.lab =Labirinto.newBuilder(nomeFile).getLabirinto();
		
		this.labirintoBuilder = new LabirintoBuilder("pizza");
		this.labAtteso = this.labirintoBuilder.addStanzaIniziale("N10").addAttrezzo("pinza", 2).addStanzaVincente("N11")
				.addStanza("biblioteca").addAttrezzo("martello", 10).addAdiacenza("biblioteca", "N10", Direzione.nord).addAdiacenza("biblioteca", "N11", Direzione.sud).getLabirinto();
		
	}
	
	@Test
	public void testCarica() {
		assertEquals(lab.getStanzaIniziale().getDescrizione(),labAtteso.getStanzaIniziale().getDescrizione());
		assertEquals(lab.getStanzaIniziale().getAttrezzi().get(0),labAtteso.getStanzaIniziale().getAttrezzi().get(0));
		assertEquals(lab.getStanzaIniziale().getDirezioni().size(),labAtteso.getStanzaIniziale().getDirezioni().size());
		assertEquals(lab.getStanzaVincente().getDescrizione(),labAtteso.getStanzaVincente().getDescrizione());
		
		
		//assertEquals(labAtteso,lab);
	}

}
