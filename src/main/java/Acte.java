import java.util.ArrayList;
import java.util.List;

public class Acte {
	private static String identifiant = "Acte";
	private List<String> contenut = new ArrayList<String>();
	
	public Acte() {
	}

	public List<String> getContenut() {
		return contenut;
	}

	public void setContenut(List<String> contenut) {
		this.contenut = contenut;
	}
}
