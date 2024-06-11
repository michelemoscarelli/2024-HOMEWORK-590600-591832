package it.uniroma3.diadia;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuratore {
	
	private static final String DIADIA_PROPERTIES = "diadia.properties";
	static final private String CFU_INIZIALI = "cfu_iniziali";
	private final static String DEFAULT_PESO_MAX_BORSA = "peso_max_borsa";
	private static Properties prop;
	
	public static int getCFUIniziali() {
		if (prop==null)
			carica();
		return Integer.parseInt(prop.getProperty(CFU_INIZIALI));
	}
	
	public static int getPesoMax() {
		if (prop==null)
			carica();
		return Integer.parseInt(prop.getProperty(DEFAULT_PESO_MAX_BORSA));
	}
	
	private static void carica() {
		prop = new Properties();
		try {
			FileInputStream fileDiInput = new FileInputStream(DIADIA_PROPERTIES);
			prop.load(fileDiInput);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
