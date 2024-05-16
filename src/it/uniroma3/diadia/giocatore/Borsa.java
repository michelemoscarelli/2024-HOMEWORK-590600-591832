package it.uniroma3.diadia.giocatore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.ComparatorePerNumeroAttrezzi;

public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private ArrayList<Attrezzo> attrezzi;
	private int numeroAttrezzi;
	private int pesoMax;
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}
	
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new ArrayList<>();
		this.numeroAttrezzi = 0;
	}
	
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(attrezzo==null) return false;
		
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		this.attrezzi.add(attrezzo);
		this.numeroAttrezzi++;
		return true;
	}
	
	public int getPesoMax() {
		return pesoMax;
	}
	
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		for (Attrezzo a1: this.attrezzi)
			if (a1.getNome().equals(nomeAttrezzo))
				a = a1;

		return a;
	}

	public int getPeso() {
		int peso = 0;
		for (Attrezzo a: attrezzi)
			peso += a.getPeso();

		return peso;
	}
	
	public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
	}
	
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}
	
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		Iterator<Attrezzo> it = this.attrezzi.iterator();
		
		while(it.hasNext()) {
			if(it.next().getNome().equals(nomeAttrezzo)) {
				a = it.next();
				it.remove();
			}
		}
		return a; // se attrezzo è stato trovato, "a" contiene l'attrezzo eliminato, altrimenti null
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
			

		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			s.append(this.getContenutoOrdinatoPerPeso()+ "\n");
			s.append("Contenuto ordinato per nome = "+ this.getContenutoOrdinatoPerNome().toString().replace("[", "{").replace("]", "}"));
			s.append(". raggruppato per peso = "+ this.getContenutoRaggruppatoPerPeso().toString().replace("=", ", ")
					.replace("[", "{").replace("]", "}")+ " ");
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
	
	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		
		ComparatorePerNumeroAttrezzi comp = new ComparatorePerNumeroAttrezzi();
		SortedSet<Attrezzo> temp = getContenutoOrdinatoPerNome();
		ArrayList<Attrezzo> out = new ArrayList<Attrezzo>();
		out.addAll(temp);
		Collections.sort(out, comp);
		return out;
		
	}
	
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		TreeSet<Attrezzo> out  = new TreeSet<Attrezzo>();
		out.addAll(this.attrezzi);
		return out;
		
	}
	// non funge penso sia un problema di iterator
	Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		Map<Integer,Set<Attrezzo>> out = new HashMap<>();
		ArrayList<Attrezzo> in = this.attrezzi;
		for(Attrezzo a: in) {
			int val = a.getPeso();
			Set<Attrezzo> temp = new TreeSet<>();
			for(Attrezzo a1: in) {
				if(a1.getPeso()==val)
					temp.add(a1);
			}
			out.put(val, temp);
		}
		return out;
		 
	}
}
