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
		if(aux >= 0 && aux < 11){
			up = new Ayudante(x, y);
		}else if(aux < 22){
			up = new Escudo(x, y);
		}else if(aux < 27){
			up = new Bomba(x, y);
		}else if(aux < 40){
			up = new Laser(x, y);
		}else if(aux < 53){
			up = new Multiplicador(x, y);
		}else if(aux < 66){
			up = new Wave(x, y);
		}else if(aux < 75){
			up = new PRocket(x, y);
		}else if(aux < 80){
			up = new Fantasma(x, y);
		}else if(aux < 85){
			up = new Tiempo(x,y);
		}
		else{
			up = new Vida(x, y);
		}
		
		/*
		up = new Escudo(x, y);
		/*
		if(aux%3==0)
			up = new Ayudante(x, y);
		if(aux%3==2)
		up = new Escudo(x, y);
		if(aux%3==1)
		up = new Laser(x, y);
/**/
		return up;
	}
	
}
