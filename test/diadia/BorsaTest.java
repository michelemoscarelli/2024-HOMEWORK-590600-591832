package diadia;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Set;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class BorsaTest {
	private static final int PESO_BORSA_IMPOSTATO = 6;
	
	private Borsa borsa;
	private Borsa borsa_peso_impostato;
	private Attrezzo attrezzo;
	private Attrezzo attrezzo_pesante;
	
	private Attrezzo a0;
	private Attrezzo a1;
	private Attrezzo a2;
	private Attrezzo a3;
	private ArrayList<Attrezzo> ord_peso_attrezzi;

	private TreeSet<Attrezzo> ord_nome_attrezzi;
	
	private Map<Integer, Set<Attrezzo>> mappa_peso_attrezzi;
	private TreeSet<Attrezzo> traboccoA;
	private TreeSet<Attrezzo> traboccoB;
	private TreeSet<Attrezzo> traboccoC;
	
	private TreeSet<Attrezzo> set_peso_attrezzi;
	
	@Before
	public void setUp() {
		this.borsa = new Borsa();
		this.borsa_peso_impostato = new Borsa(PESO_BORSA_IMPOSTATO);
		this.attrezzo = new Attrezzo("attrezzo", 0);
		this.attrezzo_pesante = new Attrezzo("attrezzo_pesante", 2);
		
		this.a0 = new Attrezzo("piombo", 5);
		this.a1 = new Attrezzo("ps", 2);
		this.a2 = new Attrezzo("piuma", 1);
		this.a3 = new Attrezzo("libro", 2);
		
		this.ord_peso_attrezzi = new ArrayList<Attrezzo>();
		this.ord_peso_attrezzi.add(a2);
		this.ord_peso_attrezzi.add(a3);
		this.ord_peso_attrezzi.add(a1);
		this.ord_peso_attrezzi.add(a0);
		
		this.ord_nome_attrezzi = new TreeSet<Attrezzo>();
		this.ord_nome_attrezzi.add(a3);
		this.ord_nome_attrezzi.add(a0);
		this.ord_nome_attrezzi.add(a2);
		this.ord_nome_attrezzi.add(a1);
		
		this.mappa_peso_attrezzi = new TreeMap<>();
		this.traboccoA = new TreeSet<>();
		this.traboccoA.add(a2);
		this.traboccoB = new TreeSet<>();
		this.traboccoB.add(a1);
		this.traboccoB.add(a3);
		this.traboccoC = new TreeSet<>();
		this.traboccoC.add(a0);
		this.mappa_peso_attrezzi.put(a2.getPeso(),traboccoA);
		this.mappa_peso_attrezzi.put(a1.getPeso(),traboccoB);
		this.mappa_peso_attrezzi.put(a0.getPeso(),traboccoC);
		
		this.set_peso_attrezzi = new TreeSet<Attrezzo>();
		this.set_peso_attrezzi.add(a2);
		this.set_peso_attrezzi.add(a3);
		this.set_peso_attrezzi.add(a1);
		this.set_peso_attrezzi.add(a0);
	}

	
	
	
	
	/* test addAttrezzo */
	@Test
	public void testAddAttrezzo_BuonFine() {
		assertTrue(this.borsa.addAttrezzo(this.attrezzo));
	}
	
	@Test
	public void testAddAttrezzo_PesoMassimoDefault() {
		for (int i=0; i<5; i++)		// 5*2(peso di attrezzo_pesante)=10, peso massimo di Borsa di default
			this.borsa.addAttrezzo(this.attrezzo_pesante);
		assertFalse(borsa.addAttrezzo(this.attrezzo_pesante));
	}
	@Test
	public void testAddAttrezzo_PesoMassimoArbitrario() {
		for (int i=0; i<5; i++)
			this.borsa_peso_impostato.addAttrezzo(this.attrezzo_pesante);
		assertFalse(borsa_peso_impostato.addAttrezzo(this.attrezzo_pesante));
	}
	
	@Test
	public void testAddAttrezzo_Null() {
		assertFalse(borsa.addAttrezzo(null));
	}
	
	
	
	/* test getAttrezzo */
	@Test
	public void testGetAttrezzo_Null() {
		assertNull(this.borsa.getAttrezzo(null));
	}
	
	@Test
	public void testGetAttrezzo_Inesistente() {
		assertNull(this.borsa.getAttrezzo("attrezzo_inesistente"));	
	}
	
	@Test
	public void testGetAttrezzo_Presente() {
		this.borsa.addAttrezzo(this.attrezzo);
		assertEquals(this.attrezzo, this.borsa.getAttrezzo("attrezzo"));
	}
	
	
	
	/* test getContenutoOrdinatoPerPeso */
	@Test
	public void testGetContenutoOrdinatoPerPeso_NessunAttrezzoInBorsa() {
		// ordino la lista della borsa
		List<Attrezzo> restituito = this.borsa.getContenutoOrdinatoPerPeso();
		// verifico che la lista di attrezzi della borsa sia uguale alla lista "ord_attrezzi"
		//assertEquals(,restituito);
	}
	
	@Test
	public void testGetContenutoOrdinatoPerPeso_4AttrezziInBorsa() {
		// aggiungo attrezzi alla borsa
		this.borsa.addAttrezzo(a0);
		this.borsa.addAttrezzo(a1);
		this.borsa.addAttrezzo(a2);
		this.borsa.addAttrezzo(a3);
		// ottengo la lista degli attrezzi in borsa ordinati per peso
		List<Attrezzo> restituito = this.borsa.getContenutoOrdinatoPerPeso();
		// verifico che la lista di attrezzi della borsa sia uguale alla lista "ord_peso_attrezzi"
		assertEquals(this.ord_peso_attrezzi,restituito);
	}
	
	
	
	/* test getContenutoOrdinatoPerNome */
	@Test
	public void testGetContenutoOrdinatoPerNome_4AttrezziInBorsa() {
		// aggiungo attrezzi alla borsa
		this.borsa.addAttrezzo(a0);
		this.borsa.addAttrezzo(a1);
		this.borsa.addAttrezzo(a2);
		this.borsa.addAttrezzo(a3);
		// ottengo l'insieme degli attrezzi in borsa ordinati per nome
		SortedSet<Attrezzo> restituito = this.borsa.getContenutoOrdinatoPerNome();
		// verifico che l'insieme di attrezzi della borsa sia uguale alla lista "ord_nome_attrezzi"
		assertEquals(this.ord_nome_attrezzi,restituito);
	}
	
	
	
	/* test getContenutoRaggruppatoPerPeso */
	@Test
	public void testGetContenutoRaggruppatoPerPeso_4AttrezziInBorsa() {
		// aggiungo attrezzi alla borsa
		this.borsa.addAttrezzo(a0);
		this.borsa.addAttrezzo(a1);
		this.borsa.addAttrezzo(a2);
		this.borsa.addAttrezzo(a3);
		// ottengo la mappa gli attrezzi in borsa raggruppati per peso
		Map<Integer, Set<Attrezzo>> restituito = this.borsa.getContenutoRaggruppatoPerPeso();
		// verifico che la mappa di attrezzi della borsa sia uguale alla mappa "mappa_peso_attrezzi"
		assertEquals(this.mappa_peso_attrezzi,restituito);
	}
	
	
	
	/* test getSortedSetOrdinatoPerPeso */
	@Test
	public void testGetSortedSetOrdinatoPerPeso_4AttrezziInBorsa() {
		// aggiungo attrezzi alla borsa
		this.borsa.addAttrezzo(a0);
		this.borsa.addAttrezzo(a1);
		this.borsa.addAttrezzo(a2);
		this.borsa.addAttrezzo(a3);
		// ottengo l'insieme degli attrezzi in borsa ordinati per nome
		SortedSet<Attrezzo> restituito = this.borsa.getSortedSetOrdinatoPerPeso();
		// verifico che l'insieme di attrezzi della borsa sia uguale alla lista "set_peso_attrezzi"
		assertEquals(this.set_peso_attrezzi,restituito);
	}
	
}
