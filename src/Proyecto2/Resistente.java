package Proyecto2;

import java.net.URL;

import javax.swing.ImageIcon;

public class Resistente extends Jugador {

	protected static URL url = (Nave.class.getClassLoader().getResource("img/Jugador/resistente.gif"));
	protected static URL urlDer = (Nave.class.getClassLoader().getResource("img/Jugador/resistenteDer.gif"));
	protected static URL urlIzq = (Nave.class.getClassLoader().getResource("img/Jugador/resistenteIzq.gif"));
	private static final int defaultVel = 3;
	private static final int defaultVida = 6;
	
	
	public Resistente(){
		super(defaultVida,defaultVel,400,560,new ImageIcon(url),new ImageIcon(urlDer),new ImageIcon(urlIzq));
	}


	public void disparar() {
		
		if(power==1){
			mapa.addDisparoJugador(new Disparo(x + width/2 - 10 , y, 0, 1, velocidadMisil));
			mapa.addDisparoJugador(new Disparo(x + width/2 + 10 , y, 0, 1, velocidadMisil));
		}
		else
			super.disparar();
		
		
	}
}