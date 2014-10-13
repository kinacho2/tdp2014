package Proyecto2;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class Aplication extends javax.swing.JFrame {

	 public Aplication() {
		 
		 Mapa map= new Mapa();

		 	Mind mind = new Mind(map,3);
	        add(mind);
	    	
	        
	        PanelEnemies panelEnemies = new PanelEnemies(mind,map);
	        mind.add(panelEnemies);
	        panelEnemies.setBounds(0, 0, 800, 600);
	        panelEnemies.setJugador(mind.getJugador());
	        
	        map.setMind(mind);
	        map.setMindEnemies(panelEnemies.getMindEnemies());
	        
	        
	        
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setSize(800, 600);
	        setLocationRelativeTo(null);
	        setTitle("R - Type");
	        setResizable(false);
	        setVisible(true);
	        
	        panelEnemies.getMindEnemies().run();
    }

	    public static void main(String[] args) {
	        new Aplication();
	        
	    }
}
