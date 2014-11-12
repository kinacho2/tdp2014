package ProyectoX.Minds;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.JPanel;

import ProyectoX.Disparos.Disparo;
import ProyectoX.Explosiones.Explosion;
import ProyectoX.Mapas.Mapa;
import ProyectoX.Naves.Enemigos.Enemigo;
import ProyectoX.Naves.Jugador.Jugador;
import ProyectoX.Naves.Jugador.Normal;
import ProyectoX.Naves.Jugador.Resistente;
import ProyectoX.Naves.Jugador.Veloz;
import ProyectoX.Paneles.PanelJugador;
import ProyectoX.PowerUps.PowerUp;
import ProyectoX.Sound.Reproductor;

public class Mind implements ActionListener {
	
    private Timer timer;
    private Jugador jugador;
    private final int delay = 15;
    private Mapa mapa;
    private PanelJugador panel;
    private boolean stop;
    private int delayFinal;
    private int contDelay;
    private Reproductor reproductor;
   

    public Mind(PanelJugador panel, int select) {
    	
    	this.panel = panel;
        
        
        crearJugador(select);
        stop = false;
        delayFinal = jugador.getExplosion().getDelay();
        contDelay = 0;
        
        timer = new Timer(delay, this);
        timer.start();
    }

    public void actionPerformed(ActionEvent e) {
    	
    	disparosJugador();
    	
    	disparosEnemigos();
    	
    	colisionPowerUp();
    	
    	verificarColisionDefensa(jugador.getDefensa());
        
        if(jugador.getVisible()) {
        	jugador.move(); 
        } else {
        	if(!stop) {
        		mapa.addExposion(jugador.getExplosion());
        		stop = true;
        	} else {
        		contDelay += delay;
        		if(contDelay >= delayFinal){
        			stop = false;
        			jugador.reset();
        			contDelay = 0;
        			
        		//	mapa.stop();
        		//	JOptionPane dialog = new JOptionPane();
        		//	dialog.showMessageDialog(null, puntaje.getText() + "\nVuelva al menu de seleccion para comenzar nuevamente", "FIN DEL JUEGO", JOptionPane.INFORMATION_MESSAGE);
        		}
        	}
        }
        if(jugador.getVida() <= 0){
        	jugador.setVisible();
        }
       	panel.repaint();  
    }
    
    private synchronized void colisionPowerUp() {
		ArrayList powers = mapa.getPowers();
		
		for (int j = 0; j < powers.size(); j++ ) {
			PowerUp pw = (PowerUp) powers.get(j);
	        if (pw.isVisible()) {
	        	pw.move();
	           	if(pw.colision(jugador)){
	           		pw.setVisible();
	            }
	        }
	        else{
	        	powers.remove(j);
	        }
	    }
	}

	// Mueve los disparos visibles de jugador y los que no son removidos; además verifica si algún disparo colisionó
    // con algún enemigo
    private synchronized void disparosJugador() {
    	// arreglo de disparos de jugador y de enemigos que se encuentran en el mapa
        ArrayList ms = mapa.getMisilesJugador();
        ArrayList enemigos = mapa.getEnemies();
        
        // mueve los misiles del jugador y remueve los que no estan visibles  
        for (int i = 0; i < ms.size(); i++) {
        	Disparo m = (Disparo) ms.get(i);
            if (m.isVisible())
                m.move();
            else 
            	mapa.removerDisparoJugador(i);
            
            // verifica si algun misil del jugador colisiono a un enemigo
            for (int j = 0; j < enemigos.size(); j++) {
            	Enemigo enemigo = (Enemigo) enemigos.get(j);
            	
            	if (m.colision(enemigo)) {
                	if (m.isVisible()){
                		enemigo.setVida(m.getDamage());
                	}
                	panel.actualizarPuntaje();
                	m.setVisible();
                	mapa.addExposion(m.newExplosion(enemigo.getY() + enemigo.getHeight()));
                }
                
            }
        }
  	
    }
    
    // Mueve los disparos visibles de los enemigos y los que no son removidos; además verifica si algún disparo colisionó
    // con algún enemigo
    private synchronized void disparosEnemigos() {
    	
    	// arreglo de disparos de los enemigos que se encuentran en el mapa
    	ArrayList ms = mapa.getMisilesEnemigos();
    	
       	Jugador aux ;
       	
        
        
        // verifica si algun misil del enemigo colisiono con el jugador o alguna de sus defensas
       
        for (int j = 0; j < ms.size(); j++ ) {
            Disparo misil = (Disparo) ms.get(j);
            misil.move();
            aux = mapa.getJugador();
       		if(aux.getVisible())
           		if (misil.colision(aux)) {
           			if (misil.isVisible()) {
           				aux.setVida(misil.getDamage());
           				misil.setVisible();
           				mapa.addExposion(misil.newExplosion(aux.getY()));
	            	}
           			else{
           				ms.remove(misil);
           			}
	            }
           		
	        
       	}
       	
    	
    }
    
    private synchronized void verificarColisionDefensa(Jugador def){
    	if(def != null){
    		ArrayList enemigos = mapa.getEnemies();
    		for (int j = 0; j < enemigos.size(); j++) {
            	Enemigo enemigo = (Enemigo) enemigos.get(j);
            
            	if(def != null && enemigo.colision(def)){
            		int danio = def.getDamageColision();
            		def.setVida(enemigo.getDamageColision());
            		enemigo.setVida( danio);
            	}
    		}
    	}
    }

    
    
    public Jugador getJugador() {
    	return jugador;
    }
    
    // Selección del tipo de nave
    public void crearJugador(int select) {
    	if (select == 1) {
        	jugador = new Veloz();
    	} else if (select == 2) {
        	jugador = new Normal();
    	} else if (select == 3) {
        	jugador = new Resistente();
    	}
        jugador.setMinHeight(530);
        
    }
    
    public void stop() {
    	timer.stop();
    }

	public void addReproductor(Reproductor rep) {
		reproductor = rep;
		jugador.addReproductor(reproductor);
		
	}
	
	public void setMapa(Mapa map){
		mapa = map;
		panel.setMapa(map);
		map.setJugador(jugador);
		jugador.setMapa(map);
	}

}
