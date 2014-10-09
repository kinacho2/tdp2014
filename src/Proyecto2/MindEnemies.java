package Proyecto2;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Random;

public class MindEnemies extends Thread  {

	protected ArrayList enemies;
	protected PanelEnemies panel;
	
	
	
	public MindEnemies(PanelEnemies p) {
		enemies = new ArrayList();
		panel = p;
		Enemigo n = new Bombardero();
		enemies.add(n);
	}


	public void run() {
		boolean stop = false;
		try {
			while (!stop) {
				
				
				MindEnemies.sleep(1000);
				
				
				panel.repaint();
				
				for (int i = 0; i < enemies.size(); i++ ) {
		            Enemigo m = (Enemigo) enemies.get(i);
		            m.move();
		        }
				
				/*
				 Random rand = new Random();
				 int num = rand.nextInt(2);
				 if (num < 1)
					 enemies.remove(0);
				 */

				
			}
		
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	public ArrayList getEnemies() {
		// TODO Auto-generated method stub
		return enemies;
	}
	

}
