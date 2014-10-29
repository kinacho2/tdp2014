package ProyectoX.Naves.Enemigos;

import java.net.URL;

import javax.swing.ImageIcon;

import ProyectoX.Explosiones_Disparos.Disparo;
import ProyectoX.Naves.Nave;

public class Artillero extends Enemigo {

	protected static final URL url = (Nave.class.getClassLoader().getResource("ProyectoX/img/Enemigo/artillero.png"));
	protected static final URL urlUp = (Nave.class.getClassLoader().getResource("ProyectoX/img/Enemigo/artilleroUP.png"));
	private static final int defaultWidth = 60;
	private static final int defaultHeight = 48;
	private static final int defaultVel = 5;
	private static final int defaultVida = 5;
	private boolean up;
	private boolean primerMovimiento = true;
	
	public Artillero(boolean up){
		super(defaultVida,defaultVel,up? new ImageIcon(urlUp): new ImageIcon(url),defaultWidth,defaultHeight);
		y = -defaultHeight;
		this.up = up;
		puntaje = 50;
	}

	@Override
	public boolean isEspecial() {
		return up;
	}

	@Override
	public void disparar() {
		if(puedeDisparar() && y<500){
			Disparo d = apuntarYDisparar();
			//d.setPosicion(d.getX(), d.getY() - defaultHeight/2);
			mapa.addDisparoEnemigo(d);
		}
	}

	@Override
	public void move() {
		if(primerMovimiento){
			x = jugador.getX();
			primerMovimiento = false;
		}
		
		y += velocidad;
		verificarColision();
		setRotacion();
	}
}
