package it.uniroma3.diadia;

import java.io.*;
import java.util.*;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.AbstractPersonaggio;
import it.uniroma3.diadia.giocatore.Cane;
import it.uniroma3.diadia.giocatore.Mago;
import it.uniroma3.diadia.giocatore.Strega;

public class CaricatoreLabirinto {

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_MARKER = "Stanze:";             

	/* prefisso di una singola riga contenente il nome della stanza iniziale */
	private static final String STANZA_INIZIALE_MARKER = "Inizio:";    

	/* prefisso della riga contenente il nome stanza vincente */
	private static final String STANZA_VINCENTE_MARKER = "Vincente:";  

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeAttrezzo> <peso> <nomeStanza> */
	private static final String ATTREZZI_MARKER = "Attrezzi:";

	/* prefisso della riga contenente le specifiche dei collegamenti tra stanza nel formato <nomeStanzaDa> <direzione> <nomeStanzaA> */
	private static final String USCITE_MARKER = "Uscite:";

	private static final String STANZE_BUIE_MARKER = "Stanze Buie:";

	private static final String STANZE_BLOCCATE_MARKER = "Stanze Bloccate:";

	private static final String STANZE_MAGICHE_MARKER = "Stanze Magiche:";

	private static final String CANE_MARKER = "Cane:";

	private static final String MAGO_MARKER = "Mago:";

	private static final String STREGA_MARKER = "Strega:";

	/*
	 *  Esempio di un possibile file di specifica di un labirinto (vedi POO-26-eccezioni-file.pdf)

		Stanze: biblioteca, N10, N11
		Inizio: N10
		Vincente: N11
		Attrezzi: martello 10 biblioteca, pinza 2 N10
		Uscite: biblioteca nord N10, biblioteca sud N11

	 */
	private LineNumberReader reader;

