package Proyecto2;

import java.util.ArrayList;
import java.util.Random;

public class MindEnemies extends Thread  {

	
	protected ArrayList enemies;
	protected PanelEnemies panel;
	private Mapa mapa;
	private Random ran;
	private boolean stop;
	
	public MindEnemies(PanelEnemies p, Mapa map) {
		enemies = new ArrayList();
		panel = p;
		
		stop = false;
		
		ran=new Random(5);
		mapa=map;
		
	}
	
	public void run() {
		try {
			while (!stop) {
				
				MindEnemies.sleep(100);
				
				//calcula la probabilidad de aparicion de los enemigos en pantalla
				
				if(ran.nextInt(30)==0){
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
		        mapa.setEnemies(enemies);
				panel.repaint();
			}
		
		} catch (InterruptedException e) {
			
		}
	}

	public ArrayList getEnemies() {
		return enemies;
	}
	
	public void setJugador(Jugador jugador){
		for (int i = 0; i < enemies.size(); i++ ) {
            Enemigo m = (Enemigo) enemies.get(i);
            m.setJugador(jugador);
        }
		
	}
	
	public void setStop(){
		stop=true;
	}

}

