package gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Info extends JFrame implements ActionListener {
	
	JButton bouton1;
	 JTextField jtf;
	 JButton bouton;
	 JButton bouton2;
	
	public Info(){
		this.setTitle("Information");

	    this.setSize(750, 250);

	    this.setLocationRelativeTo(null);

	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	    
	    JPanel pan = new JPanel();
	    pan.setLayout(null);
	    
	    //premier titre
	    JLabel label = new JLabel("Entrer des informations complémentaires(par ex: SCENES ou scène)");
	    Font font = new Font("Arial Black", Font.BOLD, 15);
	    label.setFont(font);
	    pan.add(label).setBounds(20, 20, 680, 40);
	   
	    //insertion champ
	    jtf = new JTextField("");
	    jtf.setPreferredSize(new Dimension(150, 30));
	    pan.add(jtf).setBounds(20, 100,380, 30);
	    
	    //insertion bouton
	    bouton = new JButton("OK");
	    bouton.addActionListener(this);
	    pan.add(bouton).setBounds(410, 100, 160, 30);
	    
	    bouton1 = new JButton("Retour");
	    bouton1.addActionListener(this);
	    pan.add(bouton1).setBounds(100, 160, 90, 30);
	    
	   
	    
	    pan.setBackground(Color.WHITE);
	    this.setContentPane(pan); 
	    this.setVisible(true);
	}
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == bouton){}
            
		else
		  {
			
				this.dispose();
				ChoixScene c=new ChoixScene(-1);
			
			
		  }
		
	  }    


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Info i=new Info();

	}

}
