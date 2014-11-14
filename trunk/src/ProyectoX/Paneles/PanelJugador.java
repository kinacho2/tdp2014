package ProyectoX.Paneles;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ProyectoX.Naves.Enemigos.Enemigo;
import ProyectoX.Naves.Jugador.Jugador;
import ProyectoX.PowerUps.PowerUp;
import ProyectoX.Disparos.Disparo;
import ProyectoX.Frames.Explosion;
import ProyectoX.Frames.Objeto;
import ProyectoX.Mapas.Mapa;
import ProyectoX.Minds.Mind;

public class PanelJugador extends JPanel implements ActionListener{

	private Mind mind;
	private Mapa mapa;
	private Jugador jugador;
	
	private JPanel bar;
	    
	private JLabel puntaje;
	private JLabel labelVida;
	private JLabel contadorEnemigos;
	private boolean pause = false;
	
	public PanelJugador(Mapa map, int select){
		mind = new Mind( this, select);
		map.setMind(mind);
		jugador = mind.getJugador();
		mapa = map;
		setOpaque(false);
        
        setLayout(null);
        
        setFocusable(true);
        setDoubleBuffered(true);
        addKeyListener(new TAdapter());
        
        
	}
	
	public void setBar(JPanel bar) {
		this.bar = bar;
        puntaje = new JLabel("Puntaje: "+ 0);
		puntaje.setBounds(800-150, 0, 350, 50);
		bar.add(puntaje);
		
		labelVida = new JLabel("Vida: " + jugador.getVida());
		labelVida.setBounds(800-250, 0, 100, 50);
		bar.add(labelVida);
		
		contadorEnemigos = new JLabel("Enemigos restantes: " + mapa.cantEnemies());
		contadorEnemigos.setBounds(800-500, 0, 250, 50);
		bar.add(contadorEnemigos);
		
		labelVida.setFont(new java.awt.Font("Segoe UI",0,20));
		labelVida.setForeground(new java.awt.Color(255,0,0));
		puntaje.setFont(new java.awt.Font("Segoe UI",0,20));
		puntaje.setForeground(new java.awt.Color(0,0,255));
		contadorEnemigos.setFont(new java.awt.Font("Segoe UI",0,20));
		contadorEnemigos.setForeground(new java.awt.Color(0,255,0));
    
    }
	
	public synchronized void paint(Graphics g) {
        super.paint(g);
        
        Graphics2D g2d = (Graphics2D) g;
        
        ArrayList ms = mapa.getMisilesEnemigos();
        
        //mueve, repinta y elimina los disparos en caso de que ya no sean visibles
        for (int j = 0; j < ms.size(); j++ ) {
            Disparo misil = (Disparo) ms.get(j);
	        if(misil.isVisible()) {
		        g2d.drawImage(misil.getImage(), misil.getX(), misil.getY(), this);
	        } 
            
        }
        
        // Pinta el jugador
        if (jugador.getVisible()){
        	g2d.drawImage(jugador.getImage(), jugador.getX(), jugador.getY(), this);
        }
        
        
        Jugador aux = jugador.getDefensa();
        
        if (aux != null){
        	if(aux.getVisible()){
        		g2d.drawImage(aux.getImage(), aux.getX(), aux.getY(), this);
        	}
        	else
        		jugador.dropDefensa();
        }
        
        //repinta los powerUps
        ms = mapa.getPowers();
        
        for (int j = 0; j < ms.size(); j++ ) {
            PowerUp pw = (PowerUp) ms.get(j);
            g2d.drawImage(pw.getImage(), pw.getX(), pw.getY(), this);
        }
        
        
        //pinta las explosiones	        
        ms = mapa.explosiones();
        for (int i = 0; i < ms.size(); i++ ) {
            Explosion m = (Explosion) ms.get(i);
            g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
            if(!m.getVisible()) {
            	ms.remove(i);
            }
        }
        
        ms = mapa.getObjeto();
        
        for(int i = 0; i<ms.size(); i++){
        	Objeto o = (Objeto) ms.get(i);
        	if(o.getVisible()){
                g2d.drawImage(o.getImage(), o.getX(), o.getY(), this);
                o.move();
        	}
        	else
        		ms.remove(i);
        }
        
      	puntaje.setText("Puntaje: " + jugador.getPuntaje());
        labelVida.setText("Vida: " + jugador.getVida());
		contadorEnemigos.setText("Enemigos restantes: " + mapa.cantEnemies());
         
        
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
        
       
	}
	
	
	private class TAdapter implements KeyListener {

        public void keyReleased(KeyEvent e) {
        	jugador.keyReleased(e);
        	
        }

        public void keyPressed(KeyEvent e) {
        	jugador.keyPressed(e);
        	
        	 int key = e.getKeyCode();
             
             if (key == KeyEvent.VK_P) {
            	 if(!pause)
            		 pause = true;
            	 else
            		 pause = false;
            	 mind.pause(pause);
             }
        	
        }

		public void keyTyped(KeyEvent arg0) {
			// Es necesaria poner esta funcion porque implementa a una interfaz TAdapter, no realiza nada
		}
    }

	public void actualizarPuntaje(){
        puntaje.setText("Puntaje: " + jugador.getPuntaje());

	}
	
	public void actionPerformed(ActionEvent event) {
		
		mind.actionPerformed(event);
	}

	public void setMapa(Mapa map) {
		mapa = map;
		
	}
	
}
