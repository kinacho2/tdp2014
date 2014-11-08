package ProyectoX;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ProyectoX.Frames_Minds.Mind;
import ProyectoX.Frames_Minds.PanelEnemies;
import ProyectoX.Frames_Minds.PanelJugador;
import ProyectoX.Mapas.Mapa;
import ProyectoX.Mapas.Nivel_I;
import ProyectoX.Naves.Nave;
import ProyectoX.Naves.Jugador.Normal;
import ProyectoX.Naves.Jugador.Resistente;
import ProyectoX.Naves.Jugador.Veloz;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


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
	private JButton resistente;
	private JButton atras;
	private JLabel labelResistente;
	private JLabel labelNormal;
	private JLabel labelVeloz;
	private JLabel resistenteStatics, normalStatics, velozStatics;
	private JButton normal;
	private JButton veloz;
	private JButton quit;
	private JButton newGame;
	private JPanel main;
	private JPanel game;
	private Mind mind;
	private PanelJugador panel;
	private Mapa map;
	private JPanel bar;
	private JButton volverMenu;
	
	

	 public Aplication() {
		 
		initGUI();
    }

    public static void main(String[] args) {
        new Aplication();
        
    }
	    
    //inicializa el panel del juego
    
    private void initGame(int select){
    	
    	game = new JPanel();
    	
    	map = new Nivel_I();
		getContentPane().add(game, BorderLayout.CENTER);
		game.setBackground(new java.awt.Color(0,0,0));
		
		
		panel = new PanelJugador(map,select);
		//mind = new Mind(map,select);
		
		
		
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
		
		
        // Mente de los enemigos
		
        PanelEnemies panelEnemies = new PanelEnemies(map);
        panel.add(panelEnemies);
        panelEnemies.setBounds(0, 0, 800, 600);
		
        // Establece las Mentes al mapa
        
        map.setMind(mind);
        map.setMindEnemies(panelEnemies.getMindEnemies());
		
        
        // Inicia el hilo de los Enemigos
      
        panelEnemies.getMindEnemies().start();
        
	}
	    
  //inicializa el primer panel para iniciar o salir del juego
    
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
	
	private void initPrimerPanel(){
		principal = new JPanel();
		getContentPane().add(principal);
		principal.setLayout(null);
		principal.setBackground(new java.awt.Color(0,0,0));
		{
			newGame = new JButton();
			principal.add(newGame);
			newGame.setText("JUEGO NUEVO");
			newGame.setBounds(400 - 288/2, 90, 288, 50);
			newGame.setForeground(new java.awt.Color(0,255,0));
			newGame.setBackground(new java.awt.Color(0,0,0));
			newGame.setBorder(BorderFactory.createCompoundBorder(null,null));
			newGame.setFont(new java.awt.Font("Segoe UI",0,20));
			newGame.addActionListener(new OyenteNuevoJuego());
		}
		{
			quit = new JButton();
			principal.add(quit);
			quit.setText("SALIR");
			quit.setBounds(400 - 288/2, 177, 288, 50);
			quit.setBackground(new java.awt.Color(0,0,0));
			quit.setForeground(new java.awt.Color(0,255,0));
			quit.setPreferredSize(new java.awt.Dimension(14, 7));
			quit.setFont(new java.awt.Font("Segoe UI",0,20));
			quit.setBorder(BorderFactory.createCompoundBorder(null, null));
			quit.addActionListener(new OyenteQuit());
		}
	}
	
	//inicializa el segundi panel para seleccionar tipo de nave
	
	private void initMain(){
		main = new JPanel();
		getContentPane().add(main);
		main.setLayout(null);
		main.setBackground(new java.awt.Color(0,0,0));
		{
			veloz = new JButton();
			main.add(veloz);
			veloz.setText("Veloz");
			veloz.setBounds(400 - 288/2, 88, 288, 64);
			veloz.setBackground(new java.awt.Color(0,0,0));
			veloz.setFont(new java.awt.Font("Segoe UI",0,20));
			veloz.setForeground(new java.awt.Color(0,255,0));
			veloz.addActionListener(new OyenteVeloz());
		}
		{
			normal = new JButton();
			main.add(normal);
			normal.setText("Normal");
			normal.setBounds(256, 188, 288, 64);
			normal.setFont(new java.awt.Font("Segoe UI",0,20));
			normal.setForeground(new java.awt.Color(0,255,0));
			normal.setBackground(new java.awt.Color(0,0,0));
			normal.addActionListener(new OyenteNormal());
		}
		{
			resistente = new JButton();
			main.add(resistente);
			resistente.setText("Resistente");
			resistente.setBounds(400 - 288/2, 288, 288, 64);
			resistente.setBackground(new java.awt.Color(0,0,0));
			resistente.setForeground(new java.awt.Color(0,255,0));
			resistente.setFont(new java.awt.Font("Segoe UI",0,20));
			resistente.addActionListener(new OyenteResistente());
		}
		{
			labelVeloz = new JLabel();
			main.add(labelVeloz);
			ImageIcon icon = new ImageIcon(Nave.class.getClassLoader().getResource("ProyectoX/img/Jugador/veloz.gif"));
			labelVeloz.setIcon(icon);
			labelVeloz.setBounds(175 + icon.getIconWidth() / 2, 88, icon.getIconWidth(), 64);
			labelVeloz.setBackground(new java.awt.Color(155,155,155));
			labelVeloz.setOpaque(true);
			
			velozStatics = new JLabel();
			main.add(velozStatics);
			velozStatics.setFont(new java.awt.Font("Segoe UI",0,9));
			velozStatics.setForeground(new java.awt.Color(0,255,0));
			velozStatics.setText(Veloz.getEstadisticas());
			velozStatics.setBounds(80, 88, 164, 64);
			velozStatics.setBackground(new java.awt.Color(155,155,155));
			velozStatics.setOpaque(true);
			
		}
		{
			labelNormal = new JLabel();
			main.add(labelNormal);
			ImageIcon icon = new ImageIcon(Nave.class.getClassLoader().getResource("ProyectoX/img/Jugador/normal.gif"));
			labelNormal.setIcon(icon);
			labelNormal.setBounds(175 + icon.getIconWidth() / 2, 188, icon.getIconWidth(), 64);
			labelNormal.setOpaque(true);
			labelNormal.setBackground(new java.awt.Color(155,155,155));
			
			normalStatics = new JLabel();
			main.add(normalStatics);
			normalStatics.setFont(new java.awt.Font("Segoe UI",0,9));
			normalStatics.setForeground(new java.awt.Color(0,255,0));
			normalStatics.setText(Normal.getEstadisticas());
			normalStatics.setBounds(80, 188, 164, 64);
			normalStatics.setBackground(new java.awt.Color(155,155,155));
			normalStatics.setOpaque(true);
			
		}
		{
			labelResistente = new JLabel();
			main.add(labelResistente);
			ImageIcon icon = new ImageIcon(Nave.class.getClassLoader().getResource("ProyectoX/img/Jugador/resistente.gif"));
			labelResistente.setIcon(icon);
			labelResistente.setBounds(175, 288, icon.getIconWidth(), 64);
			labelResistente.setBackground(new java.awt.Color(155,155,155));
			labelResistente.setOpaque(true);
			
			resistenteStatics = new JLabel();
			main.add(resistenteStatics);
			resistenteStatics.setFont(new java.awt.Font("Segoe UI",0,9));
			resistenteStatics.setForeground(new java.awt.Color(0,255,0));
			resistenteStatics.setText(Resistente.getEstadisticas());
			resistenteStatics.setBounds(80, 288, 164, 64);
			resistenteStatics.setBackground(new java.awt.Color(155,155,155));
			resistenteStatics.setOpaque(true);
		}
		{
			atras = new JButton();
			main.add(atras);
			ImageIcon ii = new ImageIcon(Nave.class.getClassLoader().getResource("ProyectoX/img/Menu_barras/back.png"));
			atras.setIcon(new ImageIcon(ii.getImage().getScaledInstance(61,44,Image.SCALE_DEFAULT)));
			
			atras.setBounds(70, 461, 61, 44);
			atras.setBackground(new java.awt.Color(0,0,0));
			atras.setFont(new java.awt.Font("Segoe UI",0,20));
			atras.setForeground(new java.awt.Color(0,255,0));
			atras.addActionListener(new OyenteAtras());
		}
	
}
	
	private class OyenteQuit implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
            int verd;
            
            JOptionPane dialogo = new JOptionPane();
            
            verd = dialogo.showConfirmDialog(null, "Desea salir del juego?", "Atencion",JOptionPane.YES_NO_OPTION);
			
            if(verd == 0){
            	System.exit(0);
            }
            
		}
	}
		
	private class OyenteNuevoJuego implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
            principal.setVisible(false);
            setVisible(false);
            initMain();
            setVisible(true);
            
		}
	}
	
	private class OyenteVeloz implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			main.setVisible(false);
			setVisible(false);
			initGame(1);
			setVisible(true);
			
		}
	}
	
	private class OyenteNormal implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			main.setVisible(false);
			setVisible(false);
			initGame(2);
			setVisible(true);
            
		}
	}
	
	private class OyenteResistente implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			main.setVisible(false);
			setVisible(false);
			initGame(3);
			setVisible(true);
           
		}
	}
	
	private class OyenteAtras implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			main.setVisible(false);
			initPrimerPanel();
		}
	}
	
	private class OyenteVolver implements ActionListener{

		@Override
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
            }
            
		}
	}
	
	
	private void cerrarJuego() {
		this.dispose();
		System.exit(0);
		
	}
	
}
