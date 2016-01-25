package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import background.ParserODT;

public class ChoixPersonnage extends JFrame implements ActionListener {

	JButton bouton1;
	JButton bouton2;
	ParserODT parserO;

	static int nbre = 4;
	static String[] tabnom = { "souki", "chris", "oliv", "ari" };
	Set<String> listNom;
	List<Personnage> listPers = new ArrayList<Personnage>();
	// Personnage[] pers=new Personnage[nbre];
	Personnage[] pers1;
	// JButton[] butt=new JButton[nbre];
	JComboBox[] com = new JComboBox[nbre];

	private List<Personnage> realiserListePersonnage() {
		Personnage personnage;
		ArrayList<Personnage> listPers = null;
		for (String s : listNom) {
			personnage = new Personnage(s);
			listPers.add(personnage);
		}
		return listPers;
	}

	public ChoixPersonnage(ParserODT paser) {
		parserO = paser;
		this.setVisible(true);
		synchronized (paser) {
			try {
				paser.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println();
		listPers = realiserListePersonnage();
		this.setTitle("Choix Personnage");

		this.setSize(700, 750);

		this.setLocationRelativeTo(null);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel pan = new JPanel();

		pan.setLayout(null);

		// premier titre
		JLabel label = new JLabel("Il y'a " + nbre + "  personnages");
		Font font = new Font("Arial Black", Font.ITALIC, 20);
		label.setFont(font);
		label.setHorizontalAlignment(JLabel.CENTER);
		pan.add(label).setBounds(20, 20, 600, 40);

		JLabel label1 = new JLabel(
				"Sélectionnez un personnage associé aux différents noms de personnage de votre pièce:");
		pan.add(label1).setBounds(20, 80, 700, 40);

		int j = 120;
		int j1 = 120;
		int s = 0;
		int s1 = 0;

		// insertion liste déroulante
		for (int j2 = 0; j2 < nbre; j2++) {
			JComboBox b = new JComboBox();
			for (int i = 0; i < nbre; i++) {
				int k = i + 1;
				ImageIcon icon = new ImageIcon("C:\\Users\\ariane\\Documents\\photo_personnage\\img" + k + ".jpg");
				// JButton button = new JButton(icon);
				// System.out.println(personne.getNom());
				b.addItem(icon);
			}
			com[j2] = b;
			com[j2].addActionListener(this);
			JLabel label2 = new JLabel(tabnom[j2]);
			pan.add(label2).setBounds(250, s1 + j1, 200, 40);
			pan.add(b).setBounds(20, s + j, 140, 70);
			s = s + j;
			s1 = s1 + j1;

		}

		bouton1 = new JButton("OK");
		bouton1.addActionListener(this);
		pan.add(bouton1).setBounds(300, 650, 70, 30);

		bouton2 = new JButton("Retour");
		bouton2.addActionListener(this);
		pan.add(bouton2).setBounds(490, 650, 90, 30);

		pan.setBackground(Color.PINK);

		this.setContentPane(pan);
		this.setVisible(true);

	}

	public void actionPerformed(ActionEvent arg0) {
		// this.dispose();

		if (arg0.getSource() == bouton1) {
			this.dispose();
			ChoixScene scene = new ChoixScene(-1);
		} else {
			if (arg0.getSource() == bouton2) {
				// this.dispose();
				// ChoixPiece piece=new ChoixPiece();}
			} else {
				for (int i = 0; i < nbre; i++) {
					if (arg0.getSource() == com[i]) {
						for (int j = 0; j < nbre; j++) {
							if (listPers.get(j).getIcon().toString().equals(com[i].getSelectedItem().toString())) {
								Personnage p = new Personnage(tabnom[i], listPers.get(j).getIcon());
								pers1[i] = p;
								System.out.println("ActionListener : action sur " + pers1[i].getIcon().toString() + " "
										+ pers1[i].getNom());
								j = nbre;
							}

						}
						i = nbre;
					}
				}
			}
		}

	}

	public Personnage[] getPersonnage() {
		return pers1;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//ChoixPersonnage choix = new ChoixPersonnage(-1);

	}

}
