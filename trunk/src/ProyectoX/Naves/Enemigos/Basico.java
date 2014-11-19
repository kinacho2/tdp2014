package ProyectoX.Naves.Enemigos;

import java.net.URL;
import java.util.Random;
import javax.swing.ImageIcon;
import ProyectoX.Disparos.Disparo;
import ProyectoX.Naves.Nave;

/**
  * Basico es un Enemigo que aparece desde arriba en una coordenada x aleatoria
  * y baja hasta una altura tambien aleatoria describiendo un movimiento de parabola
  * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */

public class Basico extends Enemigo {

	protected static final URL url = (Nave.class.getClassLoader().getResource("ProyectoX/img/Enemigo/basico.png"));
	protected static final URL urlUp = (Nave.class.getClassLoader().getResource("ProyectoX/img/Enemigo/basicoUP.png"));
	private static final int defaultWidth = 27;
	private static final int defaultHeight = 38;
	private static final int defaultVel = 2;
	private static final int defaultVida = 20;
	
	// up indica si el enemigo es de tipo especial y devuelve el PowerUp
	private boolean up;
	// indica hasta donde se mover� el enemigo 
	private int alturaMinima;
	
	/**
	 * Constructor de la clase Basico
	 * @param up indica si la instancia es especial
	 */
	
	public Basico(boolean up) {
		super(defaultVida, defaultVel, up? new ImageIcon(urlUp): new ImageIcon(url), defaultWidth, defaultHeight);	
		y = -defaultHeight;
		Random rand = new Random();
		alturaMinima = rand.nextInt(10) + 20;
		
		boolean hemisferioInicial = rand.nextBoolean();
		if (hemisferioInicial) {
			x = rand.nextInt(maxWidth/2);
			posInicialX = x;
			
		} else {
			x = maxWidth - rand.nextInt(maxWidth/2);
			posInicialX = x;
		}
		
		this.up = up;
		setFrecuenciaDeDisparo(10,80);
		puntaje = 25;
	}
	
	/**
	 * redefine move() de la clase Nave 
	 * modifica las coordenadas del Basico de modo que se mueva en forma parab�lica 
	 */
	public void move() {
		if(puedeMoverse()){
			y += alturaMinima;
			alturaMinima -= 1;
			if (posInicialX <= 100)
				x += velocidad;
			else
				x -= velocidad;
			
			dx=-2*x;
			dy=0;
		}
		setMove();
		verificarColision();
		
	}
	
	/**
	 * agrega al Mapa un Disparo con direccion hacia el Jugador
	 * ademas agrega el sonido correspondiente a la accion de disparar de la instancia
	 */

	public boolean isEspecial() {
		return up;
	}
	
	/**
	 * agrega al Mapa un Disparo con direccion hacia abajo
	 * ademas agrega el sonido correspondiente a la accion de disparar de la instancia
	 */

	public void disparar() {
	    if(puedeDisparar()){
	    	Disparo d = new Disparo(x + width/2 , y + height, 0, 1, velocidadMisil);
	    	d.setReproductor(reproductor);
	    	mapa.addDisparoEnemigo(d);
	    	addSonido();
	    }
	}
	
	
}