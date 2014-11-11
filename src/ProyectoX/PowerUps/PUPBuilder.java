package ProyectoX.PowerUps;

import java.util.Random;

public class PUPBuilder {

	private Random rn;
	
	public PUPBuilder(int rn){
		this.rn = new Random(rn);
	}
	
	public PowerUp getPowerUpRandom(int x, int y){
		PowerUp up;
		int aux = rn.nextInt(100);
		if(aux >= 0 && aux < 13){
			up = new Ayudante(x, y);
		}else if(aux < 26){
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
		//up = new Ayudante(x,y);
		
		return up;
	}
	
}
