package Proyecto2;

import java.net.URL;
import java.util.Random;
import javax.swing.ImageIcon;

public class Basico extends Enemigo {

	protected static final URL url = (Nave.class.getClassLoader().getResource("img/Enemigo/basico.png"));
	protected static final URL urlUp = (Nave.class.getClassLoader().getResource("img/Enemigo/basicoUp.png"));
	private static final int defaultWidth = 27;
	private static final int defaultHeight = 38;
	private static final int defaultVel = 3;
	private static final int defaultVida = 2;
	
	// up indica si el enemigo es de tipo especial y devuelve el PowerUp
	private boolean up;
	// indica hasta donde se moverá el enemigo 
	private int alturaMinima;
	
	public Basico(boolean up) {
		super(defaultVida,defaultVel,0,-defaultHeight,up? new ImageIcon(urlUp): new ImageIcon(url),defaultWidth,defaultHeight);	
		Random rand = new Random();
		alturaMinima = rand.nextInt(20) + 10;
		
		boolean hemisferioInicial = rand.nextBoolean();
		if (hemisferioInicial) {
			x = rand.nextInt(maxWidth/2);
			posInicialX = x;
			
		} else {
			x = maxWidth - rand.nextInt(maxWidth/2);
			posInicialX = x;
		}
		
		this.up = up;
		setFrecuenciaDeDisparo(10,20);
		puntaje = 25;
	}
	
	// mueve al enemigo en forma parabólica 
	public void move() {
		
		y += alturaMinima;
		alturaMinima -= 1;
		if (posInicialX <= 100)
			x += velocidad;
		else
			x -= velocidad;
		
		dx=-2*x;
		dy=0;
		
		setRotacion();
		verificarColision();
		
	}

	public boolean isEspecial() {
		return up;
	}

	public void disparar() {
	    if(puedeDisparar())
	    	mapa.addDisparoEnemigo(new Disparo(x + width/2 , y + height, 0, 1, velocidadMisil));
	}
	
	
}