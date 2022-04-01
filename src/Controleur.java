import java.awt.Component;

public class Controleur {
	private static final long serialVersionUID = 1L;
	Modele modele;
	View view;
	
	ControleurOperator jcoperateur;
	ControleurChiffre  jcchiffre;
	
	boolean clicOperateur = false;
	boolean update = false;
	private String operation = "";
	double nombre;

	public Controleur(){
		jcchiffre = new ControleurChiffre(this);
		jcoperateur = new ControleurOperator(this);        
	}
	public void setModele(Modele modele){
		this.modele=modele;
	}
	public void setView(View view) {
		this.view=view;
	}
	public Component getChiffre() {
		return jcchiffre;
	}
	public Component getOperateur() {
		return jcoperateur;
	}
	private void calcul(){
		if(operation.equals("+")) {
			nombre = nombre + modele.getDouble();
			changeValue(nombre);
		}
		if(operation.equals("-")) {
			nombre = nombre - modele.getDouble();
			changeValue(nombre);
		}               
		if(operation.equals("*")) {
			nombre = nombre * modele.getDouble();
			changeValue(nombre);
		}       
		if(operation.equals("/")) {
			try{
				nombre = nombre / modele.getDouble();
				changeValue(nombre);
			}catch(ArithmeticException e){
				changeValue(0);
			}
		}
	}
	// on change le modele (cf class modele)
	public void setChiffre(String str) {
		//si update = true, c'est le premier chiffre saisi, on passe update = false
		if(update){
			update = false;
			//si update = false, on change la valeur
		} else {
			//cas particulier
			if(!modele.getValue().equals("0")){ //modele pas égal à zéro
				if (str.equals(".") ) //on a saisi une virgule
					if (modele.getValue().contains(".")) // mais il y a déjà une virgule
						str = modele.getValue();
					else // cas non particuliers on ajoute str au modele
						str = modele.getValue() + str;
				else	
					str = modele.getValue() + str;
			}
		}
		// le modele prends la valeur str
		changeValue(str);
	}
	public void egal() {
		calcul();
		update = true;
		clicOperateur = false;
	}
	public void raz() {
		clicOperateur = false;
		update = true;
		nombre = 0;
		operation = "";
		changeValue(nombre);
	}
	public void plus() {
		if(clicOperateur) {
			calcul();
			changeValue(nombre);
		} else {
			nombre = modele.getDouble();
			clicOperateur = true;
		}
		operation = "+";
		update = true;
	}
	public void moins() {
		if(clicOperateur) {
			calcul();
			changeValue(nombre);
		} else {
			nombre = modele.getDouble();
			clicOperateur = true;
		}
		operation = "-";
		update = true;
	}
	public void multiplier() {
		if(clicOperateur) {
			calcul();
			changeValue(nombre);
		} else {
			nombre = modele.getDouble();
			clicOperateur = true;
		}
		operation = "*";
		update = true;
	}
	public void diviser() {
		// clicoperateur est à false au début
		//si il est à true (2e, 3e opération) on calcule (calcul  intermédiaire)
		//si on met 2 opérations de suite, ça prends 2 fois la même valeur de chiffre
		// ex : 2// va faire la meme chose que 2/2/ donc va renvoyer 1
		// c'est une drole de calculatrice
		if(clicOperateur) {
			calcul();
			changeValue(nombre);
		} else {
			nombre = modele.getDouble();
			clicOperateur = true;
		}
		operation = "/";
		update = true;
	}
	private void changeValue(double chiffre1) {
		modele.setValue(String.valueOf(chiffre1));
		view.setValue(modele.getValue());
	}
	private void changeValue(String chiffre) {
		modele.setValue(chiffre);
		view.setValue(modele.getValue());
	}
}
