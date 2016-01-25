package background;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.odftoolkit.simple.TextDocument;
import org.odftoolkit.simple.text.Paragraph;
import org.w3c.dom.Element;

import gui.Accueil;
import gui.ChoixPiece;
import rdfa.parse;

public class ParserODT {

	private String filename;
	private RomanUtils romainUtil = new RomanUtils();
	Set<String> noms;
	org.w3c.dom.Document document;
	Element racine;
	Element dernierActe;
	Element derniereScene;
	Element dernierePersonne;
	private boolean listeFineBourrain = false;
	private ChoixPiece accueilGUI;
	
	public ParserODT() throws ParserConfigurationException {
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
		document = documentBuilder.newDocument();
		racine = document.createElement("piece");
		document.appendChild(racine);
	}

	public void enregistrerFichierXML() throws TransformerException {
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(document);
		
		StreamResult result = new StreamResult(new File("file.xml"));

		// Output to console for testing
		// StreamResult result = new StreamResult(System.out);

		transformer.transform(source, result);
	}

	private void writeActe(Paragraph paragraph) {
		String[] words = paragraph.getTextContent().split(" ");
		dernierActe = document.createElement("acte");
		dernierActe.setAttribute("numero", romainUtil.RomanToStringValueInt(words[1]));
		racine.appendChild(dernierActe);
	}

	private void writeScene(Paragraph paragraph) {
		String[] words = paragraph.getTextContent().split(" ");

		System.out.println(words[1]);
		derniereScene = document.createElement("scene");
		derniereScene.setAttribute("numero", romainUtil.RomanToStringValueInt(words[1]));
		dernierActe.appendChild(derniereScene);
	}

	private void writePersonne(String nom) {
		dernierePersonne = document.createElement("tirade");
		dernierePersonne.setAttribute("nom", nom);
		derniereScene.appendChild(dernierePersonne);
	}

	private boolean commenceParUneLettreDeLaffabet(String mot) {
		for (char c = 'A'; c <= 'z'; c++) {
			if (mot.startsWith(c + ""))
				return true;
		}
		
		return false;

	}

	public boolean isUppercase(String word) {
		return word.equals(word.toUpperCase());
	}

	public String enleverVirgule(String mot) {
		return mot.substring(0, mot.length() - 1);
	}

	public boolean isScene(Paragraph paragraphe) {
		String[] mots = paragraphe.getTextContent().split(" ");
		if (mots.length == 2) {
			if (mots[0].equals("Scène") && getRomainUtil().IsRomanNumber(mots[1])) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean isActe(Paragraph paragraphe) {
		String[] mots = paragraphe.getTextContent().split(" ");
		if (mots.length == 2) {
			if (mots[0].equals("Acte") && getRomainUtil().IsRomanNumber(mots[1])) {
				writeActe(paragraphe);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	

	public void recherchePersonnage(Paragraph paragraphe) {
		String[] mots = paragraphe.getTextContent().split(" ");
		System.out.println(mots);
		String tirade = "";
		int nb = 0;

		for (int i = 0; i < mots.length; i++) {
			// System.out.println(mots[i]);
			if (noms.contains(mots[i])) {
				mots[i].endsWith(" ");
				if (dernierePersonne != null) {
					if(!tirade.equals("")) 
					dernierePersonne.setTextContent(tirade);
				}
				if(!mots[i].equals(""))
				writePersonne(mots[i]);

			}
			if (commenceParUneLettreDeLaffabet(mots[i]))
				tirade = tirade + " " + mots[i];
			//System.out.println("tirade " + tirade);
		}
		if (dernierePersonne != null && !tirade.equals(""))
			dernierePersonne.setTextContent(tirade);

		System.out.println(nb + "\n\n\n\n\n");
	}

	public void decouperPieceEnActe(TextDocument document) {
		boolean dansUneScene = false;
		boolean dansUnActe = false;
		Iterator<Paragraph> iParagraphe = document.getParagraphIterator();
		noms = retrouverToutPersonnage(document);
		while (iParagraphe.hasNext()) {
			Paragraph paragraphe = iParagraphe.next();
			if (isActe(paragraphe) && iParagraphe.hasNext()) {
				paragraphe = iParagraphe.next();
			}
			if (isScene(paragraphe) && iParagraphe.hasNext()) {
				writeScene(paragraphe);
				paragraphe = iParagraphe.next();
			}

			recherchePersonnage(paragraphe);
			dansUnActe = false;
			dansUneScene = false;
		}
	}

	private Set<String> retrouverToutPersonnage(TextDocument textDocument) {
		Set<String> noms = new HashSet<String>();
		Iterator<Paragraph> iterator = textDocument.getParagraphIterator();
		Paragraph paragraph = null;
		while (iterator.hasNext()) {
			paragraph = iterator.next();
			noms.addAll(getWordsInUpperCase(paragraph));
		}
		System.out.println(noms);
		return noms;
	}

	public ArrayList<String> getWordsInUpperCase(Paragraph paragraphe) {
		ArrayList<String> motUpperCase = new ArrayList<String>();
		// decouper les espace
		String words = paragraphe.getTextContent();
		String[] ligne = words.split(" ");
		for (int i = 0; i < ligne.length; i++) {
			if (isUppercase(ligne[i])) {
				if (ligne[i].endsWith(",")) {
					motUpperCase.add(enleverVirgule(ligne[i]));
				}
			}
		}
		return motUpperCase;

	}

	public static void main(String[] args) {
		try {
			ParserODT p = new ParserODT();
			p.setAccueilGUI(new ChoixPiece(p));
			synchronized (p) {
				try {
					p.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			TextDocument document = (TextDocument) TextDocument.loadDocument(p.filename);
			
			ParserODT parser = new ParserODT();
			parser.decouperPieceEnActe(document);

			parser.enregistrerFichierXML();
		synchronized (parser) {
			parser.notify();
		}	
			System.out.println("fin");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public RomanUtils getRomainUtil() {
		return romainUtil;
	}

	public void setRomainUtil(RomanUtils romainUtil) {
		this.romainUtil = romainUtil;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public ChoixPiece getAccueilGUI() {
		return accueilGUI;
	}

	public void setAccueilGUI(ChoixPiece accueilGUI) {
		this.accueilGUI = accueilGUI;
	}

	public boolean isListeFineBourrain() {
		return listeFineBourrain;
	}

	public void setListeFineBourrain(boolean listeFineBourrain) {
		this.listeFineBourrain = listeFineBourrain;
	}
	
	public boolean getListeFineBourrain(){
		return listeFineBourrain;
	}

}
