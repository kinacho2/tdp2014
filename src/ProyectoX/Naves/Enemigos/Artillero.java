package ProyectoX.Naves.Enemigos;

import java.net.URL;

import javax.swing.ImageIcon;

import ProyectoX.Disparos.Disparo;
import ProyectoX.Naves.Nave;

public class Artillero extends Enemigo {

	protected static final URL url = (Nave.class.getClassLoader().getResource("ProyectoX/img/Enemigo/artillero.png"));
	protected static final URL urlUp = (Nave.class.getClassLoader().getResource("ProyectoX/img/Enemigo/artilleroUP.png"));
	private static final int defaultWidth = 60;
	private static final int defaultHeight = 48;
	private static final int defaultVel = 4;
	private static final int defaultVida = 50;
	private boolean up;
	private boolean primerMovimiento = true;
	
	private int delay = 4;
	private int cont;
	
	public Artillero(boolean up){
		super(defaultVida,defaultVel,up? new ImageIcon(urlUp): new ImageIcon(url),defaultWidth,defaultHeight);
		y = -defaultHeight;
		this.up = up;
		puntaje = 50;
		setFrecuenciaDeDisparo(5,70);
	}

	@Override
	public boolean isEspecial() {
		return up;
	}

	public void disparar() {
		if(puedeDisparar() && y<500){
			Disparo d = apuntarYDisparar();
			d.setReproductor(reproductor);
			addSonido();
			//d.setPosicion(d.getX(), d.getY() - defaultHeight/2);
			mapa.addDisparoEnemigo(d);
		}
	}

	@Override
	public void move() {
		if(puedeMoverse()){
			if(primerMovimiento){
				x = jugador.getX();
				primerMovimiento = false;
			}
			
			y += velocidad;
		}
		setMove();
		verificarColision();
		setRotacion();
	}
}
