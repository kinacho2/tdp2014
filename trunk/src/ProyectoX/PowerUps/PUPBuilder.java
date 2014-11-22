package ProyectoX.PowerUps;

import java.util.Random;

/**
 * Esta clase se encarga de crear PowerUP al azar 
 * dependiendo de la semilla pasada por parametro
 * y una probabilidad de aparicion dependiente del 
 * tipo de PowerUP 
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */
public class PUPBuilder {

	private Random rn;
	/**
	 * Constructor de la clase PUPBuilder
	 * @param rn semilla inicial del Random
	 */
	public PUPBuilder(int rn){
		this.rn = new Random(rn);
	}
	
	/**
	 * crea un PowerUP aleatorio en las coordenadas x,y y lo retorna
	 * @param x coordenada x
	 * @param y coordenada y
	 * @return instancia de PowerUP
	 */
	
	public PowerUp getPowerUpRandom(int x, int y){
		PowerUp up;
		int aux = rn.nextInt(100);
		if(aux >= 0 && aux < 12){
			up = new Ayudante(x, y);
		}else if(aux < 25){
			up = new Escudo(x, y);
		}else if(aux < 30){
			up = new Bomba(x, y);
		}else if(aux < 45){
			up = new Multiplicador(x, y);
		}else if(aux < 60){
			up = new Laser(x, y);
		}else if(aux < 75){
			up = new Wave(x, y);
		}else if(aux < 80){
			up = new PRandom(x, y);
		}else{
			up = new Vida(x, y);
		}
		up = new Escudo(x,y);
		return up;
	}
	
}
