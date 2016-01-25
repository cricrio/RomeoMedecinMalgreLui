package gui;

import javax.swing.ImageIcon;

public class Personnage {
	
	private String nom;
	private ImageIcon icone ;
	
	public Personnage(String nom){
		this.setNom(nom);
	}
	
	public Personnage(String nom, ImageIcon icone)
	{
		this.setNom(nom);
		this.setIcone(icone);
	}
	
	

	public ImageIcon getIcon()
	{
		return getIcone();
	}
	
	public String getNom()
	{
		return nom;
	}
	
	public void setIcon(ImageIcon icone)
	{
		this.setIcone(icone);
	}
	
	public void setName(String nom)
	{
		this.setNom(nom);
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public ImageIcon getIcone() {
		return icone;
	}

	public void setIcone(ImageIcon icone) {
		this.icone = icone;
	}

}
