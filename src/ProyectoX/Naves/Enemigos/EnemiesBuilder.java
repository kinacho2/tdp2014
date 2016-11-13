package ProyectoX.Naves.Enemigos;

import java.util.Random;

/**
 * Clase que se encarga de crear enemigos en 
 * forma aleatoria con un indice de probabilidad dado
 */

public class EnemiesBuilder {
	
	private Random rn;
	private int bombardero = 0;
	
	/**
	 * constructor de la clase EnemiesBuilder
	 * @param ran es la semilla para crear una instancia de Random
	 */
	
	public EnemiesBuilder(int ran){
		rn = new Random(ran);
	}
	
	/**
	 * retorna un enemigo generado de manera aleatoria
	 * @return instancia de Enemigo que puede ser una instancia dinamica de Artillero, Basico, Bombardero o Kamikaze
	 */
	
	public Enemigo getNextEnemigo(){
		Enemigo m;
		int probabilidad = rn.nextInt(100);
		
		if(probabilidad % 5 == 0)
			m = new Kamikaze(probabilidad % 15 == 0);
		else if(probabilidad % 13 == 0){
			if(bombardero<2){
				m = new Bombardero(probabilidad % 2 == 0);
				bombardero  ++;
			}
			else
				m = new Artillero(false);
		}
		else if(probabilidad % 5 == 1) 
			m = new Artillero((probabilidad % 20) == 1);
		else
			m = new Basico((probabilidad % 9) == 0);
		
		m = new Basico(true);
		
        return m;
	}
	
	public void bombarderoFuera(){
		bombardero --;
	}

}
