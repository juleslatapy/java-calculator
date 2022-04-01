import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ControleurChiffre extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	String[] tab_string = {"", "", "", "", "", "", "", "", ""};
	// tableau avec 9 boutons
	JButton[] tab_button = new JButton[tab_string.length];
	private Dimension dim = new Dimension(50, 50);
	private Controleur controleur;
	public ControleurChiffre(Controleur controleur){
		this.controleur=controleur;
		setPreferredSize(new Dimension(200, 200));
		// i++ correspond à i = i +1
		for(int i = 0; i < tab_string.length; i++) {
			tab_button[i] = new JButton(tab_string[i]);
			tab_button[i].setPreferredSize(dim);
			add(tab_button[i]);
			tab_button[i].addActionListener(this);
		}
	}
	// pour récupérer les chiffres saisis par l'utilisateur:
	public void actionPerformed(ActionEvent e) {
		//On met un "5"
		String str = ((JButton)e.getSource()).getText();
		// on va dans controleur set chiffre
		controleur.setChiffre(str);
	}
}



