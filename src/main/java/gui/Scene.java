package gui;
import javax.swing.ImageIcon;

public class Scene {
	
	private String nom;
	private ImageIcon icone ;
	
	public Scene(String nom, ImageIcon icone)
	{
		this.nom=nom;
		this.icone=icone;
	}
	
	public ImageIcon getIcon()
	{
		return icone;
	}
	
	public String getNom()
	{
		return nom;
	}
	
	public void setIcon(ImageIcon icone)
	{
		this.icone=icone;
	}
	
	public void setName(String nom)
	{
		this.nom=nom;
	}


}
