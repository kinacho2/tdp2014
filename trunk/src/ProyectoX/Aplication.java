package ProyectoX;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ProyectoX.Paneles.PanelEnemies;
import ProyectoX.Paneles.PanelGame;
import ProyectoX.Paneles.PanelInit;
import ProyectoX.Paneles.PanelJugador;
import ProyectoX.Paneles.PanelSelect;
import ProyectoX.Frames.MenteSplash;
import ProyectoX.Frames.SplashScreen;
import ProyectoX.Mapas.Mapa;
import ProyectoX.Mapas.Nivel_I;
import ProyectoX.Mapas.Nivel_II;
import ProyectoX.Sound.Reproductor;
import ProyectoX.Sound.Sonido;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.Random;


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
	
	private JPanel principal;
	private JPanel main;
	private JPanel game;
	private static final String sound = "/ProyectoX/sounds/music/menu_";
	private Reproductor rep;
	private Random rn;
	
	private URL url = (Aplication.class.getClassLoader().getResource("ProyectoX/img/Fondos/fondoNivel1.png"));

	 public Aplication() {
		rep = new Reproductor();
		rn = new Random();
		rep.addSound(sound+rn.nextInt(4)+".mp3",true);
		initGUI();
    }

    public static void main(String[] args) {
        new Aplication();
        
    }
	    
    //inicializa el panel del juego
    
    public void showSplashBeforeGame(int select){
    	setVisible(false);
    	MenteSplash spl = new MenteSplash(5000, url, this, select);
    	spl.start();
    }
    
    public void initGame(int select){
    	rep.stop(100);
    	
    	setVisible(false);
    	
    	game = new PanelGame(this,select,rep);    
        
        setVisible(true);
       
        
        
	}
	    
  
    private void initGUI() {
    	
    	initPrimerPanel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setTitle("Chaos Wind");
        setResizable(false);
        setVisible(true);
        this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				cerrarJuego();
			}
		});
        
	}
    
    //inicializa el primer panel para iniciar o salir del juego
    
	public void initPrimerPanel(){
		setVisible(false);
		principal = new PanelInit(this);
		add(principal);
		setVisible(true);
	}
	
	//inicializa el segundo panel para seleccionar tipo de nave
	
	public void initMain(){
		setVisible(false);
		main = new PanelSelect(this);
		add(main);
		setVisible(true);
	}
	
	
	
	private void cerrarJuego() {
		this.dispose();
		System.exit(0);
		
	}
	
}
