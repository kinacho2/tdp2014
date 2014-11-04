package ProyectoX.Mapas;

import java.util.Random;

import ProyectoX.Naves.Enemigos.EnemiesBuilder;
import ProyectoX.Naves.Enemigos.Enemigo;
import ProyectoX.Naves.Enemigos.Jefes.Jefe;
import ProyectoX.Naves.Enemigos.Jefes.JefeTanque;
import ProyectoX.PowerUps.PUPBuilder;

public class Nivel_I extends Mapa{
	
	public Nivel_I(){
		super();
		rn = new Random(7);
		cantEnemies = 101;
		power = new PUPBuilder(7);
		enBuilder = new EnemiesBuilder(5);
	}

	
	public Enemigo nextEnemigo() {
		Enemigo m;
		if(indiceEnemigos < cantEnemies) {	
			m = super.nextEnemigo();    
		} else {
			if(!jefe){
				m = new JefeTanque();
				mindEnemies.addBoss((Jefe)m);
				jefe = true;
				m.setJugador(jugador);
				m.setMapa(this);
				m = null;
				
			}
			else{
				m = null;
			}
		}
		
		return m;
	}
	
	
}
