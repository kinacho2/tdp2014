package ProyectoX.Frames_Minds;

import java.util.ArrayList;
import java.util.Random;

import ProyectoX.Mapa;
import ProyectoX.Naves.Enemigos.Enemigo;
import ProyectoX.Naves.Enemigos.Jefes.Jefe;
import ProyectoX.Naves.Jugador.Jugador;

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
		
		ran=new Random(0);
		mapa=map;
		
	}
	
	public void run() {
		try {
			while (!stop) {
				enemies = mapa.getEnemies();
				MindEnemies.sleep(100);
				
				//calcula la probabilidad de aparicion de los enemigos en pantalla
				
				if(ran.nextInt(20)==0){
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
	
	public void setStop(){
		stop=true;
	}

	public void addBoss(Jefe boss){
		enemies.add(boss);
		ArrayList en = boss.getTorretas();
		
		for(int i=0; i<en.size(); i++){
			enemies.add(en.get(i));
		}
		
	}
}
