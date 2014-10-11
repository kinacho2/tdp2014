package Proyecto2;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Random;

public class MindEnemies extends Thread  {

	protected ArrayList enemies;
	protected PanelEnemies panel;
	private Mapa mapa;
	private Random ran;
	
	
	public MindEnemies(PanelEnemies p, Mapa map) {
		enemies = new ArrayList();
		panel = p;
		ran=new Random(5);
		mapa=map;
		
	}


	public void run() {
		boolean stop = false;
		try {
			while (!stop) {
				MindEnemies.sleep(150);
				if(ran.nextInt(20)==0){
					Enemigo m = mapa.nextEnemigo();
					if(m!=null){
						enemies.add(m);
					}
				}

		        for (int i = 0; i < enemies.size(); i++ ) {
		            Enemigo m = (Enemigo) enemies.get(i);
		            m.move();
		            if (!m.getVisible()){
		            	enemies.remove(i);
		            }
		        m.disparar();
		        m.setDis();
		           
		        }
		        mapa.setEnemies(enemies);
				panel.repaint();
			}
		
		} catch (InterruptedException e) {
			//
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
	

}
