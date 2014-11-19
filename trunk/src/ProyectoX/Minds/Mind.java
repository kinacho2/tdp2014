package ProyectoX.Minds;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;
import ProyectoX.Disparos.Disparo;
import ProyectoX.Mapas.Mapa;
import ProyectoX.Naves.Jugador.Jugador;
import ProyectoX.Naves.Jugador.Normal;
import ProyectoX.Naves.Jugador.Resistente;
import ProyectoX.Naves.Jugador.Veloz;
import ProyectoX.Paneles.PanelJugador;
import ProyectoX.PowerUps.PowerUp;
import ProyectoX.Sound.Reproductor;

/**
 * Hilo que controla los movimientos del jugador, verifica colisiones entre el jugador y los disparos enemigos
 * las colisiones con powerUP y la muerte del jugador
 * Ademas se encarga de repintar el PanelJugador
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */

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
    private boolean pause = false;
   
    /**
     * Constructor de la clase Mind
     * @param panel JPanel donde se dibuja el jugador, los disparos enemigos, los objetosm, los powerup y las explosiones
     * @param select indica el tipo de nave elegida por el jugador
     */

    public Mind(PanelJugador panel, int select) {
    	
    	this.panel = panel;
        
        
        crearJugador(select);
        stop = false;
        delayFinal = jugador.getExplosion().getDelay();
        contDelay = 0;
        
        timer = new Timer(delay, this);
        timer.start();
    }
    
    /**
     * define actionPerformed(ActionEvent e) de la interface ActionListener
     * controla el hilo principal del juego, mueve los objetos y verifica las colisiones que le corresponde
     * (PowerUP, Jugador y Defensa, Disparos enemigos)
     * controla tambien las muertes del juegador
     */

    public void actionPerformed(ActionEvent e) {
    	
    	if(!pause){
	    	disparosEnemigos();
	    	
	    	colisionPowerUp();
	        
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
	        		}
	        	}
	        }
	        if(jugador.getVida() <= 0){
	        	jugador.setVisible();
	        }
	        if(jugador.getHearts()<0){
	        	mapa.gameOver();
	        }
	       	panel.repaint();  
    	}
    }
    
    /**
     * verifica si el Jugador colisiona con un PowerUP
     */
    
    private synchronized void colisionPowerUp() {
    	//Arreglo de PowerUP
		ArrayList powers = mapa.getPowers();
		
		for (int j = 0; j < powers.size(); j++ ) {
			PowerUp pw = (PowerUp) powers.get(j);
	        if (pw!=null && pw.isVisible()) {
	        	pw.move();
	           	if(pw.colision(jugador)){
	           		pw.setVisible();
	            }
	        }
		}
	    for (int j = 0; j < powers.size(); j++ ) {
			PowerUp pw = (PowerUp) powers.get(j);
			if(!pw.isVisible())
				powers.remove(j);
		}
	    
	}
    
    /**
     * Mueve los disparos visibles de los enemigos y los que no son removidos
     * además verifica si algún disparo colisiono con algún jugador
     */
    private synchronized void disparosEnemigos() {
    	
    	// arreglo de disparos de los enemigos que se encuentran en el mapa
    	ArrayList ms = mapa.getMisilesEnemigos();
    	
       	Jugador aux ;
       	
        Jugador def = jugador.getDefensa();
        
        // verifica si algun misil del enemigo colisiono con el jugador o alguna de sus defensas
       
        for (int j = 0; j < ms.size(); j++ ) {
            Disparo misil = (Disparo) ms.get(j);
            if(misil!=null){
	            misil.move();
	            aux = mapa.getJugador();
	       		if(aux.getVisible())
	           		if (misil!=null && misil.colision(aux)) {			
	           			aux.setVida(misil.getDamage());
	           			misil.setVisible();
	           			mapa.addExposion(misil.newExplosion(aux.getY()));
		            }
	       		if(def != null && def.getVisible())
	           		if ( misil.colision(def)) {
	           			def.setVida(misil.getDamage());
	           			misil.setVisible();
	           			mapa.addExposion(misil.newExplosion(def.getY()));
		           }
            }
       	}
        for (int j = 0; j < ms.size(); j++ ) {
            Disparo misil = (Disparo) ms.get(j);
            if(!misil.isVisible()){
            	ms.remove(j);
            }
        }
       	
    }
    
    /**
     * retorna el jugador principal del juego
     * @return instancia de Jugador
     */
    
    public Jugador getJugador() {
    	return jugador;
    }
    
    /**
     * Se crea un Jugador de acuerdo a la selección del tipo de nave
     * @param select indica el tipo de nave elegida
     */
    
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
    
    /**
     * detiene el hilo del juego
     */
    
    public void stop() {
    	timer.stop();
    }
    /**
     * setea un nuevo reproductor a la clase
     * @param rep de tipo Reproductor el nuevo reproductor de la clase
     */

	public void addReproductor(Reproductor rep) {
		reproductor = rep;
		jugador.addReproductor(reproductor);
		
	}
	
	/**
	 * setea un nuevo Mapa a la clase
	 * @param map de tipo Mapa nuevo mapa de la clase
	 */
	
	public void setMapa(Mapa map){
		mapa = map;
		panel.setMapa(map);
		map.setJugador(jugador);
		jugador.setMapa(map);
	}
	
	/**
	 * pausa o reanuda el juego
	 * @param arg booleano si es true se pausa el juego, si es false se reanuda
	 */

	public void pause(boolean arg){
		mapa.pause(arg);
		jugador.pause(arg);
		pause = arg;
	}
	
	/**
	 * silencia o reanuda el sonido
	 * @param arg booleano si es true se silencia el reproductor, caso contrario se inicia el sonido
	 */
	
	public void silencio(boolean arg){
		reproductor.setEnabled(arg);
	}
}
