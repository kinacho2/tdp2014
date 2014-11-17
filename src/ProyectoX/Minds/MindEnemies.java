package ProyectoX.Minds;

import java.util.ArrayList;
import java.util.Random;

import ProyectoX.Disparos.Disparo;
import ProyectoX.Mapas.Mapa;
import ProyectoX.Naves.Enemigos.Enemigo;
import ProyectoX.Naves.Enemigos.Jefes.Jefe;
import ProyectoX.Naves.Jugador.Jugador;
import ProyectoX.Paneles.PanelEnemies;
import ProyectoX.Sound.Reproductor;

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
	
	public MindEnemies(PanelEnemies p) {
		enemies = new ArrayList();
		panel = p;
		
		stop = false;
		
		ran=new Random(0);
		//mapa=map;
		
	}
	
	public void run() {
		try {
			while (!stop) {
				
				MindEnemies.sleep(20);
				
				if(!pause){
					enemies = mapa.getEnemies();
					//calcula la probabilidad de aparicion de los enemigos en pantalla
					
					disparosJugador();
					
					if(ran.nextInt(88)==0){
						Enemigo m = mapa.nextEnemigo();
						if(m!=null){
							enemies.add(m);
						}
					}
	
					//mueve y remueve, en caso de ser necesario, las naves enemigas
					
			        for (int i = 0; i < enemies.size(); i++ ) {
			            Enemigo m = (Enemigo) enemies.get(i);
			            m.move();
			            if (!m.getVisible()){
			            	enemies.remove(i);
			            	mapa.addExposion(m.getExplosion());
			            	
			            }
			            //dispara y aumenta un contador ciclico que retrasa la distancia entre la ejecucion de los disparos
			            m.disparar();
			            m.setDis();
			        }
			        panel.repaint();
					
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
				
			}
		
		} catch (InterruptedException e) {
			
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
            if (m.isVisible()){
                m.move();
                for (int j = 0; j < enemigos.size(); j++) {
                	Enemigo enemigo = (Enemigo) enemigos.get(j);
                	
                	if (m.colision(enemigo)) {
                    	if (m.isVisible()){
                    		enemigo.setVida(m.getDamage());
                    	}
                    	m.setVisible();
                    	mapa.addExposion(m.newExplosion(enemigo.getY() + enemigo.getHeight()));
                    }
                    
                }
            }
            else 
            	mapa.removerDisparoJugador(i);
            
            // verifica si algun misil del jugador colisiono a un enemigo
           
        }
  	
    }
	
	
	

	public ArrayList getEnemies() {
		return enemies;
	}
	
	public void setStop(){
		stop=true;
	}

	public void addBoss(Jefe boss){
		enemies.add(boss);
		ArrayList en = boss.getTorretas();
		jefe = true;
		
		for(int i=0; i<en.size(); i++){
			enemies.add(en.get(i));
		}
		
	}
	
	public void setMapa(Mapa map){
		mapa = map;
		panel.setMapa(map);
	}
	
	public void pause(boolean arg){
		pause = arg;
	}
	
	public void setReproductor(Reproductor rep){
		this.rep = rep;
	}

	public void setEstaJefe() {
		jefe = false;
		
	}
}

