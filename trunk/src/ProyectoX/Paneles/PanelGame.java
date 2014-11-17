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
import ProyectoX.Mapas.Nivel_II;
import ProyectoX.Mapas.Nivel_III;
import ProyectoX.Sound.Reproductor;


public class PanelGame  extends JPanel implements AbstractPanel {
	
	private static final String sound = "/ProyectoX/sounds/music/menu_";
	private PanelJugador panel;
	private JPanel bar;
	private JButton volverMenu;
	private Mapa map;
	private Aplication api;
	private Reproductor rep;
	private Random rn ;
	
	public PanelGame(Aplication api, int select, Reproductor rep){
		this.api = api;
		this.rep = rep;
		rn = new Random();
		
		map = new Nivel_III(api, this);
		api.add(this, BorderLayout.CENTER);
		setBackground(new java.awt.Color(0,0,0));
		
		//panel del jugador
		panel = new PanelJugador(map,select);
		
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
	
	private class OyenteVolver implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			
            int verd;
            
            JOptionPane dialogo = new JOptionPane();
            
            verd = dialogo.showConfirmDialog(null, "Desea volver al menu principal?", "Atencion",JOptionPane.YES_NO_OPTION);
			
            if(verd == 0){
            	map.stop();
            	setVisible(false);
    			api.initMain(rep);
        		rep.addSound(sound+rn.nextInt(4)+".mp3",true);

            }
            
    		
		}
	}

	@Override
	public void function() {
		map.stop();
		api.initPrimerPanel();
	}

}
