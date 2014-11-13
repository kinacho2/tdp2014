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
import ProyectoX.Paneles.PanelInit;
import ProyectoX.Paneles.PanelJugador;
import ProyectoX.Paneles.PanelSelect;
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
	private PanelJugador panel;
	private Mapa map;
	private JPanel bar;
	private JButton volverMenu;
	private static final String sound = "/ProyectoX/sounds/music/menu_";
	private Reproductor rep;
	private Random rn;
	int round = 0;

	 public Aplication() {
		rep = new Reproductor();
		rn = new Random();
		rep.addSound(new Sonido(sound+rn.nextInt(4)+".mp3",true));
		initGUI();
    }

    public static void main(String[] args) {
        new Aplication();
        
    }
	    
    //inicializa el panel del juego
    
    public void initGame(int select){
    	rep.stop(100);
    	setVisible(false);
    	
    	game = new JPanel();
    	
    	map = new Nivel_II();
		getContentPane().add(game, BorderLayout.CENTER);
		game.setBackground(new java.awt.Color(0,0,0));
		
		//panel del jugador
		panel = new PanelJugador(map,select);
		
		game.add(panel);
		panel.setLayout(null);
		panel.setPreferredSize(new java.awt.Dimension(800, 520));
		
		// Barra de estado
	
		bar = new JPanel();
		game.add(bar);
		bar.setLayout(null);
		bar.setPreferredSize(new java.awt.Dimension(800, 50));
		bar.setBackground(new java.awt.Color(0,0,0));
		
		panel.setBar(bar);
	
        // boton volver al menu
		
		volverMenu = new JButton("Salir al Menu");
		volverMenu.addActionListener(new OyenteVolver());
		volverMenu.setBounds(25, 11, 150, 25);
		volverMenu.setForeground(new java.awt.Color(0,255,0));
		volverMenu.setBackground(new java.awt.Color(0,0,0));
		volverMenu.setBorder(BorderFactory.createCompoundBorder(null,null));
		volverMenu.setFont(new java.awt.Font("Segoe UI",0,20));
		bar.add(volverMenu);
		
		
        // panel de los enemigos
		
        PanelEnemies panelEnemies = new PanelEnemies(map);
        panel.add(panelEnemies);
        panelEnemies.setBounds(0, 0, 800, 600);
        
        // Inicia el hilo de los Enemigos
        setVisible(true);
        panelEnemies.getMindEnemies().start();
        
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
	
	private class OyenteVolver implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			
            int verd;
            
            JOptionPane dialogo = new JOptionPane();
            
            verd = dialogo.showConfirmDialog(null, "Desea volver al menu principal?", "Atencion",JOptionPane.YES_NO_OPTION);
			
            if(verd == 0){
            	map.stop();
            	game.setVisible(false);
    			setVisible(false);
    			initMain();
    			setVisible(true);
        		rep.addSound(new Sonido(sound+rn.nextInt(4)+".mp3",true));

            }
            
    		
		}
	}
	
	private void cerrarJuego() {
		this.dispose();
		System.exit(0);
		
	}
	
}
