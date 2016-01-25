package gui;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChoixScene extends JFrame implements ActionListener {
	
	JButton bouton1;
	JButton bouton2;
	JComboBox[] com=new JComboBox[nbre];
	Scene[] scene=new Scene[nbre];
	Scene[] scene1;
	static int nbre =4;
	static String[] tabnom={"scene1","scene2","scene3","scene4"};
	
	public ChoixScene(int ip){
		scene1=new Scene[nbre];
		this.setTitle("Choix Personnage");

	    this.setSize(700, 750);

	    this.setLocationRelativeTo(null);

	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	    
	    JPanel pan = new JPanel();
	   
	    pan.setLayout(null);
	     
	    //premier titre
	    JLabel label = new JLabel("Il y'a " +nbre+ "  scènes");
	    Font font = new Font("Arial Black", Font.ITALIC, 20);
	    label.setFont(font);
	    label.setHorizontalAlignment(JLabel.CENTER);
	    pan.add(label).setBounds(20, 20, 600, 40);
	    
	    
	    JLabel label1 = new JLabel("Sélectionnez les scénes approprié pour votre pièce: ");
	    pan.add(label1).setBounds(20, 80,700 , 40);
	   
	    int j=120;
	    int j1=120;
	    int s=0;
	    int s1=0;
	    
	    
	   
	    //insertion liste déroulante
	    	for(int j2=0;j2<nbre;j2++){
	    		JComboBox b=new JComboBox();
	    for(int i=0;i<nbre;i++)
	    {
	    	int k=i+1;
	    	ImageIcon icon = new ImageIcon("C:\\Users\\ariane\\Documents\\photo_scene\\img"+k+".jpg");
	    	Scene s3=new Scene(tabnom[i],icon);
	    	scene[i]=s3;
	    	 //JButton button = new JButton(icon);
	    	 //System.out.println(personne.getNom());
	    	 b.addItem(icon);
	    }
	        com[j2]=b;
	        com[j2].addActionListener(this);
	    	JLabel label2 = new JLabel(tabnom[j2]);
	 	    pan.add(label2).setBounds(250, s1+j1,200 , 40);
	 	   pan.add(b).setBounds(20, s+j,180 , 70);
	 	    s=s+j;
	 	   s1=s1+j1;
	    
	    	} //pan.add(b).setBounds(20, 130,200 , 100);
	    	
	    	//butt[i]=button;
	    	// butt[i].addActionListener(this);
	 	    //pan.add(butt[i]).setBounds(20, s+j, 100, 100);
	 	    
	 	   bouton1 = new JButton("OK");
		    bouton1.addActionListener(this);
		    pan.add(bouton1).setBounds(300, 650, 70, 30);
		    
		    bouton2 = new JButton("Retour");
		    bouton2.addActionListener(this);
		    pan.add(bouton2).setBounds(490, 650, 90, 30);
		    
		    pan.setBackground(Color.LIGHT_GRAY);
		    
		    this.setContentPane(pan); 
		    this.setVisible(true);
		   
	}

	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == bouton1){
			Info i=new Info();
		}
		  else
		  {
			  if(arg0.getSource() == bouton2){
		         //   this.dispose();
				//    ChoixPersonnage per=new ChoixPersonnage(-1);}
			  }  else{
				  for(int i=0;i<nbre;i++){
						if(arg0.getSource()==com[i])
						{
							for(int j=0;j<nbre;j++){
							if(scene[j].getIcon().toString().equals(com[i].getSelectedItem().toString())){
								Scene p=new Scene(tabnom[i],scene[j].getIcon());
								scene1[i]=p;
								System.out.println("ActionListener : action sur " +scene1[i].getIcon().toString()+ " "+scene1[i].getNom() );
								j=nbre;
							}
						
							
							
						}
							i=nbre;
					}
					}
			  } 
		  }
		
	  }    
	public  Scene[] getScene(){
		return scene1;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        ChoixScene scene=new ChoixScene(-1);
	}

}
