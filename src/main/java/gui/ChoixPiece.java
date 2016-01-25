package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import background.ParserODT;

public class ChoixPiece extends JFrame implements ActionListener {

	JButton bouton1;
	JTextField jtf;
	JButton bouton;
	JButton bouton2;
	ParserODT parserODT;

	public ChoixPiece(ParserODT parser)

	{
		parserODT = parser;
		this.setTitle("Choix Pièce");

		this.setSize(650, 250);

		this.setLocationRelativeTo(null);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel pan = new JPanel();
		pan.setLayout(null);

		// premier titre
		JLabel label = new JLabel("Choisir une pièce de théâtre:");
		Font font = new Font("Arial Black", Font.BOLD, 30);
		label.setFont(font);
		pan.add(label).setBounds(20, 20, 600, 40);

		// insertion champ
		jtf = new JTextField(" ");
		jtf.setPreferredSize(new Dimension(150, 30));
		pan.add(jtf).setBounds(20, 100, 380, 30);

		// insertion bouton
		bouton = new JButton("Parcourir");
		bouton.addActionListener(this);
		pan.add(bouton).setBounds(410, 100, 160, 30);

		bouton1 = new JButton("OK");
		bouton1.addActionListener(this);
		pan.add(bouton1).setBounds(100, 160, 90, 30);

		bouton2 = new JButton("Retour");
		bouton2.addActionListener(this);
		pan.add(bouton2).setBounds(280, 160, 100, 30);

		pan.setBackground(Color.GRAY);
		this.setContentPane(pan);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == bouton1) {
			if (jtf.getText().equals(" ")) {
				// Boîte du message d'erreur

				JOptionPane jop = new JOptionPane();
				jop.showMessageDialog(null, "aucun fichier n'a été sélectionné", "Erreur", JOptionPane.ERROR_MESSAGE);
			} else {
				this.dispose();
				synchronized (parserODT) {
					parserODT.notify();
				}
				
			}
		} else {
			if (arg0.getSource() == bouton2) {
				this.dispose();
				/* Accueil acceuil=new Accueil(); */
			} else {
				JFileChooser chooser = new JFileChooser();
				int returnVal = chooser.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File selection = chooser.getSelectedFile();
					jtf.setText(selection.getAbsolutePath());
					parserODT.setFilename(selection.getAbsolutePath());
					synchronized (parserODT) {
						parserODT.notify();
					}
					ChoixPersonnage personne = new ChoixPersonnage(parserODT);
				}

			}
		}

	}
}
