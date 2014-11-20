package ProyectoX.PowerUps;

import java.net.URL;
import java.util.Random;
import javax.swing.ImageIcon;
import ProyectoX.Naves.Jugador.Jugador;


/**
 * PowerUP que le setea aleatoriamente un efecto al Jugador
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */

public class PRandom extends PowerUp{

	protected static final URL url = (PowerUp.class.getClassLoader().getResource("ProyectoX/img/PUP/random.png"));
	
	private int rn;
	
	/**
	 * constructor de la clase PRandom
	 * @param x coordenada x
	 * @param y coordenada y
	 */
	
	public PRandom(int x, int y) {
		super(x, y, new ImageIcon(url));
		Random ran = new Random();
		this.rn = ran.nextInt(100);
	}

	/**
	 * crea un PowerUP random y aplica su efecto al Jugador
	 * @param jugador instancia actual de Jugador
	 */
	
	protected void efecto(Jugador jugador) {
		PowerUp up;
		int aux = rn;
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
		}else{
			up = new Vida(x, y);
		}
		up.efecto(jugador);
	}
}
