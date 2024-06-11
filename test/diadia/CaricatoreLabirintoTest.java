package diadia;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;


public class CaricatoreLabirintoTest {
	private Labirinto lab;
	private String nomeFile;
	private LabirintoBuilder labirintoBuilder;
	private Labirinto labAtteso;
	
	@Before
	public void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
		this.nomeFile = new String(/*C:\\Users\\pappa\\eclipse-workspace\\2024-HOMEWORK-590600-591832-main (2).zip_expanded\\2024-HOMEWORK-590600-591832-main\\test\\diadia\\*/"a.txt");
		this.lab = new Labirinto(nomeFile);
		
		this.labirintoBuilder = new LabirintoBuilder();
		this.labAtteso = this.labirintoBuilder.addStanzaIniziale("N10").addAttrezzo("pinza", 2).addStanzaVincente("N11")
				.addStanza("biblioteca").addAttrezzo("martello", 10).addAdiacenza("biblioteca", "N10", "nord").addAdiacenza("biblioteca", "N11", "sud").getLabirinto();
		
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
