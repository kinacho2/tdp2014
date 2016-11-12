package ProyectoX.Minds;

import java.util.ArrayList;
import java.util.Random;

import ProyectoX.Disparos.Disparo;
import ProyectoX.Mapas.Mapa;
import ProyectoX.Naves.Enemigos.Enemigo;
import ProyectoX.Naves.Enemigos.Jefes.Jefe;
import ProyectoX.Paneles.PanelEnemies;
import ProyectoX.Sound.Reproductor;

/**
 * Hilo que controla los movimientos de los enemigos, sus acciones, 
 * las colisiones entre los enemigos y los disparos del jugador,
 * Se encarga de repintar el PanelEnemigo y
 * ademas de verificar la muerte del Jefe y cambiar de nivel
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */

public class MindEnemies extends Thread  {

	
	protected ArrayList enemies;
	protected PanelEnemies panel;
	private Mapa mapa;
	private Random ran;
	private boolean stop;
	private boolean jefe = false;
	private boolean muerteJefe = false; 
	private long init;
	private int delay = 8000;
	private boolean pause = false;
	private Reproductor rep;
	private String outro = "/ProyectoX/sounds/music/outro.mp3";
	private String bosscoming = "/ProyectoX/sounds/bosscoming.mp3";
	
	private boolean sonido = false;
	private long wait = 0;
	private boolean pauseTime;
	
	
	/**
	 * Constructor de la clase MindEnemies
	 * @param p JPanel donde se dibujan los enemigos, el fondo y los disparos del jugador
	 */
	public MindEnemies(PanelEnemies p) {
		enemies = new ArrayList();
		panel = p;
		
		stop = false;
		
		ran=new Random(0);
		//mapa=map;
		
	}
	
	/**
	 * redefine run() de la clase Thread
	 * controla el bucle de los enemigos, mueve y remueve los objetos que le corresponden
	 * (Enemigos, Disparos del jugador y Jefe)
	 */
	
	public void run() {
		try {
			while (!stop) {
				
				MindEnemies.sleep(20);
				
				if(!pause){
					enemies = mapa.getEnemies();
					//calcula la probabilidad de aparicion de los enemigos en pantalla
					
					disparosJugador();
					//TODO
					if(!pauseTime)
						if(ran.nextInt(75)==0){
							Enemigo m = mapa.nextEnemigo();
							if(m!=null){
								enemies.add(m);
							}
						}
	
					//mueve y remueve, en caso de ser necesario, las naves enemigas
					//TODO
					if(!pauseTime)
			        for (int i = 0; i < enemies.size(); i++ ) {
			            Enemigo m = (Enemigo) enemies.get(i);
			            if(m!=null && m.getVisible()){
				            m.move();
				            //dispara y aumenta un contador ciclico que retrasa la distancia entre la ejecucion de los disparos
				            m.disparar();
				            m.setDis();
			            }
			        }
			        
			        for (int i = 0; i < enemies.size(); i++ ) {
			            Enemigo m = (Enemigo) enemies.get(i);
			            if(m!=null){
				            if (!m.getVisible()){
				            	enemies.remove(i);
				            	mapa.addExposion(m.getExplosion());
				            }
			            }
			        }
			        
			        
					
					if(jefe){
						if(enemies.size() == 0 && !muerteJefe){
							muerteJefe = true;
							init = System.currentTimeMillis();
						}
						if(muerteJefe){
							if(System.currentTimeMillis() - init > delay){
								rep.stop(100);
								rep.addSound(outro, true);
								panel.jefeMuerto();
								jefe = false;
								muerteJefe = false;
							}
						}
					}
				}
				panel.repaint();
				
			}
		
		} catch (InterruptedException e) {
			
		}
		
	}
	
	/**
	 *  Mueve los disparos visibles de jugador y los que no son removidos
	 *  además verifica si algún disparo colisiono con algún enemigo
	 */
	
    private synchronized void disparosJugador() {
    	// arreglo de disparos de jugador y de enemigos que se encuentran en el mapa
        ArrayList ms = mapa.getMisilesJugador();
        ArrayList enemigos = mapa.getEnemies();
        
        // mueve los misiles del jugador y remueve los que no estan visibles  
        for (int i = 0; i < ms.size(); i++) {
        	Disparo m = (Disparo) ms.get(i);
            if (m!=null && m.isVisible()){
                m.move();
                for (int j = 0; j < enemigos.size(); j++) {
                	Enemigo enemigo = (Enemigo) enemigos.get(j);
                	
                	if (m.colision(enemigo)) {
                    	if (m.isVisible()){
                    		enemigo.setVida(m.getDamage());
                    	}
                    	m.setVisible();
                    	if(!m.isVisible())
                    		mapa.addExposion(m.newExplosion(enemigo.getY() + enemigo.getHeight()));
                    }
                    
                }
            }
            
            
            // verifica si algun misil del jugador colisiono a un enemigo
           
        }
        
        for (int i = 0; i < ms.size(); i++) {
        	Disparo m = (Disparo) ms.get(i);
        	if(m!=null){
        		if(!m.isVisible()){
        			ms.remove(i);
        		}
        	}
        }
  	
    }
	
	/**
	 * retorna un ArrayList con los enemigos en pantalla
	 * @return ArrayList de Enemigo
	 */
	

	public ArrayList getEnemies() {
		return enemies;
	}
	
	/**
	 * le indica al hilo que debe detenerse
	 */
	
	public void setStop(){
		stop=true;
	}

	/**
	 * agrega un Jefe al arreglo de enemigos en pantalla y tambien sus torretas
	 * @param boss de tipo Jefe el ultimo Enemigo del nivel
	 * @throws InterruptedException 
	 */

	public void addBoss(Jefe boss){
		if(!sonido && !jefe){
			wait = System.currentTimeMillis();
			rep.addSound(bosscoming, false);
			sonido = true;
		}
		if(System.currentTimeMillis()-wait>=5000){
			enemies.add(boss);
			ArrayList en = boss.getTorretas();
			jefe = true;
			sonido = false;
			for(int i=0; i<en.size(); i++){
				enemies.add(en.get(i));
			}
		}
	}
	
	/**
	 * setea un nuevo Mapa a la clase 
	 * @param map nuevo mapa de la clase
	 */
	
	public void setMapa(Mapa map){
		mapa = map;
		panel.setMapa(map);
	}
	
	/**
	 * pausa o reanuda el hilo
	 * @param arg booleano si es true se pausa el juego, si es false se reanuda
	 */
	
	public void pause(boolean arg){
		pause = arg;
	}
	
	/**
	 * setea un nuevo Reproductor a la clase
	 * @param rep nuevo Reproductor de la clase
	 */
	
	public void setReproductor(Reproductor rep){
		this.rep = rep;
	}
	
	/**
	 * en caso de morir el jugador ante el Jefe se debe setear el booleano,
	 * que controla la aparicion del Jefe y su muerte, en false
	 */

	public void setEstaJefe() {
		jefe = false;
		sonido=false;
	}
	
	public boolean estaJefe() {
		return jefe;
		
	}

	public void pauseTime(boolean arg) {
		pauseTime = arg;
		
	}
}