	private Map<String, Stanza> nome2stanza;

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;


	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		this.nome2stanza = new HashMap<String,Stanza>();
		this.reader = new LineNumberReader(new FileReader(nomeFile));
	}

	public void carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			this.leggiECreaStanzeBuie();
			this.leggiECreaStanzeBloccate();
			this.leggiECreaStanzeMagiche();
			this.leggiInizialeEvincente();
			this.leggiECollocaAttrezzi();
			this.leggiECollocaCane();
			this.leggiECollocaStrega();
			this.leggiECollocaMago();
			this.leggiEImpostaUscite();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}

	private void leggiECollocaMago() throws FormatoFileNonValidoException {
		String specificaMago = this.leggiRigaCheCominciaPer(MAGO_MARKER);

		//for(String specificaAttrezzo : separaStringheAlleVirgole(specificaCane)) {
		String nomeStanza = null; 
		String nomeMago = null;
		String presentazione = null;
		String nomeAttrezzo = null;
		String pesoAttrezzo = null;
		try (Scanner scannerLinea = new Scanner(specificaMago)) {
			while (scannerLinea.hasNext()) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome del mago."));
				nomeMago = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la presentazione del mago."));
				presentazione = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome dell'attrezzo che hal mago."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare il mago "+nomeMago+"."));
				nomeStanza = scannerLinea.next();
			}	
			AbstractPersonaggio mago = new Mago(nomeMago, presentazione, new Attrezzo(nomeAttrezzo, Integer.parseInt(pesoAttrezzo)));
			this.nome2stanza.get(nomeStanza).setPersonaggio(mago);
		}
		//}
	}

	private void leggiECollocaStrega() throws FormatoFileNonValidoException {
		String specificaStrega = this.leggiRigaCheCominciaPer(STREGA_MARKER);

		//for(String specificaAttrezzo : separaStringheAlleVirgole(specificaCane)) {
		String nomeStanza = null; 
		String nomeStrega = null;
		String presentazione = null;
		try (Scanner scannerLinea = new Scanner(specificaStrega)) {
			while (scannerLinea.hasNext()) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della strega."));
				nomeStrega = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la presentazione della strega."));
				presentazione = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare la strega "+nomeStrega+"."));
				nomeStanza = scannerLinea.next();
			}	
			AbstractPersonaggio strega = new Strega(nomeStrega, presentazione);
			this.nome2stanza.get(nomeStanza).setPersonaggio(strega);
		}
		
		//}
	}

	private void leggiECollocaCane() throws FormatoFileNonValidoException {
		String specificaCane = this.leggiRigaCheCominciaPer(CANE_MARKER);

		//for(String specificaAttrezzo : separaStringheAlleVirgole(specificaCane)) {
		String nomeStanza = null; 
		String nomeCane = null;
		String presentazione = null;
		String nomeCiboPreferito = null;
		String nomeAttrezzoTraIDenti = null;
		String pesoAttrezzoTraIDenti = null;
		String pesoCiboPreferito = null;
		try (Scanner scannerLinea = new Scanner(specificaCane)) {
			while (scannerLinea.hasNext()) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome del cane."));
				nomeCane = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la presentazione del cane."));
				presentazione = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome del cibo preferito."));
				nomeCiboPreferito = scannerLinea.next();
//				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeCiboPreferito+"."));
//				pesoCiboPreferito = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome dell'attrezzo tra i denti."));
				nomeAttrezzoTraIDenti = scannerLinea.next();
//				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzoTraIDenti+"."));
//				pesoAttrezzoTraIDenti = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare il cane "+nomeCane+"."));
				nomeStanza = scannerLinea.next();
			}		
			AbstractPersonaggio cane = new Cane(nomeCane, presentazione, 
					new Attrezzo(nomeCiboPreferito, 3), new Attrezzo(nomeAttrezzoTraIDenti, 2));
			this.nome2stanza.get(nomeStanza).setPersonaggio(cane);
		}
		//}

	}

	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			check(riga.startsWith(marker),"era attesa una riga che cominciasse per "+marker);
			return riga.substring(marker.length());	
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	private void leggiECreaStanze() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			Stanza stanza = new Stanza(nomeStanza);
			this.nome2stanza.put(nomeStanza, stanza);
		}
	}

	private void leggiECreaStanzeBuie() throws FormatoFileNonValidoException {

		String specificheStanzeBuie = this.leggiRigaCheCominciaPer(STANZE_BUIE_MARKER);
		for(String specificaStanzaBuia : separaStringheAlleVirgole(specificheStanzeBuie)) {
			String nomeStanza = null;
			String attrezzoIlluminante = null;
			try (Scanner scannerLinea = new Scanner(specificaStanzaBuia)) {

				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza buia"));
				nomeStanza = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome dell'attrezzo illuminante."));
				attrezzoIlluminante = scannerLinea.next();
			}
			Stanza stanza = new StanzaBuia(nomeStanza,attrezzoIlluminante);
			this.nome2stanza.put(nomeStanza, stanza);
		}

	}

	private void leggiECreaStanzeBloccate() throws FormatoFileNonValidoException {
		String specificheStanzeBloccate = this.leggiRigaCheCominciaPer(STANZE_BLOCCATE_MARKER);
		for(String specificaStanzaBloccata : separaStringheAlleVirgole(specificheStanzeBloccate)) {
			String nomeStanza = null;
			String attrezzoChiave = null;
			Direzione direzioneBloccata = null;
			try (Scanner scannerLinea = new Scanner(specificaStanzaBloccata)) {

				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza bloccata"));
				nomeStanza = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome dell'attrezzo chiave."));
				attrezzoChiave = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della direzione bloccata."));
				direzioneBloccata = Direzione.valueOf(scannerLinea.next());
			}
			Stanza stanza = new StanzaBloccata(nomeStanza, direzioneBloccata,attrezzoChiave);
			this.nome2stanza.put(nomeStanza, stanza);
		}

	}

	private void leggiECreaStanzeMagiche() throws FormatoFileNonValidoException {
		String nomiStanzeMagiche = this.leggiRigaCheCominciaPer(STANZE_MAGICHE_MARKER);
		for(String nomeStanzaMagica : separaStringheAlleVirgole(nomiStanzeMagiche)) {
			Stanza stanza = new StanzaBuia(nomeStanzaMagica);
			this.nome2stanza.put(nomeStanzaMagica, stanza);
		}

	}

	private List<String> separaStringheAlleVirgole(String string) {
		List<String> result = new LinkedList<>();
		Scanner scanner = new Scanner(string);
		scanner.useDelimiter(",");
		try (Scanner scannerDiParole = scanner) {
			while(scannerDiParole.hasNext()) {
				result.add(scannerDiParole.next());
			}
		}
		return result;
	}


	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = null;
		nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
		check(this.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale +" non definita");
		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
		check(this.isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");
		this.stanzaIniziale = this.nome2stanza.get(nomeStanzaIniziale);
		this.stanzaVincente = this.nome2stanza.get(nomeStanzaVincente);
	}

	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);

		for(String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo "+nomeAttrezzo+"."));
				nomeStanza = scannerLinea.next();
			}				
			posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
		}
	}

	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
			check(isStanzaValida(nomeStanza),"Attrezzo "+ nomeAttrezzo+" non collocabile: stanza " +nomeStanza+" inesistente");
			this.nome2stanza.get(nomeStanza).addAttrezzo(attrezzo);
		}
		catch (NumberFormatException e) {
			check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
		}
	}


	private boolean isStanzaValida(String nomeStanza) {
		return this.nome2stanza.containsKey(nomeStanza);
	}

	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);
		for(String specifica : separaStringheAlleVirgole(specificheUscite)) {
			try (Scanner scannerDiLinea = new Scanner(specifica)) {			
				while (scannerDiLinea.hasNext()) {
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("le uscite di una stanza."));
					String stanzaPartenza = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la direzione di una uscita della stanza "+stanzaPartenza));
					Direzione dir = Direzione.valueOf(scannerDiLinea.next());
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la destinazione di una uscita della stanza "+stanzaPartenza+" nella direzione "+dir));
					String stanzaDestinazione = scannerDiLinea.next();

					impostaUscita(stanzaPartenza, dir, stanzaDestinazione);
				}
			} 
		}

	}

	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere "+msg;
	}

	private void impostaUscita(String stanzaDa, Direzione dir, String nomeA) throws FormatoFileNonValidoException {
		check(isStanzaValida(stanzaDa),"Stanza di partenza sconosciuta "+dir);
		check(isStanzaValida(nomeA),"Stanza di destinazione sconosciuta "+ dir);
		Stanza partenzaDa = this.nome2stanza.get(stanzaDa);
		Stanza arrivoA = this.nome2stanza.get(nomeA);
		partenzaDa.impostaStanzaAdiacente(dir, arrivoA);
	}


	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException("Formato file non valido [" + this.reader.getLineNumber() + "] "+messaggioErrore);		
	}

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}
}