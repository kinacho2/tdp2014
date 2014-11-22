package ProyectoX.Paneles;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ProyectoX.Aplication;
import ProyectoX.Mapas.Mapa;
import ProyectoX.Mapas.Nivel_I;
import ProyectoX.Mapas.Nivel_III;
import ProyectoX.Sound.Reproductor;

/**
 * Panel que contiene los paneles principales del juego
 * implementa la interface AbstractPanel
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */

public class PanelGame extends JPanel implements AbstractPanel {
	
	private static final String sound = "/ProyectoX/sounds/music/menu_";
	private PanelJugador panel;
	private JPanel bar;
	private JButton volverMenu;
	private Mapa map;
	private Aplication api;
	private Reproductor rep;
	
	/**
	 * Constructor de la clase PanelGame
	 * crea los paneles del jugador y del enemigo
	 * crea tambien el primer nivel y setea los atributos correspondientes
	 * @param api Aplicacion principal
	 * @param select entero que representa el tipo de Jugador elegido
	 * @param rep Reproductor en curso de la Aplicacion
	 */
	public PanelGame(Aplication api, int select,String nombre, Reproductor rep){
		this.api = api;
		this.rep = rep;
		
		map = new Nivel_III(api, this,rep);
		api.add(this, BorderLayout.CENTER);
		setBackground(new java.awt.Color(0,0,0));
		
		//panel del jugador
		panel = new PanelJugador(map,select,nombre);
		
		add(panel);
		panel.setLayout(null);
		panel.setPreferredSize(new java.awt.Dimension(800, 530));
		
		// Barra de estado
	
		bar = new JPanel();
		add(bar);
		bar.setLayout(null);
		bar.setPreferredSize(new java.awt.Dimension(800, 35));
		bar.setBackground(new java.awt.Color(0,0,0));
		
		panel.setBar(bar);
	
        // boton volver al menu
		
		volverMenu = new JButton("Salir");
		volverMenu.addActionListener(new OyenteVolver());
		volverMenu.setBounds(10, 0, 50, 25);
		volverMenu.setForeground(new java.awt.Color(0,255,0));
		volverMenu.setBackground(new java.awt.Color(0,0,0));
		volverMenu.setBorder(BorderFactory.createCompoundBorder(null,null));
		volverMenu.setFont(new java.awt.Font("Segoe UI",0,20));
		bar.add(volverMenu);
		
		
        // panel de los enemigos
		
        PanelEnemies panelEnemies = new PanelEnemies(map,api);
        panel.add(panelEnemies);
        panelEnemies.setBounds(0, 0, 800, 600);
        
    	// Inicia el hilo de los Enemigos
        panelEnemies.getMindEnemies().start();
	}
	
	/**
	 * clase que implementa la interface ActionListener
	 * se encarga de volver hacia el panel de seleccion de jugador en caso de ser solicitado
	 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
	 */
	
	private class OyenteVolver implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			
            int verd;
            
            JOptionPane dialogo = new JOptionPane();
            
            verd = dialogo.showConfirmDialog(null, "Desea volver al menu principal?", "Atencion",JOptionPane.YES_NO_OPTION);
			
            if(verd == 0){
            	map.stop();
            	setVisible(false);
    			api.initMain(rep);
        		
            }
            
    		
		}
	}

	/**
	 * define function() de la interface AbstractPanel
	 * se encarga de frenar la ejecucion del juego y volver al primer panel de la aplicacion
	 */
	public void function() {
		map.stop();
		api.initPrimerPanel();
	}

}
