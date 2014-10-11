package Proyecto2;

import java.net.URL;
import java.util.Random;

import javax.swing.ImageIcon;

public class Basico extends Enemigo{

	protected static final URL url = (Nave.class.getClassLoader().getResource("img/Enemigo/basico.png"));
	private static final int defaultWidth = 27;
	private static final int defaultHeight = 38;
	private static final int defaultVel = 5;
	private static final int defaultVida = 2;
	private int alturaMinima;
	
	public Basico(){
		super(defaultVida,defaultVel,0,-defaultHeight,new ImageIcon(url),defaultWidth,defaultHeight);	
		
		alturaMinima = 25;
		
		Random rand = new Random();
		boolean hemisferioInicial = rand.nextBoolean();
		if (hemisferioInicial) {
			x = rand.nextInt(100);
			posInicialX = x;
			
		} else {
			x = 800 - rand.nextInt(100);
			posInicialX = x;
		}
	}
	
	public void move() {
		y += alturaMinima;
		alturaMinima -= 1;
		if (posInicialX <= 100)
			x += velocidad;
		else
			x -= velocidad;
		
		setRotacion();
		verificarColision();
	}
}
