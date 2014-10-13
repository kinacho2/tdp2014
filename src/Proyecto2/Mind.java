package Proyecto2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.Timer;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Mind extends JPanel implements ActionListener {
	
    private Timer timer;
    private Jugador jugador;
    private final int delay = 15;
    private Mapa mapa;
    private boolean stop;
    private int delayFinal;
    private int contDelay;

    public Mind(Mapa map,int select) {
    	setOpaque(true);
        setSize(400, 300);
        setLayout(null);
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        mapa = map;
        crearJugador(select);
        stop = false;
        delayFinal = jugador.getExplosion().getDelay();
        contDelay = 0;
        timer = new Timer(delay, this);
        timer.start();
        
    }
    
    public void paint(Graphics g) {
	        super.paint(g);

	        Graphics2D g2d = (Graphics2D)g;
	        if (jugador.getVisible())
	        	g2d.drawImage(jugador.getImage(), jugador.getX(), jugador.getY(), this);
	        
	        ArrayList ms = mapa.getMisilesJugador();
	        
	        
	        //repinta los disparos
	        for (int i = 0; i < ms.size(); i++ ) {
	            Disparo m = (Disparo) ms.get(i);
	            g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
	        }
	        
	        //repinta las explosiones
	        
	        ms = mapa.explosiones();
	        for (int i = 0; i < ms.size(); i++ ) {
	            Explosion m = (Explosion) ms.get(i);
	            g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
	            m.setTime(delay);
	            if(!m.getVisible()){
	            	ms.remove(i);
	            }
	        }
	
	        Toolkit.getDefaultToolkit().sync();
	        g.dispose();
    }

    public void actionPerformed(ActionEvent e) {
        ArrayList ms = mapa.getMisilesJugador();
        ArrayList enemigos = mapa.getEnemies();
        
        //mueve los misiles del jugador y remueve los que no estan visibles
        
        for (int i = 0; i < ms.size(); i++) {
        	Disparo m = (Disparo) ms.get(i);
            if (m.isVisible()) 
                m.move();
            else 
            	mapa.removerDisparoJugador(i);
            
            //verifica si algun misil del jugador colisiono a un enemigo
            
            for (int j = 0; j < enemigos.size(); j++) {
            	Enemigo enemigo = (Enemigo) enemigos.get(j);
                if (m.colision(enemigo)) {
                	if (m.isVisible())
                		enemigo.setVida(m.getDaño());
                	m.setVisible();
                	mapa.addExposion(m.newExplosion());
                }
            }
        }
        
        ms = mapa.getMisilesEnemigos();
        
        //verifica si algun misil del enemigo colisiono con el jugador
        
        for (int j = 0; j < ms.size(); j++ ) {
            Disparo misil = (Disparo) ms.get(j);
            if (misil.colision(jugador)){
            	if (misil.isVisible()) 
            		jugador.setVida(misil.getDaño());
            	misil.setVisible();
            	mapa.addExposion(misil.newExplosion());
            }
        }
        
        
        if(jugador.getVisible()){
        	jugador.move();
            
        }
        else{
        	if(!stop){
        		mapa.addExposion(jugador.getExplosion());
        		stop = true;
        	}
        	else{
        		contDelay += delay;
        		if(contDelay >= delayFinal)
        			mapa.stop();
        	}
        }
        
       	repaint();  
       
    }

    private class TAdapter implements KeyListener {

        public void keyReleased(KeyEvent e) {
        	jugador.keyReleased(e);
        	if(e.getKeyCode() == KeyEvent.VK_SPACE){
        		jugador.setDisCero();
            }
        }

        public void keyPressed(KeyEvent e) {
        	jugador.keyPressed(e);
        	if(e.getKeyCode() == KeyEvent.VK_SPACE){
        		jugador.setDis();
            }
        	
        }

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
    }
    
    public Jugador getJugador(){
    	return jugador;
    }
    
    public void crearJugador(int select){
    	if(select==1){
        	jugador = new Veloz();
    	}
    	if(select==2){
        	jugador = new Normal();
    	}
    	if(select==3){
        	jugador = new Resistente();
    	}
        jugador.setMapa(mapa);
        mapa.setJugador(jugador);
    }
    
    public void stop(){
    	timer.stop();
    }

}
