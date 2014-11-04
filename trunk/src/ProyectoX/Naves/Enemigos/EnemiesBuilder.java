package ProyectoX.Naves.Enemigos;

import java.util.Random;

public class EnemiesBuilder {
	
	private Random rn;
	
	public EnemiesBuilder(int ran){
		rn = new Random(ran);
	}
	
	
	public Enemigo getNextEnemigo(){
		Enemigo m;
		int probabilidad = rn.nextInt(100);
		
		if(probabilidad % 5 == 0)
			m = new Kamikaze(probabilidad % 15 == 0);
		else if(probabilidad % 13 == 0)
			m = new Bombardero(probabilidad % 2 == 0);
		else if(probabilidad % 5 == 1) 
			m = new Artillero((probabilidad % 30) == 1);
		else
			m = new Basico((probabilidad % 11) == 0);
		
        return m;
	}
	



}
