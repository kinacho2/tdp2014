package Proyecto2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
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
	
	private JPanel principal;
	private JButton resistente;
	private JButton atras;
	private JLabel labelResistente;
	private JLabel labelNormal;
	private JLabel labelVeloz;
	private JButton normal;
	private JButton veloz;
	private JButton quit;
	private JButton newGame;
	private JPanel main;
	private JPanel game;
	private Mind mind;
	private JPanel bar;
	

	 public Aplication() {
		 
		initGUI();
    }

	    public static void main(String[] args) {
	        new Aplication();
	        
	    }
	    
	    private void initGame(int select){
	    	
	    	game = new JPanel();
	    	
	    	Mapa map = new Mapa();
			getContentPane().add(game, BorderLayout.CENTER);
			game.setBackground(new java.awt.Color(0,0,0));
			{
				mind = new Mind(map,select);
				game.add(mind);
				mind.setLayout(null);
				mind.setPreferredSize(new java.awt.Dimension(800, 520));
				
			}
			{
				bar = new JPanel();
				game.add(bar);
				bar.setLayout(null);
				bar.setPreferredSize(new java.awt.Dimension(800, 50));
				bar.setBackground(new java.awt.Color(25,100,25));
				mind.setBar(bar);
			}
			
			
	        
	        
	        // Mente de los enemigos
	        PanelEnemies panelEnemies = new PanelEnemies(mind,map);
	        mind.add(panelEnemies);
	        panelEnemies.setBounds(0, 0, 800, 600);
	        panelEnemies.setJugador(mind.getJugador());
	        
	        // Establece las Mentes al mapa
	        map.setMind(mind);
	        map.setMindEnemies(panelEnemies.getMindEnemies());
	       
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setSize(800, 600);
	        setLocationRelativeTo(null);
	        setTitle("R - Type");
	        setResizable(false);
	        setVisible(true);
	        mind.repaint();
	        // Inicia el hilo de los Enemigos
	        
	        panelEnemies.getMindEnemies().start();
			
	        
		}
	    
	    private void initGUI() {
			
	    	initPrimerPanel();
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setSize(800, 600);
	        setLocationRelativeTo(null);
	        setTitle("R - Type");
	        setResizable(false);
	        setVisible(true);
		
	}
	
	private void initPrimerPanel(){
		principal = new JPanel();
		getContentPane().add(principal);
		principal.setLayout(null);
		principal.setBackground(new java.awt.Color(0,0,0));
		{
			newGame = new JButton();
			principal.add(newGame);
			newGame.setText("NEW GAME");
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
			quit.setText("QUIT");
			quit.setBounds(400 - 288/2, 177, 288, 50);
			quit.setBackground(new java.awt.Color(0,0,0));
			quit.setForeground(new java.awt.Color(0,255,0));
			quit.setPreferredSize(new java.awt.Dimension(14, 7));
			quit.setFont(new java.awt.Font("Segoe UI",0,20));
			quit.setBorder(BorderFactory.createCompoundBorder(null, null));
			quit.addActionListener(new OyenteQuit());
		}
	}
	
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
			labelVeloz.setIcon(new ImageIcon(Nave.class.getClassLoader().getResource("img/Jugador/veloz.gif")));
			labelVeloz.setBounds(174, 88, 64, 64);
			labelVeloz.setBackground(new java.awt.Color(155,155,155));
			labelVeloz.setOpaque(true);
		}
		{
			labelNormal = new JLabel();
			main.add(labelNormal);
			labelNormal.setIcon(new ImageIcon(Nave.class.getClassLoader().getResource("img/Jugador/normal.gif")));
			labelNormal.setBounds(175, 188, 64, 64);
			labelNormal.setOpaque(true);
			labelNormal.setBackground(new java.awt.Color(155,155,155));
		}
		{
			labelResistente = new JLabel();
			main.add(labelResistente);
			labelResistente.setIcon(new ImageIcon(Nave.class.getClassLoader().getResource("img/Jugador/resistente.gif")));
			labelResistente.setBounds(175, 288, 64, 64);
			labelResistente.setBackground(new java.awt.Color(155,155,155));
			labelResistente.setOpaque(true);
		}
		{
			atras = new JButton();
			main.add(atras);
			ImageIcon ii = new ImageIcon(Nave.class.getClassLoader().getResource("img/Menu _barras/back.png"));
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
	
	
	
}
